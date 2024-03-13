import Api.DeleteUserApi;
import Api.RegistrationUserApi;

import io.qameta.allure.Description;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.xpath;

public class UiTest extends DriverProperties {
    int randomNumber = new Random().nextInt(1000000);
    String userName = "garri-" + randomNumber;
    String userEmail = "garri-" + randomNumber + "@yandex.ru";
    String userPassword = "123456";


    WebDriver driver;

    @Before
    public void setUp() {
        System.out.println("---------Предусловие--------");
        new RegistrationUserApi().registrationUser(userName, userEmail, userPassword); //Создание нового клиента
        System.out.println("---------Клиент с email:" + userEmail + " создан--------");
        driver = initDriver("yandex");
    }

    @Test
    @DisplayName("logInAccount1") // имя теста
    @Description("вход по кнопке «Войти в аккаунт» на главной") // описание теста
    public void logInAccount1() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://stellarburgers.nomoreparties.site/");
        driver.findElement(xpath(".//button[text() = 'Войти в аккаунт']")).click();
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));
        UserAuthorization userAuthorization = new UserAuthorization(driver);
        userAuthorization.setUserDataAuthorization(userEmail, userPassword);
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));
    }

    @Test
    @DisplayName("logInAccount2") // имя теста
    @Description("вход через кнопку «Личный кабинет»") // описание теста
    public void logInAccount2() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://stellarburgers.nomoreparties.site/");
        driver.findElement(xpath(".//p[text() = 'Личный Кабинет']")).click();
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));
        UserAuthorization userAuthorization = new UserAuthorization(driver);
        userAuthorization.setUserDataAuthorization(userEmail, userPassword);
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));
    }

    @Test
    @DisplayName("logInAccount3") // имя теста
    @Description("вход через кнопку в форме регистрации") // описание теста
    public void logInAccount3() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://stellarburgers.nomoreparties.site/register");
        driver.findElement(xpath(".//a[text() = 'Войти']")).click();
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));
        UserAuthorization userAuthorization = new UserAuthorization(driver);
        userAuthorization.setUserDataAuthorization(userEmail, userPassword);
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));
    }

    @Test
    @DisplayName("logInAccount4") // имя теста
    @Description("вход через кнопку в форме восстановления пароля") // описание теста
    public void logInAccount4() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        driver.findElement(xpath(".//a[text() = 'Войти']")).click();
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));
        UserAuthorization userAuthorization = new UserAuthorization(driver);
        userAuthorization.setUserDataAuthorization(userEmail, userPassword);
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));
    }

    @Test
    @DisplayName("logInAccount5") // имя теста
    @Description("Переходы по страницам") // описание теста
    public void logInAccount5() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://stellarburgers.nomoreparties.site/");
        driver.findElement(xpath(".//button[text() = 'Войти в аккаунт']")).click();
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));
        UserAuthorization userAuthorization = new UserAuthorization(driver);
        userAuthorization.setUserDataAuthorization(userEmail, userPassword);
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));

        System.out.println("Шаг1: Переход по клику на «Личный кабинет».");
        driver.findElement(xpath(".//p[text() = 'Личный Кабинет']")).click();
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/account/profile"));

        System.out.println("Шаг2: Переход по клику на «Конструктор»");
        driver.findElement(xpath(".//p[text() = 'Конструктор']")).click();
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));

        System.out.println("Шаг3: Переход обратно Переход в личный кабинет");
        driver.findElement(xpath(".//p[text() = 'Личный Кабинет']")).click();
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/account/profile"));

        System.out.println("Шаг4: Переход по клику логотип Stellar Burgers");
        driver.findElement(className("AppHeader_header__logo__2D0X2")).click();
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));

        System.out.println("Шаг5: Переход обратно Переход в личный кабинет");
        driver.findElement(xpath(".//p[text() = 'Личный Кабинет']")).click();
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/account/profile"));

        System.out.println("Шаг6: Переход выход по кнопке «Выйти» в личном кабинете.");
        WebElement exitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//button[text() = 'Выход']")));
        exitButton.click();
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));
    }


    @Test
    @DisplayName("designer") // имя теста
    @Description("Раздел «Конструктор» Переходы: Булки, Соусы, Начинки") // описание теста
    public void designer() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://stellarburgers.nomoreparties.site/");
        DesignerAssembleBurger designerAssembleBurger = new DesignerAssembleBurger(driver);

        designerAssembleBurger.setDesigner("Начинки");
        wait.until(ExpectedConditions.attributeToBe(By.xpath(".//section[1]/div[1]/div[3]"), "class", "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect"));

        designerAssembleBurger.setDesigner("Соусы");
        wait.until(ExpectedConditions.attributeToBe(By.xpath(".//section[1]/div[1]/div[2]"), "class", "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect"));

        designerAssembleBurger.setDesigner("Булки");
        wait.until(ExpectedConditions.attributeToBe(By.xpath(".//section[1]/div[1]/div[1]"), "class", "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect"));
    }

    @After
    public void tearDown() {
        System.out.println("----------------Постусловие----------------");
        driver.quit(); // Закрывает браузер
        new DeleteUserApi().deleteUser(userName, userEmail, userPassword); //Удаляет клиента
    }

}
