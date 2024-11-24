package net.fglist;

import net.fglist.commands.Whitelist;
import net.fglist.commands.WhitelistTabCompleter;
import net.fglist.handlers.PlayerJoinHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

// Aminium is the best dev ever :cap:

public final class FGList extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("FGList is online.");

        new PlayerJoinHandler(this);

        getCommand("fglist").setExecutor(new Whitelist());
        getCommand("fglist").setTabCompleter(new WhitelistTabCompleter());
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("FGList is offline.");
    }
}
