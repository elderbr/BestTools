package com.mc.besttools.controllers;

import com.mc.besttools.dao.PicaretaDao;
import com.mc.besttools.exceptions.ToolsException;
import com.mc.besttools.interfaces.Data;
import com.mc.besttools.model.Picareta;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PicaretaController implements Data {

    private Picareta picareta = new Picareta();
    private PicaretaDao picaretaDao = new PicaretaDao();
    private String myBlock;
    private ItemStack itemHand;
    private int itemHandPosition = -1;
    private PlayerInventory playerInventory;

    public PicaretaController() {
    }

    public void create() {
        if (picaretaDao.findByMaterial(Material.STONE) == null) {
            picaretaDao.create();
        }else{
            picaretaDao.findAll();
        }
    }

    public void brockBreak(Player player, Block block) {
        if (block.getType() == null || block.getType() == Material.AIR) {
            return;
        }

        playerInventory = player.getInventory();
        itemHand = playerInventory.getItemInMainHand();
        itemHandPosition = playerInventory.getHeldItemSlot();

        if (picareta.contains(block)) {

            myBlock = toMaterial(block.getType());

            for (int i = 0; i < playerInventory.getContents().length; i++) {
                ItemStack itemStack = playerInventory.getItem(i);
                if (itemStack == null) continue;

                if (picareta.equals(itemStack)) {
                    playerInventory.setItem(i, itemHand);
                    playerInventory.setItem(itemHandPosition, itemStack);
                    break;
                }
            }
        }
    }

    public void update(){
        picaretaDao.update();
    }

    public boolean addBlock(Player player) throws ToolsException {
        Block block = player.getTargetBlock(null, 5);
        if (PickaxeListMaterial.contains(block.getType())) {
            throw new ToolsException("O bloco já está na lista da picareta!!!");
        }
        if (block.getType() == Material.AIR) {
            throw new ToolsException("Você precisa olha para um bloco!!!");
        }
        return picaretaDao.add(block);
    }

    public boolean removeBlock(Player player) throws ToolsException {
        Block block = player.getTargetBlock(null, 5);
        if (block == null || block.getType() == Material.AIR) {
            throw new ToolsException("Você precisa olha para um bloco!!!");
        }
        if (PickaxeListMaterial.contains(block.getType())) {
            picaretaDao.remove(block);
        } else {
            throw new ToolsException("O bloco não está na lista da picareta!!!");
        }
        return false;
    }

}
