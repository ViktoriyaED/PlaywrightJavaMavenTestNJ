import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.regex.Pattern;


public class NavigationTest extends BaseTest {

    @Test
    public void testHomePage() {
        String expectedHomeUrl = "https://magento.softwaretestingboard.com/";
        String expectedHomeTitle = "Home Page";

        page.navigate("https://magento.softwaretestingboard.com/");

        Assert.assertEquals(page.url(), expectedHomeUrl);
        Assert.assertEquals(page.title(), expectedHomeTitle);
    }

    @DataProvider(name="TopNavigationBar")
    public Object[][] menuItems() {
        return new Object[][]{
                {"What's New", "https://magento.softwaretestingboard.com/what-is-new.html", "What's New"},
                {"Women", "https://magento.softwaretestingboard.com/women.html", "Women"},
                {"Men", "https://magento.softwaretestingboard.com/men.html", "Men"},
                {"Gear", "https://magento.softwaretestingboard.com/gear.html", "Gear"},
                {"Training", "https://magento.softwaretestingboard.com/training.html", "Training"},
                {"Sale", "https://magento.softwaretestingboard.com/sale.html", "Sale"}
        };
    }

    @Test(dependsOnMethods = "testHomePage", dataProvider = "TopNavigationBar")
    public void testSubMenu_NavigatesTo_SubMenuPage(String submenu, String expectedUrl, String expectedTitle) {
        String homePageUrl = page.url();
        String homePageTitle = page.title();

        page.navigate("https://magento.softwaretestingboard.com/");
        page.getByRole((AriaRole.MENUITEM), new Page.GetByRoleOptions().setName(Pattern.compile(submenu))).click();

        String whatsNewUrl = page.url();
        String whatsNewTitle = page.title();

        Assert.assertNotEquals(homePageUrl, whatsNewUrl);
        Assert.assertNotEquals(homePageTitle, whatsNewTitle);

        Assert.assertEquals(page.url(), expectedUrl);
        Assert.assertEquals(page.title(), expectedTitle);
    }

    @Test
    public void test_tc05() {

    }
}
