package blake_jh.vsmp;

import blake_jh.commands.Announce;
import blake_jh.commands.Boost;
import blake_jh.commands.Clearchat;
import blake_jh.commands.Store;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class VSMP extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("store").setExecutor(new Store());
        getCommand("announce").setExecutor(new Announce());
        getCommand("clearchat").setExecutor(new Clearchat());
        getCommand("boost").setExecutor(new Boost(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Shutting down Vitalize SMP");
    }
}
