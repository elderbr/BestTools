package com.mc.besttools.events;

import com.mc.besttools.controllers.JogadorController;
import com.mc.besttools.exceptions.ToolsException;
import com.mc.besttools.interfaces.Data;
import com.mc.besttools.model.Jogador;
import com.mc.besttools.model.Msg;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinPlayer implements Listener, Data {

    private Jogador jogador;
    private JogadorController jogadorController = new JogadorController();

    @EventHandler
    public void join(PlayerJoinEvent event){
        jogador = new Jogador(event.getPlayer());
        try {
            jogadorController.add(jogador.getName());
        } catch (ToolsException e) {
            Msg.ServidorRed(e.getMessage());
        }
    }

}
