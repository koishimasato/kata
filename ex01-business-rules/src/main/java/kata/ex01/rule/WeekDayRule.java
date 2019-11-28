package kata.ex01.rule;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;

import java.time.DayOfWeek;
import java.util.Arrays;

public class WeekDayRule implements Rule {
    public long calc(HighwayDrive drive) {
        if (isWeekDay(drive) && isInPeriod(drive) && isRural(drive)) {
            if (isMaxUsingCount(drive.getDriver().getCountPerMonth())) {
                return 50;
            } else {
                return 30;
            }
        } else {
            return 0;
        }
    }

    private boolean isMaxUsingCount(int countPerMonth) {
        return countPerMonth >= 10;
    }

    private boolean isRural(HighwayDrive drive) {
        return drive.getRouteType() == RouteType.RURAL;
    }

    boolean isInPeriod(HighwayDrive drive) {
        // - 平日「朝:6時〜9時」、「夕:17時〜20時」
        int enteredHour = drive.getEnteredAt().getHour();
        int exitedHour = drive.getExitedAt().getHour();

        return (6 <= enteredHour && enteredHour <= 9) ||
                (6 <= exitedHour && exitedHour <= 9) ||
                (17 <= enteredHour && enteredHour <= 20) ||
                (17 <= exitedHour && exitedHour <= 20);
    }

    private boolean isWeekDay(HighwayDrive drive) {
        var weekEnds = new DayOfWeek[]{DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};
        return !Arrays.asList(weekEnds).contains(drive.getEnteredAt().getDayOfWeek()) &&
                !Arrays.asList(weekEnds).contains(drive.getExitedAt().getDayOfWeek());
    }
}
