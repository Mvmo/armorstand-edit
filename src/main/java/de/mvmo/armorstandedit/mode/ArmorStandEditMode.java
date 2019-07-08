package de.mvmo.armorstandedit.mode;

import com.google.common.collect.Lists;
import de.mvmo.armorstandedit.util.ItemStackBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Setter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ArmorStandEditMode {

    private static final List<ArmorStandEditMode> EDIT_MODE_LIST;

    static {
        EDIT_MODE_LIST = Lists.newArrayList();
    }

    private final Player player;

    private ArmorStand armorStand;

    public static ArmorStandEditMode of(Player player) {
        ArmorStandEditMode editMode = new ArmorStandEditMode(player);

        EDIT_MODE_LIST.add(editMode);

        return editMode;
    }

    public void applyInventory() {
        if (armorStand == null) {
            player.getInventory().setItem(0, new ItemStack(Material.STONE));
            return;
        }
    }

    public static boolean isInEditMode(Player player) {
        return EDIT_MODE_LIST.stream()
                .anyMatch(editMode -> editMode.player.equals(player));
    }

    public static ArmorStandEditMode editModeFromPlayer(Player player) {
        return EDIT_MODE_LIST.stream()
                .filter(editMode -> editMode.player.equals(player))
                .findFirst()
                .orElse(null);
    }

    public static List<ArmorStandEditMode> getEditModeList() {
        return EDIT_MODE_LIST;
    }
}
