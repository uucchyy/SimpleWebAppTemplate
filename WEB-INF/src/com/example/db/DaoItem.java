package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.example.model.Item;

public class DaoItem {
    
	private static SimpleDateFormat sdfDate = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdfDatetime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static List<Item> getItems(String employeeId, String targetDate) throws Exception {
		
		List<Item> items = new ArrayList<Item>();
		
    	Context context = new InitialContext();
    	DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/Dbs");
    	Connection con = ds.getConnection();
    	
    	StringBuilder sql = new StringBuilder();
    	sql.append("SELECT * FROM item T1 ");
    	sql.append("LEFT JOIN item T2 ON T1.item_id = T2.item_id ");
    	sql.append("LEFT JOIN step T3 ON T1.step_id = T3.step_id ");
    	sql.append("WHERE T1.input_date = ? AND T1.employee_id = ? ");
    	sql.append("ORDER BY T1.start_time; ");
        
        PreparedStatement ps = con.prepareStatement(sql.toString());
        ps.setString(1, targetDate);
        ps.setString(2, employeeId);
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()) {
        	Item item = new Item(
        			rs.getInt("id"),
        			rs.getString("item_name"),
        			);
        	items.add(report);
        }
        rs.close();        
        ps.close();
        con.close();		
        
        return items;
	}
	
	public static int insertItem(String itemId, String name) throws Exception {
		
		int result = 0;
		Context context = null;
    	DataSource ds = null;
    	Connection con = null;
    	PreparedStatement ps = null;
    	
    	try {

        	context = new InitialContext();
        	ds = (DataSource)context.lookup("java:comp/env/jdbc/Dbs");
        	con = ds.getConnection();
        	
        	StringBuilder sql = new StringBuilder();
        	sql.append("INSERT INTO item ");
        	sql.append("(`item_id`, `name`) ");
        	sql.append("values(?, ?); ");
        	
        	ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        	DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        	LocalDateTime inputDateTime = LocalDateTime.parse(date + " 00:00:00", f);
        	LocalDateTime startDateTime = LocalDateTime.parse(date + " " + startTime +":00", f);
        	LocalDateTime endDateTime = LocalDateTime.parse(date + " " + endTime +":00", f);
        	
            ps = con.prepareStatement(sql.toString());
            ps.setInt(1, convertIntOrZero(itemId));
            ps.setString(2, name);
            
            result = ps.executeUpdate();
            con.commit();
    	} catch (Exception ex) {
    		con.rollback(); 
    		throw ex;
    	} finally {
    		if (ps != null) ps.close();
    		if (con != null) con.close();		
    	}
    	
    	return result;
	}
	
	public static int deleteItem(String itemId) throws Exception {
		
		int result = 0;
		Context context = null;
    	DataSource ds = null;
    	Connection con = null;
    	PreparedStatement ps = null;
    	
    	try {

        	context = new InitialContext();
        	ds = (DataSource)context.lookup("java:comp/env/jdbc/Dbs");
        	con = ds.getConnection();
        	
        	StringBuilder sql = new StringBuilder();
        	sql.append("DELETE FROM item ");
        	sql.append("WHERE item_id = ? ;");
        	        	
            ps = con.prepareStatement(sql.toString());
            ps.setInt(1, convertIntOrZero(itemId));
            
            result = ps.executeUpdate();
            con.commit();
    	} catch (Exception ex) {
    		con.rollback(); 
    		throw ex;
    	} finally {
    		if (ps != null) ps.close();
    		if (con != null) con.close();		
    	}
    	
    	return result;
	}
	
	private static int convertIntOrZero(String str) {
		if (str.equals("")) {
			return 0;
		}
	    try {
	        return Integer.parseInt(str);
	    } catch (NumberFormatException e) {
	       	return 0;
	    }
	}

	private static double convertDoubleOrZero(String str) {
		if (str.equals("")) {
			return 0.0;
		}
	    try {
	        return Double.parseDouble(str);
	    } catch (NumberFormatException e) {
	       	return 0.0;
	    }
	}
	
//    public static void main(String[] args) throws Exception {
//        
//        //接続
//        String url = "jdbc:mariadb://192.168.56.123:3306/fss";
//        Connection con = DriverManager.getConnection(url, "fss", "passpass");
//        
//        Statement stmt = con.createStatement();
//        
//        ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
//        
//        while(rs.next()) {
//            System.out.println(rs.getString("name"));
//        }
//        rs.close();        
//        stmt.close();
//        
//        con.commit();
//        con.close();
//    }
    
}