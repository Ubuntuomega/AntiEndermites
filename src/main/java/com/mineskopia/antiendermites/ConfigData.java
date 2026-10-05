package com.mineskopia.antiendermites;

import org.bukkit.Particle;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public class ConfigData {

    private boolean logEnabled;

    private boolean filterworldsEnabled;
    private WorldFilterType filterWorldsType;
    private List<String> filterWorldsList;


    private boolean particleEffectEnabled;
    private Particle particle;
    private int particleCount;

    private boolean soundEffectEnabled;
    private String soundEffectId;
    private float soundEffectPitch;
    private float soundEffectVolume;

    public ConfigData(FileConfiguration fileConfiguration) {
        Logger logger = AntiEndermites.getInstance().getLogger();
        logger.info("Loading configuration...");

        if (!fileConfiguration.contains("log-enabled")) {
            logger.warning("'log-enabled' section in config doesn't exist! Using default value...");
        }

        logEnabled = fileConfiguration.getBoolean("log-enabled", false);

        // --- World Filter section ---
        ConfigurationSection worldsSection = fileConfiguration.getConfigurationSection("filter-worlds");
        if (worldsSection != null) {
            filterworldsEnabled = worldsSection.getBoolean("enabled", false);

            if (filterworldsEnabled) {
                // World Filter Type
                try {
                    filterWorldsType = WorldFilterType.valueOf(worldsSection.getString("type", "whitelist").toUpperCase(Locale.ROOT));
                } catch (IllegalArgumentException e) {
                    logger.warning("filter-worlds.type '" + worldsSection.getString("type") + "' unknown! Values allowed: <whitelist|blacklist>. Using default value...");
                    filterWorldsType = WorldFilterType.WHITELIST;
                }

                // World list
                filterWorldsList = worldsSection.getStringList("worlds");
                logger.info("Loaded " + filterWorldsList.size() + " worlds in the world filter!");
            }
        } else {
            logger.warning("Section 'filter-worlds' not found in config.yml!");
            filterworldsEnabled = false;
        }


        // --- Effects section ---
        ConfigurationSection effectsSection = fileConfiguration.getConfigurationSection("effects");
        if (effectsSection != null) {
            ConfigurationSection particleSection = effectsSection.getConfigurationSection("particles");
            if (particleSection != null) {
                particleEffectEnabled = particleSection.getBoolean("enabled");

                particleCount = particleSection.getInt("count", 1);
                if (particleCount < 1) {
                    logger.severe("effects.particles.count must be at lest 1! Using default...");
                    particleCount = 1;
                }


                try {
                    particle = Particle.valueOf(particleSection.getString("particle"));
                } catch (IllegalArgumentException e) {
                    logger.severe("Unknown particle '" + particleSection.getString("particle") + "' in effects.particles.particle! Using default...");
                    particle = Particle.POOF;
                    throw new RuntimeException(e);
                }
            }


            ConfigurationSection soundEffectSection = effectsSection.getConfigurationSection("sound");
            if (soundEffectSection != null) {
                soundEffectEnabled = soundEffectSection.getBoolean("enabled", false);
                soundEffectId = soundEffectSection.getString("id");
                soundEffectPitch = (float) soundEffectSection.getDouble("pitch");
                soundEffectVolume = (float) soundEffectSection.getDouble("volume");
            } else {
                logger.warning("Section 'effects.sound' not found in config.yml!");
                soundEffectEnabled = false;
            }
        }

        logger.info("Configuration loaded!");


    }


    public boolean isLogEnabled() {
        return logEnabled;
    }

    public boolean isFilterworldsEnabled() {
        return filterworldsEnabled;
    }

    public WorldFilterType getFilterWorldsType() {
        return filterWorldsType;
    }

    public List<String> getFilterWorldsList() {
        return filterWorldsList;
    }

    public boolean isParticleEffectEnabled() {
        return particleEffectEnabled;
    }

    public boolean isSoundEffectEnabled() {
        return soundEffectEnabled;
    }

    public String getSoundEffectId() {
        return soundEffectId;
    }

    public float getSoundEffectPitch() {
        return soundEffectPitch;
    }

    public float getSoundEffectVolume() {
        return soundEffectVolume;
    }

    public Particle getParticle() {
        return particle;
    }

    public int getParticleCount(){
        return particleCount;
    }

    private void checkIfSectionExists(ConfigurationSection parent, String child) {
        if (!parent.contains(child)) {
            AntiEndermites.getInstance().getLogger().warning(parent.getCurrentPath() + "." + child + " doesn't exist! Using default value...");
        }
    }

}
