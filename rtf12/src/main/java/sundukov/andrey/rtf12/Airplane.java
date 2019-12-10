package sundukov.andrey.rtf12;

import java.time.Duration;

public class Airplane extends Carrier {
    public Airplane() {
        super("airplane");
    }

    @Override
    public double price(Town town) {
        switch (town) {
            case MOSCOW:
                return 46734;
            case SAROV:
                return 48476;
            case SAMARA:
                return 34343;
            default:
                return -1;
        }
    }

    @Override
    public Duration duration(Town town) {
        switch (town) {
            case MOSCOW:
                return Duration.ofHours(4);
            case SAROV:
                return Duration.ofHours(20);
            case SAMARA:
                return Duration.ofHours(17);
            default:
                return Duration.ZERO;
        }
    }
}
