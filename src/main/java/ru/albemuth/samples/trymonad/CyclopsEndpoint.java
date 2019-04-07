package ru.albemuth.samples.trymonad;

import cyclops.control.Future;
import cyclops.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * @author Vladimir Kornyshev {@literal <gnuzzz@mail.ru>}
 */
public class CyclopsEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(CyclopsEndpoint.class);

    public Optional<Result> call1(int param) throws Throwable {
        CyclopsServiceClient client = new CyclopsServiceClient();
        Future<Integer> f1 = client.getValue1(param);
        Future<Long> f2 = f1.flatMap(value1 -> client.getValue2(value1 + 1));
        Future<Double> f3 = client.getValue3(param * 2);
        Future<Integer> f4 = f2.flatMap(value2 -> client.getValue4((int) (value2 + 1)));
        Future<Long> f5 = f3.flatMap(value3 -> client.getValue5((int) (value3 * 2)));

        try {
            Result result = f1.getFuture().thenCompose(
                    value1 -> f2.getFuture().thenCompose(
                            value2 -> f3.getFuture().thenCompose(
                                    value3 -> f4.getFuture().thenCompose(
                                            value4 -> f5.getFuture().thenCompose(
                                                    value5 -> CompletableFuture.completedFuture(
                                                            new Result()
                                                                    .withValue1(value1)
                                                                    .withValue2(value2)
                                                                    .withValue3(value3)
                                                                    .withValue4(value4)
                                                                    .withValue5(value5))))))).join();
            return Optional.of(result);
        } catch (CompletionException ce) {
            try {
                throw ce.getCause();
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

    public Optional<Result> call2(int param) throws Throwable {
        CyclopsServiceClient client = new CyclopsServiceClient();
        Future<Integer> f1 = client.getValue1(param);
        Future<Long> f2 = f1.flatMap(value1 -> client.getValue2(value1 + 1));
        Future<Double> f3 = client.getValue3(param * 2);
        Future<Integer> f4 = f2.flatMap(value2 -> client.getValue4((int) (value2 + 1)));
        Future<Long> f5 = f3.flatMap(value3 -> client.getValue5((int) (value3 * 2)));

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

    private <T> T value(Future<T> future) throws Throwable {
        Try<T, ? extends Throwable> t = future.get();
        if (t.isSuccess()) {
            return t.get().toOptional().get();
        } else {
            throw t.failureGet().toOptional().get();
        }
    }

}
