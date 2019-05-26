package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptHelper {
	
	// getValue() which returns a String value i.e value of a text field
	public static String getValue(WebDriver driver, WebElement textField) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		return js.executeScript("return arguments[0].value", textField).toString();
	}

	// bringElementToView() which will bring given element into view
	public static void bringElementToView(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}
	
	//scrollPage() which will scroll the web page by given amount of pixels
	public static void scrollPage(WebDriver driver, int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.documentElement.scrollBy(arguments[0], arguments[1])", x, y);
	}
}
