package de.lukaspellny.coreframeworkbukkit.commands;

import de.lukaspellny.coreframeworkbukkit.main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 || args.length == 2) {
            Player target;
            GameMode mode;
            String permission;

            switch (args[0]) {
                case "1":
                    mode = GameMode.CREATIVE;
                    permission = "core.command.gamemode.creative";
                    break;
                case "2":
                    mode = GameMode.ADVENTURE;
                    permission = "core.command.gamemode.adventure";
                    break;
                case "3":
                    mode = GameMode.SPECTATOR;
                    permission = "core.command.gamemode.spectator";
                    break;
                case "0":
                default:
                    mode = GameMode.SURVIVAL;
                    permission = "core.command.gamemode.survival";
                    break;
            }

            if (args.length == 2) {
                if (sender.hasPermission(permission + ".other")) {
                    target = Bukkit.getPlayer(args[1]);
                    if (target != null) {
                        target.setGameMode(mode);
                        target.sendMessage("§7Dein GameMode wurde auf §e" + mode.toString() + " §7gesetzt.");
                        sender.sendMessage("§7Du hast den GameMode von §e" + target.getName() + "§7 auf §e" + mode.toString() + " §7gesetzt.");
                    } else {
                        sender.sendMessage("§7Der Spieler §e" + args[1] + " §7ist nicht online.");
                    }
                } else {
                    sender.sendMessage(main.NO_PERMISSION);
                }
            } else if (sender instanceof Player) {
                if (sender.hasPermission(permission)) {
                    Player player = (Player) sender;
                    player.setGameMode(mode);
                    player.sendMessage("§7Dein GameMode wurde auf §e" + mode.toString() + " §7gesetzt.");
                } else {
                    sender.sendMessage(main.NO_PERMISSION);
                }
            } else {
                sender.sendMessage("§cDu musst einen Spieler angeben, wenn du diesen Befehl von der Konsole aus benutzt.");
            }
        } else {
            sender.sendMessage("§7Verwende §e/gm §7<§eSpielmodus§7> <§eSpieler§7>");
        }
        return true;
    }
}
