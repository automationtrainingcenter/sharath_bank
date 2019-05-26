package utilities;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.Destination;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotHelper {

	public static String getFilePath(String folderName, String fileName) {
		return System.getProperty("user.dir") + File.separator + folderName + File.separator + fileName;
	}

	public static String getDate() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("dd-MMM-yy_HH-mm-ss_z");
		return df.format(date);
	}

	/**
	 * captures the screenshot using selenium TekeScreenshot interface for fileName
	 * parameter no need to specify file extension. This methos will automatically
	 * save the image in .png extension
	 * 
	 * @param driver
	 * @param folderName
	 * @param fileName
	 */
	public static String captureScreenShot(WebDriver driver, String folderName, String fileName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcImg = ts.getScreenshotAs(OutputType.FILE);
		File destImg = new File(getFilePath(folderName, fileName + getDate() + ".png"));
		try {
			BufferedImage bi = ImageIO.read(srcImg);
			ImageIO.write(bi, "png", destImg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destImg.getAbsolutePath();
	}

	/**
	 * This method will capture screenshot of alert in a page.
	 * No need to specify file extension. it will save image with .png extension
	 * @param folderName
	 * @param fileName
	 */
	public static String alertScreenCapture(String folderName, String fileName) {
		File destImg = null;
		try {
			Robot r = new Robot();
			Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
			BufferedImage bi = r.createScreenCapture(new Rectangle(size));
			destImg = new File(getFilePath(folderName, fileName+getDate()+".png"));
			ImageIO.write(bi, "png", destImg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destImg.getAbsolutePath();
	}

}
