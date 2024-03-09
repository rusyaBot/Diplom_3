package Api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteUserApi {
    private final String apiPathLogin = "https://stellarburgers.nomoreparties.site/api/auth/login";
    private final String apiPathUser = "https://stellarburgers.nomoreparties.site/api/auth/user";

    @Step("------Удаление клиента-----")
    public void deleteUser(String name, String email, String password) {
        User user = new User(name, email, password);
        Response response =
                given()
                        .header("Content-type", "application/json") // заполнил header
                        .body(user) // заполнить body
                        .when()
                        .post(apiPathLogin)// отправить запрос в ручку
                        .then().extract().response(); //вытащить ответ
        if (response.statusCode() == 200) {
            String token = response.jsonPath().getString("accessToken");
            System.out.println("Получен токен клиента: " + token);
            System.out.println("------Удаление клиента-----");
            Response response2 =
                    given()
                            .header("Authorization", token)
                            .when()
                            .delete(apiPathUser);// отправить запрос в ручку
            response.then().assertThat();
            System.out.println("Статус кода ответа: " + response.getStatusCode());
            System.out.println("Тело ответа: ");
            String[] lines = response2.getBody().asString().split(","); //перенос строки если встретит запятую
            for (String line : lines) {
                System.out.println(line);
            }
            System.out.println("Клиент удалён");
        } else System.out.println("Нет клиента для удаления");
    }
}