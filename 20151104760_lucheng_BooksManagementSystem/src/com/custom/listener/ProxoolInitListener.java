package com.custom.listener;

import java.io.File;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;

public class ProxoolInitListener implements ServletContextListener {
	private static final Logger logger = Logger
			.getLogger(ProxoolInitListener.class);
	private static final String XML_FILE_PROPERTY = "xmlFile";
	public void contextDestroyed(ServletContextEvent arg0) {
	}
	public void contextInitialized(ServletContextEvent contextEvent) {
		ServletContext context = contextEvent.getServletContext(); // 对应servlet的init方法中ServletConfig.getServletContext()
		String appDir = contextEvent.getServletContext().getRealPath("/");
		Properties properties = new Properties();
		Enumeration<?> names = context.getInitParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String value = context.getInitParameter(name);
			if (name.equals(XML_FILE_PROPERTY)) {
				try {
					File file = new File(value);
					if (file.isAbsolute()) {
						JAXPConfigurator.configure(value, false);
					} else {
						JAXPConfigurator.configure(appDir + File.separator
								+ value, false);
					}
				} catch (ProxoolException e) {
					logger.error("Problem configuring " + value, e);
				}
			}
		}
		if (properties.size() > 0) {
			try {
				PropertyConfigurator.configure(properties);
			} catch (Exception e) {
				logger.error("Problem configuring using init properties", e);
			}
		}

	}
}