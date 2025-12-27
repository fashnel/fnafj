package main;

public class TestMain {
    static int numberHour = 0;
    static String AmPm = " PM";
    static String outHour = "";
    static String hour;

    public static String time(long now) {
        hour = String.format("%2s", numberHour % 12 + 1);

        AmPm = " AM";
        numberHour++;
        outHour = hour + AmPm;
        return outHour;
    }
    static void main() {
        System.out.println(time(231231));
    }
}
