package de.mvmo.armorstandedit.listener;

import de.mvmo.armorstandedit.mode.ArmorStandEditMode;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageEventListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof ArmorStand))
            return;

        ArmorStand armorStand = (ArmorStand) event.getEntity();

        ArmorStandEditMode.getEditModeList().stream()
                .filter(edit -> edit.getArmorStand().equals(armorStand))
                .findFirst()
                .ifPresent(editMode -> {
                    ArmorStandEditMode.getEditModeList().remove(editMode);
                    editMode.getPlayer().getInventory().clear();
                });

    }

}
