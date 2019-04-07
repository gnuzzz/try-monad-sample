package ru.albemuth.samples.trymonad;

import com.jasongoodwin.monads.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * @author Vladimir Kornyshev {@literal <gnuzzz@mail.ru>}
 */
public class BetterMonadsEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(BetterMonadsEndpoint.class);

    public Optional<Result> call(int param) throws Throwable {
        BetterMonadsServiceClient client = new BetterMonadsServiceClient();
        CompletableFuture<Try<Integer>> f1 = client.getValue1(param);
        CompletableFuture<Try<Long>> f2 = f1.thenCompose(value1 -> map(value1, v -> client.getValue2(v + 1)));
        CompletableFuture<Try<Double>> f3 = client.getValue3(param * 2);
        CompletableFuture<Try<Integer>> f4 = f2.thenCompose(value2 -> map(value2, v -> client.getValue4((int) (v + 1))));
        CompletableFuture<Try<Long>> f5 = f3.thenCompose(value3 -> map(value3, v -> client.getValue5((int) (v * 2))));

        try {
            Result result = new Result()
                    .withValue1(f1.join().get())
                    .withValue2(f2.join().get())
                    .withValue3(f3.join().get())
                    .withValue4(f4.join().get())
                    .withValue5(f5.join().get());
            LOGGER.info("{}", result);
            return Optional.of(result);
        } catch (SocketTimeoutException e) {
            LOGGER.error("Socket timeout", e);
            throw e;
        } catch (IOException e) {
            LOGGER.error("IOException", e);
            throw e;
        } catch (Throwable e) {
            LOGGER.error("Unexpected throwable", e);
            throw e;
        }
    }

    private <FROM, TO> CompletableFuture<Try<TO>> map(Try<FROM> t, Function<FROM, CompletableFuture<Try<TO>>> mapFunction) {
        CompletableFuture<Try<TO>> future;
        if (t.isSuccess()) {
            future = mapFunction.apply(t.getUnchecked());
        } else {
            future = CompletableFuture.completedFuture(Try.failure(throwable(t)));
        }
        return future;
    }

    private Throwable throwable(Try<?> t) {
        try {
            t.get();
            return null;
        } catch (Throwable e) {
            return e;
        }
    }

}
