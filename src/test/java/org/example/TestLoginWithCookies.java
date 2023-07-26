package org.example;

import com.zebrunner.carina.core.IAbstractTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class TestLoginWithCookies implements IAbstractTest {
    @Test
    public void testSimulateLogin() {
        String expectedCookieName = "session-username";
        String expectedCookieValue = "standard_user";
        WebDriver driver = getDriver();
        driver.get("https://www.saucedemo.com/");
        Cookie newCookie = new Cookie.Builder("session-username", "standard_user").sameSite("Strict").build();
        driver.manage().addCookie(newCookie);
        driver.get("https://www.saucedemo.com/inventory.html");
        Set<Cookie> cookies = driver.manage().getCookies();
        Assert.assertFalse(cookies.isEmpty());
        for (Cookie cookie : cookies) {
            Assert.assertEquals(cookie.getName(), expectedCookieName, String.format("Wrong cookies. name: %s, value: %s",
                    cookie.getName(), cookie.getValue()));
            Assert.assertEquals(cookie.getValue(), expectedCookieValue,  String.format("Wrong cookies value. name: %s, value: %s",
                    cookie.getName(), cookie.getValue()));
        }
    }
}
