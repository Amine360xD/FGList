package net.fglist.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Whitelist implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String labe, String [] args) {
        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to run this command.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Please specify a valid mode.");
            return true;
        }

        if (args[0].equals("add")) { // add a player to the whitelist
            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "Please enter a valid player name.");
                return true;
            }

            if (Bukkit.getPlayer(args[1]) != null) {
                Player player = Bukkit.getPlayer(args[1]);
                player.setWhitelisted(true);
                Bukkit.reloadWhitelist();
                sender.sendMessage(ChatColor.AQUA + "Added " + ChatColor.YELLOW + args[1] + ChatColor.AQUA + " to whitelist.");
            } else {
                OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                player.setWhitelisted(true);
                Bukkit.reloadWhitelist();
                sender.sendMessage(ChatColor.AQUA + "Added " + ChatColor.YELLOW + args[1] + ChatColor.AQUA + " to whitelist.");
            }

        } else if (args[0].equals("remove")) { // remove a player from the whitelist
            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "Please enter a valid player name.");
                return true;
            }

            if (Bukkit.getPlayer(args[1]) != null) {
                Player player = Bukkit.getPlayer(args[1]);
                player.setWhitelisted(false);
                Bukkit.reloadWhitelist();

                if (Bukkit.hasWhitelist()) {
                    if (!Bukkit.getWhitelistedPlayers().contains(player)) {
                        player.kickPlayer("You are not whitelisted.");
                    }
                }

                sender.sendMessage(ChatColor.RED + "Removed " + ChatColor.YELLOW + args[1] + ChatColor.RED + " from whitelist.");
            } else {
                OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                player.setWhitelisted(false);
                Bukkit.reloadWhitelist();
                sender.sendMessage(ChatColor.RED + "Removed " + ChatColor.YELLOW + args[1] + ChatColor.RED + " from whitelist.");
            }

        } else if (args[0].equals("on")) { // enable the whitelist (so event listener can detect)
            if (sender instanceof Player) { // self-whitelist to not get insta-kicked :P
                Player player = (Player) sender;
                if (!player.isWhitelisted()) {
                    player.setWhitelisted(true);
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "Self whitelisted.");
                }
            }

            Bukkit.reloadWhitelist();
            Bukkit.setWhitelist(true);

            for (Player player : Bukkit.getOnlinePlayers()) { // eliminate unwhitelisted players
                if (!Bukkit.getWhitelistedPlayers().contains(player)) {
                    player.kickPlayer("You are not whitelisted.");
                }
            }

            sender.sendMessage(ChatColor.DARK_GREEN + "Enabled whitelist.");

        } else if (args[0].equals("off")) { // disable the whitelist (so event listener can detect)
            Bukkit.setWhitelist(false);
            sender.sendMessage(ChatColor.BLUE + "Disabled whitelist.");

        }

        return true;
    }
}
