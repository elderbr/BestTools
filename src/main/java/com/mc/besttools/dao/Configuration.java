package com.mc.besttools.dao;

import com.mc.besttools.exceptions.ToolsException;
import com.mc.besttools.interfaces.Data;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class Configuration implements Data {
    public final static YamlConfiguration CONFIG;

    static {

        if (!FILE_CONFIG.exists()) {
            try {
                FILE_CONFIG.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        CONFIG = CONFIG_DEFAULT;
        findByAdm();
    }

    public static void saveDefault() {
        CONFIG.options().setHeader(Arrays.asList("Sistema criado para ajudar a selecionar a melhor ferramenta para quebrar os itens"));
        CONFIG.set("author", "ElderBR");
        CONFIG.set("Discord", "ElderBR#5398");
        CONFIG.set("github", "https://github.com/elderbr");

        CONFIG.set("version", MyPlugin.getDescription().getVersion());
        CONFIG.setComments("version", Arrays.asList("Versão do plugin BestTools"));

        CONFIG.set("Comandos", Arrays.asList("addBlock", "removeBlock", "activated"));
        CONFIG.setComments("Comandos", Arrays.asList("Para adicionar ou remover o item na lista, precisa segurar a ferramenta na mão", "Lista de comandos"));

        CONFIG.set("adm", ADM_LIST);
        CONFIG.setComments("adm", Arrays.asList("Lista de administrador do plugin BestTools"));

        save();

        // Percorrendo a lista de material
        for (Material material : Material.values()) {
            if (!material.isBlock()) continue;
            PickaxeListMaterial.add(material.name());// Adicionando o material na lista global
        }
    }

    public static void addAdm(String[] namePlayer) throws ToolsException {
        if(namePlayer.length < 1){
            throw new ToolsException("Escreva o nome do jogador!!!");
        }
        String name = namePlayer[0];
        if(name.length() < 6){
            throw new ToolsException("O nome do jogador precisa ter mais do que 5 letras!!!");
        }
        if(ADM_LIST.contains(name)){
            throw new ToolsException("O jogador "+ name +" já está na lista de Adm!!!");
        }
        ADM_LIST.add(name);
        Collections.sort(ADM_LIST);
        CONFIG.set("adm", ADM_LIST);
        save();
    }

    public static void removeAdm(String[] namePlayer) throws ToolsException {
        if(namePlayer.length < 1){
            throw new ToolsException("Escreva o nome do jogador!!!");
        }
        String name = namePlayer[0];
        if(name.length() < 6){
            throw new ToolsException("O nome do jogador precisa ter mais do que 5 letras!!!");
        }
        if(!ADM_LIST.contains(name)){
            throw new ToolsException("O jogador "+ name +" não está na lista de Adm!!!");
        }
        ADM_LIST.remove(name);
        Collections.sort(ADM_LIST);
        CONFIG.set("adm", ADM_LIST);
        save();
    }

    public static void findByAdm(){
        ADM_LIST.clear();
        for(Object obj : CONFIG.getList("adm")){
            ADM_LIST.add(obj.toString());
        }
    }


    private static void save() {
        try {
            CONFIG.save(FILE_CONFIG);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
