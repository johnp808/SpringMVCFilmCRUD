package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.skilldistillery.film.entities.Film;

public class FilmDaoJdbcImpl implements FilmDAO {
	String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	String user = "student";
	String pass = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Film findById(int filmId) {
		Film film = null;
		String sql = "SELECT id, title, description, release_year, language_id, rental_duration, rental_rate, "
				+ "length, replacement_cost, rating, special_features FROM film where id = ?";

		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, filmId);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					film = new Film(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
							rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getDouble(9), rs.getString(10),
							rs.getString(11));
				}
			} catch (SQLException e) {
				System.err.println("Database error: " + e);
			}
		} catch (SQLException e) {
			System.err.println("Database Error: " + e);
		}
		return film;
	}

	@Override
	public Film createFilm(Film film) {

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // Start transaction
			String sql = "INSERT INTO film (title, description, release_year,"
					+ " language_id, rental_duration, rental_rate, length,"
					+ " replacement_cost, rating, special_features)"
					+ " VALUES(?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			System.out.println(film.getReplacementCost());
			st.setString(1, film.getTitle());
			st.setString(2, film.getDescription());
			st.setInt(3, film.getReleaseYear());
			st.setInt(4, film.getLanguageId());
			st.setInt(5, film.getRentalDuration());
			st.setDouble(6, film.getRentalRate());
			st.setInt(7, film.getLength());
			st.setDouble(8, film.getReplacementCost());
			st.setString(9, film.getRating());
			st.setString(10, film.getSpecialFeatures());

			try {
				int cf = st.executeUpdate();
				conn.commit();
				System.out.println(cf + "Added A New Film To The Database.");

				ResultSet keys = st.getGeneratedKeys();
				while (keys.next()) {
					System.out.println("New film ID: " + keys.getInt(1));
				}
			} catch (SQLException e) {
				System.err.println("Error during Creation.");
				System.err.println("SQL Error: " + e.getErrorCode() + ": " + e.getMessage());
				System.err.println("SQL State: " + e.getSQLState());
				if (conn != null) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						System.err.println("Error rolling back.");
						e1.printStackTrace();
					}
				}
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public boolean deleteFilm(Film film) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Film updateFilm(Film film) {
		// TODO Auto-generated method stub
		return null;
	}
	
}