package software.ulpgc.katas.infrastructure.adapters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import software.ulpgc.katas.architecture.domain.FibonacciResponse;
import software.ulpgc.katas.architecture.ports.FibonacciClient;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

public class HttpFibonacciClient implements FibonacciClient {
    private final String baseUrl;
    private final HttpClient httpClient;
    private final Gson gson;

    public HttpFibonacciClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    @Override
    public Optional<FibonacciResponse> fetch(int n) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/fibonacci?n=" + n))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200 || response.body() == null || response.body().isBlank()) {
                return Optional.empty();
            }

            Type listType = new TypeToken<List<Integer>>() {}.getType();
            List<Integer> sequence = gson.fromJson(response.body(), listType);

            return Optional.of(new FibonacciResponse(n, sequence));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}