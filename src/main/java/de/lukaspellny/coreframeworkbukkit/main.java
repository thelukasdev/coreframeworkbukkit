package de.lukaspellny.coreframeworkbukkit;

import de.lukaspellny.coreframeworkbukkit.commands.*;
import de.lukaspellny.coreframeworkbukkit.events.JoinListener;
import de.lukaspellny.coreframeworkbukkit.events.PlayerNameHighlightListener;
import de.lukaspellny.coreframeworkbukkit.events.QuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {

    public static final String NO_PERMISSION = "§cUnzureichende Rechte.";
    public static final String NOT_PLAYER = "§cNur Spieler können diesen Befehl ausführen.";

    @Override
    public void onEnable() {

        getLogger().info("Das Plugin COREFRAMEWORKBUKKIT wurde fehlerfrei aktiviert.");


        getCommand("chatclear").setExecutor(new ChatClearCommand());
        getCommand("day").setExecutor(new DayCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("gamemode").setExecutor(new FlyCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("kill").setExecutor(new KillCommand());
        getCommand("workbench").setExecutor(new WorkbenchCommand());
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("gift").setExecutor(new GiftCommand());
        getCommand("spectate").setExecutor(new SpectateCommand());
        getCommand("teamchat").setExecutor(new TeamChatCommand());

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new QuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerNameHighlightListener(), this);
    }

    @Override
    public void onDisable() {

        getLogger().info("Das Plugin COREFRAMEWORKBUKKIT wurde deaktiviert.");

    }
}
