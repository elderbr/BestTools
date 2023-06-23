package com.mc.besttools.controllers;

import com.mc.besttools.dao.MachadoDao;
import com.mc.besttools.exceptions.ToolsException;
import com.mc.besttools.interfaces.Data;
import com.mc.besttools.model.Machado;
import com.mc.besttools.model.Msg;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class MachadoController implements Data {

    private Machado machado = new Machado();
    private MachadoDao machadoDao = new MachadoDao();

    private String myBlock;
    private ItemStack itemHand;
    private int itemHandPosition = -1;
    private PlayerInventory playerInventory;

    public MachadoController() {
    }

    public void create(){
        if(machadoDao.findByBlock(Material.OAK_BOAT) == null){
            machadoDao.create();
        }else{
            machadoDao.findAll();
        }
    }

    public void brockBreak(Player player, Block block) {
        if (block.getType() == null || block.getType() == Material.AIR) {
            return;
        }

        playerInventory = player.getInventory();
        itemHand = playerInventory.getItemInMainHand();
        itemHandPosition = playerInventory.getHeldItemSlot();

        if (machado.contains(block)) {

            myBlock = toMaterial(block.getType());

            for (int i = 0; i < playerInventory.getContents().length; i++) {
                ItemStack itemStack = playerInventory.getItem(i);
                if (itemStack == null) continue;

                if (machado.equals(itemStack)) {
                    playerInventory.setItem(i, itemHand);
                    playerInventory.setItem(itemHandPosition, itemStack);
                    break;
                }
            }
        }
    }

    public void update(){
        machadoDao.update();
    }

    public boolean addBlock(Player player) throws ToolsException {
        Block block = player.getTargetBlock(null, 5);
        if(block.getType() == Material.AIR){
            throw new ToolsException("Você precisa olhar para um bloco valido!!!");
        }
        if(AxeListMaterial.contains(block.getType())){
            throw new ToolsException("O bloco já está na lista de machado!!!");
        }
        return machadoDao.add(block);
    }

    public boolean removeBlock(Player player) throws ToolsException{
        Block block = player.getTargetBlock(null, 5);
        if(block.getType() == Material.AIR){
            throw new ToolsException("Você precisa olhar para um bloco valido!!!");
        }
        if(!machado.contains(block)){
            throw new ToolsException("O bloco não está na lista do machado!!!");
        }
        return machadoDao.remove(block);
    }

}
