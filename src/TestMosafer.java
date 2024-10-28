import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestMosafer {
	WebDriver driver = new ChromeDriver();
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
	@Test (priority = 3)
	public void CheckTheContactNumber() {
		String ActualNumber = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
		String ExpectedNumber = "+966554400000";
		Assert.assertEquals(ActualNumber, ExpectedNumber);
	}
	@Test (priority = 4)
	public void CheckTheLogo() {
		boolean ActualLogo = driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG")).isDisplayed();
		boolean ExpectedLogo = true;
		Assert.assertEquals(ActualLogo, ExpectedLogo);
	}
}
