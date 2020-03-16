package ru.stqa.pft.test.tests;

import net.lightbody.bmp.core.har.Har;
import org.testng.annotations.Test;

public class BrowserLogsTest extends TestBaseLiteCartProxy {
    @Test
    public void testGetBrowsersLogs(){
        proxy.newHar();
        driver.get("https://seleniumhq.wordpress.com/");
        driver.get("https://www.selenium.dev/blog/");
        Har har = proxy.endHar();
        har.getLog().getEntries().forEach(l-> System.out.println(l.getResponse()
                .getStatus()+ ":" + l.getRequest().getUrl()));

    }
}
