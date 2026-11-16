package software.ulpgc.katas;

import software.ulpgc.katas.architecture.ports.FibonacciClient;
import software.ulpgc.katas.infrastructure.adapters.HttpFibonacciClient;
import software.ulpgc.katas.infrastructure.server.FibonacciServer;

public class Main {
    private static final String BASE_URL = "http://localhost:8080";
    private static final String INVALID_URL = "http://localhost:9999";

    public static void main(String[] args) {
        FibonacciServer server = new FibonacciServer(8080);
        server.start();

        FibonacciClient validClient = new HttpFibonacciClient(BASE_URL);
        validClient.fetch(15).ifPresentOrElse(
                response -> System.out.println("Consulta correcta (" + response.requestedTerms() + " terminos): " + response.sequence()),
                () -> System.out.println("Error inesperado en consulta valida.")
        );

        FibonacciClient unavailableClient = new HttpFibonacciClient(INVALID_URL);
        unavailableClient.fetch(5).ifPresentOrElse(
                response -> System.out.println("Respuesta recibida: " + response.sequence()),
                () -> System.out.println("Error controlado: Servicio no disponible en " + INVALID_URL)
        );

        server.stop();
    }
}