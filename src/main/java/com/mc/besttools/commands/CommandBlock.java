package com.mc.besttools.commands;

import com.mc.besttools.controllers.*;
import com.mc.besttools.dao.Configuration;
import com.mc.besttools.exceptions.ToolsException;
import com.mc.besttools.model.Enxada;
import com.mc.besttools.model.Tools;
import com.mc.besttools.model.Msg;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static com.mc.besttools.interfaces.Data.ADM_LIST;

public class CommandBlock implements CommandExecutor {

    private PicaretaController picaretaController = new PicaretaController();
    private MachadoController machadoController = new MachadoController();

    private PaController paController = new PaController();

    private EnxadaController enxadaController = new EnxadaController();
    private TesouraController tesouraController = new TesouraController();

    private Player myPlayer;
    private JogadorController jogadorController = new JogadorController();
    private ItemStack tools = new ItemStack(Material.AIR);
    private Block block;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (sender instanceof Player player) {

            myPlayer = player;
            tools = myPlayer.getInventory().getItemInMainHand();
            block = myPlayer.getTargetBlock(null, 5);

            switch (command.getName().toLowerCase()) {
                case "addblock":

                    if(!ADM_LIST.contains(myPlayer.getName())){
                        Msg.PlayerRed(myPlayer, "Ops, você não um adm!");
                        return false;
                    }



                    switch (Tools.Type(tools)) {
                        case "pickaxe":
                            addPickaxe();
                            break;
                        case "axe":
                            addAxe();
                            break;
                        case "shovel":
                            addShovel();
                            break;
                        case "hoe":
                            addHoe();
                            break;
                        case "shears":
                            addShears();
                            break;
                    }
                    break;
                case "removeblock":
                    if(!ADM_LIST.contains(myPlayer.getName())){
                        Msg.PlayerRed(myPlayer, "Ops, você não um adm!");
                        return false;
                    }
                    switch (Tools.Type(tools)) {
                        case "pickaxe":
                            removePickaxe();
                            break;
                        case "axe":
                            removeAxe();
                            break;
                        case "shavel":
                            removeShovel();
                            break;
                        case "hoe":
                            removeHoe();
                            break;
                        case "shears":
                            removeShears();
                            break;
                    }
                    break;
                default:
                    return false;
            }
        }
        return false;
    }

    private void addPickaxe() {
        try {
            if (picaretaController.addBlock(myPlayer)) {
                Msg.PlayerTodos(String.format("Foi adicionado novo bloco %s na lista de picareta!!!", picaretaController.toBlock(block)));
            } else {
                Msg.PlayerGold(myPlayer, "Ocorreu um erro ao adicionar o bloco!!!");
            }
        } catch (Exception e) {
            Msg.PlayerRed(myPlayer, e.getMessage());
            Msg.ServidorErro("Erro ao adicionar o bloco na lista da Picareta", "add()", getClass(), e);
        }
    }

    private void removePickaxe() {
        try {
            if (picaretaController.removeBlock(myPlayer)) {
                Msg.PlayerTodos("Novo bloco adicionado para lista da picareta " + machadoController.toMaterial(block.getType()));
            } else {
                Msg.PlayerRed(myPlayer, "Ocorreu um erro ao remover o bloco!!!");
            }
        } catch (Exception e) {
            Msg.PlayerRed(myPlayer, e.getMessage());
            Msg.ServidorErro("Erro ao remover o bloco da picareta!!!", "removePickaxe()", getClass(), e);
        }
    }

    private void addAxe() {
        try {
            if (machadoController.addBlock(myPlayer)) {
                Msg.PlayerTodos(String.format("Foi adicionado novo bloco %s na lista do machado ", machadoController.toBlock(block)));
            } else {
                Msg.PlayerRed(myPlayer, "Erro ao adicionar o bloco!!!");
            }
        } catch (Exception e) {
            Msg.PlayerRed(myPlayer, e.getMessage());
            Msg.ServidorErro("Erro ao adicionar o bloco na lista do machado!!!", "addAxe()", getClass(), e);
        }
    }

    private void removeAxe() {
        try {
            if (machadoController.removeBlock(myPlayer)) {
                Msg.PlayerTodos(String.format("O bloco %s foi removido da lista do machado!!!", machadoController.toBlock(block)));
            } else {
                Msg.PlayerRed(myPlayer, String.format("Ocorreu um erro ao removo o bloco %s!!!", machadoController.toBlock(block)));
            }
        } catch (Exception e) {
            Msg.PlayerRed(myPlayer, e.getMessage());
            Msg.ServidorErro("Erro ao remover o bloco da lista de machado!!!", "removeAxe()", getClass(), e);
        }
    }

    private void addShovel() {
        try {
            if(paController.addBlock(myPlayer)){
                Msg.PlayerTodos(String.format("Foi adicionado novo bloco %s na lista da pá!!!", paController.toBlock(block)));
            }else{
                Msg.PlayerRed(myPlayer, "Não foi possivél adicionar o bloco na lista!!!");
            }
        } catch (Exception e) {
            Msg.PlayerRed(myPlayer, e.getMessage());
            Msg.ServidorErro("Erro ao adicionar o bloco na lista da pá", "addShovel()", getClass(), e);
        }
    }

    private void removeShovel() {
        try {
            if(paController.removeBlock(myPlayer)){
                Msg.PlayerTodos(String.format("Foi removido bloco %s da lista da pá!!!", paController.toBlock(block)));
            }else{
                Msg.PlayerRed(myPlayer, "Não foi possivél remover o bloco na lista!!!");
            }
        } catch (ToolsException e) {
            Msg.PlayerRed(myPlayer, e.getMessage());
            Msg.ServidorErro("Erro ao remover o bloco na lista da pá", "removeShovel()", getClass(), e);
        }
    }

    private void addHoe(){
        try{
            if(enxadaController.addBlock(myPlayer)){
                Msg.PlayerTodos(String.format("O bloco %s foi adicionado para a ferramenta enxada!!!", enxadaController.toBlock(block)));
            }else{
                Msg.PlayerRed(myPlayer, String.format("O bloco %s não pode ser adicionado!!!"));
            }
        }catch (ToolsException e){
            Msg.PlayerRed(myPlayer, e.getMessage());
            Msg.ServidorErro("Erro ao adicionar o bloco da ferramenta enxada!!!", "addHoe()", getClass(), e);
        }
    }

    private void removeHoe(){
        try {
            if(enxadaController.removeBlock(myPlayer)){
            Msg.PlayerTodos(String.format("O bloco %s foi removido da ferramenta enxada!!!", enxadaController.toBlock(block)));
            }else{
                Msg.PlayerRed(myPlayer, String.format("Não foi possivél remover o bloco %s da ferramenta enxada!!!", enxadaController.toBlock(block)));
            }
        } catch (ToolsException e) {
            Msg.PlayerRed(myPlayer, e.getMessage());
            Msg.ServidorErro("Erro ao remover o bloco da ferramenta enxada!!!", "removeHoe()", getClass(), e);
        }
    }

    private void addShears(){
        try {
            tesouraController.addBlock(myPlayer);
            Msg.PlayerTodos(String.format("O bloco %s foi adicionado para a ferramenta tesoura!!!", tesouraController.toBlock(block)));
        } catch (ToolsException e) {
            Msg.PlayerRed(myPlayer, e.getMessage());
        }
    }

    private void removeShears(){
        try {
            tesouraController.removeBlock(myPlayer);
            Msg.PlayerTodos(String.format("O bloco %s foi removido da ferramenta tesoura!!!", tesouraController.toBlock(block)));
        } catch (ToolsException e) {
            Msg.PlayerRed(myPlayer, e.getMessage());
        }
    }
}
