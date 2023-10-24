package DuploWeb;



import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class swagLabAutomation {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		
		//Launch Browser and Enter URL
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com");
		
		//1. Login
		WebElement username= driver.findElement(By.id("user-name"));
		username.sendKeys("standard_user");
		WebElement password=driver.findElement(By.cssSelector("#password"));
		password.sendKeys("secret_sauce");
		WebElement loginbtn=driver.findElement(By.xpath("//input[@type='submit']"));
		loginbtn.click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		//2. OpenMenu and view Inventory List
		WebElement openMenu=driver.findElement(By.xpath("(//button[normalize-space()='Open Menu'])[1]"));
		openMenu.click();
		WebElement inventoryView= driver.findElement(By.xpath("//a[@id='inventory_sidebar_link']"));
		inventoryView.click();
		
		//Scroll down inventory screen
		JavascriptExecutor js=  (JavascriptExecutor) driver;	
		js.executeScript("window.scrollBy(0, 500)");
		js.executeScript("window.scrollBy(0, -500)");
		
		//3a. Filter Page
	    WebElement pgFilter= driver.findElement(By.cssSelector(".select_container"));
	    pgFilter.click();
		
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
		
		WebElement sortPg1 = driver.findElement(By.cssSelector(".product_sort_container"));
		Select select1 = new Select(sortPg1);
        select1.selectByVisibleText("Name (A to Z)");
		
		
		//3b. Filter page 2
	    WebElement pgFilter2= driver.findElement(By.cssSelector(".select_container"));
	    pgFilter2.click();
		WebElement sortPg2 = driver.findElement(By.cssSelector(".product_sort_container"));
		Select select = new Select(sortPg2);
        select.selectByVisibleText("Price (low to high)");
		
		
		
		//4a. Add 1st product to Cart
		WebElement addToCart1=driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
		addToCart1.click();
		
		//4b. Add 2nd product to cart
		WebElement addToCart2=driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
		addToCart2.click();
		
		
		
		//5. View Cart
		WebElement cartView=driver.findElement(By.cssSelector(".shopping_cart_link"));
		cartView.click();
		
		
		
		
		//6. Remove product from cart
		WebElement removeItem=driver.findElement(By.id("remove-sauce-labs-backpack"));
		removeItem.click();
		
		
		
		
		//7. Checkout
		WebElement chkout=driver.findElement(By.cssSelector("#checkout"));
		chkout.click();
		
		WebElement chkoutFstName= driver.findElement(By.id("first-name"));
		chkoutFstName.sendKeys("Tested");
		
		WebElement chkoutLstName= driver.findElement(By.id("last-name"));
		chkoutLstName.sendKeys("Okay");
		
		WebElement zipCode= driver.findElement(By.id("postal-code"));
		zipCode.sendKeys("MB1123");
		
		WebElement ctn= driver.findElement(By.cssSelector("#continue"));
		ctn.click();
		
		WebElement totalAmt=driver.findElement(By.xpath("//div[@class='summary_info_label summary_total_label']"));
		String Amount=totalAmt.getText();
		System.out.println(Amount);
		
		WebElement smtChkout=driver.findElement(By.id("finish"));
		smtChkout.click();
		
		
		WebElement message=driver.findElement(By.cssSelector(".complete-header"));
		String descMsg=message.getText();
		System.out.println(descMsg);
		Assert.assertEquals(descMsg, "Thank you for your order!");
		
		
		//8. Logout
		
		WebElement bckhomepg=driver.findElement(By.id("back-to-products"));
		bckhomepg.click();
		
		WebElement openMenu2=driver.findElement(By.xpath("(//button[normalize-space()='Open Menu'])[1]"));
		openMenu2.click();
		WebElement inventoryView2= driver.findElement(By.xpath("//a[@id='inventory_sidebar_link']"));
		inventoryView2.click();
		
		WebElement logout=driver.findElement(By.cssSelector("#logout_sidebar_link"));
		
		logout.click();
		
		driver.quit();
		
		
	}

}
