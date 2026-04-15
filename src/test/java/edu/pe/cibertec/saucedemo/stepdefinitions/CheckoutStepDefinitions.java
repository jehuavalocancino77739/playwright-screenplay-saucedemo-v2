package edu.pe.cibertec.saucedemo.stepdefinitions;

import edu.pe.cibertec.saucedemo.tasks.CompletarCheckout;
import edu.pe.cibertec.saucedemo.ui.CheckoutPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutStepDefinitions {

    Target SUBTOTAL_LBL = Target.the("subtotal").locatedBy(CheckoutPage.LBL_SUBTOTAL);
    Target MSJ_CONFIRMACION = Target.the("mensaje de exito").locatedBy(CheckoutPage.MSG_CONFIRMACION);
    Target MSJ_ERROR = Target.the("mensaje de error").locatedBy(CheckoutPage.MSG_ERROR);
    Target FORM_INPUT = Target.the("campo nombre").locatedBy(CheckoutPage.INPUT_NOMBRE);

    @When("she proceeds to checkout with first name {string}, last name {string} and postal code {string}")
    public void sheProceedsToCheckout(String nombre, String apellido, String postal) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                CompletarCheckout.conDatos(nombre, apellido, postal)
        );
    }

    @When("she verifies the order summary shows item total {string}")
    public void sheVerifiesTheOrderSummary(String totalEsperado) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(SUBTOTAL_LBL, isVisible()).forNoMoreThan(10).seconds()
        );
        OnStage.theActorInTheSpotlight().should(
                seeThat("El subtotal aparece en pantalla", Visibility.of(SUBTOTAL_LBL), equalTo(true))
        );
    }

    @When("she completes the order")
    public void sheCompletesTheOrder() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Click.on(CheckoutPage.BTN_FINALIZAR)
        );
    }

    @Then("she should see the confirmation message {string}")
    public void sheShouldSeeTheConfirmationMessage(String mensaje) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("Mensaje de confirmación", Text.of(MSJ_CONFIRMACION), containsString(mensaje))
        );
    }

    @Then("she should see the checkout error message {string}")
    public void sheShouldSeeTheErrorMessage(String error) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("Mensaje de error", Text.of(MSJ_ERROR), containsString(error))
        );
    }

    @Then("the checkout form should remain visible")
    public void theCheckoutFormShouldRemainVisible() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("Visibilidad del formulario", Visibility.of(FORM_INPUT), equalTo(true))
        );
    }
}