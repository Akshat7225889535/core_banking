import java.util.*;

import com.project.dl.dao.BankDAO;
import com.project.dl.dto.BankDTO;
import com.project.dl.exception.DAOException;
import com.project.dl.interfaces.BankDAOInterface;
public class BankAddTestCase {

	public static void main(String[] args) 
	{	
		Scanner sc=new Scanner(System.in);
		int pin;
		String name;
		int ch;
		while(true)
		{
			System.out.println("<---Welcome to the Bank--->");
			System.out.println("1. Create Account");
			System.out.println("2. Login Account");
			System.out.println("3. Exit");
			try
			{
				System.out.println("Enter your choice : ");
				ch=sc.nextInt();
				if(ch==1)
				{
					try
					{
					System.out.println("Enter Unique Name : ");
					name=sc.nextLine();
					name+=sc.nextLine();
					System.out.println("Enter New PIN");
					pin=sc.nextInt();
					BankDAO bankDAO=new BankDAO();
					BankDTO bankDTO=new BankDTO();
					bankDTO.setName(name);
					bankDTO.setPin(pin);
					if(bankDAO.createAccount(bankDTO))
					{
						System.out.println("Account Created Sucessfully");
						
					}
					else
					{
						System.out.println("Account Creation Failed..!");
						break;
					}
					}catch(Exception e)
					{
						System.out.println("Invalid Data, Insertion Failed");
						break;
					}
				
				}
				else if(ch==2)
				{
					try
					{
						System.out.println("Enter username : ");
						name=sc.nextLine();
						name+=sc.nextLine();
						
						System.out.println("Enter your PIN : ");
						pin=sc.nextInt();
						BankDTO bankDTO=new BankDTO();
						bankDTO.setName(name);
						bankDTO.setPin(pin);
						BankDAO bankDAO=new BankDAO();
						if(bankDAO.loginAccount(bankDTO))
						{
							System.out.println("Login Successfully");
							
						}
						else 
						{
							System.out.println("Login Failed");
						}
						
					}catch(Exception e)
					{
						System.out.println("Enter valid Data, Login Failed");
					}
				}
				else if(ch==3)
				{
					System.out.println("Thank you");
					break;
				}
				else 
				{
					System.out.println("Invalid Input");
					break;
				}
				
			}catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
		}
	}

}
