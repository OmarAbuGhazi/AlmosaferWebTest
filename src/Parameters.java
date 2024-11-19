import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Parameters {
	WebDriver driver = new ChromeDriver();
	Random rand = new Random();

	String TheWebSite = "https://global.almosafer.com/en";

	String ExpectedLanguage = "en";

	String ExpectedCurrency = "SAR";

	String ExpectedNumber = "+966554400000";

	boolean ExpectedLogo = true;

	String ExpectedHotelTab = "false";

//	LocalDate today = LocalDate.now();
//	int Today = LocalDate.now().getDayOfMonth();
	int Tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();
//  We Can Do it Like This.
//	String ExpectedDate = String.format("%02d", AfterTomorrow);
	String ExpectedDate = String.valueOf(Tomorrow);

	int AfterTomorrow = LocalDate.now().plusDays(2).getDayOfMonth();
//  We Can Do it Like This.
//	String ExpectedReturnDate = String.format("%02d", AfterTomorrow);
	String ExpectedReturnDate = String.valueOf(AfterTomorrow);

	String[] WebsitesForLanguages = { "https://www.almosafer.com/ar", "https://www.almosafer.com/en" };
	int RandomLang = rand.nextInt(WebsitesForLanguages.length);
	String[] ArabicValue = { "دبي", "جدة" };
	int RandomArValue = rand.nextInt(ArabicValue.length);
	String RandomArValueString = ArabicValue[RandomArValue];
	String[] EnglishValue = { "Dubai", "Jeddah", "Riyadh" };
	int RandomEnValue = rand.nextInt(EnglishValue.length);
	String RandomEnValueString = EnglishValue[RandomEnValue];
	
	public void SetupToEnterAndPrepareTheWebSite() throws InterruptedException {
		driver.manage().window().maximize();
		driver.get(TheWebSite);
		WebElement LanguageBtn = driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		Thread.sleep(1000);
		LanguageBtn.click();
	}

	public void ScreenShot() throws IOException, InterruptedException {
		Thread.sleep(2000);
		Date myDate = new Date();
		String fileName = myDate.toString().replace(":", "-");

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File SrcFile = screenshot.getScreenshotAs(OutputType.FILE);

		File destFile = new File("./ScreenShot/" + fileName + ".png");
		FileUtils.copyFile(SrcFile, destFile);
	}
	public void CheckTheLanguageToFillSearchBarRandomly(WebElement InputBar) throws InterruptedException {
		Thread.sleep(1000);
		if (driver.getCurrentUrl().equals("https://www.almosafer.com/ar")) {
			String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String ExpectedLanguage = "ar";
			Assert.assertEquals(ActualLanguage, ExpectedLanguage);
			InputBar.sendKeys(RandomArValueString);
			Thread.sleep(2000);
		} else {
			String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String ExpectedLanguage = "en";
			Assert.assertEquals(ActualLanguage, ExpectedLanguage);
			InputBar.sendKeys(RandomEnValueString);
			Thread.sleep(2000);
		}
	}
}
