# AntiEndermites
[English](https://github.com/Ubuntuomega/AntiEndermites/blob/main/README.md) | [Español](https://github.com/Ubuntuomega/AntiEndermites/blob/main/README.es.md)
<br>
A plugin for Spigot (and forks!) 1.21.11 and above that kill endermites when are targetted by Endermands, breaking the endermite-based exp farms.

## Features
- Kill Endermites when are targetted by endermans.
- Log the locations in console (configurable).
- World Whitelist and Blacklist.
- Effects when Endermites are removed by the plugin! (Sounds and Particles).

## Commands and Permissions
|Command|Description|Permission|
|--|--|--|
|/antiendermites reload|Reloads the config|antiendermites.admin|

## Configuration

```yaml
# Prints the location of the endermite in the console when it's removed.
log-enabled: true

filter-worlds:
  # If disabled, the plugin will work on all worlds.
  enabled: true

  # If this filter is considered a blacklist or a whitelist. Allowed types:
  # Blacklist: All worlds, except those specified, will be affected.
  # Whitelist: Only the specified worlds will be affected.
  type: whitelist
  worlds:
  - World_the_end

# Special effects when the plugin removes an endermite.
effects:

  particles:
    # If particles are spawned at the location of the endermite.
    enabled: true

    # The particle that will be used.
    # List: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Particle.html
    # Warning! If the particle needs an extra DataType (Color, etc), will not work!
    particle: POOF

    # The particles that will be spawned. Must be at least 1.
    count: 10

  sound:
    # If a sound is played at the location of the endermite.
    enabled: false
    id: "minecraft:entity.generic.explode"
    pitch: 1
    volume: 1
```

## AI Disclosure
I disprove the AI slop-generated and unsupervised code. This repository is developed entirely by me, by and for humans.
