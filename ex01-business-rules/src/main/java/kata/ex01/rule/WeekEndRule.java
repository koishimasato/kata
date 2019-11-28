package kata.ex01.rule;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;
import kata.ex01.util.HolidayUtils;

import java.time.DayOfWeek;
import java.util.Arrays;

public class WeekEndRule implements Rule {
    public long calc(HighwayDrive drive) {
        if (isWeekEnd(drive) && isRural(drive)) {
            return 30;
        } else {
            return 0;
        }
    }

    private boolean isRural(HighwayDrive drive) {
        return drive.getRouteType() == RouteType.RURAL;
    }

    private boolean isWeekEnd(HighwayDrive drive) {
        // 土曜・日曜・祝日
        var weekEnds = new DayOfWeek[]{DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};
        return Arrays.asList(weekEnds).contains(drive.getEnteredAt().getDayOfWeek()) ||
               Arrays.asList(weekEnds).contains(drive.getExitedAt().getDayOfWeek()) ||
                HolidayUtils.isHoliday(drive.getEnteredAt().toLocalDate()) ||
                HolidayUtils.isHoliday(drive.getExitedAt().toLocalDate());

    }
}
