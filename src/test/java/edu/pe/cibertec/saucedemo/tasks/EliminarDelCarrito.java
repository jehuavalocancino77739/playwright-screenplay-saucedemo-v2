package edu.pe.cibertec.saucedemo.tasks;

import edu.pe.cibertec.saucedemo.ui.CarritoPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class EliminarDelCarrito implements Task {
    private final String producto;

    public EliminarDelCarrito(String producto) {
        this.producto = producto;
    }

    public static EliminarDelCarrito elProducto(String producto) {
        return Tasks.instrumented(EliminarDelCarrito.class, producto);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String productoFormateado = producto.toLowerCase().replace(" ", "-");
        String selector = String.format(CarritoPage.BTN_QUITAR_CARRITO, productoFormateado);

        actor.attemptsTo(Click.on(selector));
    }
}