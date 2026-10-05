package com.mineskopia.antiendermites.listeners;

import com.mineskopia.antiendermites.AntiEndermites;
import com.mineskopia.antiendermites.ConfigData;
import com.mineskopia.antiendermites.WorldFilterType;
import org.bukkit.Location;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class EndermiteListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEndermanTargetEndermite(final EntityTargetEvent e) {
        if (e.getEntityType().equals(EntityType.ENDERMAN) && e.getTarget() != null && e.getTarget().getType().equals(EntityType.ENDERMITE)) {
            final Endermite endermite = (Endermite) e.getTarget();
            final Location loc = endermite.getLocation();
            final ConfigData config = AntiEndermites.getInstance().getConfigData();

            if (config.isFilterworldsEnabled()) {

                if (config.getFilterWorldsType() == WorldFilterType.BLACKLIST) {
                    if (config.getFilterWorldsList().contains(loc.getWorld().getName())) {
                        return;
                    }

                } else {
                    if (!config.getFilterWorldsList().contains(loc.getWorld().getName())) {
                        return;
                    }
                }
            }


            if (config.isParticleEffectEnabled()) {
                endermite.getWorld().spawnParticle(config.getParticle(), loc, config.getParticleCount());
            }

            if (config.isSoundEffectEnabled()) {
                endermite.getWorld().playSound(loc, config.getSoundEffectId(), config.getSoundEffectVolume(), config.getSoundEffectPitch());
            }

            endermite.remove();

            if (config.isLogEnabled()) {
                AntiEndermites.getInstance().getLogger().info("Endermite removed in: " + loc.getWorld().getName() + "(X: " + loc.getBlockX() + ", Y: " + loc.getBlockY() + ", Z: " + loc.getBlockZ() + ")");
            }

            e.setCancelled(true);
        }
    }

}
