package kata.ex01.rule;

import kata.ex01.model.Driver;
import kata.ex01.model.HighwayDrive;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static kata.ex01.model.RouteType.RURAL;
import static kata.ex01.model.VehicleFamily.STANDARD;
import static org.assertj.core.api.Assertions.assertThat;

class WeekDayRuleTest {
    private Driver driver(int usingCount) {
        Driver driver = new Driver();
        driver.setCountPerMonth(usingCount);
        return driver;
    }

    @Test
    public void test期間内() {
        var weekdayRule = new WeekDayRule();
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2016, 3, 31, 18, 0));
        drive.setExitedAt(LocalDateTime.of(2016, 3, 31, 21, 30));
        drive.setDriver(driver(5));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);

        assertThat(weekdayRule.isInPeriod(drive)).isTrue();
    }

    @Test
    public void test帰りが期間内() {
        var weekdayRule = new WeekDayRule();
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2016, 3, 31, 23, 0));
        drive.setExitedAt(LocalDateTime.of(2016, 4, 1, 6, 30));
        drive.setDriver(driver(5));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);

        assertThat(weekdayRule.isInPeriod(drive)).isTrue();
    }
}