package Homework3;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TEST2 {
    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String USER_LOGIN = "user";
    private static final String USER_PASSWORD = "1234";
    private static final String expensesMenu = "//ul[contains(@class,'nav-multilevel')]/li[contains(.,'Контрагенты')]";
    private static final String expensesSubmenu = "//li[@data-route='crm_contact_index']/a";
    private static final String createButton = "//a[@title='Создать контактное лицо']";
    private static final String orgChosen = "//span[@class='select2-arrow']";
    private static final String orgInput = "//input[contains (@class,'select2-input')]";
    private static final String orhResult = "//div[@class='select2-result-label']";
    private static final String saveButton = "//button[contains(.,'Сохранить и закрыть')]";
    private static final String message =  "//div[contains (@Class, 'alert-success')]";
    private static WebDriver driver;


    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        login();

        Actions actionContractor = new Actions(driver);
        WebElement contractorMenu = driver.findElement(By.xpath(expensesMenu));
        actionContractor.moveToElement(contractorMenu).perform();

        driver.findElement(By.xpath(expensesSubmenu));

        driver.findElement(By.xpath(createButton)).click();
//поле фамилия
        WebElement fieldLastName = driver.findElement(By.name("crm_contact[lastName])"));
        fieldLastName.sendKeys("Лоскутов");
        System.out.println("Поле Фамилия заполнено:" + !fieldLastName.getAttribute("value").isEmpty());
        System.out.println("-----------------------");

        //поле имя
        WebElement fieldFirstName = driver.findElement(By.name("crm_contact[company]"));
        fieldFirstName.sendKeys("Дмитрий");
        System.out.println("Поле Имя заполнено:" + !fieldLastName.getAttribute("value").isEmpty());
        System.out.println("-----------------------");

        // поле организация
        WebElement fieldOrganisation = driver.findElement(By.name("crm_contact[company]"));

        driver.findElement(By.xpath(orgChosen)).click();
        driver.findElement(By.xpath(orgInput)).sendKeys("104");
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(orhResult))));
        driver.findElement(By.xpath(orgInput)).sendKeys(Keys.ENTER);

        System.out.println("Поле Организация заполнено:" + !fieldOrganisation.getAttribute("value").isEmpty());
        System.out.println("-----------------------");

        //поле Должность

        WebElement fieldjobTitle = driver.findElement(By.name("crm_contact[jobTitle]"));
        fieldjobTitle.sendKeys("Менеджер");
        System.out.println("Поле Должность заполнено:" + !fieldjobTitle.getAttribute("value").isEmpty());
        System.out.println("-----------------------");

        //Сохранение и закрытие

        driver.findElement(By.xpath(saveButton)).click();

        //"Контактное лицо сохранено"
        WebElement messageSuccess = driver.findElement(By.xpath(message));
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(messageSuccess));
        System.out.println("Отображается сообщение 'Контакное лицо сохранено':" +messageSuccess.isDisplayed());

        tearDown();
    }

    private static void login() {
        driver.get(LOGIN_PAGE_URL);

        driver.manage().window().maximize();

        WebElement loginInput = driver.findElement(By.name("_username"));
        loginInput.sendKeys(USER_LOGIN);

        WebElement passwordInput = driver.findElement(By.name("_password"));
        passwordInput.sendKeys(USER_PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
        loginButton.click();

        }
        private static void tearDown() {
        System.out.println();
        System.out.println("Test is comleted!");
        driver.quit();

        }

    }

