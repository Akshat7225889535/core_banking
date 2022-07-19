package com.project.dl.dao;
import java.sql.*;
public class DAOConnection {
	static Connection con=null; 
	public static Connection getConnection()
	{
		try {
			
			
			String mysqlJDBCDriver="com.mysql.cj.jdbc.Driver"; 
			String url="jdbc:mysql://localhost:3306/core_banking?autoReconnect=true&useSSL=false"; 
		//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysys?autoReconnect=true&useSSL=false","root","pass")
			String user = "root";	
			String pass = "APer2022@%tenT"; 
			Class.forName(mysqlJDBCDriver);
			con =DriverManager.getConnection(url,user,pass);
			
			
		}
		catch (Exception e) {
			System.out.println("Connection Failed!");
		}
    return con;
		
	}
}

