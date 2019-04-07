package ru.albemuth.samples.trymonad;

import java.util.concurrent.CompletableFuture;

/**
 * @author Vladimir Kornyshev {@literal <gnuzzz@mail.ru>}
 */
public class CompletableFutureResultProvider {

    private CompletableFutureServiceClient client = new CompletableFutureServiceClient();

    public CompletableFuture<Result> getResult(int param) {
        CompletableFuture<Integer> f1 = client.getValue1(param);
        CompletableFuture<Long> f2 = f1.thenCompose(value1 -> client.getValue2(value1 + 1));
        CompletableFuture<Double> f3 = client.getValue3(param * 2);
        CompletableFuture<Integer> f4 = f2.thenCompose(value2 -> client.getValue4((int) (value2 + 1)));
        CompletableFuture<Long> f5 = f3.thenCompose(value3 -> client.getValue5((int) (value3 * 2)));
        return CompletableFuture.allOf(f1, f2, f3, f4, f5).thenApply(
                (Void) -> new Result()
                        .withValue1(f1.join())
                        .withValue2(f2.join())
                        .withValue3(f3.join())
                        .withValue4(f4.join())
                        .withValue5(f5.join())
        );
    }
}
