package com.example.administrator.musicbox;

/**
 * @author wjf
 * @date 2016-09-20下午 6:20
 */

public class TimeUtils
{
    public static String getFormatTime(int time)
    {
        int SECOND = 1;
        int MIN = SECOND * 60;
        int HOUR = MIN * 60;
        int timeHour = time / HOUR;
        int timeMin = (time % HOUR) / MIN;
        int timeSec = (time % HOUR % MIN) / SECOND;
        String timeHourStr = "";
        String timeMinStr = "";
        String timeSecStr = "";
        if (timeHour < 10 && timeHour > 0)
            timeHourStr = "0" + timeHour;
        if (timeHour >=10)
            timeHourStr += timeHour;

        if (timeMin < 10)
            timeMinStr += "0" + timeMin;
        if (timeMin >= 10)
            timeMinStr += timeMin;

        if (timeSec < 10)
            timeSecStr += "0" + timeSec;
        if (timeSec >= 10)
            timeSecStr += timeSec;
        String timeStr = "";
        timeStr = timeHourStr.equals("") ? "" : timeHourStr + ":";
        timeStr = timeHourStr + timeMinStr + ":" + timeSecStr;
        return timeStr;
    }

    public static int getPercent(int totalTime, int progressTime)
    {
        return progressTime/totalTime;
    }
}
