package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage{

    public CartPage(WebDriver driver){
        super(driver);
    }

    //Capture the 'Cart' link
    @FindBy(css = "a[href='#/cart']")
    WebElement cartLnk;

    //Capture the total
    @FindBy(css = ".total.ng-binding")
    WebElement total;

    //Click 'Cart' link
    public void clickCartLink() {
        cartLnk.click();
    }

    //Get the 'Product Price'
    public Double getProductPrice(int rowId) {
        //Get the price of each product in the cart.
        String productPrice = driver.findElement(By.xpath("//tbody/tr["+rowId+"]/td[2]")).getText().substring(1);
        return Double.parseDouble(productPrice);
    }

    //Get the 'Sub Total'
    public Double getSubTotal(int rowId) {
        String subTotal = driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr["+rowId+"]/td[4]")).getText().substring(1);
        return Double.parseDouble(subTotal);
    }

    //Get the 'Total'
    public double getTotal() {
        double totalVal = Double.parseDouble(total.getText().split(" ")[1]);
        return totalVal;
    }
}
