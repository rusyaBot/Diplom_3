import Api.DeleteUserApi;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.After;
import java.time.Duration;
import java.util.Random;

public class UserPageRegistrationTest {
    int randomNumber = new Random().nextInt(1000000);
    String userName ="garri-" + randomNumber;
    String userEmail ="garri-" + randomNumber + "@yandex.ru";
    String userPassword ="123456";


    WebDriver driver;
    @Before
    public void setUp() { //Используем менеджер для простой и удобной подготовки драйверов
        WebDriverManager.chromedriver().setup(); //Драйвер для chrome
        driver = new ChromeDriver();
//        WebDriverManager.yandexdriver().setup(); //Драйвер для Яндекс.Браузера
//        driver = new InternetExplorerDriver();
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @Test
    @DisplayName("RegistrationUser") // имя теста
    @Description("Регистрация клиента") // описание теста
    public void RegistrationUser() {
        UserPageRegistration userPageRegistration = new UserPageRegistration(driver);
        userPageRegistration.setUserData(userName,userEmail, userPassword);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));
    }


    @After
    public void tearDown() {
        System.out.println("----------------Постусловие----------------");
        driver.quit(); // Закрывает браузер
        new DeleteUserApi().deleteUser(userName, userEmail, userPassword); //Удаляет клиента
    }
}
