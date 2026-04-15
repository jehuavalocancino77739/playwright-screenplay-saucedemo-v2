package edu.pe.cibertec.saucedemo.stepdefinitions;

import edu.pe.cibertec.saucedemo.tasks.AgregarAlCarrito;
import edu.pe.cibertec.saucedemo.tasks.EliminarDelCarrito;
import edu.pe.cibertec.saucedemo.ui.CarritoPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

public class CarritoStepDefinitions {

    // Envolvemos nuestros Strings de la UI en objetos Target oficiales de Serenity
    Target BADGE_TARGET = Target.the("badge del carrito").locatedBy(CarritoPage.BADGE_CARRITO);
    Target ITEMS_TARGET = Target.the("nombres de items").locatedBy(CarritoPage.ITEM_NOMBRE_CARRITO);
    Target ICONO_TARGET = Target.the("icono del carrito").locatedBy(CarritoPage.ICONO_CARRITO);

    @When("she adds the product {string} to the cart")
    public void sheAddsTheProductToTheCart(String producto) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                AgregarAlCarrito.elProducto(producto)
        );
    }

    @When("she removes the product {string} from the cart")
    public void sheRemovesTheProductFromTheCart(String producto) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                EliminarDelCarrito.elProducto(producto)
        );
    }

    @Then("the cart icon should display {string}")
    public void theCartIconShouldDisplay(String cantidad) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("El contador del carrito", Text.of(BADGE_TARGET), equalTo(cantidad))
        );
    }

    @Then("the cart should contain {string} and {string}")
    public void theCartShouldContainAnd(String producto1, String producto2) {
        OnStage.theActorInTheSpotlight().attemptsTo(Click.on(ICONO_TARGET));

        OnStage.theActorInTheSpotlight().should(
                seeThat("Los productos en el carrito", Text.ofEach(ITEMS_TARGET), hasItems(producto1, producto2))
        );
    }

    @Then("the cart should only contain {string}")
    public void theCartShouldOnlyContain(String producto) {
        OnStage.theActorInTheSpotlight().attemptsTo(Click.on(ICONO_TARGET));

        OnStage.theActorInTheSpotlight().should(
                seeThat("El producto en el carrito", Text.ofEach(ITEMS_TARGET), hasItem(producto)),
                seeThat("Cantidad de items", Text.ofEach(ITEMS_TARGET), hasSize(1))
        );
    }
}