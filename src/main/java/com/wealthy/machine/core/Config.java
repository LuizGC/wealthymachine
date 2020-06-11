package com.wealthy.machine.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class Config {

	private static final String DEFAULT_BOVESPA_URL;
	private static final String INITIAL_YEAR;

	static {
		var appProps = new Properties();
		try (
				var appConfigPath = Thread
						.currentThread()
						.getContextClassLoader()
						.getResourceAsStream("config.properties")
		){
			appProps.load(appConfigPath);
			DEFAULT_BOVESPA_URL = appProps.getProperty("bovespaUrl", "http://bvmf.bmfbovespa.com.br/InstDados/SerHist/COTAHIST_A{{YYYY}}.ZIP");
			INITIAL_YEAR = appProps.getProperty("initialYear", "2000");
		} catch (IOException e) {
			var logger = LoggerFactory.getLogger(Config.class);
			logger.error("Error during loading properties", e);
			throw new RuntimeException(e);
		}
	}

	public Config() {
	}

	public int getInitialYear() {
		return Integer.parseInt(INITIAL_YEAR);
	}

	public String getDataPath() {
		return DEFAULT_BOVESPA_URL;
	}

	public Logger getLogger(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}
}