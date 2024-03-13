import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

public class DesignerAssembleBurger {
    WebDriver driver;

    public DesignerAssembleBurger(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Конструктор")
    public void setDesigner(String designer) {
        if (designer == "Начинки") {
            System.out.println("Переход в Начинки");
            driver.findElement(xpath(".//span[text() = 'Начинки']")).click();
        } else if (designer == "Соусы") {
            System.out.println("Переход в Соусы");
            driver.findElement(xpath(".//span[text() = 'Соусы']")).click();
        } else if (designer == "Булки") {
            System.out.println("Переход в Булки");
            driver.findElement(xpath(".//span[text() = 'Булки']")).click();
        } System.out.println("не верный конструктор");

    }

}
