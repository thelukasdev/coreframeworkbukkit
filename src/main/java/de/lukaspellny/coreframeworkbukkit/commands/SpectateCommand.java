package de.lukaspellny.coreframeworkbukkit.commands;


import de.lukaspellny.coreframeworkbukkit.main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class SpectateCommand implements CommandExecutor {

    private static final String ONLY_PLAYERS = "§cDieser Befehl kann nur von einem Spieler ausgeführt werden.";
    private static final String CANNOT_SPECTATE_SELF = "§cF: Du kannst dich nicht selbst verfolgen.";
    private static final String SPECTATING_STOPPED = "§7Du wurdest zum §eAusgangspunkt§7 zurück teleportiert.";
    private static final String NO_LONGER_SPECTATING = "§7Du verfolgst nun keinen Spieler mehr.";

    private final Map<Player, Location> previousLocations = new HashMap<>();
    private final Map<Player, Player> spectatingPlayers = new HashMap<>();
    private JavaPlugin plugin = null;

    public SpectateCommand() {
        this.plugin = plugin;
        startSpectateCheckTask();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ONLY_PLAYERS);
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("core.command.spectate")) {
            player.sendMessage(main.NO_PERMISSION);
            return true;
        }

        if (spectatingPlayers.containsKey(player)) {
            stopSpectating(player);
            spectatingPlayers.remove(player);
            player.sendMessage(NO_LONGER_SPECTATING);
            return true;
        }

        if (args.length < 1) {
            player.sendMessage("§cBitte gebe einen Spieler an, den du verfolgen möchtest.");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage("§cF: Der Spieler ist nicht online.");
            return true;
        }

        if (player.equals(target)) {
            player.sendMessage(CANNOT_SPECTATE_SELF);
            return true;
        }

        startSpectating(player, target);
        return true;
    }

    private void startSpectating(Player player, Player target) {
        previousLocations.put(player, player.getLocation());
        player.setGameMode(GameMode.SPECTATOR);
        player.setSpectatorTarget(target);
        spectatingPlayers.put(player, target);

        player.sendMessage("§7Du verfolgst nun §e" + target.getName() + "§7.");
    }

    private void stopSpectating(Player player) {
        if (previousLocations.containsKey(player)) {
            player.teleport(previousLocations.get(player));
            player.setGameMode(GameMode.SURVIVAL);
            previousLocations.remove(player);
        }
        player.sendMessage(SPECTATING_STOPPED);
    }

    private void startSpectateCheckTask() {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Player player : spectatingPlayers.keySet()) {
                Player target = spectatingPlayers.get(player);

                if (!target.isOnline()) {
                    stopSpectating(player);
                    spectatingPlayers.remove(player);
                } else {
                    player.setSpectatorTarget(target);
                }
            }
        }, 0L, 20L);
    }
}
