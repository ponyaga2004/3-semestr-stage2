package sundukov.andrey.rtf12;

import java.time.Duration;

public class Truck extends Carrier {
    public Truck() {
        super("truck");
    }


    @Override
    public double price(Town town) {
        switch (town) {
            case MOSCOW:
                return 1000;
            case SAROV:
                return 8888;
            case SAMARA:
                return 6382;
            default:
                return -1;
        }
    }

    @Override
    public Duration duration(Town town) {
        switch (town) {
            case MOSCOW:
                return Duration.ofDays(4);
            case SAROV:
                return Duration.ofDays(10);
            case SAMARA:
                return Duration.ofDays(7);
            default:
                return Duration.ZERO;
        }
    }
}
