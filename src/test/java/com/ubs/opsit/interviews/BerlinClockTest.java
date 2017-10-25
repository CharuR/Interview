package com.ubs.opsit.interviews;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by 368798 on 10/25/2017.
 */
public class BerlinClockTest {

    BerlinClock berlinClock = new BerlinClock();

    // Yellow lamp should blink on/off every two seconds
    @Test
    public void testYellowLampShouldBlinkOnOffEveryTwoSeconds() {
        Assert.assertEquals(Constants.ON_INDICATOR, berlinClock.getSeconds(0));
        Assert.assertEquals(Constants.OFF_INDICATOR, berlinClock.getSeconds(1));
        Assert.assertEquals(Constants.ON_INDICATOR, berlinClock.getSeconds(30));
        Assert.assertEquals(Constants.OFF_INDICATOR, berlinClock.getSeconds(59));
    }

    // Top hours should have 4 lamps
    @Test
    public void testTopHoursShouldHave4Lamps() {
        Assert.assertEquals(Constants.TOP_HOURS_COUNT, berlinClock.getTopHours(7).length());
    }

    // Top hours should light a red lamp for every 5 hours
    @Test
    public void testTopHoursShouldLightRedLampForEvery5Hours() {
        Assert.assertEquals("OOOO", berlinClock.getTopHours(0));
        Assert.assertEquals("RROO", berlinClock.getTopHours(13));
        Assert.assertEquals("RRRO", berlinClock.getTopHours(19));
        Assert.assertEquals("RRRR", berlinClock.getTopHours(24));
    }

    // Bottom hours should have 4 lamps
    @Test
    public void testBottomHoursShouldHave4Lamps() {
        Assert.assertEquals(4, berlinClock.getBottomHours(5).length());
    }

    // Bottom hours should light a red lamp for every hour left from top hours
    @Test
    public void testBottomHoursShouldLightRedLampForEveryHourLeftFromTopHours() {
        Assert.assertEquals("OOOO", berlinClock.getBottomHours(0));
        Assert.assertEquals("ROOO", berlinClock.getBottomHours(11));
        Assert.assertEquals("RRRO", berlinClock.getBottomHours(23));
        Assert.assertEquals("RRRR", berlinClock.getBottomHours(24));
    }

    // Top minutes should have 11 lamps
    @Test
    public void testTopMinutesShouldHave11Lamps() {
        Assert.assertEquals(Constants.TOP_MINUTES_COUNT, berlinClock.getTopMinutes(34).length());
    }

    // Top minutes should have 3rd, 6th and 9th lamps in red to indicate first quarter, half and last quarter
    @Test
    public void testTopMinutesShouldHave3rd6thAnd9thLampsInRedToIndicateFirstQuarterHalfAndLastQuarter() {
        String minutes32 = berlinClock.getTopMinutes(32);
        Assert.assertEquals(Constants.QUARTER_INDICATOR, minutes32.substring(2, 3));
        Assert.assertEquals(Constants.QUARTER_INDICATOR, minutes32.substring(5, 6));
        Assert.assertEquals(Constants.OFF_INDICATOR, minutes32.substring(8, 9));
    }

    // Top minutes should light a yellow lamp for every 5 minutes unless it's first quarter, half or last quarter
    @Test
    public void testTopMinutesShouldLightYellowLampForEvery5MinutesUnlessItIsFirstQuarterHalfOrLastQuarter() {
        Assert.assertEquals("OOOOOOOOOOO", berlinClock.getTopMinutes(0));
        Assert.assertEquals("YYROOOOOOOO", berlinClock.getTopMinutes(17));
        Assert.assertEquals("YYRYYRYYRYY", berlinClock.getTopMinutes(59));
    }

    // Bottom minutes should have 4 lamps
    @Test
    public void testBottomMinutesShouldHave4Lamps() {
        Assert.assertEquals(4, berlinClock.getBottomMinutes(0).length());
    }

    // Bottom minutes should light a yellow lamp for every minute left from top minutes
    @Test
    public void testBottomMinutesShouldLightYellowLampForEveryMinuteLeftFromTopMinutes() {
        Assert.assertEquals("OOOO", berlinClock.getBottomMinutes(0));
        Assert.assertEquals("YYOO", berlinClock.getBottomMinutes(17));
        Assert.assertEquals("YYYY", berlinClock.getBottomMinutes(59));
    }

    // Berlin Clock should result in string with 5 lines
    @Test
    public void testBerlinClockShouldResultInStringWith5Lines()  {
        Assert.assertEquals(5, berlinClock.convertTime("13:17:01").split(Constants.NEW_LINE).length);
    }

    // Berlin Clock it should "result in correct seconds, hours and minutes" in {
    @Test
    public void testBerlinClockShouldResultInCorrectSecondsHoursAndMinutes() {
        String[] berlinTime = berlinClock.convertTime("16:37:16").split(Constants.NEW_LINE);
        String[] expected = new String[] {"Y", "RRRO", "ROOO", "YYRYYRYOOOO", "YYOO"};
        Assert.assertEquals(expected.length, berlinTime.length);
        for (int index = 0; index < expected.length; index++) {
            Assert.assertEquals(expected[index], berlinTime[index]);
        }
    }

}
