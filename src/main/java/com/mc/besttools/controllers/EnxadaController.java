package com.mc.besttools.controllers;

import com.mc.besttools.dao.EnxadaDao;
import com.mc.besttools.exceptions.ToolsException;
import com.mc.besttools.interfaces.Data;
import com.mc.besttools.model.Enxada;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class EnxadaController implements Data{

    private Enxada enxada = new Enxada();
    private EnxadaDao enxadaDao = new EnxadaDao();

    private ItemStack itemHand;
    private int itemHandPosition = -1;
    private PlayerInventory playerInventory;

    public EnxadaController() {
    }

    public void create(){
        if(enxadaDao.findByBlock(Material.WHEAT) == null){
            enxadaDao.create();
        }else{
            enxadaDao.findAll();
        }
    }

    public void update(){
        enxadaDao.update();
    }

    public void brockBreak(Player player, Block block) {
        if (block.getType() == null || block.getType() == Material.AIR) {
            return;
        }

        playerInventory = player.getInventory();
        itemHand = playerInventory.getItemInMainHand();
        itemHandPosition = playerInventory.getHeldItemSlot();

        if (enxada.contains(block)) {
            for (int i = 0; i < playerInventory.getContents().length; i++) {
                ItemStack itemStack = playerInventory.getItem(i);
                if (itemStack == null) continue;

                if (enxada.equals(itemStack)) {
                    playerInventory.setItem(i, itemHand);
                    playerInventory.setItem(itemHandPosition, itemStack);
                    break;
                }
            }
        }
    }

    public boolean addBlock(Player player) throws ToolsException {
        Block block = player.getTargetBlock(null, 5);
        if(block.getType() == Material.AIR){
            throw new ToolsException("Olhe para um bloco valido!!!");
        }
        if(HoeListMaterial.contains(block.getType())){
            throw new ToolsException(String.format("O bloco %s já está na lista da enxada!!!", enxada.toBlock(block)));
        }
        return enxadaDao.add(block);
    }

    public boolean removeBlock(Player player) throws ToolsException {
        Block block = player.getTargetBlock(null, 5);
        if(block.getType() == Material.AIR){
            throw new ToolsException("Olhe para um bloco valido!!!");
        }
        if(!HoeListMaterial.contains(block.getType())){
            throw new ToolsException(String.format("O bloco %s não está na lista da enxada!!!", enxada.toBlock(block)));
        }
        return enxadaDao.remove(block);
    }
}
