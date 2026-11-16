package software.ulpgc.katas.architecture.ports;

import software.ulpgc.katas.architecture.domain.FibonacciResponse;
import java.util.Optional;

public interface FibonacciClient {
    Optional<FibonacciResponse> fetch(int n);
}