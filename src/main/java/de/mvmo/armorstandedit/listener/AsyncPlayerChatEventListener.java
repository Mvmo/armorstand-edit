package de.mvmo.armorstandedit.listener;

import de.mvmo.armorstandedit.misc.Axis;
import de.mvmo.armorstandedit.mode.ArmorStandEditMode;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

public class AsyncPlayerChatEventListener implements Listener {

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        if (!ArmorStandEditMode.isInEditMode(event.getPlayer()))
            return;

        ItemStack item = event.getPlayer().getItemInHand();
        if (item == null || item.getItemMeta() == null || item.getItemMeta().getDisplayName() == null || item.getType().equals(Material.AIR))
            return;

        ArmorStandEditMode editMode = ArmorStandEditMode.editModeFromPlayer(event.getPlayer());

        try {
            int input;
            boolean add = event.getMessage().toLowerCase().startsWith("a");

            if (add)
                input = Integer.valueOf(event.getMessage().replaceFirst("a", "").split(" ")[0]);
            else
                input = Integer.valueOf(event.getMessage().split(" ")[0]);

            if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§cRotation")) {
                Location location = editMode.getArmorStand().getLocation();
                location.setYaw(add ? location.getYaw() + input : input);

                editMode.getArmorStand().teleport(location);
                return;
            }

            Axis axis = Axis.valueOf(ChatColor.stripColor(event.getPlayer().getItemInHand().getItemMeta().getDisplayName()));

            if (editMode.isWhole()) {
                editMode.executeMove(axis, input, !add);
                return;
            }

            if (input > 360 || input < 0)
                return;

            ArmorStandEditMode.editModeFromPlayer(event.getPlayer()).executePartRotation(axis, input, !add);
            event.setCancelled(true);
        } catch (IllegalArgumentException exception) {
        }
    }

}
