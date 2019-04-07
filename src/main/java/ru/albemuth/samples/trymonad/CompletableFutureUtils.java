package ru.albemuth.samples.trymonad;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * @author Vladimir Kornyshev {@literal <gnuzzz@mail.ru>}
 */
public final class CompletableFutureUtils {

    private CompletableFutureUtils() {
    }

    public static <T> T value(CompletableFuture<T> future) throws Throwable {
        try {
            return future.join();
        } catch (CompletionException e) {
            throw e.getCause();
        }
    }

}
