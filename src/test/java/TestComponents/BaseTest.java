package TestComponents;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class BaseTest {

    Properties properties = new Properties();
    DesiredCapabilities capabilities = new DesiredCapabilities();
    AppiumDriver driver;

    @BeforeTest
    public AppiumDriver initializeDriver() {
        try {
            FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\capabilities.properties");
            properties.load(input);

            for (String key : properties.stringPropertyNames()) {
                capabilities.setCapability(key, properties.getProperty(key));
            }
            driver = new AndroidDriver(new URL("https://localhost:4723/wd/hub"), capabilities);
            return driver;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
