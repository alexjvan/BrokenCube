package com.brokencube.api.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.brokencube.api.API;

public class Database {
	API instance;
	
	private Connection con;
	
	String url;
	String user;
	String password;
	
	public Database(API instance) {
		this.instance = instance;
		
		this.url = (String)instance.getConf().get("db.host");
		this.user = (String)instance.getConf().get("db.user");
		this.password = (String)instance.getConf().get("db.password");
		
		if(this.url == "" || this.user == "" || this.password == "") {
			instance.getLogger().log(Level.SEVERE, "Part of the database record is not set in the config - exiting.");
			instance.getServer().shutdown();
		}
		
		String testquery = "SELECT VERSION()";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			
			try {
				con = createConnection();
				Statement st = con.createStatement();
				ResultSet rs =  st.executeQuery(testquery);
				if(rs.next())
					System.out.println("[Test Query] VERSION: " + rs.getString(1));
			} catch(SQLException e) {
				instance.getLogger().log(Level.SEVERE, "Issue in query!", e);
			}
			
		} catch(ClassNotFoundException e) {
			instance.getLogger().log(Level.SEVERE, "jdbc driver unavailable!", e);
			return;
		}
	}
	
	public Connection createConnection() {
		if(con == null) {
			try {
				con = DriverManager.getConnection(url, user, password);
			} catch(SQLException e) {
				instance.getLogger().log(Level.SEVERE, "Connection cannot be established!", e);
				return null;
			}
		}
		return con;
	}
	
	public void resetConnection() {
		try {
			con.close();
			con = DriverManager.getConnection(url, user, password);
		} catch(SQLException e) {
			instance.getLogger().log(Level.SEVERE, "Issue re-establishing connection with database!", e);
		}
	}
	
	public boolean connectionOk() {
		try {
			if(con != null && !con.isClosed()) {
				return true;
			} else {
				return false;
			}
		} catch(SQLException e) {
			return false;
		}
	}
	
	/* EXAMPLES
	 * INSERT INTO users VALUES ('', player.getName(), "", "", "", 15, 0, 0, 0)
	 * INSERT INTO users VALUES ('', player.getName(), "", "", "", 15, 0, 0, 0), ('', player.getName(), "", "", "", 15, 0, 0, 0)
	 */
	public boolean insertQuery(String query) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			return true;
		} catch(SQLException e) {
			instance.getLogger().log(Level.SEVERE, "Ran into problem with insert query!", e);
		}
		return false;
	}
	
	/* EXAMPLE
	 * UPDATE users SET custom_name="Banana_Man" where id=userid
	 */
	public int updateQuery(String query) {
		try {
			Statement stmt = con.createStatement();
			int updated = stmt.executeUpdate(query);
			return updated;
		} catch(SQLException e) {
			instance.getLogger().log(Level.SEVERE, "Ran into problem with update query!", e);
		}
		return 0;
	}
	
	/* EXAMPLE
	 * SELECT id FROM users WHERE username=alexjvan
	 */
	public List<String[]> getQuery(String query) {
		return getQuery(query, false);
	}
	
	public List<String[]> getQuery(String query, boolean supress) {
		try {
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			List<String[]> data = processQuery(rs, supress);
			return data;
		} catch (SQLException e) {
			if(!supress) {
				instance.getLogger().log(Level.SEVERE, "Query: " + query, e);
				instance.getLogger().log(Level.SEVERE, "Ran into problem with get query!", e);
			}
		}
		return null;
	}
	
	private List<String[]> processQuery(ResultSet rs, boolean supress) {
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			List<String[]> data = new ArrayList<String[]>();
			
			while(rs.next()) {
				String[] newData = new String[cols];
				for(int i = 0; i < cols; i++) {
					newData[i] = rs.getString(i + 1);
				}
				data.add(newData);
			}
			return data;
		} catch(SQLException e) {
			if(!supress)
				instance.getLogger().log(Level.SEVERE, "Ran into problem with get query while processing data!", e);
		}
		return null;
	}
}
