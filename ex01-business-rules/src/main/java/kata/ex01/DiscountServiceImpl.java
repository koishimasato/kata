package kata.ex01;

import kata.ex01.model.HighwayDrive;
import kata.ex01.rule.EtcRule;
import kata.ex01.rule.MidnightRule;
import kata.ex01.rule.WeekDayRule;
import kata.ex01.rule.WeekEndRule;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author kawasima
 */
public class DiscountServiceImpl implements DiscountService {
    @Override
    public long calc(HighwayDrive drive) {
        var weekdayRule = new WeekDayRule();
        var weekendRule = new WeekEndRule();
        var midnightRule = new MidnightRule();
        var etcRule = new EtcRule();

        var discounts = new Long[]{
                weekdayRule.calc(drive),
                weekendRule.calc(drive),
                midnightRule.calc(drive),
                etcRule.calc(drive)
        };

        return Arrays.stream(discounts).max(Comparator.naturalOrder()).get();
    }
}
