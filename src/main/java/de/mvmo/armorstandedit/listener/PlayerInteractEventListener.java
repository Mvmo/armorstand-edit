package de.mvmo.armorstandedit.listener;

import de.mvmo.armorstandedit.mode.ArmorStandEditMode;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractEventListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!ArmorStandEditMode.isInEditMode(player))
            return;

        ArmorStandEditMode editMode = ArmorStandEditMode.editModeFromPlayer(player);

        if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
            return;

        ItemStack item = event.getItem();
        if (item == null || item.getItemMeta() == null || item.getItemMeta().getDisplayName() == null || item.getType().equals(Material.AIR))
            return;

        if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§cCreate ArmorStand")) {
            ArmorStand armorStand = (ArmorStand) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);

            armorStand.setBasePlate(false);
            armorStand.setGravity(false);
            armorStand.setArms(true);

            editMode.setArmorStand(armorStand);
            editMode.applyInventory();

            return;
        }
    }

}
