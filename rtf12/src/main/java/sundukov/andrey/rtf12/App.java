package sundukov.andrey.rtf12;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        final List<Carrier> carriers = new ArrayList<>();
        carriers.add(new Airplane());
        carriers.add(new Train());
        carriers.add(new Truck());

        for (Carrier carrier : carriers) {
            System.out.printf("Information for %s%n", carrier.getName());
            for (Town town : Town.values()) {
                final Duration duration = carrier.duration(town);
                final double price = carrier.price(town);
                System.out.printf("\tto %s duration %s hours with price %s%n", town, duration.toHours(), price);
            }
        }

    }
}
