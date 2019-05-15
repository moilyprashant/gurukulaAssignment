package com.gurukula.testgurukula.util;

/**
 * Copyright (c) 2019, ChamLabs.
 * Responsible: Chandra.Sekhar.Muttineni
 * @author https://github.com/cham6
 * @email: paperplanes.chandra@gmail.com
 * @fork: https://github.com/cham6/TestAutomationFrameworkForGurukula
 */

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverWrapperUtil {

	public static String getPageTitle(WebDriver driver) {
		if (driver == null) {
			return null;
		}
		return driver.getTitle();
	}
	
	public static boolean elementExistsByID(WebDriver driver, String id) {
		
		try {
			
			driver.findElement(By.id(id));
			return true;
		} catch(NoSuchElementException nse) {
			System.out.println("Element with id " + id + " not found");
		}
		return  false;
	}
	
	public static boolean elementExistsByIDTimeout(WebDriver driver, String id, int timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
			return true;
		} catch(NoSuchElementException nse) {
			System.out.println("Element with id " + id + " not found");
		}
		return  false;
	}
	
	public static WebElement getElementByID(WebDriver driver, String id) {
		
		try {
			
			WebElement element = driver.findElement(By.id(id));
			return element;
		} catch(NoSuchElementException nse) {
			System.out.println("Element with id " + id + " not found");
		}
		return  null;
	}
	
	public static WebElement getElementByIDTimeout(WebDriver driver, String id, int timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
			return element;
		} catch(NoSuchElementException nse) {
			System.out.println("Element with id " + id + " not found");
		}
		return  null;
	}
	
	public static WebElement getWebTableCellElement(WebElement table, String cell) {
		try {
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			System.out.println("Found " + rows.size() + " rows in the table");
			for (int i=0; i<rows.size(); i++) {
				List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
				System.out.println("Found " + columns.size() + " columns in the table");
				if(columns.size()==0) {
					continue;
				}
				for (int j=0; j<columns.size(); j++) {
					String cellText = columns.get(j).getText();
					System.out.println("Celltext is:   " + columns.get(j).getText());
					if(cellText.contains(cell)) {
						System.out.println("Found cell with value "+ cell);
						return columns.get(j);
					}
				}
			}
		} catch(NoSuchElementException nse) {
			System.out.println("Column with text " + cell + " not found");
		}
		return  null;
	}
	
	public static WebElement scrollHyperlinkWithLinkTextIntoView(WebDriver driver, String linkText) {
		try {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				WebElement hyperlink = driver.findElement(By.linkText(linkText));
		        js.executeScript("arguments[0].scrollIntoView();", hyperlink);
		        return hyperlink;
			} catch(NoSuchElementException nse) {
			System.out.println("Element with linkText " + linkText + " not found");
		}
		return null;
	}
	
	public static WebElement scrollElementWitIDIntoView(WebDriver driver, String id) {
		try {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				WebElement element = driver.findElement(By.id(id));
		        js.executeScript("arguments[0].scrollIntoView();", element);
		        return element;
			} catch(NoSuchElementException nse) {
			System.out.println("Element with id " + id + " is not found");
		}
		return null;
	}
	
	public static void mouseHover(WebDriver driver, WebElement element) {
		try {
				Actions actions = new Actions(driver);
				actions.moveToElement(element);
				actions.perform();
			} catch(NoSuchElementException nse) {
			System.out.println("Cannot mouse hover to element");
			}
		}
	
	public static void rightClick(WebDriver driver, WebElement element) {
		try {
				Actions actions = new Actions(driver);
				actions.contextClick(element);
				actions.perform();
			} catch(NoSuchElementException nse) {
			System.out.println("Cannot right click on element");
			}
		}
	
	public static void doubleClick(WebDriver driver, WebElement element) {
		try {
				Actions actions = new Actions(driver);
				actions.doubleClick(element);
				actions.perform();
			} catch(NoSuchElementException nse) {
			System.out.println("Cannot double click on element");
			}
		}
	public static WebElement getBranchStaffTableActionElement
			(WebElement webTable, Map<String, String> map) {
		try {	
			List<WebElement> rows = webTable.findElements(By.tagName("tr"));
			System.out.println("Found " + rows.size() + " rows in the table");
			for (int i=0; i<rows.size(); i++) {
				List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
				System.out.println("Found " + columns.size() + " columns in the table");
				if(columns.size()==0) {
					continue;
				}
				if(columns.get(1).getText().equalsIgnoreCase(map.get("Name")) 
						&& columns.get(2).getText().equalsIgnoreCase(map.get("Code"))) {
					if(map.get("Action").equalsIgnoreCase("View")) {
						return webTable.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ (i) + "]/td[4]/button[1]"));
					} else if(map.get("Action").equalsIgnoreCase("Edit")) {
						return webTable.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ (i) + "]/td[4]/button[2]"));
					} else {
						return webTable.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[4]/table/tbody/tr["+ (i) + "]/td[4]/button[3]"));

					}
				}
			}
		} catch(Exception nse) {
			System.out.println("Exception while getting " +map.get("Action") + " button");
		}
		return  null;
	}
	
	public static boolean selectItemFromDropdown (WebElement element, String valueToBeSelected) {
		try {
			Select selectObject = new Select(element);
			selectObject.selectByVisibleText(valueToBeSelected);
		} catch(Exception nse) {
			System.out.println("Exception while selecting item from dropdown");
		}
		
		return false;
	}
	
	public static String captureScreenshot(WebDriver driver, String screenshotName) throws Exception {

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);

		return destination;
	}
}