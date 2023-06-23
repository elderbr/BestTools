package com.mc.besttools.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class JogadorTabCommand implements TabCompleter {
    private final String[] activatedList = {"true", "false"};

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (sender instanceof Player player) {
            if (command.getName().equalsIgnoreCase("activated")) {
                return Arrays.asList(activatedList);
            }
        }
        return null;
    }
}
