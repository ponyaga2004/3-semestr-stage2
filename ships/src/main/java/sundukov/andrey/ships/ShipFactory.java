package sundukov.andrey.ships;

import java.util.Random;

public class ShipFactory implements Runnable {
    private final Tunnel tunnel;
    private final Random random = new Random();

    public ShipFactory(final Tunnel tunnel) {
        this.tunnel = tunnel;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("ShipFactory");
        while (true) {
            final Production production = randomProduction();
            final int size = randomSize();
            final Ship ship = new Ship(production, size);
            tunnel.put(ship);
        }
    }

    private int randomSize() {
        switch (random.nextInt(3)) {
            case 0:
                return 10;
            case 1:
                return 50;
            case 2:
                return 100;
            default:
                throw new RuntimeException("Invalid random value for size fetching");
        }
    }

    private Production randomProduction() {
        return Production.values()[random.nextInt(Production.values().length)];
    }
}
