import interaction.SeriesNetflix;
import models.users.Datum;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import questions.GetUsersQuestion;
import questions.ResponseCode;
import tasks.GetAllUsers;

import java.util.logging.Level;
import java.util.logging.Logger;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SerenityRunner.class)
public class SerenityInitialTest {

    public static final String REST_API_URL = "https://reqres.in";
    private static final Logger LOGGER = Logger.getLogger(SerenityInitialTest.class.getName());


    @Test
    public void getUseres() {

        Actor julian = Actor.named("Gustavo").whoCan(CallAnApi.at(REST_API_URL));
        julian.attemptsTo(
                GetAllUsers.fromPage(1)
        );

        julian.should(seeThat("Respose Code", ResponseCode.was(), equalTo(200)));

        Datum user = new GetUsersQuestion().answeredBy(julian).getData().stream().filter(x -> x.getId() == 1).findFirst().orElse(null);
        julian.should(
                seeThat("Get user actual", actor -> user, notNullValue())
        );

        julian.should(seeThat("Email user", act -> user.getEmail(), equalTo("george.bluth@reqres.in")),
                seeThat("Email user", act -> user.getAvatar(), equalTo("https://reqres.in/img/faces/1-image.jpg")));

        LOGGER.log(Level.INFO, new GetUsersQuestion().answeredBy(julian).getData().get(0).getLastName());
    }

    @Test
    public void userFact() {

        Actor julian = Actor.named("Gustavo").whoCan(CallAnApi.at(REST_API_URL));
        julian.has(SeriesNetflix.toView());

    }


    /**
     @Test public void getUseresFail(){

     Actor julian= Actor.named("Gustavo").whoCan(CallAnApi.at(REST_API_URL));
     julian.attemptsTo(Get.resource("users?page=2"));

     assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(500);
     }
     **/


}
