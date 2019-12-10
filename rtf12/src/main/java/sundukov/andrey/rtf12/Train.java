package sundukov.andrey.rtf12;

import java.time.Duration;

public class Train extends Carrier {
    public Train() {
        super("train");
    }

    @Override
    public double price(Town town) {
        switch (town) {
            case MOSCOW:
                return 1400;
            case SAROV:
                return 4234;
            case SAMARA:
                return 5513;
            default:
                return -1;
        }
    }

    @Override
    public Duration duration(Town town) {
        switch (town) {
            case MOSCOW:
                return Duration.ofDays(1);
            case SAROV:
                return Duration.ofDays(3);
            case SAMARA:
                return Duration.ofDays(2);
            default:
                return Duration.ZERO;
        }
    }
}
