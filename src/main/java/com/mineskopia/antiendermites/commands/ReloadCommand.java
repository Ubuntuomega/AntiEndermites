package com.mineskopia.antiendermites.commands;

import com.mineskopia.antiendermites.AntiEndermites;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.awt.*;
import java.util.List;

public class ReloadCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            TextComponent message = new TextComponent("You must provide at least one argument!");
            message.setColor(ChatColor.RED);

            sender.spigot().sendMessage(message);
        }

        if (args[0].equalsIgnoreCase("reload")) {
            AntiEndermites.getInstance().reloadConfiguration();

            TextComponent message = new TextComponent("Plugin reloaded successful!");
            message.setColor(ChatColor.GREEN);

            sender.spigot().sendMessage(message);
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            return List.of("reload");
        }

        return List.of();
    }
}
