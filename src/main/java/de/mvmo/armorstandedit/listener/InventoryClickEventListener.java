package de.mvmo.armorstandedit.listener;

import de.mvmo.armorstandedit.mode.ArmorStandEditMode;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClickEventListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!ArmorStandEditMode.isInEditMode(player))
            return;

        ArmorStandEditMode editMode = ArmorStandEditMode.editModeFromPlayer(player);

        Inventory inventory = event.getClickedInventory();
        if (inventory == null || inventory.getTitle() == null)
            return;

        if (!inventory.getTitle().equalsIgnoreCase("§cArmorStandEdit §8» §cGUI"))
            return;
        event.setCancelled(true);

        ItemStack item = event.getCurrentItem();
        if (item == null || item.getItemMeta() == null || item.getItemMeta().getDisplayName() == null || item.getType().equals(Material.AIR))
            return;

        if (item.getItemMeta().getDisplayName().startsWith("§cSize §8» §e")) {
            editMode.getArmorStand().setSmall(!editMode.getArmorStand().isSmall());
            editMode.openPropertiesInventory();

            return;
        }

        if (item.getItemMeta().getDisplayName().startsWith("§cVisibility §8» §e")) {
            editMode.getArmorStand().setVisible(!editMode.getArmorStand().isVisible());
            editMode.openPropertiesInventory();

            return;
        }
    }

}
