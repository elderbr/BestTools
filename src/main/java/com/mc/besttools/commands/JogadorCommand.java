package com.mc.besttools.commands;

import com.mc.besttools.controllers.JogadorController;
import com.mc.besttools.dao.Configuration;
import com.mc.besttools.exceptions.ToolsException;
import com.mc.besttools.model.Msg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class JogadorCommand implements CommandExecutor {

    private JogadorController jogadorController = new JogadorController();


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if(sender instanceof Player player){
            if(command.getName().equalsIgnoreCase("activated")){
                jogadorController.update(player, args[0]);
                return false;
            }
            if(command.getName().equalsIgnoreCase("addadm") && player.isOp()){
                try {
                    Configuration.addAdm(args);
                    Msg.PlayerTodos("Agora o jogador "+ args[0] +" é adiministrador do plugin BestTools!");
                } catch (ToolsException e) {
                    Msg.PlayerRed(player, e.getMessage());
                    Msg.ServidorRed(e.getMessage());
                }
            }
        }else{
            if(command.getName().equalsIgnoreCase("addadm")){
                try {
                    Configuration.addAdm(args);
                    Msg.PlayerTodos("Agora o jogador "+ args[0] +" é adiministrador do plugin BestTools!");
                } catch (ToolsException e) {
                    Msg.ServidorRed(e.getMessage());
                }
                return false;
            }
            if(command.getName().equalsIgnoreCase("removeadm")){
                try {
                    Configuration.removeAdm(args);
                    Msg.PlayerTodos("Agora o jogador "+ args[0] +" não é mais adiministrador do plugin BestTools!");
                } catch (ToolsException e) {
                    Msg.ServidorRed(e.getMessage());
                }
            }
        }
        return false;
    }
}
