package ru.albemuth.samples.trymonad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Optional;

/**
 * @author Vladimir Kornyshev {@literal <gnuzzz@mail.ru>}
 */
public class SyncEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncEndpoint.class);

    public Optional<Result> call(int param) throws Throwable {
        SyncServiceClient client = new SyncServiceClient();
        try {
            int value1 = client.getValue1(param);
            long value2 = client.getValue2(value1 + 1);
            double value3 = client.getValue3(param * 2);
            int value4 = client.getValue4((int) (value2 + 1));
            long value5 = client.getValue5((int) (value3 * 2));
            Result result = new Result()
                    .withValue1(value1)
                    .withValue2(value2)
                    .withValue3(value3)
                    .withValue4(value4)
                    .withValue5(value5);
            LOGGER.info("{}", result);
            return Optional.of(result);
        } catch (SocketTimeoutException e) {
            LOGGER.error("Socket timeout", e);
            throw e;
        } catch (IOException e) {
            LOGGER.error("IOException", e);
            throw e;
        }
    }

}
