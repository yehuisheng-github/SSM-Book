package utils;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

/*
 * 	��Ӽ��������ڹرն�������ݿ����ӳ��߳�
 */
public class MyListener extends HttpServlet implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		
	}

	public void contextDestroyed(ServletContextEvent sce) {
		AbandonedConnectionCleanupThread.checkedShutdown();
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			if (driver.getClass().getClassLoader() == cl) {
				try {
					DriverManager.deregisterDriver(driver);
				} catch (SQLException ex) {
					System.out.println("Error deregistering JDBC driver {}");
					ex.printStackTrace();
				}
			} else {
				System.out.println("Not deregistering JDBC driver {} as it does not belong to this webapp's ClassLoader");
			}
		}
	}
}
