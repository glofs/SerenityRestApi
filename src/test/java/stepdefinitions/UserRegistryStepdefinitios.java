package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.users.RegisterUserInfo;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import questions.ResponseCode;
import tasks.ResgisterOneUser;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class UserRegistryStepdefinitios {
    public static final String REST_API_URL = "https://reqres.in";
   private static Actor julian;


    @Given("Gustavo open the bank registry application")
    public void gustavoOpenTheBankRegistryApplication() {
        julian = Actor.named("Gustavo").whoCan(CallAnApi.at(REST_API_URL));

    }
    @When("he send your data for sing up")
    public void heSendYourDataForSingUp() {
        RegisterUserInfo registerUserInfo = new RegisterUserInfo();
        registerUserInfo.setEmail("eve.holt@reqres.in");
        registerUserInfo.setPassword("pistol");
        julian.attemptsTo(
                ResgisterOneUser.with(registerUserInfo)
        );
    }

    @Then("he see the message successfully sing up")
    public void heSeeTheMessageSuccessfullySingUp() {
            julian.should(seeThat("Respose Code", ResponseCode.was(), equalTo(200)));
        }

}
