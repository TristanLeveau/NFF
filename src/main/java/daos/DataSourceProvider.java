package daos;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// Fonction de paramétrage de base de données

public class DataSourceProvider {

	public DataSource getDatasource() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName("jfrpocyduwfg38kq.chr7pe7iynqr.eu-west-1.rds.amazonaws.com\t");
		dataSource.setPort(3306);
		dataSource.setDatabaseName("b7traorj9eq6vt7m");
		dataSource.setUser("mmfvxsar2cfss3lw");
		dataSource.setPassword("xi7upgcxwlgyw6jy");

		return dataSource;
	}

	private static class DataSourceProviderHolder {
		private final static DataSourceProvider instance = new DataSourceProvider();
	}
	
	public static DataSourceProvider getInstance() {
		return DataSourceProviderHolder.instance;
	}

	private MysqlDataSource dataSource;

	private DataSourceProvider() {
		initDataSource();
	}

	private void initDataSource() {
		Properties jdbcProperties = new Properties();
		InputStream configFileStream = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			jdbcProperties.load(configFileStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dataSource = new MysqlDataSource();
		dataSource.setServerName(jdbcProperties.getProperty("servername"));
		dataSource.setPort(Integer.parseInt(jdbcProperties.getProperty("port")));
		dataSource.setDatabaseName(jdbcProperties.getProperty("databasename"));
		dataSource.setUser(jdbcProperties.getProperty("user"));
		dataSource.setPassword(jdbcProperties.getProperty("password"));
	}

	public DataSource getDataSource() {
		return dataSource;
	}
}