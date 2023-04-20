package com.alura.hotel.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionFactory {

	private final DataSource dataSource;

	public ConexionFactory() {

		var comboPooledDataSource = new ComboPooledDataSource();

		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel?useTimeZone=true&serverTimeZone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("12345");
		comboPooledDataSource.setMaxPoolSize(10);

		this.dataSource = comboPooledDataSource;

	}

	public Connection getConexion() {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
