package org.eclipse.jkube.maven.sample.spring.boot;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import javax.json.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {
    private final String hubUrl = System.getProperty("selenium.hub.url");
    private final String targetBaseURL = System.getProperty("target.base.url");

    private String body;

    private String response;
    private String createdName;
    private String createdDescription;

    private boolean isDriverReady = false;
    private WebDriver driver = null;

    @Given("^I use Chrome browser$")
    public void I_use_Chrome_browser() throws Throwable {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("chrome");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        initDriver(capabilities);
    }

    @When("^I get (.*)$")
    public void i_get_collection(String path) throws Throwable {
        if(path.equals("root")) {
            path = "";
        }

        driver.navigate().to(new URL(String.format("%s/%s", targetBaseURL, path)));
        body = getBody();
    }

    @Then("^I should get (.*)$")
    public void i_should_get(String expected) throws Throwable {
        assertEquals("Actual body does not match expected body", body, expected);
    }

    @Then("I clean up")
    public void i_clean_up() {
        this.driver.quit();
    }

    private String getBody() {
        String body = driver.findElement(By.tagName("body")).getText();
        return body;
    }

    private void initDriver(DesiredCapabilities capabilities) {
        if(this.isDriverReady) {
            return;
        }
        try {
            this.driver = new RemoteWebDriver(new URL(this.hubUrl + "/wd/hub"), capabilities);
            this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch(UnreachableBrowserException e) {
            Assert.fail("UnreachableBrowserException: " + e.getMessage());
        } catch(MalformedURLException e) {
            Assert.fail("MalformedURLException: " + this.hubUrl + "/wd/hub");
        } catch(WebDriverException e) {
            Assert.fail("WebDriverException: " + e.getMessage());
        }
        this.isDriverReady = true;
    }
}
