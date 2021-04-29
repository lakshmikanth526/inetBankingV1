package com.inetbanking.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass
{
	
	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("user name is provided");
		lp.setPassword(password);
		logger.info("password is provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		AddCustomerPage addcust = new AddCustomerPage(driver);
		
		addcust.clickAddNewCustomer();
		
		logger.info("providing customer details....");
		addcust.custName("lakshmikanth");
		addcust.custgender("male");
		addcust.custdob("03","04","1987");
		Thread.sleep(3000);
		addcust.custaddress("INDIA");
		addcust.custcity("HYD");
		addcust.custstate("AP");
		addcust.custpinno("500017");
		addcust.custtelephoneno("8099602136");
		
		String email = randomestring() + "@gmail.com";
		addcust.custemailid(email);
		
		addcust.custpassword("abcdef");
		addcust.custsubmit();
		
		Thread.sleep(3000);
		
		logger.info("validattion started....");
		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(res == true)
		{
			Assert.assertTrue(true);
			logger.info("test case is passed");
		}
		else
		{
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
			logger.info("test case is failed");
		}
		
		
	}
	
		
}
