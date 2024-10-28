package de.lukaspellny.coreframeworkbukkit.commands;


import de.lukaspellny.coreframeworkbukkit.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

public class WorkbenchCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(main.NOT_PLAYER);
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("core.command.werkbank")) {
            player.sendMessage(main.NO_PERMISSION);
            return true;
        }

        player.openInventory(player.getServer().createInventory(null, InventoryType.WORKBENCH));
        player.sendMessage("§7Du hast deine Werkbank geöffnet!");

        return true;
    }
}
