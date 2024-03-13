import Api.DeleteUserApi;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParameterizedUserPageRegistrationTest extends DriverProperties {
    int randomNumber = new Random().nextInt(1000000);
    String userName = "garri-" + randomNumber;
    String userEmail = "garri-" + randomNumber + "@yandex.ru";

    private final String userPasswordNotCorrect;
    private final int errorResult;

    public ParameterizedUserPageRegistrationTest(String userPasswordNotCorrect, int errorResult) {
        this.userPasswordNotCorrect = userPasswordNotCorrect;
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
    public void setUp() {
        driver = initDriver("yandex");
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @Test
    @DisplayName("RegistrationUserErrorPassword") // имя теста
    @Description("Регистрация клиента с некорректным паролем") // описание теста
    public void RegistrationUserErrorPassword() {
        UserPageRegistration userPageRegistration = new UserPageRegistration(driver);
        userPageRegistration.setUserData(userName, userEmail, userPasswordNotCorrect);
        assertEquals(errorResult, userPageRegistration.errorPassword());
    }

    @After
    public void tearDown() {
        System.out.println("----------------Постусловие----------------");
        driver.quit(); // Закрывает браузер
        new DeleteUserApi().deleteUser(userName, userEmail, userPasswordNotCorrect); //Удаляет клиента
    }
}
