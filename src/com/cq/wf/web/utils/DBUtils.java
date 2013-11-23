package com.cq.wf.web.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBUtils {

	private static Logger log = LoggerFactory.getLogger(DBUtils.class);

	private Connection con;

	private static SqlSessionFactory sqlSF;

	public static Connection getConn() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("proxool.wf");
			con.setAutoCommit(false);
		} catch (SQLException se) {
			log.error(se.getLocalizedMessage());
		}
		return con;
	}

	public static SqlSession getSqlSession() {

		return sqlSF.openSession();
	}

	public DBUtils(Connection con) {
		this.con = con;
	}

	/**
	 * getQueryList
	 * 
	 * @param sql
	 * @param conditions
	 * @param clazz
	 *            resultBean's Class
	 * @return resultList
	 * @throws SQLException
	 * @throws Exception
	 */
	public <T> List<T> getQueryList(String sql, List<String> conditions,
			Class<T> clazz) throws SQLException, Exception {
		PreparedStatement pst = null;
		ResultSet rst = null;
		ArrayList<T> list = new ArrayList<T>();
		try {
			log.info("getQueryList sql:" + sql);

			pst = con.prepareStatement(sql);
			int index = 1;
			if (conditions != null) {
				log.info("getQueryList conditions:"
						+ conditions.toArray().toString());
				for (String condtion : conditions) {
					pst.setString(index, condtion);
				}
			}
			rst = pst.executeQuery();

			// get resultset's columns' name
			ResultSetMetaData rsm = rst.getMetaData();
			int colsCnt = rsm.getColumnCount();
			ArrayList<String> colsNm = new ArrayList<String>();
			for (int idx = 1; idx <= colsCnt; idx++) {
				colsNm.add(rsm.getColumnLabel(idx).toLowerCase());
			}

			// set result data to class instance
			while (rst.next()) {
				T result = clazz.newInstance();
				for (Field fd : clazz.getDeclaredFields()) {

					String fdNm = fd.getName().toLowerCase();
					if (colsNm.contains(fdNm)) {
						String mod = Modifier.toString(fd.getModifiers());
						if (mod.indexOf("static") != -1) {
							// static ignore
							continue;
						}
						boolean canAccess = fd.isAccessible();
						if (canAccess == false) {
							fd.setAccessible(true);
						}
						// set result value to String type except Date
						if (fd.getType().getSimpleName()
								.equalsIgnoreCase("Date")) {

							fd.set(result,
									rst.getDate(colsNm.indexOf(fdNm) + 1));

						} else {

							fd.set(result,
									rst.getString(colsNm.indexOf(fdNm) + 1));

						}

						fd.setAccessible(canAccess);

					}
				}

				list.add(result);
			}

			return list;

		} catch (InstantiationException e) {
			throw e;
		} catch (IllegalAccessException e) {
			throw e;
		} finally {
			if (rst != null) {
				rst.close();
			}

			if (pst != null) {
				pst.close();
			}
		}

	}

	public static SqlSessionFactory getSqlSF() {
		return sqlSF;
	}

	public static void setSqlSF(SqlSessionFactory sqlSF) {
		DBUtils.sqlSF = sqlSF;
	}
}
