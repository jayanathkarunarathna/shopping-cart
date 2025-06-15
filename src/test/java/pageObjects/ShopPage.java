package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShopPage extends BasePage {

    public ShopPage(WebDriver driver) {
        super(driver);
    }


    //Capture the 'Shop' link
    @FindBy(css = "a[href='#/shop']")
    WebElement shopLnk;

    @FindBy(css = ".cart-count.ng-binding")
    WebElement cartCount;

    public int getCartCount(){
        //Get the ordered product count.
        return Integer.parseInt(cartCount.getText());
    }

    //Click 'Shop' link
    public void clickShopLink() {
        shopLnk.click();
    }

    public void orderProduct(String productName, int quantity) {
        //Add products to the 'Shopping Cart'.
        WebElement productElement = driver.findElement(By.xpath("//li[@class='product ng-scope'][.//h4[@class='product-title ng-binding' and text()='" + productName + "']]"));
        String productId = productElement.getDomAttribute("id");
        for (int i = 0; i < quantity; i++) {
            driver.findElement(By.cssSelector("li[id=" + productId + "] a[class=\"btn btn-success\"]")).click();
        }
    }
}
