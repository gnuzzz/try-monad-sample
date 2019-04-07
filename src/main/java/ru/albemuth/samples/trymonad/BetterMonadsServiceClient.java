package ru.albemuth.samples.trymonad;

import com.jasongoodwin.monads.Try;

import java.util.concurrent.CompletableFuture;

/**
 * @author Vladimir Kornyshev {@literal <gnuzzz@mail.ru>}
 */
public class BetterMonadsServiceClient {
    //https://github.com/jasongoodwin/better-java-monads/

    private SyncServiceClient client = new SyncServiceClient();

    public CompletableFuture<Try<Integer>> getValue1(int param) {
        return CompletableFuture.supplyAsync(() -> Try.ofFailable(() -> client.getValue1(param)));
    }

    public CompletableFuture<Try<Long>> getValue2(int param) {
        return CompletableFuture.supplyAsync(() -> Try.ofFailable(() -> client.getValue2(param)));
    }

    public CompletableFuture<Try<Double>> getValue3(int param) {
        return CompletableFuture.supplyAsync(() -> Try.ofFailable(() -> client.getValue3(param)));
    }

    public CompletableFuture<Try<Integer>> getValue4(int param) {
        return CompletableFuture.supplyAsync(() -> Try.ofFailable(() -> client.getValue4(param)));
    }

    public CompletableFuture<Try<Long>> getValue5(int param) {
        return CompletableFuture.supplyAsync(() -> Try.ofFailable(() -> client.getValue5(param)));
    }

}
