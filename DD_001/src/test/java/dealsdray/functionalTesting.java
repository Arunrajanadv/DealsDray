package dealsdray;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;

import dealsdray.GenericUtiliy.PropertyFileUtility;
import dealsdray.GenericUtiliy.ScreenRecorderUtil;

public class functionalTesting {

	public static void main(String[] args) throws Exception {
		ScreenRecorderUtil.startRecord("main");
		WebDriver driver= null;		
		PropertyFileUtility putil = new PropertyFileUtility();
		String browser = putil.toReadDatafromProperty("BROWSER");
		String url = putil.toReadDatafromProperty("URL");
		String username = putil.toReadDatafromProperty("USERNAME");
		String password = putil.toReadDatafromProperty("PASSWORD");
		
		
		
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
		 driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get(url);
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Login']")).click();	
		driver.findElement(By.xpath("//button[@class='MuiButtonBase-root has-submenu compactNavItem css-46up3a']")).click();	
		driver.findElement(By.linkText("Orders")).click();	
		driver.findElement(By.xpath("//button[text()='Add Bulk Orders']")).click();	  
		WebElement File = driver.findElement(By.xpath("//input[@type='file']"));
		File.sendKeys("C:\\Users\\OR\\Desktop\\New folder\\demo-data.xlsx");
		driver.findElement(By.xpath("//button[@class=('MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButtonBase-root  css-6aomwy')]")).click();
		driver.findElement(By.xpath("//button[@class=('MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButtonBase-root  css-6aomwy')]")).click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
		TakesScreenshot ts = (TakesScreenshot) driver; // typecasting
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File src= new File("./errorShots/" +" "+driver+ " "+ "Final" + "-" + dateFormat.format(new Date())+".png");
		FileHandler.copy(temp, src);
		System.out.println("DONE TAKING SCREENSHOT OF SITE");
		Thread.sleep(2000);
		driver.quit();
		ScreenRecorderUtil.stopRecord();
		}

}
