package blake_jh.vsmp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class VSMP extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Shutting down Vitalize SMP");
    }
}