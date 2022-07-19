package com.project.dl.dao;

import java.sql.*;

import com.project.dl.dto.BankDTO;
import com.project.dl.exception.DAOException;
import com.project.dl.interfaces.BankDAOInterface;
import java.util.*;

public class BankDAO implements BankDAOInterface
{
	static Connection con=DAOConnection.getConnection();
	static String sql="";
	
	@Override
	public boolean createAccount(BankDTO bankDTO) throws DAOException 
	{
		String name=bankDTO.getName();
		Integer pin=bankDTO.getPin();
		try
		{
			if(name=="" || pin==0)
			{
				System.out.println("All Fields Required");
				return false;
			}
			
			Statement st=con.createStatement();
			sql="insert into bank_details(name,balance,pin) values ('"+name+"',1000,"+pin+")";
			if(st.executeUpdate(sql)==1)
			{
				System.out.println(name+", Now you can Login..!");
				return true;
			}
		}catch (SQLIntegrityConstraintViolationException e) 
		{
            System.out.println("Username Not Available!");
        }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean loginAccount(BankDTO bankDTO) throws DAOException 
	{
		try
		{
		String name=bankDTO.getName();
		Integer pin=bankDTO.getPin();
		if(name=="" || pin==0)
		{
			System.out.println("ALl fields Required");
			return false;
		}
		sql="select * from bank_details where name='"+name+"' and pin="+pin;
		PreparedStatement st=con.prepareStatement(sql);
		ResultSet rs=st.executeQuery();
		Scanner sc=new Scanner(System.in);
		
		if(rs.next())
		{
			int snt=rs.getInt("acc_number");
			int cho=0;
			while(true)
			{
				try
				{
				System.out.println("Hello "+rs.getString("name"));
				System.out.println("1. Check Balance");
				System.out.println("2. Withdraw Amount");
				System.out.println("3. Deposit Amount");
				System.out.println("4. logout");
				System.out.println("Enter your choice : ");
				cho=sc.nextInt();
				if(cho==1)
				{
					BankDAO bankDAO=new BankDAO();
					bankDAO.getBalance(snt);
				}
				else if(cho==2)
				{ 	
					BankDAO bankDAO=new BankDAO();
					bankDAO.withdrawAmount(snt);
					
				}
				else if(cho==3)
				{
					BankDAO bankDAO=new BankDAO();
					bankDAO.depositAmount(snt);
				}
				else
				{
					System.out.println("Thank you, Have a Nice Day :) ");
					break;
				}
				
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				{
					
				}
			}
		}
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public void getBalance(int accountNumber) throws DAOException 
	{
		try
		{
			sql="select * from bank_details where acc_number="+accountNumber;
			PreparedStatement st=con.prepareStatement(sql);
			ResultSet rs=st.executeQuery(sql);
			System.out.printf("%12s %10s %10s\n","Account No", "Name","Balance");
			while (rs.next()) {
                System.out.printf("%12d %10s %10s\n",rs.getInt("acc_number"),rs.getString("name"),rs.getString("balance"));
            }
            System.out.println("-----------------------------------------------------------\n");
			
		}catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}

	@Override
	public void withdrawAmount(int accountNumber) throws DAOException 
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			sql="select * from bank_details where acc_number="+accountNumber;
			PreparedStatement st=con.prepareStatement(sql);
			ResultSet rs=st.executeQuery(sql);
		
			while(rs.next())
			{
				System.out.println("Your Available Balance is : "+rs.getString("balance"));
				System.out.println("Enter the withdrawing amount : ");
				int withdraw=sc.nextInt();
				if(Integer.parseInt(rs.getString("balance"))<withdraw || withdraw<=0)
				{
					System.out.println("Invalid amount, Insufficient Balance");
					return;
				}
				int finalAmount=Integer.parseInt(rs.getString("balance"))-withdraw;
				Statement sta=con.createStatement();
				sql="update bank_details set balance='"+finalAmount+"' where acc_number="+accountNumber;
				sta.executeUpdate(sql);
				System.out.println("Withdrawing Successfull..!");
				System.out.println("Remaining Account Balance");
				System.out.println();
				BankDAO bankDAO=new BankDAO();
				bankDAO.getBalance(accountNumber);
			}
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void depositAmount(int accountNumber) throws DAOException 
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			sql="select * from bank_details where acc_number="+accountNumber;
			PreparedStatement st=con.prepareStatement(sql);
			ResultSet rs=st.executeQuery(sql);
		
			while(rs.next())
			{
				System.out.println("Your Available Balance is : "+rs.getString("balance"));
				System.out.println("Enter the deposit amount : ");
				int deposit=sc.nextInt();
				if(deposit<=0)
				{
					System.out.println("Invalid amount");
					return;
				}
				int finalAmount=Integer.parseInt(rs.getString("balance"))+deposit;
				Statement sta=con.createStatement();
				sql="update bank_details set balance='"+finalAmount+"' where acc_number="+accountNumber;
				sta.executeUpdate(sql);
				System.out.println("Deposit Successfull..!");
				System.out.println("Available Account Balance");
				System.out.println();
				BankDAO bankDAO=new BankDAO();
				bankDAO.getBalance(accountNumber);
			}
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	
		
	

}
