package com.soccer.soccercheck.util;

import android.content.Context;

import com.soccer.soccercheck.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 26/11/16.
 */

public class DayFormatter {


    private final static long MILLISECONDS_IN_SECONDS = 1000;

    private final Context mContext;

    public DayFormatter(Context context) {
        mContext = context;
    }

    /**
     * Format a Unix timestamp into a human readable week day String such as "Today", "Tomorrow"
     * and "Wednesday"
     */
    public String format(final long unixTimestamp) {
        final long milliseconds = unixTimestamp * MILLISECONDS_IN_SECONDS;
        String day;

        if (isToday(milliseconds)) {
            day = mContext.getResources().getString(R.string.today);

        } else if (isTomorrow(milliseconds)) {
            day = mContext.getResources().getString(R.string.tomorrow);

        } else {

            day = getDayOfWeek(milliseconds);
        }

        return day;
    }


    /**
     *
     * Convert the milliseconds long to a day of the week String type
     *
     * @param milliseconds
     * @return
     */
    private String getDayOfWeek(final long milliseconds) {

        return new SimpleDateFormat("EEEE").format(new Date(milliseconds));

    }

    /**
     *
     * Verify if the date info coming is equals today date info and return true or false
     *
     * @param milliseconds
     * @return
     */
    private boolean isToday(final long milliseconds) {

        final SimpleDateFormat dayInYearFormat = new SimpleDateFormat("yyyyD");

        final String nowHash = dayInYearFormat.format(new Date());

        final String comparisonHash = dayInYearFormat.format(new Date(milliseconds));

        return nowHash.equals(comparisonHash);
    }

    /**
     *
     * Verify if the date info coming is equals today date info plus one (tomorrow) and return true or false
     *
     * @param milliseconds
     * @return
     */
    private boolean isTomorrow(final long milliseconds) {

        final SimpleDateFormat dayInYearFormat = new SimpleDateFormat("yyyyD");

        final int tomorrowHash = Integer.parseInt(dayInYearFormat.format(new Date())) + 1;

        final int comparisonHash = Integer.parseInt(dayInYearFormat.format(new Date(milliseconds)));

        return comparisonHash == tomorrowHash;
    }

}
