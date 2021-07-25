package bg.hollyweed.mobsignore;

import bg.hollyweed.mobsignore.commands.ReloadCommandExecutor;
import bg.hollyweed.mobsignore.listeners.EntityDamageByEntityEventListener;
import bg.hollyweed.mobsignore.listeners.EntityTargetEventListener;
import org.bukkit.configuration.Configuration;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class MobsIgnore extends JavaPlugin {

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        getLogger().info("MobsIgnore has been disabled!");
    }

    public void onReload() {
        HandlerList.unregisterAll(this);
        this.reloadConfig();
        registerListeners();
        getLogger().info("MobsIgnore has been reloaded!");
    }

    private void registerListeners() {
        Configuration config = this.getConfig();

        if (config.getBoolean("ignore.enabled")) {
            getServer().getPluginManager().registerEvents(new EntityTargetEventListener(), this);
        }
        if (config.getBoolean("shield.enabled")) {
            double damageModifier = config.getDouble("shield.damageModifier");
            getServer().getPluginManager().registerEvents(new EntityDamageByEntityEventListener(damageModifier), this);
        }
    }

    private void registerCommands() {
        this.getCommand("mobsignorereload").setExecutor(new ReloadCommandExecutor(this));
    }
}
