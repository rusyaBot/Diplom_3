import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverProperties {
    protected WebDriver driver;

    private static WebDriver yandexDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
        WebDriver yandexDriver = new ChromeDriver();
        return yandexDriver;
    }

    private static WebDriver chromeDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver chromeDriver = new ChromeDriver();
        return chromeDriver;
    }

    public WebDriver initDriver(String browserType) {
        switch (browserType) {
            case "chrome":
                this.driver = chromeDriver();
                return driver;
            case "yandex":
                this.driver = yandexDriver();
                return driver;
            default:
                throw new IllegalArgumentException("не верный WebDriver");
        }

    }
}