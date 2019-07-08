package de.mvmo.armorstandedit.misc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArmorStandPart {

    HEAD(0, "Head"),
    BODY(1, "Body"),
    RIGHT_ARM(2, "RightArm"),
    LEFT_ARM(3, "LeftArm"),
    RIGHT_LEG(4, "RightLeg"),
    LEFT_LEG(5, "LeftLeg");

    private int index;
    private String formattedName;

    private ArmorStandPart next() {
        int index = this.index + 1;
        if (index + 1 > values().length)
            index = 0;

        return values()[index];
    }

}
