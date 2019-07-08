package de.mvmo.armorstandedit.misc;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ArmorStandPart {

    HEAD(0),
    BODY(1),
    RIGHT_ARM(2),
    LEFT_ARM(3),
    RIGHT_LEG(4),
    LEFT_LEG(5);

    private int index;

    private ArmorStandPart next() {
        int index = this.index + 1;
        if (index + 1 > values().length)
            index = 0;

        return values()[index];
    }

}
