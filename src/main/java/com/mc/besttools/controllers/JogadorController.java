package com.mc.besttools.controllers;

import com.mc.besttools.dao.JogadorDao;
import com.mc.besttools.exceptions.ToolsException;
import com.mc.besttools.interfaces.Data;
import com.mc.besttools.model.Jogador;
import org.bukkit.entity.Player;
public class JogadorController implements Data {

    private Jogador jogador = new Jogador();
    private final JogadorDao jogadorDao;

    public JogadorController() {
        jogadorDao = new JogadorDao();
        jogadorDao.findAll();
    }

    public void add(String name) throws ToolsException {
        if (name.length() < 3) {
            throw new ToolsException("Nome do jogador invalido!!!");
        }
        if (!JOGADOR_MAP.containsKey(name)) {
            jogadorDao.add(name);
        }
    }

    public void update(Player player, Object activated){
        boolean active = false;
        if(activated instanceof Boolean ativo){
            active = ativo;
        }
        if (activated instanceof String value){
            active = Boolean.parseBoolean(value);
        }
        jogadorDao.update(player, active);
    }
}
