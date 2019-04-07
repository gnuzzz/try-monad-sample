package ru.albemuth.samples.trymonad;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * @author Vladimir Kornyshev {@literal <gnuzzz@mail.ru>}
 */
public class CompletableFutureServiceClient {

    private SyncServiceClient client = new SyncServiceClient();

    public CompletableFuture<Integer> getValue1(int param) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return client.getValue1(param);
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        });
    }

    public CompletableFuture<Long> getValue2(int param) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return client.getValue2(param);
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        });
    }

    public CompletableFuture<Double> getValue3(int param) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return client.getValue3(param);
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        });
    }

    public CompletableFuture<Integer> getValue4(int param) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return client.getValue4(param);
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        });
    }

    public CompletableFuture<Long> getValue5(int param) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return client.getValue5(param);
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        });
    }

}
