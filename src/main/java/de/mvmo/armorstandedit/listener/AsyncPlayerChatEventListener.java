package de.mvmo.armorstandedit.listener;

import de.mvmo.armorstandedit.misc.Axis;
import de.mvmo.armorstandedit.mode.ArmorStandEditMode;
import org.bukkit.ChatColor;
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

        try {
            int degree = Integer.valueOf(event.getMessage().split(" ")[0]);

            if (degree > 360 || degree < 0)
                return;

            Axis axis = Axis.valueOf(ChatColor.stripColor(event.getPlayer().getItemInHand().getItemMeta().getDisplayName()));

            ArmorStandEditMode.editModeFromPlayer(event.getPlayer()).executeRotation(axis, degree, true);
            event.setCancelled(true);
        } catch (IllegalArgumentException exception) {
        }
    }

}
