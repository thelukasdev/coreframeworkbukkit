package de.lukaspellny.coreframeworkbukkit.commands;


import de.lukaspellny.coreframeworkbukkit.main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillCommand implements CommandExecutor {
    private static final String PLAYER_NOT_FOUND = "§cF: Der Spieler nicht gefunden.";
    private static final String KILLED_MESSAGE = "§7Du wurdest von §e%s§7 getötet!";
    private static final String ADMIN_KILL_MESSAGE = "§7Du hast §e%s§7 getötet!";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(main.NOT_PLAYER);
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("core.command.kill")) {
            player.sendMessage(main.NO_PERMISSION);
            return true;
        }

        Player target;

        if (args.length > 0) {
            target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(PLAYER_NOT_FOUND);
                return true;
            }
        } else {
            target = player;
        }

        target.setHealth(0.0);
        target.sendMessage(String.format(KILLED_MESSAGE, player.getName()));

        if (!target.equals(player)) {
            player.sendMessage(String.format(ADMIN_KILL_MESSAGE, target.getName()));
        }

        return true;
    }
}
