package ru.albemuth.samples.trymonad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * @author Vladimir Kornyshev {@literal <gnuzzz@mail.ru>}
 */
public class SyncServiceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncServiceClient.class);

    public int getValue1(int param) throws IOException {
        return getValue("value1", param);
    }

    public long getValue2(int param) throws IOException {
        return getValue("value2", param);
    }

    public double getValue3(long param) throws IOException {
        return getValue("value3", (int) param);
    }

    public int getValue4(int param) throws IOException {
        return getValue("value4", param);
    }

    public long getValue5(int param) throws IOException {
        return getValue("value5", param);
    }

    private int getValue(String name, int param) throws IOException {
        LOGGER.debug("{} param {} loading...", name, param);
        try {
            switch (param) {
                case 0:
                case 1:
                    return param;
                case 2:
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new IOException(e.getMessage(), e);
                    }
                    return param;
                default:
                    throw new SocketTimeoutException(Integer.toString(param));
            }
        } finally {
            LOGGER.debug("{} param loaded", name);
        }
    }

}
