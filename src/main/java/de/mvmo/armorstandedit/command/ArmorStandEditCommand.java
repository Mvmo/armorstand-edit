package de.mvmo.armorstandedit.command;

import de.mvmo.armorstandedit.mode.ArmorStandEditMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * I don't know what i should say about this class.
 * There is a instance of it created in the @{link ArmorStandEditPlugin}-Class it is used to say Bukkit that this class is handling the "armorstandedit"-Command.
 * So everytime the user types "/armorstandedit" into the Chat the {@link #onCommand(CommandSender, Command, String, String[])} Method will be triggered.
 * Lets say it's magic🧙🧙🧙🧙.
 */
public class ArmorStandEditCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player))
            return false;
        Player player = (Player) commandSender;

        if (ArmorStandEditMode.isInEditMode(player)) {
            ArmorStandEditMode.getEditModeList().remove(ArmorStandEditMode.editModeFromPlayer(player));

            player.sendMessage("You've left the edit mode");
            return false;
        }

        if (args.length < 1) {
            ArmorStandEditMode.of(player).applyInventory();

            player.sendMessage("You've entered the edit mode");
            return false;
        }

        return false;
    }

}
