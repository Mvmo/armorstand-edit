package de.mvmo.armorstandedit.mode;

import com.google.common.collect.Lists;
import de.mvmo.armorstandedit.misc.ArmorStandPart;
import de.mvmo.armorstandedit.misc.Axis;
import de.mvmo.armorstandedit.util.ItemStackBuilder;
import de.mvmo.armorstandedit.util.TexturedSkullItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.EulerAngle;

import java.lang.reflect.Method;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ArmorStandEditMode {

    private static final List<ArmorStandEditMode> EDIT_MODE_LIST;

    static {
        EDIT_MODE_LIST = Lists.newArrayList();
    }

    private final Player player;

    private ArmorStand armorStand;
    private ArmorStandPart part;
    private boolean whole;

    public static ArmorStandEditMode of(Player player) {
        ArmorStandEditMode editMode = new ArmorStandEditMode(player);

        editMode.part = ArmorStandPart.HEAD;
        editMode.whole = false;
        EDIT_MODE_LIST.add(editMode);

        return editMode;
    }

    public void applyAxisEditInventory() {
        player.getInventory().clear();

        if (armorStand == null) {
            player.getInventory().setItem(0, new ItemStackBuilder(Material.ARMOR_STAND)
                    .setDisplayName("§cCreate ArmorStand")
                    .build());

            player.getInventory().setItem(4, new ItemStackBuilder(Material.STICK)
                    .setDisplayName("§cSelect ArmorStand")
                    .build());
            return;
        }

        applyAxisItems();

        player.getInventory().setItem(6, new ItemStackBuilder(Material.PRISMARINE_SHARD)
                .setDisplayName("§cChange Body Part §8(§7Currently §8» §7" + part.getFormattedName() + "§8)")
                .build());

        player.getInventory().setItem(8, new ItemStackBuilder(Material.REDSTONE_TORCH_ON)
                .setDisplayName("§cEdit whole ArmorStand")
                .build());
    }

    public void applyWholeArmorStandEditInventory() {
        player.getInventory().clear();

        applyAxisItems();

        player.getInventory().setItem(3, new ItemStackBuilder(TexturedSkullItem.create("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTVjZWQ5OTMxYWNlMjNhZmMzNTEzNzEzNzliZjA1YzYzNWFkMTg2OTQzYmMxMzY0NzRlNGU1MTU2YzRjMzcifX19"))
                .setDisplayName("§cRotation")
                .build());

        player.getInventory().setItem(6, new ItemStackBuilder(Material.CHEST)
                .setDisplayName("§cGUI")
                .build());

        player.getInventory().setItem(8, new ItemStackBuilder(Material.REDSTONE_TORCH_ON)
                .setDisplayName("§cEdit Axis")
                .build());
    }

    private void applyAxisItems() {
        player.getInventory().setItem(0, new ItemStackBuilder(TexturedSkullItem.create("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE2Nzg3YmEzMjU2NGU3YzJmM2EwY2U2NDQ5OGVjYmIyM2I4OTg0NWU1YTY2YjVjZWM3NzM2ZjcyOWVkMzcifX19"))
                .setDisplayName("§cX")
                .build());

        player.getInventory().setItem(1, new ItemStackBuilder(TexturedSkullItem.create("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzUyZmIzODhlMzMyMTJhMjQ3OGI1ZTE1YTk2ZjI3YWNhNmM2MmFjNzE5ZTFlNWY4N2ExY2YwZGU3YjE1ZTkxOCJ9fX0="))
                .setDisplayName("§cY")
                .build());

        player.getInventory().setItem(2, new ItemStackBuilder(TexturedSkullItem.create("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTA1ODJiOWI1ZDk3OTc0YjExNDYxZDYzZWNlZDg1ZjQzOGEzZWVmNWRjMzI3OWY5YzQ3ZTFlMzhlYTU0YWU4ZCJ9fX0="))
                .setDisplayName("§cZ")
                .build());
    }

    public void openPropertiesInventory() {
        Inventory inventory = Bukkit.createInventory(null, 4 * 9, "§cArmorStandEdit §8» §cGUI");

        inventory.setItem(0, new ItemStackBuilder(Material.DAYLIGHT_DETECTOR)
                .setDisplayName("§cSize §8» §e" + (armorStand.isSmall() ? "Small" : "Tall"))
                .build());

        inventory.setItem(1, new ItemStackBuilder(Material.GLASS_BOTTLE)
                .setDisplayName("§cVisibility §8» §e" + (armorStand.isVisible() ? "Visible" : "Invisible"))
                .build());

        player.openInventory(inventory);
    }

    public EulerAngle executePartRotation(Axis axis, int value, boolean set) {
        try {
            Method poseGetMethod = armorStand.getClass().getDeclaredMethod("get" + part.getFormattedName() + "Pose");
            poseGetMethod.setAccessible(true);

            EulerAngle angle = ((EulerAngle) poseGetMethod.invoke(armorStand));

            Method axisGetMethod = angle.getClass().getDeclaredMethod("get" + axis.toString());
            axisGetMethod.setAccessible(true);

            double axisValue = set ? Math.toRadians(value) : (double) axisGetMethod.invoke(angle) + Math.toRadians(value);
            if (Math.toDegrees(axisValue) > 360)
                axisValue = Math.toRadians(0);
            if (Math.toDegrees(axisValue) < 0)
                axisValue = Math.toRadians(360);

            Method axisSetMethod = angle.getClass().getDeclaredMethod("set" + axis.toString(), double.class);
            axisSetMethod.setAccessible(true);

            angle = (EulerAngle) axisSetMethod.invoke(angle, axisValue);

            Method poseSetMethod = armorStand.getClass().getDeclaredMethod("set" + part.getFormattedName() + "Pose", EulerAngle.class);
            poseSetMethod.setAccessible(true);
            poseSetMethod.invoke(armorStand, angle);

            return angle;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new EulerAngle(0, 0, 0);
    }

    public EulerAngle executePartRotation(Axis axis) {
        return executePartRotation(axis, 5, false);
    }

    public void executeMove(Axis axis, double value, boolean set) {
        double x = axis == Axis.X ? value : (set ? armorStand.getLocation().getX() : 0);
        double y = axis == Axis.Y ? value : (set ? armorStand.getLocation().getY() : 0);
        double z = axis == Axis.Z ? value : (set ? armorStand.getLocation().getZ() : 0);

        if (player.isSneaking()) {
            x = -x;
            y = -y;
            z = -z;
        }

        if (set) {
            Location location = armorStand.getLocation();

            location.setX(x);
            location.setY(y);
            location.setZ(z);

            armorStand.teleport(location);
            return;
        }

        armorStand.teleport(armorStand.getLocation().add(x, y, z));
    }

    public void executeMove(Axis axis) {
        executeMove(axis, 0.1, false);
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
