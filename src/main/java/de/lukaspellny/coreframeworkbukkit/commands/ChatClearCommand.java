package de.lukaspellny.coreframeworkbukkit.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatClearCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) || sender.hasPermission("core.command.chatclear")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!player.hasPermission("core.command.chatclear.bypass")) {
                    for (int i = 0; i < 100; i++) {
                        player.sendMessage("");
                    }
                }
                player.sendMessage("§7Der Chat wurde von §e" + sender.getName() + " §7geleert.");
            }
            sender.sendMessage("§7Der Caht wurde erfolgreich geleert.");
        } else {
            sender.sendMessage("§cUnzureichende Rechte.");
        }
        return true;


    }
}
