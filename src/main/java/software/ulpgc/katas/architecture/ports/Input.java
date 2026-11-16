package software.ulpgc.katas.architecture.ports;

public interface Input {
    Parameter get(String name);

    interface Parameter {
        String asString();
        int asInteger();
    }
}