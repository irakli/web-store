package database.dao;

import database.DatabaseContract;
import database.DatabaseInfo;
import database.bean.Product;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	private DataSource pool;

	public ProductDAO(DataSource dataSource) {
		this.pool = dataSource;
	}

	public List<Product> getProducts() {
		List<Product> products = new ArrayList<>();

		Connection connection = null;
		try {
			connection = pool.getConnection();
			Statement statement = connection.createStatement();
			statement.executeQuery("USE " + DatabaseInfo.MYSQL_DATABASE_NAME);

			String s = "SELECT * FROM " + DatabaseContract.ProductTable.TABLE_NAME + ";";
			System.out.println("shiiet");
			PreparedStatement viaContinentStatement = connection.prepareStatement(s);
			ResultSet resultSet = viaContinentStatement.executeQuery();

			while (resultSet.next()) {
				products.add(fetchProduct(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) try {
				connection.close();
			} catch (Exception ignored) {
			}
		}

		return products;
	}

	private Product fetchProduct(ResultSet resultSet) throws SQLException {
		Product product = new Product();
		product.setId(resultSet.getString(DatabaseContract.ProductTable.COLUMN_NAME_ID));
		product.setName(resultSet.getString(DatabaseContract.ProductTable.COLUMN_NAME_NAME));
		product.setImage(resultSet.getString(DatabaseContract.ProductTable.COLUMN_NAME_IMAGE));
		product.setPrice(resultSet.getDouble(DatabaseContract.ProductTable.COLUMN_NAME_PRICE));
		System.out.println(product);

		return product;
	}

//	public void setDataSource() {
//		PoolProperties properties = new PoolProperties();
//		properties.setDriverClassName("com.mysql.jdbc.Driver");
//		properties.setUrl(DatabaseInfo.MYSQL_DATABASE_SERVER);
//		properties.setUsername(DatabaseInfo.MYSQL_USERNAME);
//		properties.setPassword(DatabaseInfo.MYSQL_PASSWORD);
//		properties.setInitialSize(10);
//		properties.setMaxActive(100);
//
//		pool = new DataSource();
//		pool.setPoolProperties(properties);
//	}
}
