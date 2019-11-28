package kata.ex01;

import kata.ex01.model.Driver;
import kata.ex01.model.HighwayDrive;
import kata.ex01.model.MoterWayType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static kata.ex01.model.RouteType.RURAL;
import static kata.ex01.model.VehicleFamily.STANDARD;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author kawasima
 */
public class DiscountServiceTest {
    DiscountService discountService;
    private Driver driver(int usingCount) {
        Driver driver = new Driver();
        driver.setCountPerMonth(usingCount);
        return driver;
    }

    @BeforeEach
    void setUp() {
        discountService = new DiscountServiceImpl();
    }

    @Test
    public void test平日朝夕割引() {
//        月曜から火曜にかけて
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2016, 3, 31, 23, 0));
        drive.setExitedAt(LocalDateTime.of(2016, 4, 1, 6, 30));
        drive.setDriver(driver(10));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);

        assertThat(discountService.calc(drive)).isEqualTo(50);
    }

    @Test
    public void test平日朝夕割引_ルール期間から出発かつ利用回数５回() {
//        月曜から火曜にかけて
        HighwayDrive drive = new HighwayDrive();
        LocalDateTime dateTime = LocalDateTime.of(2016, 3, 31, 18, 0);
        drive.setEnteredAt(dateTime);
        System.out.println(dateTime.getDayOfWeek());
        drive.setExitedAt(LocalDateTime.of(2016, 3, 31, 21, 30));
        drive.setDriver(driver(5));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);

        assertThat(discountService.calc(drive)).isEqualTo(30);
    }

    @Test
    public void test平日朝夕割引_ルール期間外() {
//        月曜から火曜にかけて
        HighwayDrive drive = new HighwayDrive();
        LocalDateTime dateTime = LocalDateTime.of(2016, 3, 31, 10, 0);
        drive.setEnteredAt(dateTime);
        drive.setExitedAt(LocalDateTime.of(2016, 3, 31, 11, 30));
        drive.setDriver(driver(10));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);

        assertThat(discountService.calc(drive)).isEqualTo(0);
    }

    @Test
    public void test休日朝夕は休日割が適用される() {
//        金曜から土曜にかけて
        HighwayDrive drive = new HighwayDrive();
        LocalDateTime dateTime = LocalDateTime.of(2016, 4, 1, 23, 0);
        drive.setEnteredAt(dateTime);
        drive.setExitedAt(LocalDateTime.of(2016, 4, 2, 6, 30));
        drive.setDriver(driver(10));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);

        assertThat(discountService.calc(drive)).isEqualTo(30);
    }

    @Test
    public void test深夜は深夜割が適用される() {
        HighwayDrive drive = new HighwayDrive();
        LocalDateTime dateTime = LocalDateTime.of(2016, 3, 31, 23, 0);
        drive.setEnteredAt(dateTime);
        drive.setExitedAt(LocalDateTime.of(2016, 4, 1, 3, 30));
        drive.setDriver(driver(10));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);

        assertThat(discountService.calc(drive)).isEqualTo(30);
    }

    @Test
    public void test祝日割引が適用される() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2019, 11, 23, 10, 0));
        drive.setExitedAt(LocalDateTime.of(2019, 11, 23, 11, 30));
        drive.setDriver(driver(10));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);

        assertThat(discountService.calc(drive)).isEqualTo(30);
    }

    @Test
    public void testETC20割引が適用される() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2019, 11, 21, 10, 0));
        drive.setExitedAt(LocalDateTime.of(2019, 11, 21, 11, 30));
        drive.setDriver(driver(10));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);
        drive.setEtcVersion(2);
        drive.setMotorwayType(MoterWayType.KENODO);

        assertThat(discountService.calc(drive)).isEqualTo(20);
    }


}
