package framework.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import framework.utils.Properties;
import framework.utils.WebDriverFactory;


public class Driver {

    public static Map<Long, WebDriver> webDriverMap = new HashMap<>();
    public static String workingDir = System.getProperty("user.dir");
    private static Logger logger = Logger.getLogger(Driver.class.getName());

    public static Logger getLogger() {
        return logger;
    }

    @Before
    public void beforeMethod(Scenario scenario) {
        //declare local variables
        String browser = null;
        boolean isLocal = true;
        WebDriver driver=null;
        String configFile = "Env_Common.properties";
        
        //load global properties file from resource
        try {
        	DOMConfigurator.configure("log4j.xml");
			Properties.loadPropertiesFile(configFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        logger.info("Starting the scenario: "+ scenario.getName());
        
        browser = Properties.getPropertyValue("Browser");
        if(System.getProperty("IsLocal")!=null)
            isLocal=Boolean.parseBoolean(System.getProperty("IsLocal"));
        else
            isLocal = Boolean.parseBoolean(Properties.getPropertyValue("IsLocal"));
        //baseURL=Properties.getPropertyValue("BaseURL");

        //initializing the driver
        System.out.println(isLocal);
        logger.info("launching browser "+browser );
        driver = WebDriverFactory.startDriver(browser, isLocal);
        webDriverMap.put(Thread.currentThread().getId(), driver);
        
    }

    @After
    public void tearDown(Scenario scenario) {
    	
    	WebDriver driver = getWebDriver();
    	logger.info("The scenario: "+ scenario.getName() + " is completed with the status "+scenario.getStatus());
        //close browser
        if(driver!=null) {
        	embedScreenshot(scenario);
        	driver.quit();
        }
        //delete driver exe file
        WebDriverFactory.deleteDriverExe();
    }

    /**
     * This will get the current driver instance
     * @return web driver instance
     */
    public static WebDriver getWebDriver(){
        return webDriverMap.get(Thread.currentThread().getId());
    }
    
    public void embedScreenshot(Scenario scenario) {
    	WebDriver driver = getWebDriver();
		if (scenario.isFailed()) {
			try {
				scenario.write("Current Page URL is " + driver.getCurrentUrl());
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
				System.err.println(somePlatformsDontSupportScreenshots.getMessage());
			}
		}
	}

}
