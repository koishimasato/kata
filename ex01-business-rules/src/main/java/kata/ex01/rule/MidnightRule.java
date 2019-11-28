package kata.ex01.rule;

import kata.ex01.model.HighwayDrive;

public class MidnightRule implements Rule {
    public long calc(HighwayDrive drive) {
        if (isInPeriod(drive)) {
            return 30;
        } else {
            return 0;
        }
    }

    private boolean isInPeriod(HighwayDrive drive) {
        // - 深夜0~4時
        int enteredHour = drive.getEnteredAt().getHour();
        int exitedHour = drive.getExitedAt().getHour();

        return (0 <= enteredHour && enteredHour <= 4) ||
                (0 <= exitedHour && exitedHour <= 4);
    }
}
