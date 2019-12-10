package sundukov.andrey.ships;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Tunnel {
    private final int limit;
    private final List<Ship> ships;

    public Tunnel(int limit) {
        this.limit = limit;
        this.ships = new ArrayList<>();
    }

    public synchronized void put(Ship ship) {
        while (ships.size() >= limit) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("put::wait was interrupted");
            }
        }
        ships.add(ship);
        log("Tunnel added ship %s%n", ship);
        notifyAll();
    }

    public synchronized Ship get(Production production) {
        Ship ship = lookup(production);
        while (ship == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("get::wait was interrupted");
            }
            ship = lookup(production);
        }
        log("Tunnel release ship %s%n", ship);
        ships.remove(ship);
        notifyAll();
        return ship;
    }

    private Ship lookup(final Production production) {
        Optional<Ship> any = ships.stream().filter(ship -> ship.getProduction() == production).findAny();
        return any.orElse(null);
    }

    private void log(final String format, final Object... args) {
        StringBuilder builder = new StringBuilder(Thread.currentThread().getName());
        builder.append(" ").append(LocalDateTime.now()).append(": ").append(format);
        System.out.printf(builder.toString(), args);
    }
}
