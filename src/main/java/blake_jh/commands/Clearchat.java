package blake_jh.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Clearchat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /clearchat");
            return true;
        }

        // Clear chat for all players
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i < 100; i++) {
                player.sendMessage("");
            }
        }

        // Send message to all players
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&4&lMODERATION:&r Chat has been cleared by " + sender.getName()));

        return true;
    }
}