# Дипломный проект

## Diplom_3

### Установка
1. Java 11
2. JUnit 4.13.2
3. rest-assured 5.3.1
4. allure-junit4 2.15.0
5. selenium-java 4.18.1
6. webdrivermanager 5.6.2
7. maven-surefire-plugin 2.22.2

### Запуск автотестов и отчета Allure
1. Запусти тесты командой (mvn clean test) в консоли.
2. В папке target появится подпапка allure-results с отчётом Allure.
3. Перейди в папку проекта и выполни команду (mvn allure:serve).
4. Запустится веб-сервер Allure, и в браузере откроется вкладка с отчётом:

### Подключение драйверов браузера
Для подключения Яндекс браузера:
1. Качаем Яндекс Вебдрайвер здесь: https://github.com/yandex/YandexDriver/releases/tag/v24.1.0-stable
2. Закидывам его в папку ресурсов проекта: "src/main/resources/yandexdriver.exe"
3. Затем написать кусок кода:  
   System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");   
   driver = new ChromeDriver();

Для подключения Google:
1. Написать кусок кода:
   WebDriverManager.chromedriver().setup(); //Драйвер для chrome
   driver = new ChromeDriver();


