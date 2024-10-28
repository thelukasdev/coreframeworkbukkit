package de.lukaspellny.coreframeworkbukkit.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler

    public static void onQuit(PlayerQuitEvent event){

        event.setQuitMessage("");

    }
}
