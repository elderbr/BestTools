package com.mc.besttools;

import com.mc.besttools.commands.CommandBlock;
import com.mc.besttools.commands.JogadorCommand;
import com.mc.besttools.commands.JogadorTabCommand;
import com.mc.besttools.controllers.*;
import com.mc.besttools.dao.Configuration;
import com.mc.besttools.events.BreakBlockEvent;
import com.mc.besttools.events.JoinPlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class BestTools extends JavaPlugin implements Listener {

    private Block blockBraek;
    JogadorController jogadorController;
    private Player myPlayer;

    private PicaretaController picaretaController;
    private MachadoController machadoController;
    private PaController paController;
    private EnxadaController enxadaController;

    private TesouraController tesouraController;

    @Override
    public void onEnable() {

        saveDefaultConfig();
        Configuration.saveDefault();

        jogadorController = new JogadorController();

        // Controle do Picareta
        picaretaController = new PicaretaController();

        // Controle do Machado
        machadoController = new MachadoController();
        machadoController.create();

        // Controle da Pá
        paController = new PaController();
        paController.create();

        // Controle da Enxada
        enxadaController = new EnxadaController();
        enxadaController.create();

        // Tesoura
        tesouraController = new TesouraController();
        tesouraController.create();

        picaretaController.create();

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new JoinPlayer(), this);
        getServer().getPluginManager().registerEvents(new BreakBlockEvent(), this);

        getCommand("addBlock").setExecutor(new CommandBlock());
        getCommand("removeBlock").setExecutor(new CommandBlock());
        getCommand("activated").setExecutor(new JogadorCommand());
        getCommand("activated").setTabCompleter(new JogadorTabCommand());
        getCommand("addadm").setExecutor(new JogadorCommand());
        getCommand("removeadm").setExecutor(new JogadorCommand());
    }



    @Override
    public void onDisable() {
        picaretaController.update();
        machadoController.update();
        paController.update();
        enxadaController.update();
        tesouraController.update();
    }
}
