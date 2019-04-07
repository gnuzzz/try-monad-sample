package ru.albemuth.samples.trymonad;

import cyclops.control.Future;
import cyclops.control.Try;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * @author Vladimir Kornyshev {@literal <gnuzzz@mail.ru>}
 */
public class CyclopsServiceClient {
    //https://github.com/aol/cyclops

    private SyncServiceClient client = new SyncServiceClient();

    public Future<Integer> getValue1(int param) {
        return Try.withCatch(() -> client.getValue1(param)).to(Future::fromTry);
    }

    public Future<Long> getValue2(int param) {
        return Try.withCatch(() -> client.getValue2(param)).to(Future::fromTry);
    }

    public Future<Double> getValue3(int param) {
        return Try.withCatch(() -> client.getValue3(param)).to(Future::fromTry);
    }

    public Future<Integer> getValue4(int param) {
        return Try.withCatch(() -> client.getValue4(param)).to(Future::fromTry);
    }

    public Future<Long> getValue5(int param) {
        return Try.withCatch(() -> client.getValue5(param)).to(Future::fromTry);
    }

}
