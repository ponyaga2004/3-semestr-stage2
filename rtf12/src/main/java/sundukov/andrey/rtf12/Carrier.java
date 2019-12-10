package sundukov.andrey.rtf12;

import java.time.Duration;

abstract public class Carrier {
    Carrier(String name) {
        System.out.println("construct carrier " + name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract public double price(Town town);

    abstract public Duration duration(Town town);

    private String name;
}
