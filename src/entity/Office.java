package entity;

import main.GamePanel;

public class Office {
    long timeToNextHour, timeToNextPowerUsage;
    int numberHour = 11,
            powerPercent = 101, timeToNextUsageInSeconds;
    String outHour = "", hour, AmPm = "PM";

    public String time(long now) {
        if (now < timeToNextHour) {
            return outHour;
        }
        timeToNextHour = now + 30000;

        hour = String.format("%3s", numberHour % 12 + 1);
        outHour = hour + AmPm;
        AmPm = "AM";
        numberHour++;
        return outHour;
    }

    public int power(long now) {
        timeToNextUsageInSeconds = 3300;
        if (now < timeToNextPowerUsage) {
            return powerPercent;
        }
        if (GamePanel.leftDoorClosed) {
            timeToNextUsageInSeconds -= 1000;
        }
        if (GamePanel.leftDoorClosed) {
            timeToNextUsageInSeconds -= 1000;
        }
        if (GamePanel.inTablet) {
            timeToNextUsageInSeconds -= 1000;
        }
        timeToNextPowerUsage = now + timeToNextUsageInSeconds;
        powerPercent--;
        return powerPercent;
    }
}
