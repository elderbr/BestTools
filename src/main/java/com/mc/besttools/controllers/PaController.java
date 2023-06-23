package com.mc.besttools.controllers;

import com.mc.besttools.dao.PaDao;
import com.mc.besttools.exceptions.ToolsException;
import com.mc.besttools.interfaces.Data;
import com.mc.besttools.model.Msg;
import com.mc.besttools.model.Pa;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PaController implements Data {

    private Pa pa = new Pa();
    private PaDao paDao = new PaDao();

    private String myBlock;
    private ItemStack itemHand;
    private int itemHandPosition = -1;
    private PlayerInventory playerInventory;

    public PaController() {
    }

    public void create() {
        if (paDao.findByBlock(Material.DIRT_PATH) == null) {
            paDao.create();
        }else{
            paDao.findAll();
        }
    }

    public void brockBreak(Player player, Block block) {
        if (block.getType() == null || block.getType() == Material.AIR) {
            return;
        }

        playerInventory = player.getInventory();
        itemHand = playerInventory.getItemInMainHand();
        itemHandPosition = playerInventory.getHeldItemSlot();

        if (pa.contains(block)) {

            myBlock = toMaterial(block.getType());

            for (int i = 0; i < playerInventory.getContents().length; i++) {
                ItemStack itemStack = playerInventory.getItem(i);
                if (itemStack == null) continue;

                if (pa.equals(itemStack)) {
                    playerInventory.setItem(i, itemHand);
                    playerInventory.setItem(itemHandPosition, itemStack);
                    break;
                }
            }
        }
    }

    public void update(){
        paDao.update();
    }

    public boolean addBlock(Player player) throws ToolsException {
        Block block = player.getTargetBlock(null, 5);
        if (block.getType() == Material.AIR) {
            throw new ToolsException("Você precisa olhar para um bloco!!!");
        }
        if (pa.getListMaterial().contains(block.getType())) {
            throw new ToolsException(String.format("O bloco %s já está na lista!!!", block.getType().name()));
        }
        return paDao.add(block);
    }

    public boolean removeBlock(Player player) throws ToolsException {
        int size = pa.getListMaterial().size();
        Block block = player.getTargetBlock(null, 5);
        if (block.getType() == Material.AIR) {
            throw new ToolsException("Você precisa olhar para um bloco!!!");
        }
        if (!pa.getListMaterial().contains(block.getType())) {
            throw new ToolsException(String.format("O bloco %s não está na lista!!!", block.getType().name()));
        }
        paDao.remove(block);
        return (size > ShovelListMaterial.size());
    }

}
