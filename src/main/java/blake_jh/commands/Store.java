package blake_jh.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Store implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command.");
            return true;
        }

        Player player = (Player) sender;

        if (player.hasPermission("vitalize.store")) {
            TextComponent message = new TextComponent("VITALIZESMP: ");
            message.setColor(ChatColor.DARK_PURPLE.asBungee());
            message.setBold(true);

            TextComponent link = new TextComponent("Click here to go to our store!");
            link.setColor(ChatColor.WHITE.asBungee());
            link.setBold(false);
            link.setUnderlined(true);
            link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://vitalizesmp.tebex.io/"));

            message.addExtra(link);
            player.spigot().sendMessage(message);
        }

        return true;
    }
}