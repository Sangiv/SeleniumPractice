import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GooglePageTest {

    private static WebDriver driver;
    
 // Designed to return ChromeOptions to configure new ChromeDrivers in Selenium
    public static ChromeOptions chromeCfg() {
     Map<String, Object> prefs = new HashMap<String, Object>();
     ChromeOptions cOptions = new ChromeOptions();
      
     // Settings
     prefs.put("profile.default_content_setting_values.cookies", 2);
     prefs.put("network.cookie.cookieBehavior", 2);
     prefs.put("profile.block_third_party_cookies", true);

     // Create ChromeOptions to disable Cookies pop-up
     cOptions.setExperimentalOption("prefs", prefs);

     return cOptions;
     }

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        driver = new ChromeDriver();
//        driver = new ChromeDriver( chromeCfg() );
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1366, 768));

    }

    @Test
    @Ignore
    public void test() throws InterruptedException {
        driver.get("https://google.com");

        assertEquals("Google", driver.getTitle());
    }

    @After
    public void tearDown() {
        driver.close();
    }
    
    @Test
    @Ignore
    public void test2() throws InterruptedException {
        driver.get("https://google.com");
        WebElement searchBar = driver.findElement(By.name("q"));
        String searchTerm = "Puppies";
        searchBar.sendKeys(searchTerm);
        searchBar.submit();
        
        driver.findElement(By.xpath("//*[@id=\"hdtb-msb-vis\"]/div[2]/a")).click();
        
        driver.findElement(By.xpath("//*[@id=\"islrg\"]/div[1]/div[1]/a[1]/div[1]/img")).click();
    }
    
    @Test
    @Ignore
    public void shoppingTest() throws InterruptedException {
    	driver.get("http://automationpractice.com/index.php");
        WebElement searchBar = driver.findElement(By.id("search_query_top"));
        String searchTerm = "Dress";
        searchBar.sendKeys(searchTerm);
        searchBar.submit();
    	
        assertTrue(driver.findElement(By.xpath("//*[@id=\"grid\"]/a/i")).isDisplayed());
    }
    
    @Test
    @Ignore
    public void shoppingTest2() throws InterruptedException {
    	driver.get("http://automationpractice.com/index.php");
        WebElement searchBar = driver.findElement(By.id("search_query_top"));
        String searchTerm = "Dress";
        searchBar.sendKeys(searchTerm);
        searchBar.submit();
    	
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]")).click();
        WebElement create_email = driver.findElement(By.id("email_create"));
        String email = "user@user.com";
        create_email.sendKeys(email);
        create_email.submit();   
    }
    
    @Test
    public void stockTest() throws InterruptedException {
        driver.get("https://www.hl.co.uk/shares/stock-market-summary/ftse-100");
        
//        WebDriverWait wait = new WebDriverWait(driver,30);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\\\"acceptCookie\\\"]")));
        
        Thread.sleep(2500);
        driver.findElement(By.xpath("//*[@id=\"acceptCookie\"]")).click();
//      Click to riser tab
        Thread.sleep(2500);
        driver.findElement(By.xpath("//*[@id=\"view-constituents\"]/ul/li[2]/a")).click();
        
        List<WebElement> results;
        results = driver.findElements(By.xpath("//*[@id=\"view-constituents\"]/div[2]/table/tbody/*"));
        
    	Double maxprice = (double) 0;
    	String riser = "";
        for(WebElement result : results) {
        	
        	String resultString = result.getText();
        	String[] ans = resultString.split("\n");
        	
        	Double price = Double.parseDouble(ans[1]);
        	
        	if(price > maxprice) {
        		maxprice = price;
        		riser = ans[0];
        	}
        }
        
    	System.out.println(riser);
    	
//      Find highest Riser
//        Thread.sleep(2500);
//        String riser = driver.findElement(By.xpath("//*[@id=\"ls-row-RR.-L\"]/td[2]/a")).getText();
//        System.out.println("Riser: " + riser);
        
//        Thread.sleep(2500);
//        driver.findElement(By.xpath("//*[@id=\"acceptCookie\"]")).click();
//        click faller tab
//        Thread.sleep(2500);
//        driver.findElement(By.xpath("//*[@id=\"content_div_40583\"]/ul/li[3]/a")).click();
//        find highest faller
//        Thread.sleep(2500);
//        String faller = driver.findElement(By.xpath("//*[@id=\"ls-row-RB.-L\"]/td[2]/a")).getText();
//        System.out.println("Faller: " + faller);
    }
    
}