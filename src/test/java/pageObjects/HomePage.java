package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver){
        super(driver);
    }

    //Capture the 'Start Shopping' button
    @FindBy(className = "btn btn-success btn-large")
    WebElement ShoppingBtn;


    public void startShoppingButton() {
        boolean buttonExist = ShoppingBtn.isDisplayed();
    }
}

