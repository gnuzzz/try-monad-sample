package ru.albemuth.samples.trymonad;

import static java.text.MessageFormat.format;

/**
 * @author Vladimir Kornyshev {@literal <gnuzzz@mail.ru>}
 */
public class Result {

    private int value1;
    private long value2;
    private double value3;
    private int value4;
    private long value5;

    public int getValue1() {
        return value1;
    }

    public long getValue2() {
        return value2;
    }

    public double getValue3() {
        return value3;
    }

    public int getValue4() {
        return value4;
    }

    public long getValue5() {
        return value5;
    }

    public Result withValue1(final int value1) {
        this.value1 = value1;
        return this;
    }

    public Result withValue2(final long value2) {
        this.value2 = value2;
        return this;
    }

    public Result withValue3(final double value3) {
        this.value3 = value3;
        return this;
    }

    public Result withValue4(final int value4) {
        this.value4 = value4;
        return this;
    }

    public Result withValue5(final long value5) {
        this.value5 = value5;
        return this;
    }

    @Override
    public String toString() {
        return format("\"value1\": {0}, \"value2\": {1}, \"value3\": {2}, \"value4\": {3}, \"value5\": {4}",
                value1, value2, value3, value4, value5);
    }
}
