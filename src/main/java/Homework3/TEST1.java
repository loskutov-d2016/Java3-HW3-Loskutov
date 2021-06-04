package Homework3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class TEST1 {
        private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
        private static final String STUDENT_LOGIN = "user";
        private static final String STUDENT_PASSWORD = "1234";
        private static final WebDriver driver;
        private static final String name = "Лоскутов Дмитрий Сергеевич";
        private static final String organization = "1234";

        static {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        }

        public static void main(String[] args) throws InterruptedException {
            login();
            login2();
        }

        private static void login() {
            driver.get(LOGIN_PAGE_URL);
//авторизация

            WebElement loginTextInput = driver.findElement(By.cssSelector("input[id='prependedInput']"));
            loginTextInput.sendKeys(STUDENT_LOGIN);

            WebElement passwordTextInput = driver.findElement(By.cssSelector(".span2[name='_password']"));
            passwordTextInput.sendKeys(STUDENT_PASSWORD);

            WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
            loginButton.click();
        }
    private static void login2() {
//мои проекты
        WebElement project = driver.findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[3]/a/span"));
        project.click();

        WebElement Myproject = driver.findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[3]/ul/li[4]/a/span"));
        Myproject.click();
//создать проект
        WebElement crateproject = driver.findElement(By.xpath("//a[text()=\"Создать проект\"]"));
        crateproject.click();

//наименование
        WebElement nameInput = driver.findElement(By.xpath(".//input[@name='crm_project[name]']"));
        nameInput.sendKeys(name);
//организация
        WebElement organizationInput = driver.findElement(By.xpath(".//*[@id=\"s2id_autogen1\"]"));
        organizationInput.sendKeys(organization);
        WebElement OrganizationEnter = driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul[2]/li[1]/div/span"));
        OrganizationEnter.click();
//основные контактное лицо
        WebElement contactperson = driver.findElement(By.xpath("//div[@class=\"select2-container select2\"]"));
        contactperson.click();
        WebElement contactperson1 = driver.findElement(By.xpath("//li[@class=\"select2-results-dept-0 select2-result select2-result-selectable select2-highlighted\"]"));
        contactperson1.click();
//подразделение
        Select division = new Select(driver.findElement(By.name("crm.project[businessUnit]")));
        division.selectByValue("1");

//куратор
        Select fieldBProjectCurator = new Select(driver.findElement(By.name("crm_project[curator]")));
        fieldBProjectCurator.selectByVisibleText("Лоскутов Дмитрий");
//руководитель проекта
        Select fieldBProjectDirector = new Select(driver.findElement(By.name("crm_project[rp]")));
        fieldBProjectDirector.selectByVisibleText("Авласёнок Денис");

// менеджер
        Select fieldBProjectManager = new Select(driver.findElement(By.name("crm_project[manager]")));
        fieldBProjectManager.selectByVisibleText("Амелин Владимир");
//сохранить и закрыть
        WebElement saveButton = driver.findElement(By.xpath("//button[contains(.,'Сохранить и закрыть')]"));
        saveButton.click();
//сообщение
        WebElement message = driver.findElement((By.xpath("//div[contains(@class, 'alert-success')]")));

        tearDown();
    }
    private static void tearDown() {
        System.out.println();
        System.out.println("Test is comleted!");
        driver.quit();
    }
}


