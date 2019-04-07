package ru.albemuth.samples.trymonad;

import org.junit.Test;

import java.net.SocketTimeoutException;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author Vladimir Kornyshev {@literal <gnuzzz@mail.ru>}
 */
public class TestEndpoint {

    @Test
    public void testSyncEndpointSuccess() {
        try {
            Optional<Result> result = new SyncEndpoint().call(0);
            assertTrue(result.isPresent());
        } catch (Throwable e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSyncEndpointFailure() {
        try {
            Optional<Result> result = new SyncEndpoint().call(1);
            fail();
        } catch (SocketTimeoutException e) {
            //do nothing
        } catch (Throwable e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCompletableFutureEndpointSuccess1() {
        try {
            Optional<Result> result = new CompletableFutureEndpoint().call1(0);
            assertTrue(result.isPresent());
        } catch (Throwable e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCompletableFutureEndpointSuccess2() {
        try {
            Optional<Result> result = new CompletableFutureEndpoint().call2(0);
            assertTrue(result.isPresent());
        } catch (Throwable e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCompletableFutureEndpointFailure1() {
        try {
            Optional<Result> result = new CompletableFutureEndpoint().call1(1);
            fail();
        } catch (SocketTimeoutException e) {
            //do nothing
        } catch (Throwable e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCompletableFutureEndpointFailure2() {
        try {
            Optional<Result> result = new CompletableFutureEndpoint().call2(1);
            fail();
        } catch (SocketTimeoutException e) {
            //do nothing
        } catch (Throwable e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testBetterMonadsEndpointSuccess() {
        try {
            Optional<Result> result = new BetterMonadsEndpoint().call(0);
            assertTrue(result.isPresent());
        } catch (Throwable e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testBetterMonadsEndpointFailure() {
        try {
            Optional<Result> result = new BetterMonadsEndpoint().call(1);
            fail();
        } catch (SocketTimeoutException e) {
            //do nothing
        } catch (Throwable e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testVavrEndpointSuccess1() {
        try {
            Optional<Result> result = new VavrEndpoint().call1(0);
            assertTrue(result.isPresent());
        } catch (Throwable e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testVavrEndpointSuccess2() {
        try {
            Optional<Result> result = new VavrEndpoint().call2(0);
            assertTrue(result.isPresent());
        } catch (Throwable e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testVavrEndpointFailure1() {
        try {
            Optional<Result> result = new VavrEndpoint().call1(1);
            fail();
        } catch (SocketTimeoutException e) {
            //do nothing
        } catch (Throwable e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testVavrEndpointFailure2() {
        try {
            Optional<Result> result = new VavrEndpoint().call2(1);
            fail();
        } catch (SocketTimeoutException e) {
            //do nothing
        } catch (Throwable e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCyclopsEndpointSuccess1() {
        try {
            Optional<Result> result = new CyclopsEndpoint().call1(0);
            assertTrue(result.isPresent());
        } catch (Throwable e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCyclopsEndpointSuccess2() {
        try {
            Optional<Result> result = new CyclopsEndpoint().call2(0);
            assertTrue(result.isPresent());
        } catch (Throwable e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCyclopsEndpointFailure1() {
        try {
            Optional<Result> result = new CyclopsEndpoint().call1(1);
            fail();
        } catch (SocketTimeoutException e) {
            //do nothing
        } catch (Throwable e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCyclopsEndpointFailure2() {
        try {
            Optional<Result> result = new CyclopsEndpoint().call2(1);
            fail();
        } catch (SocketTimeoutException e) {
            //do nothing
        } catch (Throwable e) {
            e.printStackTrace();
            fail();
        }
    }

}
