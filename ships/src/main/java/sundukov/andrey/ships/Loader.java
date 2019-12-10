package sundukov.andrey.ships;

import java.time.LocalDateTime;

public class Loader implements Runnable {
    private final Tunnel tunnel;
    private final Production production;
    private Ship ship;

    public Loader(Tunnel tunnel, Production production) {
        this.tunnel = tunnel;
        this.production = production;
        this.ship = null;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Loader{" + production + "}");
        while (true) {
            if (ship == null) {
                ship = tunnel.get(production);
                log("starts loading %s%n", ship);
            }
            ship.load(10);
            log("loading %s%n", ship);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("sleep was interrupted");
            }
            if (ship.full()) {
                log("releases %s%n", ship);
                ship = null;
            }
        }
    }

    private void log(final String format, final Object... args) {
        System.out.printf(Thread.currentThread().getName() + " " + LocalDateTime.now() + ": " + format, args);
    }
}
