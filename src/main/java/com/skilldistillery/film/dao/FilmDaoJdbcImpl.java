package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public class FilmDaoJdbcImpl implements FilmDAO {// changed url to mountain time for no errors. 8:29 am hst.
	private static final String URL = "jdbc:mysql://localhost:3306/historydb?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
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
	public Film findFilmById(int filmId) {
		Film film = null;
		String sql = "SELECT id, title, description, release_year, language_id, rental_duration, rental_rate, "
				+ "length, replacement_cost, rating, special_features FROM film WHERE id = ?";

		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, filmId);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					film = new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getInt("release_year"), rs.getInt("language_id"),
							rs.getInt("rental_duration"), rs.getDouble("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"), rs.getString("rating"),
							rs.getString("special_features"));
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
				System.out.println("Film record deleted.");
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

	@Override
	public Film updateFilm(Film ogFilm, Film film) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Actor addActor(Actor actor) {
		// TODO Auto-generated method stub
		return null;
	}
}
