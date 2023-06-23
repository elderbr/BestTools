package com.mc.besttools.dao;

import com.mc.besttools.interfaces.Data;
import com.mc.besttools.model.Jogador;
import com.mc.besttools.model.Msg;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JogadorDao implements Data {

    private final File FILE_PLAYER = new File(ARQUIVO, "jogador.yml");
    private final YamlConfiguration config;

    private Jogador jogador;

    public JogadorDao(){
        if(!FILE_PLAYER.exists()){
            try {
                FILE_PLAYER.createNewFile();
            } catch (IOException e) {
                Msg.ServidorErro("Erro ao criar o arquivo jogador.yml!!!", "", getClass(), e);
            }
        }
        config = YamlConfiguration.loadConfiguration(FILE_PLAYER);
    }

    public boolean add(String name){
        jogador = new Jogador(name);
        config.set(name.concat(".activated"), jogador.isActivated());
        save();
        jogador.add();
        return (findByPlayer(name)!=null);
    }

    public void update(Player player, boolean activated){
        config.set(player.getName().concat(".activated"), activated);
        save();
        jogador = new Jogador(player.getName(), activated);
        jogador.update();
    }

    public boolean remove(String name){
        jogador = new Jogador(name);
        config.set(jogador.getName(), null);
        save();
        jogador.remove();
        return (findByPlayer(name)==null);
    }

    public Jogador findByPlayer(String name){
        MemorySection memory = (MemorySection) config.get(name);
        jogador = new Jogador();
        jogador.setName(name);
        jogador.setActivated(memory.getBoolean("activated"));
        return jogador;
    }

    public List<Jogador> findAll(){
        JOGADOR_MAP.clear();
        for (Map.Entry<String, Object> map : config.getValues(false).entrySet()){
            MemorySection memory = (MemorySection) map.getValue();
            jogador = new Jogador();
            jogador.setName(map.getKey());
            jogador.setActivated(memory.getBoolean("activated"));
            JOGADOR_MAP.put(jogador.getName(), jogador);
        }
        return new ArrayList<Jogador>(JOGADOR_MAP.values());
    }

    private void save(){
        try {
            config.save(FILE_PLAYER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
