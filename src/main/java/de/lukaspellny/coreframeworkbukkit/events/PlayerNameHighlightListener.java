package de.lukaspellny.coreframeworkbukkit.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerNameHighlightListener implements Listener {

    private static final String PERMISSION = "core.chathighlight";

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player sender = event.getPlayer();

        if (!sender.hasPermission(PERMISSION)) {
            return;
        }

        String message = event.getMessage();

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            String playerName = onlinePlayer.getName();

            if (message.contains(playerName)) {
                String highlightedName = ChatColor.AQUA + "@" + playerName + ChatColor.GRAY;
                message = message.replace(playerName, highlightedName);
            }
        }

        event.setMessage(message);
    }
}
