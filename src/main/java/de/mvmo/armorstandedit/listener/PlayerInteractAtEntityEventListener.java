package de.mvmo.armorstandedit.listener;

import de.mvmo.armorstandedit.misc.Axis;
import de.mvmo.armorstandedit.mode.ArmorStandEditMode;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractAtEntityEventListener implements Listener {

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        if (!ArmorStandEditMode.isInEditMode(player))
            return;

        if (!(event.getRightClicked() instanceof ArmorStand))
            return;
        ArmorStand armorStand = (ArmorStand) event.getRightClicked();

        ItemStack item = event.getPlayer().getItemInHand();
        if (item == null || item.getItemMeta() == null || item.getItemMeta().getDisplayName() == null || item.getType().equals(Material.AIR))
            return;

        try {
            Axis.valueOf(ChatColor.stripColor(item.getItemMeta().getDisplayName().toUpperCase()));
            event.setCancelled(true);
        } catch (IllegalArgumentException exception) {
        }

        ArmorStandEditMode editMode = ArmorStandEditMode.editModeFromPlayer(player);

        if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§cSelect ArmorStand")) {
            editMode.setArmorStand(armorStand);
            editMode.applyInventory();

            event.setCancelled(true);
            return;
        }
    }

}
