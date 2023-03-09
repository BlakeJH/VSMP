package blake_jh.commands;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Boost implements CommandExecutor {

    private final Plugin plugin;
    private int boosts = 0;
    private final Map<UUID, Instant> lastBoost = new HashMap<>();
    private boolean bypassTimeLimit = false; // Add this variable

    public Boost(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command.");
            return true;
        }

        Player player = (Player) sender;

        // Check if the player has the "vitalize.boost" permission or is an operator
        if (!player.hasPermission("vitalize.boost") && !player.isOp()) {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        Instant lastBoostTime = lastBoost.getOrDefault(player.getUniqueId(), Instant.EPOCH);
        Instant currentTime = Instant.now();

        // Check if the player is an operator and the "bypass" argument was passed
        if (player.isOp() && args.length > 0 && args[0].equalsIgnoreCase("bypass")) {
            // Allow the player to bypass the time limit
            bypassTimeLimit = true;
        }

        // Check if enough time has passed since the last boost (if not bypassing the time limit)
        if (!bypassTimeLimit) {
            long hoursSinceLastBoost = Duration.between(lastBoostTime, currentTime).toHours();

            if (hoursSinceLastBoost < 24) {
                player.sendMessage(ChatColor.RED + "You can only boost the airdrop once every 24 hours. Time left: " + (24 - hoursSinceLastBoost) + " hours");
                return true;
            }
        }

        if (++boosts < 10) {
            player.sendMessage(ChatColor.GREEN + "You have boosted the airdrop! " + ChatColor.YELLOW + (10 - boosts) + ChatColor.GREEN + " more boosts are needed to activate the airdrop.");
            lastBoost.put(player.getUniqueId(), currentTime);
            return true;
        }

        boosts = 0;
        lastBoost.put(player.getUniqueId(), currentTime);

        new BukkitRunnable() {
            int i = 0;
            Location loc = player.getLocation();
            int radius = (int) (Math.random() * 300) + 100;
            Random random = new Random();
            int x = (int) (loc.getX() + (random.nextInt(radius * 2) - radius));
            int z = (int) (loc.getZ() + (random.nextInt(radius * 2) - radius)); // Fixed missing parenthesis

            @Override
            public void run() {
                if (++i > 20) {
                    cancel();
                    return;
                }

                int y = loc.getWorld().getHighestBlockYAt(x, z);
                Location airdropLocation = new Location(loc.getWorld(), x, y, z);

                Bukkit.broadcastMessage(ChatColor.YELLOW + "An airdrop has been activated at " + ChatColor.RED + "(" + x + ", " + y);
            }
        };
        return true;
    }
}