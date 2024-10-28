package de.lukaspellny.coreframeworkbukkit.commands;

import de.lukaspellny.coreframeworkbukkit.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class GiftCommand implements CommandExecutor {

    private final HashMap<UUID, Long> cooldowns = new HashMap<>();
    private static final long COOLDOWN_TIME = 7 * 24 * 60 * 60 * 1000; //7 Tage

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(main.NOT_PLAYER);
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("core.command.gift")) {
            player.sendMessage("§cDafür benötigst du einen höheren Rang.");
            return true;
        }

        if (args.length != 2) {
            player.sendMessage("§7Nutze: §e/gift §7<§erolle§7> §7<§eName§7>");
            return true;
        }

        String role = args[0];
        String targetName = args[1];
        Player targetPlayer = Bukkit.getPlayer(targetName);

        if (targetPlayer == null) {
            player.sendMessage("§7Der Spieler §e" + targetName + " §7ist nicht online.");
            return true;
        }

        if (!role.equalsIgnoreCase("premium")) {
            player.sendMessage("§cF:  Diese Rolle existiert nicht.");
            return true;
        }

        UUID playerUUID = player.getUniqueId();
        long currentTime = System.currentTimeMillis();

        if (cooldowns.containsKey(playerUUID) && (currentTime - cooldowns.get(playerUUID)) < COOLDOWN_TIME) {
            long timeLeft = (COOLDOWN_TIME - (currentTime - cooldowns.get(playerUUID))) / 1000;
            player.sendMessage("§7Du kannst erst in §e" + timeLeft / 60 / 60 + " §7Stunden und §e" + (timeLeft / 60 % 60) + " §7Minuten wieder jemanden beschenken.");
            return true;
        }

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + targetName + " parent addtemp litepremium 7d");
        player.sendMessage("§7Du hast §b" + targetName + " §7erfolgreich §6Premium §7für 7d geschenkt!");

        cooldowns.put(playerUUID, currentTime);

        return true;
    }
}

