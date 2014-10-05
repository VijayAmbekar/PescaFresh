package in.co.pescafresh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class DBAccess {
	private final static String INSERT_CITY_SQL = "insert into city(city_name, state, description, city_image) values(?,?,?,?)";
	private final static String SELECT_ALL_CITIES_SQL = "SELECT * FROM city";

	private static Connection getConnection() {
		try {
			// Get DataSource
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext
					.lookup("jdbc/pescafresh");
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int insertCity(City city) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(INSERT_CITY_SQL,
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, city.getName());
			ps.setString(2, city.getState());
			ps.setString(3, city.getDescription());
			ps.setString(4, city.getImageUrl());

			ps.executeUpdate();

			// Using the getGeneratedKeys() method to retrieve
			// the key(s). In this case there is only one key column
			ResultSet keyResultSet = ps.getGeneratedKeys();
			int newCityId = 0;
			if (keyResultSet.next()) {
				newCityId = (int) keyResultSet.getInt(1);
				System.out.print("New City ID : " + newCityId);
				return newCityId;
			}

		} catch (Exception e) {
			System.out.print("Error inserting City into Database: "
					+ e.toString());
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public static JSONArray getCityList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery(SELECT_ALL_CITIES_SQL);

			JSONArray cityList = new JSONArray();
			JSONObject localCity = null;
			while (rs.next()) {
				localCity = new JSONObject();
				localCity.put("id", rs.getInt(1));
				localCity.put("name", rs.getString(2));
				localCity.put("state", rs.getString(3));
				localCity.put("description", rs.getString(4));
				localCity.put("imageUrl", rs.getString(5));
				cityList.put(localCity);
			}

			return cityList;
		} catch (Exception e) {
			System.out.println("Unable to get List of Cities: " + e.toString());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static JSONObject getCity(int id) {
		String GET_City_SQL = "SELECT * FROM city WHERE city_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(GET_City_SQL);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			JSONObject localCity = new JSONObject();
			if (rs.next()) {
				localCity.put("id", rs.getInt(1));
				localCity.put("name", rs.getString(2));
				localCity.put("state", rs.getString(3));
				localCity.put("description", rs.getString(4));
				localCity.put("imageUrl", rs.getString(5));
			}

			return localCity;
		} catch (Exception e) {
			System.out.println("Unable to get City Info for ID:" + id + " : "
					+ e.toString());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
