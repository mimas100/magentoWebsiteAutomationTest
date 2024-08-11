package magentoWebSiteTest;

import java.nio.file.WatchEvent;
import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.AbstractFindByBuilder;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

public class myFristTest {

	WebDriver driver = new ChromeDriver();
	String webSite = "https://magento.softwaretestingboard.com/";
	Random random = new Random();
	String password = "Pass1234!";
	
	String logOutPage = "https://magento.softwaretestingboard.com/customer/account/logout/";

	String Email = "";

	@BeforeTest
	public void setupTest() {
		driver.get(webSite);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); 

		
	}

	@Test(priority = 1)
	public void CeateAccountTest() {
		// create Account by Link Test
		WebElement createAnAccount = driver.findElement(By.linkText("Create an Account"));
		createAnAccount.click();

		// Create Account by Partial Test
		// WebElement createAnAccount =
		// driver.findElement(By.partialLinkText("Account"));
		// createAnAccount.click();

		// create Account by xpath
		// WebElement createAnAccount =
		// driver.findElement(By.xpath("//a[@href='https://magento.softwaretestingboard.com/customer/account/create/']"));
		// createAnAccount.click();

		// [@href="https://magento.softwaretestingboard.com/customer/account/create/"]

		// first names
		String[] first_Names = { "Alice", "Bob", "Charlie", "David", "Eve", "Fay", "Grace" };
		// last names
		String[] last_Names = { "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia" };

		int firstNameRandomIndex = random.nextInt(first_Names.length);
		int lastNameRandomIndex = random.nextInt(last_Names.length);

		String firstNameInput = first_Names[firstNameRandomIndex];
		String lastNameInput = first_Names[lastNameRandomIndex];
		int randomNum = random.nextInt(9999);
		String emailDomin = "@gmail.com";

		WebElement firstName = driver.findElement(By.id("firstname"));
		firstName.sendKeys(firstNameInput);

		WebElement lastName = driver.findElement(By.id("lastname"));
		lastName.sendKeys(lastNameInput);

		WebElement anEmailAdress = driver.findElement(By.id("email_address"));
		Email = firstNameInput + lastNameInput + randomNum + emailDomin;

		anEmailAdress.sendKeys(Email);

		WebElement passWord = driver.findElement(By.id("password"));
		passWord.sendKeys(password);

		WebElement confirmedpassWord = driver.findElement(By.id("password-confirmation"));
		confirmedpassWord.sendKeys(password);

		WebElement CreateAccountButton = driver.findElement(By.xpath("//button[@title='Create an Account']"));
		CreateAccountButton.click();

		WebElement ActualAdded = driver.findElement(By.className("message-success"));
		String  ActualMsgAccount = ActualAdded.getText();
		String ExpectedMsgAccount = "Thank you for registering with Main Website Store.";
		org.testng.Assert.assertEquals(ActualMsgAccount, ExpectedMsgAccount);
		
		
	}

	@Test(priority = 2)
	public void logOutPage() throws InterruptedException {

		driver.get(logOutPage);
WebElement LogoutMessage = driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']"));
		
		String ActualMsg = LogoutMessage.getText();
		String ExpectedMsg = "You are signed out"; 
		
		org.testng.Assert.assertEquals(ActualMsg, ExpectedMsg);


	}

	@Test(priority = 3)
	public void loginTest() {
		WebElement LoginPage = driver.findElement(By.linkText("Sign In"));
		LoginPage.click();

		WebElement EmailLoginInput = driver.findElement(By.id("email"));
		WebElement passwordInput = driver.findElement(By.id("pass"));
		WebElement LoginButton = driver.findElement(By.cssSelector(".action.login.primary"));

		EmailLoginInput.sendKeys(Email);
		passwordInput.sendKeys(password);
		LoginButton.click();
		
String WelcomeMessage = driver.findElement(By.className("logged-in")).getText();
		
		boolean ActualValue = WelcomeMessage.contains("Welcome");
		boolean ExpectedValue = true ; 
		
		org.testng.Assert.assertEquals(ActualValue, ExpectedValue);
		
	}
	
	
	@Test(priority = 4)
	public void addToWemonCard() {
	
		WebElement addToWemon = driver.findElement(By.id("ui-id-4"));
		addToWemon.click();
		
		WebElement olOfWemonItem = driver.findElement(By.className("product-items"));
		List <WebElement> listOfItems = olOfWemonItem.findElements(By.tagName("li"));
		int  listOfItemsCount = listOfItems.size();
		int randomList = random.nextInt(listOfItemsCount);
		
		WebElement indexItem = listOfItems.get(randomList);
		indexItem.click();
		
		
		
		WebElement AddSizeItem = driver.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));
		List<WebElement> listOfSize = AddSizeItem.findElements(By.tagName("div"));
		int listSizeCount =  listOfSize.size();
		int randomListSize = random.nextInt(listSizeCount);
		listOfSize.get(randomListSize).click();
		
		
		WebElement addColorItem = driver.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
		List<WebElement> colorList = addColorItem.findElements(By.tagName("div"));
		int colorListCount = colorList.size();
		int randomColorIndex = random.nextInt(colorListCount);
		colorList.get(randomColorIndex).click();
		
		WebElement addToCardWemonBtn = driver.findElement(By.id("product-addtocart-button"));
		addToCardWemonBtn.click();
		
		WebElement addedSuccess = driver.findElement(By.className("message-success"));
		String added = addedSuccess.getText();
		boolean Actual = added.contains("You added");
		//boolean ExpectedValue = true ; 
		
		org.testng.Assert.assertEquals(Actual, true);
		
		
		
		WebElement review = driver.findElement(By.id("tab-label-reviews-title"));
		review.click();
		
		WebElement raiting = driver.findElement(By.id("review"));
		raiting.click();
		
		WebElement nickname= driver.findElement(By.id("nickname_field"));
		nickname.sendKeys("Mimas");
		
		WebElement summary= driver.findElement(By.id("summary_field"));
		summary.sendKeys("very Good Item ");
		
		
		WebElement reviewfield= driver.findElement(By.id("review_field"));
		reviewfield.sendKeys(" I Will Add it ");
		
		WebElement reviewBtn= driver.findElement(By.xpath("//button[@type='submit']"));
		reviewBtn.click();
		
		
		
		
		
	
	}
		
		
}	

		
		
		
	

	/*
	@Test(priority = 4)
	  public void addToMenSection()throws InterruptedException
	{
		
		
        WebElement MenSection = driver.findElement(By.id("ui-id-5"));
		
		MenSection.click();
     	 
     	// WebElement olOfMenItems = driver.findElement(By.className("product-items"));
     	 
     	WebElement olOfMenItems = driver.findElement(By.className("product-items"));
		
     	 List <WebElement> listOfItems = olOfMenItems.findElements(By.tagName("li"));
     	 int AllItems = listOfItems.size();
     	 
     	 int randomIndexOfItem = random.nextInt(AllItems);
     	listOfItems.get(randomIndexOfItem).click();
     	
     	
		
     	WebElement sizeOfItem = driver.findElement(By.cssSelector(".swatch-attribute-options.clearfix"));
         List<WebElement> AllSize = sizeOfItem.findElements(By.className("swatch-option"));
		
     	int AllSizeCount = AllSize.size();
     	int randomSizeIndex = random.nextInt(AllSizeCount);
     	AllSize.get(randomSizeIndex).click();
     	
     	
     	
     	
     	
     	WebElement allColors= driver.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
     	List<WebElement> colorSelected = allColors.findElements(By.tagName("div"));
     	
     	int colorCount = colorSelected.size();
     	int randomColor = random.nextInt(colorCount);
     	colorSelected.get(randomColor).click();
     	
     	WebElement addToCardButton = driver.findElement(By.id("product-addtocart-button"));
     	addToCardButton.click();
     	
     	
     	WebElement messageResulte = driver.findElement(By.className("message-success"));
     //	Boolean msg = messageResulte.getText().contains("You added");
     	
     org.testng.Assert.assertEquals(messageResulte.getText().contains("You added"), true);
     	
		
	}
	*/
	
	
	
	
	
	
	
	

