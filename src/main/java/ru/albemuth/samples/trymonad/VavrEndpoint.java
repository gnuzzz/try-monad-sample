package ru.albemuth.samples.trymonad;

import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;

/**
 * @author Vladimir Kornyshev {@literal <gnuzzz@mail.ru>}
 */
public class VavrEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(VavrEndpoint.class);

    public Optional<Result> call1(int param) throws Throwable {
        VavrServiceClient client = new VavrServiceClient();
        CompletableFuture<Try<Integer>> f1 = client.getValue1(param);
        CompletableFuture<Try<Long>> f2 = f1.thenCompose(value1 -> map(value1, v -> client.getValue2(v + 1)));
        CompletableFuture<Try<Double>> f3 = client.getValue3(param * 2);
        CompletableFuture<Try<Integer>> f4 = f2.thenCompose(value2 -> map(value2, v -> client.getValue4((int) (v + 1))));
        CompletableFuture<Try<Long>> f5 = f3.thenCompose(value3 -> map(value3, v -> client.getValue5((int) (v * 2))));

        Result result = new Result()
                .withValue1(f1.join().recover(x -> Match(x).of(
                        Case($(instanceOf(SocketTimeoutException.class)), this::processSocketTimeoutException),
                        Case($(instanceOf(IOException.class)), this::processIOException)
                )).get())
                .withValue2(f2.join().recover(x -> Match(x).of(
                        Case($(instanceOf(SocketTimeoutException.class)), this::processSocketTimeoutException),
                        Case($(instanceOf(IOException.class)), this::processIOException)
                )).get())
                .withValue3(f3.join().recover(x -> Match(x).of(
                        Case($(instanceOf(SocketTimeoutException.class)), this::processSocketTimeoutException),
                        Case($(instanceOf(IOException.class)), this::processIOException)
                )).get())
                .withValue4(f4.join().recover(x -> Match(x).of(
                        Case($(instanceOf(SocketTimeoutException.class)), this::processSocketTimeoutException),
                        Case($(instanceOf(IOException.class)), this::processIOException)
                )).get())
                .withValue5(f5.join().recover(x -> Match(x).of(
                        Case($(instanceOf(SocketTimeoutException.class)), this::processSocketTimeoutException),
                        Case($(instanceOf(IOException.class)), this::processIOException)
                )).get());
        LOGGER.info("{}", result);
        return Optional.of(result);
    }

    public Optional<Result> call2(int param) throws Throwable {
        VavrServiceClient client = new VavrServiceClient();
        CompletableFuture<Try<Integer>> f1 = client.getValue1(param);
        CompletableFuture<Try<Long>> f2 = f1.thenCompose(value1 -> map(value1, v -> client.getValue2(v + 1)));
        CompletableFuture<Try<Double>> f3 = client.getValue3(param * 2);
        CompletableFuture<Try<Integer>> f4 = f2.thenCompose(value2 -> map(value2, v -> client.getValue4((int) (v + 1))));
        CompletableFuture<Try<Long>> f5 = f3.thenCompose(value3 -> map(value3, v -> client.getValue5((int) (v * 2))));

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

    private <T> T value(CompletableFuture<Try<T>> future) throws Throwable {
        return future.join().get();
    }

    private <T> T processSocketTimeoutException(SocketTimeoutException e) {
        LOGGER.error("Socket timeout", e);
        Try.failure(e).get();
        return null;
    }

    private <T> T processIOException(IOException e) {
        LOGGER.error("IOException", e);
        Try.failure(e).get();
        return null;
    }

    private <FROM, TO> CompletableFuture<Try<TO>> map(Try<FROM> t, Function<FROM, CompletableFuture<Try<TO>>> mapFunction) {
        CompletableFuture<Try<TO>> future;
        if (t.isSuccess()) {
            future = mapFunction.apply(t.get());
        } else {
            future = CompletableFuture.completedFuture(Try.failure(t.failed().get()));
        }
        return future;
    }

}
