package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Address;
import vo.City;
import vo.Country;
import vo.Customer;
import vo.CustomerAddressCityCountry;

public class CustomerDao {

	public ArrayList<HashMap<String,Object>> selectCustomerMapList(int beginRow,int rowPerPage) {
		ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
			
			String sql = "SELECT"
					+ " cu.first_name firstName, cu.last_name lastName"
					+ ", ad.address, ad.district"
					+ ", ci.city, co.country country"
					+ " FROM customer cu INNER JOIN"
					+ "		address ad on cu.address_id = ad.address_id"
					+ "		INNER join city ci ON ad.city_id = ci.city_id"
					+ "		INNER JOIN country co ON  ci.country_id = co.country_id"
					+ " ORDER BY cu.customer_id asc"
					+ " LIMIT ?,?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				HashMap<String,Object> map = new HashMap<String, Object>();
				map.put("firstName", rs.getString("firstName"));
				map.put("lastName", rs.getString("lastName"));
				map.put("address", rs.getString("address"));
				map.put("district", rs.getString("district"));
				map.put("city", rs.getString("city"));
				map.put("country", rs.getString("country"));
				
				list.add(map);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
	
	public ArrayList<CustomerAddressCityCountry> selectCustomerJoinList(int beginRow,int rowPerPage) {
		ArrayList<CustomerAddressCityCountry> list = new ArrayList<CustomerAddressCityCountry>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
			
			String sql = "SELECT"
					+ " cu.* , ad.*, ci.city city , co.country country"
					+ " FROM customer cu INNER JOIN"
					+ "		address ad on cu.address_id = ad.address_id"
					+ "		INNER join city ci ON ad.city_id = ci.city_id"
					+ "		INNER JOIN country co ON  ci.country_id = co.country_id"
					+ " ORDER BY cu.customer_id asc"
					+ " LIMIT ?,?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				CustomerAddressCityCountry cacc = new CustomerAddressCityCountry();
	            // Customer table 결과셋
	            Customer cu = new Customer();
	            cu.setCustomerId(rs.getInt("cu.customer_id"));
	            cu.setStoreId(rs.getInt("cu.store_id"));
	            cu.setFirstName(rs.getString("cu.first_name"));
	            cu.setLastName(rs.getString("cu.last_name"));
	            cu.setEmail(rs.getString("cu.email"));
	            cu.setActive(rs.getInt("cu.active"));
	            cacc.setCustomer(cu);
	            
	            // Address table 결과셋
				Address ad = new Address();
				ad.setAddress(rs.getString("ad.address"));
				ad.setAddress2(rs.getString("ad.address2"));
				ad.setDistrict(rs.getString("ad.district"));
				ad.setPhone(rs.getString("ad.phone"));
				cacc.setAddress(ad);
				
				// City table 결과셋
				City ci = new City();
				ci.setCity(rs.getString("city"));
				cacc.setCity(ci);
				
				// Country table 결과셋
				Country co = new Country();
				co.setCountry(rs.getString("country"));
				cacc.setCountry(co);
				
				list.add(cacc);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
	public int countCustomer() {
	
		Connection conn = null;
		PreparedStatement stmt = null;		
		ResultSet rs = null;
		int cnt = 0;
	    String sql = "SELECT COUNT(*) CNT FROM CUSTOMER";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
		    stmt = conn.prepareStatement(sql);
		    rs = stmt.executeQuery();
		    while(rs.next()) {
		    	cnt = rs.getInt(1);
		    }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return cnt;
	}	
}
