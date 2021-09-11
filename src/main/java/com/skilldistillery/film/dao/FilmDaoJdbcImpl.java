package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	@Override
	public Film createFilm(Film film) {
		Film newFilm = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			String sql = "insert into film(title, description, release_year, language_id, rental_duration, length, replacement_cost, rating, special_features) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, newFilm.getTitle());
			pst.setString(2, newFilm.getDescription());
			pst.setInt(3, newFilm.getReleaseYear());
			pst.setInt(4, newFilm.getLanguageId());
			pst.setInt(5, newFilm.getRentalDuration());
			pst.setInt(6, newFilm.getLength());
			pst.setDouble(7, newFilm.getReplacementCost());
			pst.setString(8, newFilm.getRating());
			pst.setString(9, newFilm.getSpecialFeatures());

			int updateCount = pst.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = pst.getGeneratedKeys();
				if (keys.next()) {
					int newFilmId = keys.getInt(1);
					newFilm.setId(newFilmId);
				}
				keys.close();
			}
			conn.commit();
			pst.close();
			conn.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting film " + film.getTitle());

		}

		return newFilm;

	}
		
	@Override
	public boolean deleteFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			String sql = "delete from film where id = ?";
			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, film.getId());

			int updateCount = pst.executeUpdate();
			if (updateCount == 1) {
				System.out.println("You successfully deleted " + updateCount + " record.");
			}
			conn.commit();
			pst.close();
			conn.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");

				}

			}
			throw new RuntimeException("Error inserting film " + film.getTitle());
		}

		return true;

	}
}
