package software.ulpgc.katas.infrastructure.server;

import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import software.ulpgc.katas.architecture.ports.Input;
import software.ulpgc.katas.architecture.ports.Output;
import software.ulpgc.katas.architecture.usecases.CalculateFibonacciSeriesUseCase;
import software.ulpgc.katas.infrastructure.adapters.ContextInput;
import software.ulpgc.katas.infrastructure.adapters.GsonSeriesSerializer;

public class FibonacciServer {
    private final int port;
    private Javalin app;

    public FibonacciServer(int port) {
        this.port = port;
    }

    public void start() {
        this.app = Javalin.create(this::init).start(port);
    }

    public void stop() {
        if (this.app != null) {
            this.app.stop();
        }
    }

    private void init(JavalinConfig config) {
        config.routes.get("/fibonacci", this::fibonacci);
    }

    private void fibonacci(Context context) {
        Input input = new ContextInput(context);
        Output output = new CalculateFibonacciSeriesUseCase(new GsonSeriesSerializer()).execute(input);
        context.result(output.result());
    }
}