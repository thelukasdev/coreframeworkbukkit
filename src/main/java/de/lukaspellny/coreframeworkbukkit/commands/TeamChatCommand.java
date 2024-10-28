package de.lukaspellny.coreframeworkbukkit.commands;


import de.lukaspellny.coreframeworkbukkit.main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeamChatCommand implements CommandExecutor {
    private static final String PREFIX = "§9§lTEAM §8» §7";
    private static final String USAGE = "§7Nutze: §e/team §7<§etext§7>";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(main.NOT_PLAYER);
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("core.command.teamchat.use")) {
            player.sendMessage(main.NO_PERMISSION);
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(USAGE);
            return true;
        }

        String message = String.join(" ", args);
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.hasPermission("core.command.teamchat")) {
                onlinePlayer.sendMessage(PREFIX + player.getName() + ": " + message);
            }
        }
        return true;
    }
}
