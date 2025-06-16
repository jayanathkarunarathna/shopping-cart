package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.ShopPage;
import testBase.BaseClass;
import utilities.ExcelReader;
import java.util.List;

public class TC03_ValidateShoppingCart extends BaseClass {

    @DataProvider(name = "orderData")
    public Object[][] orderData() throws Exception {
        // Read the data for the Order data from the Excel file.
        String path = System.getProperty("user.dir") + "/testData/TestData.xlsx";
        ExcelReader reader = new ExcelReader(path);
        List<String[]> rawData = reader.getContactFormData("OrderData", true).stream().skip(1).toList();
        return new Object[][]{{rawData}};
    }

    @Test(dataProvider = "orderData")
    public void orderProducts(List<String[]> orderItems) {
        logger.info("*** Starting orderProducts ***");

 //       try {
            ShopPage sp = new ShopPage(driver);
            sp.clickShopLink();
            int quantitySum = 0;

            for (String[] item : orderItems) {
                String productName = item[0];
                int quantity = Integer.parseInt(item[1]);

                sp.orderProduct(productName, quantity);
                quantitySum += quantity;
            }
            //Validate 'Cart Count'
            Assert.assertEquals(sp.getCartCount(), quantitySum, "Cart count is incorrect.");

//        }
//        catch (Exception e) {
//            logger.error("test is failed.." + '\n' + e);
//            Assert.fail();
//        }

        logger.info("*** Finished orderProducts ***");
    }

    @DataProvider(name = "cartData")
    public Object[][] cartData() throws Exception {
        // Read the data for the Cart data from the Excel file.
        String path = System.getProperty("user.dir") + "/testData/TestData.xlsx";
        ExcelReader reader = new ExcelReader(path);
        List<String[]> rawData = reader.getContactFormData("CartData", true).stream().skip(1).toList();
        return new Object[][]{{rawData}};
    }

    @Test(dataProvider = "cartData")
    public void verifyCartCalculations(List<String[]> cartItems) {
        logger.info("*** Starting verifyCartCalculations ***");

 //       try {
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCartLink();
        double expectedTotal = 0.0;

        for (String[] item : cartItems) {
            int rowId = cartItems.indexOf(item) + 1;
            String productName = item[0];
            double price = Double.parseDouble(item[1]);
            int quantity = Integer.parseInt(item[2]);
            double expectedSubtotal = cartPage.getSubTotal(rowId);

            //Verify the 'Product Price'.
            Assert.assertEquals(price, cartPage.getProductPrice(rowId), "Product price is incorrect for product: " + productName);
            //Verify the 'Sub Total'.
            Assert.assertEquals(expectedSubtotal, price * quantity, "Subtotal is incorrect for product: " + productName);

            expectedTotal += expectedSubtotal;
        }

        double actualTotal = cartPage.getTotal();
        //Verify the 'Total' amount.
        Assert.assertEquals(actualTotal, expectedTotal, "Total is incorrect between actual and expected totals.");

//        } catch (Exception e) {
//            logger.error("test is failed.." + '\n' + e);
//            Assert.fail();
//        }

        logger.info("*** Finished verifyCartCalculations ***");
    }

}
