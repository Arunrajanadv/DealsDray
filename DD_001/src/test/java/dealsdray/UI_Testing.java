package dealsdray;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;

import dealsdray.GenericUtiliy.PropertyFileUtility;
import dealsdray.GenericUtiliy.ScreenRecorderUtil;

public class UI_Testing {


			public static void main(String[] args) throws Exception {
				ScreenRecorderUtil.startRecord("main");
				
				WebDriver driver= null;
				
				PropertyFileUtility putil = new PropertyFileUtility();
				String browser = putil.toReadDatafromProperty("BROWSER");
				String device = putil.toReadDatafromProperty("DEVICE");
				String resolution = putil.toReadDatafromProperty("RESOLUTION4");
								
				if (browser.equals("chrome")) {
					driver = new ChromeDriver();
				} else if (browser.equals("edge")) {
				 driver = new EdgeDriver();
				}				
						
				driver.manage().window().maximize();
				driver.get("https://www.getcalley.com/page-sitemap.xml");
				
				driver.findElement(By.linkText("https://www.getcalley.com/")).click();				
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
				driver.manage().window().setSize(new Dimension(2980,2785));
				Thread.sleep(2000);				
				TakesScreenshot ts = (TakesScreenshot) driver; // typecasting
				File temp = ts.getScreenshotAs(OutputType.FILE);
				File src= new File("./errorShots/" +" "+device+ " "+resolution+" "+ "Final" + "-" + dateFormat.format(new Date())+".png");
				FileHandler.copy(temp, src);
				
				System.out.println("DONE TAKING SCREENSHOT OF SITE");
				
				Thread.sleep(2000);
				driver.quit();
				ScreenRecorderUtil.stopRecord();
			}

		

	}


