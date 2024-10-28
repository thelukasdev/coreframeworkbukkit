package de.lukaspellny.coreframeworkbukkit.commands;

import de.lukaspellny.coreframeworkbukkit.main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DayCommand implements CommandExecutor {

    private static final String SET_DAY = "§7Die Zeit wurde auf §b0§7 gesetzt.";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(main.NOT_PLAYER);
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("core.command.day")) {
            player.sendMessage(main.NO_PERMISSION);
            return true;
        }

        player.getWorld().setTime(0);
        Bukkit.broadcastMessage(SET_DAY);
        return true;
    }
}