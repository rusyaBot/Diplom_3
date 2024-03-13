
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserPageRegistration {
    // подключаем веб драйвер
    WebDriver driver;
    public UserPageRegistration(WebDriver driver) {
        this.driver = driver;
    }
    //Локаторы
    private final By userNameField = By.xpath(".//fieldset[1]/div/div/input[@name='name']"); // Имя
    private final By userEmailField = By.xpath(".//fieldset[2]/div/div/input[@name='name']"); // Email
    private final By userPasswordField = By.xpath(".//fieldset[3]/div/div/input[@name='Пароль']"); // Пароль
    private final By buttonRegistration = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text() = 'Зарегистрироваться']");
    @Step ("Регистрация")
    public void setUserData(String userName, String userEmail, String userPassword) {
        driver.findElement(userNameField).sendKeys(userName);           // Вводим имя
        driver.findElement(userEmailField).sendKeys(userEmail);         // Вводим Email
        driver.findElement(userPasswordField).sendKeys(userPassword);   // Вводим пароль
        driver.findElement(buttonRegistration).click();                 // Нажать на кнопку Зарегистрироваться
    }

    @Step ("Проверка ошибок")
    public int errorPassword(){
        WebElement errorMessage = null;
        try {
            errorMessage = driver.findElement(By.xpath("//p[contains(text(), 'Некорректный пароль')]"));
        } catch (NoSuchElementException e) {
            System.out.println("Надпись 'Некорректный пароль' не отображается при вводе неверного пароля");
        }
        if (errorMessage != null) {
            System.out.println("Надпись 'Некорректный пароль' отображается при вводе неверного пароля");
            return 1;
        }
        return 0;
    }

}
