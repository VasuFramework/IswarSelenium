package CommonFunctionLibrary;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.PropertyFileUtil;

public class FunctionLibrary 
{
	WebDriver driver;
	public static WebDriver startBrowser(WebDriver driver) throws Throwable
	{
		
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
		{
			 driver=new ChromeDriver();
		}else
			if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
			{
				driver=new FirefoxDriver();
			}else
				if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
				{
					driver=new InternetExplorerDriver();
				}
		
		return driver;
	}

	
	public static void openApplication(WebDriver driver) throws Throwable
	{
		driver.manage().window().maximize();
		
		driver.get(PropertyFileUtil.getValueForKey("URL"));
	}
	
	public static void clickAction(WebDriver driver,String locatorType,String locatorValue)
	{
		if(locatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorValue)).click();
		}else
			if(locatorType.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(locatorValue)).click();
			}else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					driver.findElement(By.xpath(locatorValue)).click();
				}
	}
	
	public static void typeAction(WebDriver driver,String locatorType,String locatorValue,String data)
	{
		if(locatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorValue)).clear();
			driver.findElement(By.id(locatorValue)).sendKeys(data);
		}else
			if(locatorType.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(locatorValue)).clear();
				driver.findElement(By.name(locatorValue)).sendKeys(data);
			}else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					driver.findElement(By.xpath(locatorValue)).clear();
					driver.findElement(By.xpath(locatorValue)).sendKeys(data);
					
				}
	}
	
	public static void waiForElement(WebDriver driver,String locatorType,String locatorValue,String waittime)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Integer.parseInt(waittime)));
		
		if(locatorType.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
		}else
			if(locatorType.equalsIgnoreCase("name"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
			}else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
					
				}
	}
	public static String generateDate()
	{
		Date date=new Date();
		
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_SS");
		
		return sdf.format(date);
	}
	
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
	
}
