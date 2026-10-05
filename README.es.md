# AntiEndermites
[English](https://github.com/Ubuntuomega/AntiEndermites/blob/main/README.md) | [Español](https://github.com/Ubuntuomega/AntiEndermites/blob/main/README.es.md)
<br>
Un plugin para Spigot (¡y derivados!) 1.21.11 y superior que elimina a los Endermites que son objetivos de Endermans, rompiendo las granjas de experiencia que usan Endermites.

## Características
- Elimina a los endermites cuando son targeteados por un enderman.
- Imprime la ubicación de los endermites eliminados en la consola (configurable).
- Lista blanca y Lista negra de mundos.
- ¡Efectos guays cuando un Endermite es eliminado por el plugin! (Sonidos y Partículas).

## Comandos y permisos
|Comando|Descripción|Permiso|
|--|--|--|
|/antiendermites reload|Recarga la configuración|antiendermites.admin|

## Configuración

```yaml
# Imprime la ubicación de los endermites en la consola cuando son eliminados por el plugin.
log-enabled: true

filter-worlds:
  # Si se desactiva, el plugin afectará a todos los mundos.
  enabled: true

  # Establece si el filtro es considerado una lista blanca o una lista negra. Tipos permitidos:
  # Blacklist: Todos los mundos, excepto los especificados, se verán afectados.
  # Whitelist: Sólo los mundos especificados se verán afectados.
  type: whitelist
  worlds:
  - World_the_end

# Efectos especiales cuando el plugin elimina a un endermite.
effects:

  particles:
    # Si se spawnearán partículas en la ubicación del endermite.
    enabled: true

    # La partícula que se usará.
    # Listado: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Particle.html
    # ¡Aviso! Si la partícula necesita un DataType extra (Color, etc), no funcionará.
    particle: POOF

    # La cantidad de partículas que se spawnearán. Como mínimo debe ser 1.
    count: 10

  sound:
    # Si se reproducirá algún sonido en la ubicación del endermite.
    enabled: false
    id: "minecraft:entity.generic.explode"
    pitch: 1
    volume: 1
```

## AI Disclosure
No me gusta cómo se utiliza la IA para vomitar código sin ningún tipo de supervisión ni sentido. El código de este repositorio ha sido escrito enteramente por mí, por y para humanos.
