package software.ulpgc.katas.architecture.domain;

import java.util.List;

public record FibonacciResponse(int requestedTerms, List<Integer> sequence) {
}