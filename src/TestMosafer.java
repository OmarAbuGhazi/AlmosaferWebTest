import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestMosafer {
	WebDriver driver = new ChromeDriver();
	Random rand = new Random();
	String TheWebSite = "https://global.almosafer.com/en";
	@BeforeTest
	public void SetUp() throws InterruptedException {
		driver.manage().window().maximize();
		driver.get(TheWebSite);
		WebElement LanguageBtn = driver
				.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		Thread.sleep(1000);
		LanguageBtn.click();
	}
	@Test(priority = 1)
	public void EnglishLanguage() {
//		String ActualLang = driver.findElement(By.cssSelector(".sc-gkFcWv.jJNggu")).getText();
//		String ExpectedLang = "العربية";
		String ExpectedLanguage = "en";
		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");

		Assert.assertEquals(ActualLanguage, ExpectedLanguage);
	}
	@Test(priority = 2)
	public void CheckTheCurrency() {

//		String Actual2 = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']")).getText();
		String ExpectedCurrency = "SAR";
		String ActualCurrency = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO")).getText();
		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
	}
	@Test(priority = 3)
	public void CheckTheContactNumber() {
		String ActualNumber = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
		String ExpectedNumber = "+966554400000";
		Assert.assertEquals(ActualNumber, ExpectedNumber);
	}
	@Test(priority = 4)
	public void CheckTheLogo() {
		boolean ActualLogo = driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG")).isDisplayed();
		boolean ExpectedLogo = true;
		Assert.assertEquals(ActualLogo, ExpectedLogo);
	}
	@Test (priority = 5)
	public void TestHotelSearchTab() {
		WebElement HotelBtn = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String ActualHotelTab = HotelBtn.getAttribute("aria-selected");
		String ExpectedHotelTab = "false";
		Assert.assertEquals(ActualHotelTab, ExpectedHotelTab);
	}
	@Test (priority = 6)
	public void TestDepartureTime() {
//		LocalDate today = LocalDate.now();
		int Today = LocalDate.now().getDayOfMonth();
		int Tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();		
		String ActualDate = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']")).getText();
		String ExpectedDate = Integer.toString(Tomorrow);
		Assert.assertEquals(ActualDate, ExpectedDate);
	} 
	@Test (priority = 7)
	public void TestReturnDate() {
		int TodayDate = LocalDate.now().getDayOfMonth();
		int AfterTomorrow = LocalDate.now().plusDays(2).getDayOfMonth();
		String ActualReturnDate = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']")).getText();
		String ExpectedReturnDate = Integer.toString(AfterTomorrow);
		Assert.assertEquals(ActualReturnDate, ExpectedReturnDate);
	}
	@Test (priority = 8, invocationCount = 5)
	public void RandomLanguage() throws InterruptedException {
		String[] WebsitesForLanguages = { "https://www.almosafer.com/ar", "https://www.almosafer.com/en" };
		int RandomLang = rand.nextInt(WebsitesForLanguages.length);
		driver.get(WebsitesForLanguages[RandomLang]);
		Thread.sleep(2000);
		if (driver.getCurrentUrl().equals("https://www.almosafer.com/ar")) {
			String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String ExpectedLanguage = "ar";
			Assert.assertEquals(ActualLanguage, ExpectedLanguage);
		} else {
			String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String ExpectedLanguage = "en";
			Assert.assertEquals(ActualLanguage, ExpectedLanguage);
		}
	}
}