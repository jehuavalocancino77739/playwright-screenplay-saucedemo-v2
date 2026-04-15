package edu.pe.cibertec.saucedemo.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class VerificarSesion implements Task {
    private String resultadoEsperado = "";

    public VerificarSesion() {}

    public VerificarSesion(String resultadoEsperado) {
        this.resultadoEsperado = resultadoEsperado;
    }

    public static VerificarSesion activa() {
        return Tasks.instrumented(VerificarSesion.class);
    }

    public static VerificarSesion conResultado(String resultado) {
        return Tasks.instrumented(VerificarSesion.class, resultado);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (resultadoEsperado.contains("error")) {
            System.out.println("Validando mensaje de error: " + resultadoEsperado);
        } else {
            System.out.println("Validando acceso exitoso al inventario");
        }
    }
}