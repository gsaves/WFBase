package com.cq.wf.web.utils;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.logicalcobwebs.proxool.ProxoolDataSource;

public class WFDataSourceFactory implements DataSourceFactory {

	private ProxoolDataSource dataSource;
	
	public WFDataSourceFactory(){
		this.dataSource = new ProxoolDataSource("wf");
	}
	@Override
	public DataSource getDataSource() {
		return this.dataSource;
	}

	@Override
	public void setProperties(Properties arg0) {
		// doNothing
	}

}
