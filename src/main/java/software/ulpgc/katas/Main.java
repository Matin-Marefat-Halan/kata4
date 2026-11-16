package software.ulpgc.katas;

import software.ulpgc.katas.architecture.ports.FibonacciClient;
import software.ulpgc.katas.infrastructure.adapters.HttpFibonacciClient;
import software.ulpgc.katas.infrastructure.server.FibonacciServer;

public class Main {
    private static final String BASE_URL = "http://localhost:8080";

    public static void main(String[] args) {
        FibonacciServer server = new FibonacciServer(8080);
        server.start();

        FibonacciClient client = new HttpFibonacciClient(BASE_URL);

        client.fetch(10).ifPresentOrElse(
                response -> System.out.println("Respuesta recibida (" + response.requestedTerms() + " terminos): " + response.sequence()),
                () -> System.out.println("Error: No se pudo obtener respuesta valida del servicio HTTP.")
        );

        server.stop();
    }
}