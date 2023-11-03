import com.microsoft.playwright.*;
import org.testng.annotations.*;

public class BaseTest {

    Playwright playwright;
    Browser browser;

    BrowserContext context;
    Page page;

//    public Page getPage() {
//        return page;
//    }

    @BeforeClass
    protected void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterClass
    protected void closeBrowser() {
        playwright.close();
    }

    @BeforeMethod
    protected void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();

//        page.navigate("https://magento.softwaretestingboard.com/");
    }

    @AfterMethod
    protected void closeContext() {
        context.close();
    }
}
