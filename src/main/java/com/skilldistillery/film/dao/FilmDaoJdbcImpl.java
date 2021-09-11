package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Film;

public class FilmDaoJdbcImpl implements FilmDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private String user = "student";
	private String pass = "student";

	public FilmDaoJdbcImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Error loading database driver:");
			System.err.println(e);
			e.printStackTrace();
		}
	}
	@Override
	public Film findById(int filmId) {
			// TODO use JDBC code to retrieve film, create Film object
			Film film = null;

			try {
				Connection conn = DriverManager.getConnection(URL, user, pass);

				String sql = "SELECT film.id, film.title, film.description, film.release_year,"
						+ " film.language_id, film.rental_duration, film.rental_rate, film.length,"
						+ " film.replacement_cost, film.rating, film.special_features, language.name"
						+ " FROM film JOIN language ON language.id = film.language_id WHERE film.id = ?";

				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, filmId);
				ResultSet filmResult = stmt.executeQuery();
				if (filmResult.next()) {
					film = new Film();
					film.setId(filmResult.getInt("id"));
					film.setTitle(filmResult.getString("title"));
					film.setDescription(filmResult.getString("description"));
					film.setReleaseYear(filmResult.getInt("release_year"));
					film.setLanguageId(filmResult.getInt("language_id"));
					film.setRentalDuration(filmResult.getInt("rental_duration"));
					film.setRentalRate(filmResult.getDouble("rental_rate"));
					film.setLength(filmResult.getInt("length"));
					film.setReplacementCost(filmResult.getDouble("replacement_cost"));
					film.setRating(filmResult.getString("rating"));
					film.setSpecialFeatures(filmResult.getString("special_features"));
				}
				filmResult.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.err.println("Database error:");
				System.err.println("Id not found.");
				System.err.println(e);
			}
			return film;
		}
}
