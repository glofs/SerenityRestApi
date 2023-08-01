package tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class GetAllUsers implements Task {


    private int pageNumber;

    public GetAllUsers(int pageNumber) {
        this.pageNumber = pageNumber;
    }


    public static GetAllUsers fromPage(int pageNumber){
        return Tasks.instrumented(GetAllUsers.class,pageNumber);

    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/api/users?page="+ pageNumber).with(requestSpecification -> requestSpecification
                        .contentType(ContentType.JSON)
                        .header("header1", "value1"))

        );
    }
}
