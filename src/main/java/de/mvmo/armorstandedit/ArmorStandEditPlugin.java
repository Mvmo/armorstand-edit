package de.mvmo.armorstandedit;

import de.mvmo.armorstandedit.command.ArmorStandEditCommand;
import de.mvmo.armorstandedit.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This is the class where it all comes together and everything has a beautiful time with each other.
 * Also the Bukkit-Server will call this class as soon as Bukkit thinks the time has come to call this class for example on the startup of the server.
 */
public class ArmorStandEditPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerInteractAtEntityEventListener(), this);
        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatEventListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractEventListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageEventListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickEventListener(), this);

        getCommand("armorstandedit").setExecutor(new ArmorStandEditCommand());
    }

}
