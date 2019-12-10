package sundukov.andrey.ships;

import java.util.concurrent.atomic.AtomicLong;

public class Ship {
    private final long index;
    private final Production production;
    private final int size;
    private int loaded;
    private final static AtomicLong indexer = new AtomicLong();

    public Ship(Production production, int size) {
        this.index = indexer.getAndIncrement();
        this.production = production;
        this.size = size;
        this.loaded = 0;
    }

    public Production getProduction() {
        return production;
    }

    @Override
    public String toString() {
        return "Ship{" + index + ",production=" + production + ",size=" + size + '}';
    }

    public void load(final int amount) {
        loaded += amount;
    }

    public boolean full() {
        return loaded >= size;
    }
}
