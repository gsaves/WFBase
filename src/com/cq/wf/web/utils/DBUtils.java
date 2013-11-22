package com.cq.wf.web.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBUtils {

    private static Logger log = LoggerFactory.getLogger(DBUtils.class);

    public static Connection getConn() {
        try {
            return DriverManager.getConnection("proxool.wf");
        } catch (SQLException se) {
            log.error(se.getLocalizedMessage());
        }
        return null;
    }

}
