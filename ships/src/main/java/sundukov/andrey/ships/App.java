package sundukov.andrey.ships;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        final Tunnel tunnel = new Tunnel(5);

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.submit(new ShipFactory(tunnel));
        executorService.submit(new Loader(tunnel, Production.BANANA));
        executorService.submit(new Loader(tunnel, Production.BREAD));
        executorService.submit(new Loader(tunnel, Production.CLOTHES));

        executorService.shutdown();
    }
}
