package in.srssprojects.keximbank;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TableHelper{

//	public static void handleTable(WebElement table, String id, String operation) {
////	locate rows
//		List<WebElement> rows = table.findElements(By.tagName("tr"));
////	iterate over the rows
//		for (int i = 1; i < rows.size(); i++) {
////		locate cells in every row
//			List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
////		id is in first cell, edit is second cell from last, delete is in last cell
//			if (cells.get(0).getText().equals(id)) {
//				if (operation.equalsIgnoreCase("edit")) {
//					cells.get(cells.size() - 2).findElement(By.tagName("a")).click();
//					break;
//				} else if (operation.equalsIgnoreCase("delete")) {
//					cells.get(cells.size() - 1).findElement(By.tagName("a")).click();
//					break;
//				} else {
//					System.out.println("invalid operation");
//				}
//			}
//		}
//	}
	
	static boolean after10 = false;

	public static void handleTable(WebDriver driver, By tableLoc, String id, String operation) {
		boolean status = false;
		WebElement table = driver.findElement(tableLoc);
//		locate rows
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		String text = rows.get(rows.size() - 1).getText();
		String[] links = text.split(" ");
		System.out.println(links[0]);
////		links loop
		int l;
		if(after10) {
			l = 2;
		}else {
			l = 1;
		}
		for (; l < links.length; l++) {
			table = driver.findElement(tableLoc);
			rows = table.findElements(By.tagName("tr"));
//		iterate over the rows
			for (int i = 1; i < rows.size() - 1; i++) {
//			locate cells in every row
				List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
//			id is in first cell, edit is second cell from last, delete is in last cell
				if (cells.get(0).getText().equals(id)) {
					if (operation.equalsIgnoreCase("edit")) {
						cells.get(cells.size() - 2).findElement(By.tagName("a")).click();
						status = true;
						break;// inner for loop for rows to find id
					} else if (operation.equalsIgnoreCase("delete")) {
						cells.get(cells.size() - 1).findElement(By.tagName("a")).click();
						status = true;
						break;// inner for loop for rows to find id
					} else {
						System.out.println("invalid operation");
					}
				}
			}
			if (status == false) {
				try {
					if (links[l].equals("...") && after10 == true) {
						rows.get(rows.size() - 1).findElement(By.xpath("//*[contains(text(), '...')][2]")).click();
					} else {

						rows.get(rows.size() - 1).findElement(By.linkText(links[l])).click();
					}
				} catch (Exception e) {

				}
			} else {
				System.out.println("terminating link loop");
				break; // breaks links loop
			}
		}
		if (status == false && after10 == true && links.length < 12) {
			System.out.println(status);
			throw new RuntimeException("id does not exist in the data base");
		}
		if (status == false) {
			after10 = true;
			handleTable(driver, tableLoc, id, operation);
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://srssprojects.in");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("txtuId")).sendKeys("Admin");
		driver.findElement(By.id("txtPword")).sendKeys("Admin");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.cssSelector("a[href='admin_banker_master.aspx']")).click();
		By table = By.cssSelector("#DG_bankdetails>tbody");
		handleTable(driver,table, "520", "edit");
		Thread.sleep(3000);
		driver.close();
		
	}

}
