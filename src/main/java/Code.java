import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by dkool on 4/26/2017.
 */
public class Code {

    public void run() throws InterruptedException, EmailException {

    	this.send(false);
    	
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\dkool\\Documents\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.navigate().to("https://wl11gp.neu.edu/udcprod8/NEUCLSS.p_disp_dyn_sched");

        if (driver.findElement(By.id("term_input_id")).isDisplayed()) {
            driver.findElement(By.id("term_input_id")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//option[contains(text(), 'Fall 2017 Semester')]")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//option[contains(text(), 'Fall 2017 Semester')]")).sendKeys(Keys.ENTER);
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@type='submit']")).click();
        }

        Thread.sleep(10000);


        driver.findElement(By.id("crn_id")).sendKeys("17613");
        driver.findElement(By.xpath("//option[@value='CS']")).click();
        driver.findElement(By.xpath("//option[@value='CS']")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Thread.sleep(5000);

        int refresh = 3000;

        while (true) {
            if (driver.findElement(By.xpath("//td[@class='dddefault'][9]")).getText().equals("0")) {
                driver.navigate().refresh();
                driver.switchTo().alert().accept();
                Thread.sleep(refresh);
            } else {
                // EMAIL
                this.send(true);
                refresh = 300000;
            }
        }
    }

    public void send(boolean real) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setTLS(true);
        email.setAuthenticator(new DefaultAuthenticator("getthatseat123", "getthatseat"));
        email.setFrom("getthatseat123@gmail.com");
        if (real) {
        	email.setSubject("Seat Open!!!");
            email.setMsg("Get that seat!");
        }
        else {
        	email.setSubject("Test");
            email.setMsg("Test");
        }
        
        email.addTo("dkoolj5@gmail.com");
        email.send();
    }
}
