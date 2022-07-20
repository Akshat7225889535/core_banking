package com.project.dl.dao;
import java.sql.*;
public class DAOConnection {
	static Connection con=null; 
	public static Connection getConnection()
	{
		try {
			
			
			String mysqlJDBCDriver="oracle.jdbc.driver.OracleDriver"; 
			//String url="jdbc:mysql://localhost:3306/core_banking?autoReconnect=true&useSSL=false"; 
		//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysys?autoReconnect=true&useSSL=false","root","pass")
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user = "core_banking";	
			String pass = "Akshat1999**"; 
			Class.forName(mysqlJDBCDriver);
			con =DriverManager.getConnection(url,user,pass);
			System.out.println("Connection established");
			
		}
		catch (Exception e) {
			System.out.println("Connection Failed!");
		}
    return con;
		
	}
}

