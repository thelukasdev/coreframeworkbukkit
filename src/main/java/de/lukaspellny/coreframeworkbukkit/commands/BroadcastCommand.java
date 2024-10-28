package de.lukaspellny.coreframeworkbukkit.commands;



import de.lukaspellny.coreframeworkbukkit.main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(main.NOT_PLAYER);
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("core.command.broadcast")) {
            player.sendMessage(main.NO_PERMISSION);
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("§7Nutze §e/bc §7<§etext§7>");
            return true;
        }

        String message = String.join(" ", args);

        Bukkit.broadcastMessage("§7");
        Bukkit.broadcastMessage("§4§lINFO §8» §7");
        Bukkit.broadcastMessage("§4§lINFO §8» §7" + message);
        Bukkit.broadcastMessage("§4§lINFO §8» §7");
        Bukkit.broadcastMessage("§7");
        return true;
    }
}
