package entity;

import main.GamePanel;

public class Office {
    long timeToNextHour, timeToNextPowerUsage;
    int numberHour = 11,
            powerPercent = 101,
            timeToNextUsageInSeconds, minusSeconds;
    String outHour = "", hour, AmPm = "PM";

    public String time(long now) {
        if (now < timeToNextHour) {
            return outHour;
        }
        timeToNextHour = now + 25000;

        hour = String.format("%3s", numberHour % 12 + 1);
        outHour = hour + AmPm;
        AmPm = "AM";
        numberHour++;
        return outHour;
    }

    public int power(long now) {
        timeToNextUsageInSeconds = 2500;
        minusSeconds = 500;
        if (now < timeToNextPowerUsage) {
            return powerPercent;
        }
        if (GamePanel.leftDoorClosed) {
            timeToNextUsageInSeconds -= minusSeconds;
        }
        if (GamePanel.leftDoorClosed) {
            timeToNextUsageInSeconds -= minusSeconds;
        }
        if (GamePanel.inTablet) {
            timeToNextUsageInSeconds -= minusSeconds;
        }
        timeToNextPowerUsage = now + timeToNextUsageInSeconds;
        powerPercent--;
        return powerPercent;
    }
}
