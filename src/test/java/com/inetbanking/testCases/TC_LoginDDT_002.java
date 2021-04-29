package com.inetbanking.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass
{
	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException, IOException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		logger.info("user name provided");
		lp.setPassword(pwd);
		logger.info("password provided");
		
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		if(isAlertPresent() == true)
		{
			//captureScreen(driver,"loginDDT");
			
			driver.switchTo().alert().accept(); // close login failed alert
			driver.switchTo().defaultContent(); // go back to main page
			Assert.assertTrue(false);
			logger.warn("Login failed");
									
		}
		else
		{
			Assert.assertTrue(true);
			lp.clickLogout();
			
			Thread.sleep(3000);
			driver.switchTo().alert().accept(); // close the logout alert
			driver.switchTo().defaultContent();
			logger.info("Login paased");
		}
	}
	
	// User defined method to check alert present or not
	public boolean isAlertPresent() 
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
		
	}
	
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		
		int rowcount = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String logindata[][] = new String[rowcount][colcount];
		
		for(int i=1;i<=rowcount;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
				
			}
		}
		return logindata;
		
	}

}
