package net.fglist.handlers;

import net.fglist.FGList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinHandler implements Listener {
    public PlayerJoinHandler(FGList plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) { // get when a player joins the server
        Player player = event.getPlayer();

        if (Bukkit.hasWhitelist()) {
            if (Bukkit.getWhitelistedPlayers().contains(player)) {
                player.sendMessage(ChatColor.DARK_PURPLE + "Welcome back, " + ChatColor.YELLOW + player.getName());
            } else {
                player.kickPlayer(ChatColor.DARK_RED + "You are not whitelisted.");
            }
        } else {
            player.sendMessage(ChatColor.DARK_PURPLE + "Welcome back, " + ChatColor.YELLOW + player.getName());
        }
    }
}
