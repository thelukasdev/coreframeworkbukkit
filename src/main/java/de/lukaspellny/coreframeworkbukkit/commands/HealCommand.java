package de.lukaspellny.coreframeworkbukkit.commands;

import de.lukaspellny.coreframeworkbukkit.main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) && args.length == 0) {
            sender.sendMessage(main.NOT_PLAYER);
            return true;
        }

        Player target;

        if (args.length == 0 && !sender.hasPermission("core.command.heal")) {
            sender.sendMessage(main.NO_PERMISSION);
            return true;
        }

        if (args.length > 0) {
            if (!sender.hasPermission("core.command.heal.other")) {
                sender.sendMessage(main.NO_PERMISSION);
                return true;
            }

            target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage("§cF: Der Spieler ist nicht online.");
                return true;
            }
        } else {
            target = (Player) sender;
        }

        target.setHealth(target.getMaxHealth());
        target.setFireTicks(0);
        target.sendMessage("§7Du wurdest §egeheilt§7.");

        if (!target.equals(sender)) {
            sender.sendMessage("§7Du hast §e" + target.getName() + "§7 geheilt!");
        }
        return true;
    }
}