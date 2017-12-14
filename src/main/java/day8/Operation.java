package day8;

public enum Operation {

    INCREASE,
    DECREASE;

    Operation fromString(String input) {
        switch (input) {
            case "inc":
                return INCREASE;
            case "dec":
                return DECREASE;
            default:
                throw new Error("VTP - unknown operation:" + input);
        }
    }
}
