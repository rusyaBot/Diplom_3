import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserAuthorization {

    WebDriver driver;
    public UserAuthorization(WebDriver driver) {
        this.driver = driver;
    }

    private final By userEmailField = By.xpath(".//input[@name='name']"); // Email
    private final By userPasswordField = By.xpath(".//input[@name='Пароль']"); // Пароль
    private final By buttonRegistration = By.xpath(".//button[text() = 'Войти']");
    @Step("Авторизация")
    public void setUserDataAuthorization( String userEmail, String userPassword) {
        driver.findElement(userEmailField).sendKeys(userEmail);         // Вводим Email
        driver.findElement(userPasswordField).sendKeys(userPassword);   // Вводим пароль
        driver.findElement(buttonRegistration).click();                 // Нажать на кнопку Зарегистрироваться
    }
}
