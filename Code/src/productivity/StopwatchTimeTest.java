package productivity;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StopwatchTimeTest {

    @Test
    public void diff() {
        StopwatchTime startTime = new StopwatchTime("00:00:00");
        StopwatchTime endTime = new StopwatchTime("01:30:00");

        Assert.assertEquals(endTime.diff(startTime).toString(), "01:30:00");

        startTime = new StopwatchTime("00:50:00");
        endTime = new StopwatchTime("01:30:00");

        Assert.assertEquals(endTime.diff(startTime).toString(), "00:40:00");

        startTime = new StopwatchTime("00:00:50");
        endTime = new StopwatchTime("01:30:30");

        Assert.assertEquals(endTime.diff(startTime).toString(), "01:29:40");

        startTime = new StopwatchTime("00:50:50");
        endTime = new StopwatchTime("01:30:30");

        Assert.assertEquals(endTime.diff(startTime).toString(), "00:39:40");

    }
}