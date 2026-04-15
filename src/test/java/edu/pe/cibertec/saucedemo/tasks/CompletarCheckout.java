package edu.pe.cibertec.saucedemo.tasks;

import edu.pe.cibertec.saucedemo.ui.CarritoPage;
import edu.pe.cibertec.saucedemo.ui.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class CompletarCheckout implements Task {
    private final String nombre, apellido, postal;

    public CompletarCheckout(String nombre, String apellido, String postal) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.postal = postal;
    }

    public static CompletarCheckout conDatos(String nombre, String apellido, String postal) {
        return Tasks.instrumented(CompletarCheckout.class, nombre, apellido, postal);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(CarritoPage.ICONO_CARRITO),
                Click.on(CheckoutPage.BTN_CHECKOUT),
                Enter.theValue(nombre).into(CheckoutPage.INPUT_NOMBRE),
                Enter.theValue(apellido).into(CheckoutPage.INPUT_APELLIDO),
                Enter.theValue(postal).into(CheckoutPage.INPUT_POSTAL),
                WaitUntil.the(CheckoutPage.BTN_CONTINUAR, isClickable()).forNoMoreThan(10).seconds(),
                Click.on(CheckoutPage.BTN_CONTINUAR)
        );
    }
}