package com.ubs.opsit.interviews;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

/**
 * Created by 368798 on 10/25/2017.
 */
public class BerlinClock implements TimeConverter {

    private static final Logger LOG = LoggerFactory.getLogger(BerlinClock.class);

    @Override
    public String convertTime(String aTime) {
        int[] parts = Stream.of(aTime.split(Constants.TIME_SEPARATOR_SIGN)).mapToInt(Integer::parseInt).toArray();

        return new StringBuilder()
                .append(getSeconds(parts[2])).append(Constants.NEW_LINE)
                .append(getTopHours(parts[0])).append(Constants.NEW_LINE)
                .append(getBottomHours(parts[0])).append(Constants.NEW_LINE)
                .append(getTopMinutes(parts[1])).append(Constants.NEW_LINE)
                .append(getBottomMinutes(parts[1]))
                .toString();
    }

    protected String getSeconds(int number) {
        if (number % 2 == 0)
            return Constants.ON_INDICATOR;
        else
            return Constants.OFF_INDICATOR;
    }

    protected String getTopHours(int number) {
        return getOnOff(Constants.TOP_HOURS_COUNT, getTopNumberOfOnSigns(number));
    }

    protected String getBottomHours(int number) {
        return getOnOff(Constants.BOTTOM_HOURS_COUNT, number % 5);
    }

    protected String getTopMinutes(int number) {
        return getOnOff(Constants.TOP_MINUTES_COUNT, getTopNumberOfOnSigns(number), Constants.ON_INDICATOR)
                .replaceAll(Constants.TOP_ROW_MINUTES_WITHOUT_QUARTERS, Constants.TOP_ROW_MINUTES_WITH_QUARTERS);
    }

    protected String getBottomMinutes(int number) {
        return getOnOff(Constants.BOTTOM_MINUTES_COUNT, number % 5, Constants.ON_INDICATOR);
    }

    private String getOnOff(int lampsCountInRow, int switchedOnLamps) {
        return getOnOff(lampsCountInRow, switchedOnLamps, Constants.QUARTER_INDICATOR);
    }

    private String getOnOff(int lampsCountInRow, int switchedOnLamps, String onSign) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < switchedOnLamps; i++) {
            out.append(onSign);
        }
        for (int i = 0; i < (lampsCountInRow - switchedOnLamps); i++) {
            out.append(Constants.OFF_INDICATOR);
        }
        return out.toString();
    }

    private int getTopNumberOfOnSigns(int number) {
        return (number - (number % 5)) / 5;
    }

}
