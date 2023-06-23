package com.mc.besttools.controllers;

import com.mc.besttools.dao.TesouraDao;
import com.mc.besttools.exceptions.ToolsException;
import com.mc.besttools.interfaces.Data;
import com.mc.besttools.model.Tesoura;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class TesouraController implements Data {

    private Tesoura tesoura = new Tesoura();
    private TesouraDao tesouraDao = new TesouraDao();

    private Block block;

    public TesouraController() {
    }

    public void create() {
        if (tesouraDao.findByBlock(Material.GRASS) == null) {
            tesouraDao.create();
        } else {
            tesouraDao.findAll();
        }
    }

    public void brockBreak(Player player, Block block) {
        if (block.getType() == null || block.getType() == Material.AIR) {
            return;
        }

        PlayerInventory playerInventory = player.getInventory();
        ItemStack itemHand = playerInventory.getItemInMainHand();
        int itemHandPosition = playerInventory.getHeldItemSlot();

        if (tesoura.contains(block)) {

            String myBlock = toMaterial(block.getType());

            for (int i = 0; i < playerInventory.getContents().length; i++) {
                ItemStack itemStack = playerInventory.getItem(i);
                if (itemStack == null) continue;

                if (tesoura.equals(itemStack)) {
                    playerInventory.setItem(i, itemHand);
                    playerInventory.setItem(itemHandPosition, itemStack);
                    break;
                }
            }
        }
    }

    public void update() {
        tesouraDao.update();
    }

    public boolean addBlock(Player player) throws ToolsException {
        block = player.getTargetBlock(null, 5);
        if (block.getType() == Material.AIR) {
            throw new ToolsException("Olhe para um bloco valido!!!");
        }
        if (tesoura.contains(block)) {
            throw new ToolsException(String.format("O bloco %s já está na lista da ferramenta tesoura!!!", tesoura.toBlock(block)));
        }
        return tesouraDao.add(block);
    }

    public boolean removeBlock(Player player) throws ToolsException {
        block = player.getTargetBlock(null, 5);
        if (block.getType() == Material.AIR) {
            throw new ToolsException("Olhe para um bloco valido!!!");
        }
        if (!tesoura.contains(block)) {
            throw new ToolsException(String.format("O bloco %s já está na lista da ferramenta tesoura!!!", tesoura.toBlock(block)));
        }
        return tesouraDao.remove(block);
    }
}
