package utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TableHelper {

//	public static void handleTable(WebElement table, String id, String operation) {
////		locate rows
//		List<WebElement> rows = table.findElements(By.tagName("tr"));
////		iterate over the rows
//		for (int i = 1; i < rows.size(); i++) {
////			locate cells in every row
//			List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
////			id is in first cell, edit is second cell from last, delete is in last cell
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
//
//	}
	static boolean after10 = false;

	public static void handleTable(WebElement table, String id, String operation) {
		boolean status = false;
//		locate rows
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		String text = rows.get(rows.size() - 1).getText();
		String[] links = text.split(" ");
//		links loop
		for (int l = 1; l < links.length; l++) {
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
			throw new RuntimeException("id does not exis in the data base");
		}
		if (status == false) {
			after10 = true;
			handleTable(table, id, operation);
		}
		
		

	}

}
