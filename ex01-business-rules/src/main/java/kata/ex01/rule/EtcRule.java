package kata.ex01.rule;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.MoterWayType;

public class EtcRule implements Rule {
    public long calc(HighwayDrive drive) {
        if (isVersion2(drive) && isKenoDo(drive)) {
            return 20;
        } else {
            return 0;
        }
    }

    private boolean isVersion2(HighwayDrive drive) {
        return drive.getEtcVersion() == 2;
    }

    private boolean isKenoDo(HighwayDrive drive) {
        return drive.getMotorwayType() == MoterWayType.KENODO;
    }
}
