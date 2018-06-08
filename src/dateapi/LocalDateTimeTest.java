package dateapi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class LocalDateTimeTest {

    public static void main(String[] args) {
        // LocalDate 사용
        LocalDate today = LocalDate.now(); // 현재 날짜
        System.out.println(today);

        LocalDate today2 = LocalDate.now();
        System.out.println(today2);

        System.out.println(today.compareTo(today2) == 0); // ture

        // 년월일 날짜 출력
        System.out.println("년/월/일 : "+today.getYear()+"/"+
                today.getMonth()+"/"+today.getMonthValue()+"/"+today.getDayOfMonth());
        System.out.println("요일 : "+today.getDayOfWeek() + " " +today.getDayOfWeek().getValue());


        // 특정 날짜를 지정해서 LocalDate생성
        LocalDate endDay = LocalDate.of(2018,12,31);

        System.out.println("현재 날짜기준으로 몇일이 남았는지?"+today.until(endDay, ChronoUnit.DAYS));
        System.out.println("현재 기준 1개월 후 : "+today.plusMonths(1));
        System.out.println("현재 기준 3일후 요일 :"+today.getDayOfWeek().plus(3));

        // LocalTime
        LocalTime currentTime = LocalTime.now();
        System.out.println(currentTime);
        System.out.println(currentTime.getHour() +" "+ currentTime.getMinute() +" "+ currentTime.getSecond() +" "+ currentTime.getNano());

        LocalTime bedTime = LocalTime.of(1,10);
        LocalTime wakeTime = bedTime.plusHours(6);
        System.out.println("기상시간 : "+wakeTime);

        // LocalDateTime
        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dt);
        System.out.println(dt.toLocalDate()); // 날짜만 추출
        System.out.println(dt.toLocalTime()); // 시간만 추출

        LocalDateTime dt2 = LocalDateTime.of(2018,1,2,2,3,1);
        System.out.println(dt2);


        // fommating
        System.out.println(dt2.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(dt2.format(DateTimeFormatter.ofPattern("yyyy년mm월dd일", Locale.KOREA)));


    }

}
