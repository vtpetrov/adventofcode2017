package day8;

public class Register {

    private String name;
    private int value;

    Register(String nameParam) {
        this.name = nameParam;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "name= '" + name + '\'' + ", value= " + value + "}\n";
    }
}
