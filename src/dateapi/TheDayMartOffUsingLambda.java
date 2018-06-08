package dateapi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.TemporalAdjusters.*;


public class TheDayMartOffUsingLambda {
    public static void main(String[] args) {

        LocalDate today = LocalDate.now();//LocalDate.of(2018,6,27);
        LocalDate martOffDay = today.with(temporal -> {
            // 기준이 되는 날짜를 구한다.
            LocalDate theDay = LocalDate.from(temporal);
            System.out.println("기준날짜 : "+theDay);
            // 둘째, 네째 일요일을 구한다.
            LocalDate secondDay = theDay.with(dayOfWeekInMonth(2,DayOfWeek.SUNDAY));
            LocalDate fourthDay = theDay.with(dayOfWeekInMonth(4,DayOfWeek.SUNDAY));

            // 기준날짜와 비교
            if(theDay.isBefore(secondDay)) {
                return secondDay;
            } else if (theDay.isBefore(fourthDay)) {
                return fourthDay;
            } else {
                return theDay.plusMonths(1).with(dayOfWeekInMonth(2,DayOfWeek.SUNDAY));
            }

        });
        System.out.println("다음 마트 쉬는날 : "+ martOffDay);
    }
}
