package com.cq.wf.web.utils.listener;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.logicalcobwebs.proxool.ProxoolFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBConPoolInitListener implements ServletContextListener {
    private static Logger log = LoggerFactory
            .getLogger(DBConPoolInitListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        try {
            log.info("DBConnectionPool init start");
            ResourceBundle bundle = ResourceBundle.getBundle("db");
            String jdbcDriver = bundle.getString("jdbc.driver");
            String jdbcUrl = bundle.getString("jdbc.url");
            String jdbcUser = bundle.getString("jdbc.username");
            String jdbcPw = bundle.getString("jdbc.password");

            Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
            Properties info = new Properties();
            info.setProperty("proxool.maximum-connection-count", "10");
            info.setProperty("proxool.house-keeping-test-sql",
                    "select CURRENT_DATE");
            info.setProperty("user", jdbcUser);
            info.setProperty("password", jdbcPw);
            String alias = "wf";
            String driverClass = jdbcDriver;
            String driverUrl = jdbcUrl;
            String url = "proxool." + alias + ":" + driverClass + ":"
                    + driverUrl;
            ProxoolFacade.registerConnectionPool(url, info);
            log.info("DBConnectionPool init end");
        } catch (Exception e) {

            log.error(e.getLocalizedMessage());
        }
    }

}
