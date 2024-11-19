import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestMosafer extends Parameters {

	@BeforeTest
	public void SetUp() throws InterruptedException {
		SetupToEnterAndPrepareTheWebSite();
	}

	@Test(priority = 1)
	public void EnglishLanguage() {
//		String ActualLang = driver.findElement(By.cssSelector(".sc-gkFcWv.jJNggu")).getText();
//		String ExpectedLang = "العربية";
		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");

		Assert.assertEquals(ActualLanguage, ExpectedLanguage);
	}

	@Test(priority = 2)
	public void CheckTheCurrency() throws IOException, InterruptedException {

//		String Actual2 = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']")).getText();
		String ActualCurrency = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO")).getText();
		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
		ScreenShot();
	}

	@Test(priority = 3)
	public void CheckTheContactNumber() throws InterruptedException {
		String ActualNumber = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
		Assert.assertEquals(ActualNumber, ExpectedNumber);
	}

	@Test(priority = 4)
	public void CheckTheLogo() {
		boolean ActualLogo = driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG")).isDisplayed();
		Assert.assertEquals(ActualLogo, ExpectedLogo);
	}

	@Test(priority = 5)
	public void TestHotelSearchTab() {
		WebElement HotelBtn = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String ActualHotelTab = HotelBtn.getAttribute("aria-selected");
		Assert.assertEquals(ActualHotelTab, ExpectedHotelTab);
	}

	@Test(priority = 6)
	public void TestDepartureTime() {
		String ActualDate = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']")).getText();
		// Convert ActualDate to an integer to remove leading zeros
		ActualDate = String.valueOf(Integer.parseInt(ActualDate));
		Assert.assertEquals(ActualDate, ExpectedDate);
	}

	@Test(priority = 7)
	public void TestReturnDate() {
		String ActualReturnDate = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']")).getText();
		// Convert ActualReturnDate to an integer to remove leading zeros
		ActualReturnDate = String.valueOf(Integer.parseInt(ActualReturnDate));
		Assert.assertEquals(ActualReturnDate, ExpectedReturnDate);
	}

	@Test(priority = 8)
	public void RandomChecksForLanguageAndInputs() throws InterruptedException {
		driver.get(WebsitesForLanguages[RandomLang]);
		WebElement StayBtn = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		StayBtn.click();
		Thread.sleep(2000);
		WebElement InputBar = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
		CheckTheLanguageToFillSearchBarRandomly(InputBar);
		WebElement Ulitems = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
		Ulitems.findElements(By.tagName("li")).get(1).click();
		Thread.sleep(1000);
		WebElement AdultsOption = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
		Select selector = new Select(AdultsOption);
		int RandomOption = rand.nextInt(2);
		selector.selectByIndex(RandomOption);
		Thread.sleep(1000);
		WebElement SearchBtn = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		SearchBtn.click();
	}

	@Test(priority = 9)
	public void SearchResultPage() throws InterruptedException {
		Thread.sleep(30000);
		WebElement FindResult = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']"));
		boolean ActualResult = FindResult.getText().contains("found") || FindResult.getText().contains("إقامة");
		boolean ExpectedResult = true;
		Assert.assertEquals(ActualResult, ExpectedResult);
	}

	@Test(priority = 10)
	public void SortingOptions() throws InterruptedException {
		WebElement LowestBtn = driver.findElement(By.xpath("//div[@data-testid='srp_sort_LOWEST_PRICE']"));
		LowestBtn.click();
		Thread.sleep(2000);
		if (driver.getCurrentUrl().contains("en")) {
			Thread.sleep(2000);
			WebElement EnContainer = driver.findElement(By.cssSelector(".__ds__comp.undefined.MuiBox-root.muiltr-1smo8f0"));
			List<WebElement> Prices = EnContainer.findElements(By.cssSelector(
					".MuiTypography-root.MuiTypography-heading3SemBld.__ds__comp.undefined.muiltr-18vmb2l"));
			int LowestPrice = Integer.parseInt(Prices.getFirst().getText().replace("SAR ", ""));
			int HighestPrice = Integer.parseInt(Prices.getLast().getText().replace("SAR ", ""));
			System.out.println(LowestPrice);
			System.out.println(HighestPrice);
			boolean ActualValue = LowestPrice < HighestPrice;
			boolean ExpectedValue = true;
			Assert.assertEquals(ActualValue, ExpectedValue);
		} else {
			WebElement ArContainer = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[5]/div/div[2]"));
			List<WebElement> Prices = ArContainer.findElements(By.cssSelector(
					".MuiTypography-root.MuiTypography-heading3SemBld.__ds__comp.undefined.muirtl-1l5b3qq"));
			int LowestPrice = Integer.parseInt(Prices.getFirst().getText().replace("ر.س. ", ""));
			int HighestPrice = Integer.parseInt(Prices.getLast().getText().replace("ر.س. ", ""));
			System.out.println(LowestPrice);
			System.out.println(HighestPrice);
			boolean ActualValue = LowestPrice < HighestPrice;
			boolean ExpectedValue = true;
			Assert.assertEquals(ActualValue, ExpectedValue);
		}
	}
}
