package dateapi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.*;

public class TemporalAdjusterTest {
    public static void main(String[] args) {
        LocalDate d = LocalDate.now();
        System.out.println("두번째 금요일 : " +
            d.with(dayOfWeekInMonth(2,DayOfWeek.FRIDAY))
        );

        System.out.println("다음달 첫날 : : " +
            d.with(firstDayOfNextMonth())
        );

        System.out.println("첫번째 목요일 :" +
            d.with(firstInMonth(DayOfWeek.THURSDAY))
        );

        System.out.println("이번달 마지막날 : "+
            d.with(lastDayOfMonth())
        );

        System.out.println("이번달 마지막 월요일 :"+
            d.with(lastInMonth(DayOfWeek.MONDAY))
        );

        System.out.println("다음 수요일 : "+d.with(next(DayOfWeek.WEDNESDAY)));

        System.out.println("이전 화요일 : "+ d.with(previous(DayOfWeek.TUESDAY)));


    }
}
