package software.ulpgc.katas.architecture.usecases;

import software.ulpgc.katas.architecture.ports.Input;
import software.ulpgc.katas.architecture.ports.Output;

public interface UseCase {
    Output execute(Input input);
}