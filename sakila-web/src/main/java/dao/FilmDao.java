package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Film;

public class FilmDao {
	
	// rating : String[] 여러개의 등급
	/*
	 *  rating == null || rating.length == 5
	 *  SELECT * FROM film
	 *  
	 *  rating == 4
	 *  SELECT * FROM film WHERE rating IN (?,?,?,?)
	 *  
	 *  
	 *  
	 */

	public ArrayList<Film> selectFilmList2(String[] rating){
		ArrayList<Film> list = new ArrayList<Film>();
		String sql = "";
		if(rating == null || rating.length == 5) {
			sql ="SELECT * FROM film";
		}else if(rating.length == 4) {
			sql = "SELECT * FROM film WHERE rating IN (?,?,?,?)";
		}else if(rating.length == 3) {
			sql = "SELECT * FROM film WHERE rating IN (?,?,?)";
		}else if(rating.length == 2) {
			sql = "SELECT * FROM film WHERE rating IN (?,?)";			
		}else if(rating.length == 1) {
			sql = "SELECT * FROM film WHERE rating IN (?)";			
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
			
			stmt = conn.prepareStatement(sql);
			if(rating != null) {
				for(int i=1;i<=rating.length;i++) {
					stmt.setString(i, rating[i-1]);
				}
			}
			rs = stmt.executeQuery();
			while(rs.next()){
				Film f = new Film();
				f.setFilmId(rs.getInt("film_id"));
				f.setTitle(rs.getString("title"));
				f.setRating(rs.getString("rating"));
				f.setLength(rs.getInt("length"));
				
				list.add(f);
			}
			
		}catch(Exception e) {
			
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	
	public ArrayList<Film> selectFilmListBySearch(String col,String sort,String searchCol,String searchWord){
		ArrayList<Film> list = new ArrayList<Film>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		System.out.println(searchCol + searchWord);		

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
/*			String sql = null;
			if(searchWord == null) {
				sql = "SELECT * FROM FILM"
					   + " ORDER BY " + col + " " + sort ;
			}else {
			sql = "SELECT * FROM FILM"
					+ " WHERE " + searchCol + " like '%" + searchWord + "%'" 
     				+ " ORDER BY " + col + " " + sort ;
			}
			stmt = conn.prepareStatement(sql);
*/
		String sql = "";
		
		if(searchCol == null || searchWord == null) { // 검색버턴 조회가 아닌경우
			System.out.println("검색버턴 X");
			sql = "SELECT film_id filmId, title, description, release_year releaseYear, language_id languageId, original_language_id OriginalLanguageId, rental_duration rentalDuration, rental_rate rentalRate, length, replacement_cost replacementCost, rating, special_features specialFeatures, last_update lastUpdate"
				+ "	FROM film"
				+ "	ORDER BY "+col+" "+sort;
			stmt = conn.prepareStatement(sql);
		} else {
			System.out.println("검색버턴 O");
			String whereCol = "";
			if(searchCol.equals("titleAndDescription")) {
				whereCol = "CONCAT(title,' ',description)";
			} else {
				whereCol = searchCol;
			}
			sql = "SELECT film_id filmId, title, description, release_year releaseYear, language_id languageId, original_language_id OriginalLanguageId, rental_duration rentalDuration, rental_rate rentalRate, length, replacement_cost replacementCost, rating, special_features specialFeatures, last_update lastUpdate"
					+ "	FROM film"
					+ " WHERE "+whereCol+" LIKE ?"
					+ "	ORDER BY "+col+" "+sort;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+searchWord+"%");
		}		
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Film f = new Film();
				
				f.setFilmId(rs.getInt("filmId"));
				f.setTitle(rs.getString("title"));
				f.setDescription(rs.getString("description"));
				f.setReleaseYear(rs.getString("releaseYear"));
				f.setLanguageId(rs.getInt("languageId"));
				f.setOriginalLanguageId(rs.getInt("originalLanguageId"));
				f.setRentalDuration(rs.getInt("rentalDuration"));
				f.setRentalRate(rs.getDouble("rentalRate"));
				f.setLength(rs.getInt("length"));
				f.setSpecialFeatures(rs.getString("specialFeatures"));
				f.setLastUpdate(rs.getString("lastUpdate"));
				
				list.add(f);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
