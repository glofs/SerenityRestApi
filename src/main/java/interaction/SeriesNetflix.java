package interaction;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.facts.Fact;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeriesNetflix implements Fact {
    private String infoPlans;
    public static final Logger LOGGER = Logger.getLogger(SeriesNetflix.class.getName());

    public static SeriesNetflix toView() {
        return new SeriesNetflix();

    }

    @Override
    public void setup(Actor actor) {
        actor.attemptsTo(
                Get.resource("/api/plans")
        );
        List<HashMap<String,String>>series= SerenityRest.lastResponse().jsonPath().getList("data");
        //o puedo crearla de serieList<series>

        actor.remember("plans", series);

        LOGGER.log(Level.INFO,series.toString());
        //para imprimir en el reporte
        infoPlans=series.toString();



    }
    @Override
    public String toString() {
        return
                "Los planes son  " + infoPlans;
    }


}
