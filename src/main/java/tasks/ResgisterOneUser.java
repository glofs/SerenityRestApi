package tasks;

import io.restassured.http.ContentType;
import models.users.RegisterUserInfo;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class ResgisterOneUser implements Task {

    private RegisterUserInfo user;


    public ResgisterOneUser(RegisterUserInfo user) {
        this.user = user;
    }


    public static ResgisterOneUser with(RegisterUserInfo user) {

        return Tasks.instrumented(ResgisterOneUser.class,user);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(

                Post.to("/api/register").with(requestSpecification -> requestSpecification
                        .contentType(ContentType.JSON)
                        .body(user)
                        .log()
                        .all()
                        .then()
                        .log()
                        .all()
                        .request()
                )
        );

    }
}
