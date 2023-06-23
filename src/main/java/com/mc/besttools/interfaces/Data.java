package com.mc.besttools.interfaces;

import com.mc.besttools.model.Jogador;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.*;

public interface Data {

    Plugin MyPlugin = Bukkit.getServer().getPluginManager().getPlugin("BestTools");
    File ARQUIVO = MyPlugin.getDataFolder().getAbsoluteFile();

    // Configuração
    File FILE_CONFIG = new File(ARQUIVO, "config.yml");
    YamlConfiguration CONFIG_DEFAULT = YamlConfiguration.loadConfiguration(FILE_CONFIG);

    String[] toolsName = {"pickaxe", "axe", "shovel", "hoe", "shears"};
    List<String> PickaxeListMaterial = new ArrayList<>();
    List<String> AxeListMaterial = new ArrayList<>();
    List<String> ShovelListMaterial = new ArrayList<>();
    List<String> HoeListMaterial = new ArrayList<>();
    List<String> ShearsListMaterail = new ArrayList<>();

    List<Material> listTools = new ArrayList<>();
    List<Material> PICKAXES = new ArrayList<>();
    List<Material> AXES = new ArrayList<>();
    List<Material> SHOVELS = new ArrayList<>();
    List<Material> HOES = new ArrayList<>();

    List<String> ADM_LIST = new ArrayList<>();
    Map<String, Jogador> JOGADOR_MAP = new HashMap<>();
    default String toItemStack(ItemStack itemStack){
        return itemStack.getType().getKey().getKey().replaceAll("_"," ");
    }
    default String toMaterial(Material material){
        return material.getKey().getKey().replaceAll("_"," ");
    }

    default String toBlock(Block block){
        return block.getType().getKey().getKey().replaceAll("_"," ");
    }

    static String To_ItemStack(ItemStack itemStack){
        return itemStack.getType().getKey().getKey().replaceAll("_"," ");
    }

    static String To_Material(Material material){
        return material.getKey().getKey().replaceAll("_"," ");
    }
}
