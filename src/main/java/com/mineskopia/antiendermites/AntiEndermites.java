package com.mineskopia.antiendermites;

import com.mineskopia.antiendermites.commands.ReloadCommand;
import com.mineskopia.antiendermites.listeners.EndermiteListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiEndermites extends JavaPlugin {

    private static AntiEndermites intance;
    private ConfigData configData;

    public void onEnable() {
        AntiEndermites.intance = this;
        this.getServer().getLogger().info("Loading AntiEndermites...");

        saveDefaultConfig();
        configData = new ConfigData(getConfig());

        this.getServer().getPluginManager().registerEvents(new EndermiteListener(), this);

        getCommand("antiendermites").setExecutor(new ReloadCommand());
    }

    public void onDisable() {
        this.getServer().getLogger().info("Unloading AntiEndermites...");
    }

    public static AntiEndermites getInstance() {
        return AntiEndermites.intance;
    }

    public void reloadConfiguration() {
        reloadConfig();
        configData = new ConfigData(getConfig());
    }

    public ConfigData getConfigData() {
        return configData;
    }
}
