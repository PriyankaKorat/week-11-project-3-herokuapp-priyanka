package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Create the package ‘testsuite’ and create the
 * following class inside the ‘testsuite’ package.
 * 1. LoginTest
 * 3. Write down the following test into ‘LoginTest’ class
 * 1. userSholdLoginSuccessfullyWithValidCredentials
 * * Enter “tomsmith” username
 * * Enter “SuperSecretPassword!” password
 * * Click on ‘LOGIN’ button
 * * Verify the text “Secure Area”
 * 2. verifyTheUsernameErrorMessage
 * * Enter “tomsmith1” username
 * * Enter “SuperSecretPassword!” password
 * * Click on ‘LOGIN’ button
 * * Verify the error message “Your username
 * is invalid!”
 * 3. verifyThePasswordErrorMessage //
 * * Enter “tomsmith” username
 * * Enter “SuperSecretPassword” password
 * * Click on ‘LOGIN’ button
 * * Verify the error message “Your password
 * is invalid!”
 */
public class LoginTest extends BaseTest {
    String baseUrl = "https://the-internet.herokuapp.com/login";
    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials(){
        //enter valid username in username field
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        //enter valid password in password field
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        //click on login button
        driver.findElement(By.cssSelector(".fa.fa-2x.fa-sign-in")).click();
        //Verify the text
        String expectedText = "Secure Area";
        String actualText = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals("Text not match",expectedText,actualText);

    }
    @Test
    public void verifyTheUsernameErrorMessage(){
        //enter inValid username in username field
        driver.findElement(By.id("username")).sendKeys("tomsmith1");
        //enter valid password in password field
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        //click on login button
        driver.findElement(By.cssSelector(".fa.fa-2x.fa-sign-in")).click();
        //Verify the text
        String expectedErrorMessage = "Your username is invalid!\n×";
        String actualErrorMessage = driver.findElement(By.xpath("//div[@id = 'flash']")).getText();
        Assert.assertEquals("Error message not display",expectedErrorMessage,actualErrorMessage);
    }
    @Test
    public void verifyThePasswordErrorMessage(){
        //enter valid username in username field
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        //enter inValid password in password field
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword");
        //click on login button
        driver.findElement(By.cssSelector(".fa.fa-2x.fa-sign-in")).click();
        //Verify the text
        String expectedErrorMessage = "Your password is invalid!\n" + "×";
        String actualErrorMessage = driver.findElement(By.xpath("//div[@id='flash-messages']//div[1]")).getText();
        Assert.assertEquals("Error message not display",expectedErrorMessage,actualErrorMessage);
    }
    @After
    public void tearDown(){
        closeBrowser();
    }

}
