package com.project.Tests;


import java.sql.Connection;

import com.project.Dao.ConnectionPool;

public class Thread1 extends Thread  {
	
	public void run(){
		 ConnectionPool cp = ConnectionPool.getInstance( );
	
			Connection con=cp.getConnection();
			System.out.println("Connected");
			
			cp.returnConnection(con);
			System.out.println("Notify");
	
		
	}

}
