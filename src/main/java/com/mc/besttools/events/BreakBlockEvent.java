package com.mc.besttools.events;

import com.mc.besttools.controllers.*;
import com.mc.besttools.interfaces.Data;
import com.mc.besttools.model.Jogador;
import com.mc.besttools.model.Msg;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BreakBlockEvent implements Listener, Data {

    private Block blockBraek;
    JogadorController jogadorController;
    private Player myPlayer;

    private PicaretaController picaretaController = new PicaretaController();
    private MachadoController machadoController = new MachadoController();
    private PaController paController = new PaController();
    private EnxadaController enxadaController = new EnxadaController();

    private TesouraController tesouraController = new TesouraController();


    @EventHandler
    public void blockbreck(PlayerInteractEvent event) {

        if (event.getAction() == Action.LEFT_CLICK_AIR
                || event.getAction() == Action.RIGHT_CLICK_BLOCK
                || event.getAction() == Action.RIGHT_CLICK_AIR) {
            return;
        }

        myPlayer = event.getPlayer();
        blockBraek = event.getClickedBlock();

        // Verifica se o jogador optou por usar o BestTools
        if(!JOGADOR_MAP.get(myPlayer.getName()).isActivated()){
            return;
        }

        picaretaController.brockBreak(myPlayer, blockBraek);
        machadoController.brockBreak(myPlayer, blockBraek);
        paController.brockBreak(myPlayer, blockBraek);
        enxadaController.brockBreak(myPlayer, blockBraek);
        tesouraController.brockBreak(myPlayer, blockBraek);
    }
}
