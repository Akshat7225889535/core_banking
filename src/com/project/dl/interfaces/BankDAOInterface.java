package com.project.dl.interfaces;

import com.project.dl.dto.BankDTO;
import com.project.dl.exception.DAOException;

public interface BankDAOInterface 
{
	public boolean  createAccount(BankDTO bankDTO) throws DAOException;
	public boolean loginAccount(BankDTO bankDTO) throws DAOException;
	public void getBalance(int accountNumber) throws DAOException;
	public void withdrawAmount(int accountNumber) throws DAOException;
	public void depositAmount(int accountNumber) throws DAOException;
	
	
}
