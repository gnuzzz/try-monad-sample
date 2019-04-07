package ru.albemuth.samples.trymonad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static ru.albemuth.samples.trymonad.CompletableFutureUtils.value;

/**
 * @author Vladimir Kornyshev {@literal <gnuzzz@mail.ru>}
 */
public class CompletableFutureEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompletableFutureEndpoint.class);

    public Optional<Result> call1(int param) throws Throwable {
        CompletableFutureServiceClient client = new CompletableFutureServiceClient();
        CompletableFuture<Integer> f1 = client.getValue1(param);
        CompletableFuture<Long> f2 = f1.thenCompose(value1 -> client.getValue2(value1 + 1));
        CompletableFuture<Double> f3 = client.getValue3(param * 2);
        CompletableFuture<Integer> f4 = f2.thenCompose(value2 -> client.getValue4((int) (value2 + 1)));
        CompletableFuture<Long> f5 = f3.thenCompose(value3 -> client.getValue5((int) (value3 * 2)));

        try {
            Result result = new Result()
                    .withValue1(value(f1))
                    .withValue2(value(f2))
                    .withValue3(value(f3))
                    .withValue4(value(f4))
                    .withValue5(value(f5));
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

    public Optional<Result> call2(int param) throws Throwable {
        CompletableFutureResultProvider provider = new CompletableFutureResultProvider();
        try {
            Result result = value(provider.getResult(param));
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

}
