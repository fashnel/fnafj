package entity;

public class Office {
    long timeToNextHour;
    int numberHour = 11;
    String AmPm = " PM", outHour = "";

    public String time(long now) {
        if (now < timeToNextHour) {
            return outHour;
        }
        timeToNextHour = now + 20000;
        outHour = (numberHour % 12 + 1) + AmPm;
        AmPm = " AM";
        numberHour++;
        return outHour;
    }
    /*
    Прописать время ~ 2 минуты игра длится
    Добавить энергию
     */
}
