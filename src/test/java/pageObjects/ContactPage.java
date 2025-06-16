package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactPage extends BasePage {

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    //Capture the 'Contact' link
    @FindBy(css = "a[href='#/contact']")
    WebElement contactLnk;

    //Capture the 'Forename' field
    @FindBy(id = "forename")
    WebElement forenameFld;

    //Capture the 'Surname' field
    @FindBy(id = "surname")
    WebElement surnameFld;

    //Capture the 'Email ' field
    @FindBy(id = "email")
    WebElement emailFld;

    //Capture the 'Telephone' field
    @FindBy(id = "telephone")
    WebElement telephoneFld;

    //Capture the 'Message' field
    @FindBy(id = "message")
    WebElement messageFld;

    //Capture the 'Submit' button
    @FindBy(css = ".btn-contact.btn.btn-primary")
    WebElement submitBtn;

    //Capture Error messages
    //Capture the top error message
    @FindBy(css = ".alert.alert-error.ng-scope")
    WebElement topErrMsg;

    //Capture the 'Forename' error message
    @FindBy(css = "#forename-err")
    WebElement forenameErrMsg;

    //Capture the 'Email' error message
    @FindBy(css = "#email-err")
    WebElement emailErrMsg;

    //Capture the 'Message' error message
    @FindBy(css = "#message-err")
    WebElement messageErrMsg;

    //Capture the 'Success' message
    @FindBy(css = ".alert.alert-success")
    WebElement successMsg;

    //Capture the 'Back' button
    @FindBy(css = "a[class=\"btn\"]")
    WebElement backBtn;

    //Click 'Contact' link
    public void clickContactLnk() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement contactLnk = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='#/contact']")));
        contactLnk.click();
    }

    //Click 'Submit' button
    public void clickSubmitBtn() {
        submitBtn.click();
    }

    //Click 'Back' button
    public void clickBackBtn() {
        backBtn.click();
    }

    //Error messages are displayed
    public boolean isTopErrMsgDisplayed(){
        boolean result = topErrMsg.isDisplayed();
        return result;
    }

    public boolean isForenameErrMsgDisplayed(){
        boolean result = forenameErrMsg.isDisplayed();
        return result;
    }

    public boolean isEmailErrMsgDisplayed(){
        boolean result =  emailErrMsg.isDisplayed();
        return result;
    }

    public boolean isMessageErrMsgDisplayed(){
        boolean result = messageErrMsg.isDisplayed();
        return result;
    }

    //Capture the size of the error message elements
    public boolean errorTobBannerExist(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        boolean isGone = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".alert.alert-error.ng-scope")));
        return isGone;
    }
    public boolean errorForeNameExist(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        boolean isGone = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("forename-err")));
        return isGone;
    }
    public boolean errorEmailExist(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        boolean isGone = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("email-err")));
        return isGone;
    }
    public boolean errorMessageExist(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        boolean isGone = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("message-err")));
        return isGone;
    }

    //Insert values to the text fields
    public void setForename(String forename) {
        forenameFld.clear();
        forenameFld.sendKeys(forename);
    }
    public void setEmail(String email) {
        emailFld.clear();
        emailFld.sendKeys(email);
    }
    public void setMessage(String message) {
        messageFld.clear();
        messageFld.sendKeys(message);
    }
    public void setSurname(String surname) {
        surnameFld.clear();
        surnameFld.sendKeys(surname);
    }
    public void setTelephone(String telephone) {
        telephoneFld.clear();
        telephoneFld.sendKeys(telephone);
    }

    //Get success message
    public String getSuccessMsg() {
        try{
            return (successMsg.getText());
        }catch (Exception e){
            return (e.getMessage());
        }

    }

    public String getSuccessMsgLocator(){
        String successMsgLocator = (".alert.alert-success");
        return  successMsgLocator;
    }

}
