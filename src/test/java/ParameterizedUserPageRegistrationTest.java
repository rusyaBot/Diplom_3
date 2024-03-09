import Api.DeleteUserApi;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParameterizedUserPageRegistrationTest {
    int randomNumber = new Random().nextInt(1000000);
    String userName = "garri-" + randomNumber;
    String userEmail = "garri-" + randomNumber + "@yandex.ru";

    private final String userPasswordNotcorrect;
    private final int errorResult;

    public ParameterizedUserPageRegistrationTest(String userPasswordNotcorrect, int errorResult) {
        this.userPasswordNotcorrect = userPasswordNotcorrect;
        this.errorResult = errorResult;
    }

    @Parameterized.Parameters
    public static Object[][] getUserData() {
        //Тестовые данные
        return new Object[][]{
                {"12345", 1},
                {"1", 1},
                {"123456", 0},
        };
    }

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
    @DisplayName("RegistrationUserErrorPassword") // имя теста
    @Description("Регистрация клиента с некорректным паролем") // описание теста
    public void RegistrationUserErrorPassword() {
        UserPageRegistration userPageRegistration = new UserPageRegistration(driver);
        userPageRegistration.setUserData(userName, userEmail, userPasswordNotcorrect);
        assertEquals(errorResult, userPageRegistration.errorPassword());
    }

    @After
    public void tearDown() {
        System.out.println("----------------Постусловие----------------");
        driver.quit(); // Закрывает браузер
        new DeleteUserApi().deleteUser(userName, userEmail, userPasswordNotcorrect); //Удаляет клиента
    }
}
