package com.mc.besttools.model;

import com.mc.besttools.interfaces.Data;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Tools implements Data {

    private ItemStack itemStack;
    private String name;
    private double durability = 0;

    private List<String> listItem = new ArrayList<>();
    public Tools() {
    }

    public Tools(ItemStack itemStack) {
        setItemStack(itemStack);
    }

    public Tools setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        name = toItemStack(itemStack);
        durability = itemStack.getType().getMaxDurability();
        return this;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public String getName() {
        return name;
    }

    public double getDurability() {
        return durability;
    }

    public abstract String getSufix();

    @Override
    public boolean equals(Object object) {
        if (object instanceof ItemStack itemStack) {
            String nameItem = toItemStack(itemStack);
            if (nameItem.split("\\s").length > 0) {
                String[] split = nameItem.split("\\s");
                for (String value : split) {
                    if (value.equals(getSufix())) {
                        return true;
                    }
                }
            } else {
                return getSufix().equals(toItemStack(itemStack));
            }
        }
        return false;
    }

    public Tools addItem(String value) {
        listItem.add(value);
        return this;
    }

    public List<String> getListItem() {
        return listItem;
    }

    public abstract boolean addListMaterial(Material material);

    public abstract boolean addListMaterial(String material);
    public abstract boolean removeListMaterial(Material material);
    public abstract List<String> getListMaterial();
    public static String Type(ItemStack itemStack) {
        if(itemStack.getType().name().contains("_")) {
            return itemStack.getType().name().toLowerCase().split("_")[1];
        }else{
            return itemStack.getType().name().toLowerCase();
        }
    }

    public abstract boolean contains(Block block);

    public boolean pertence(String material) {
        if (material.split("_").length > 0) {
            String[] split = material.split("_");
            for (String nameItem : listItem) {
                for (String name : split) {
                    if (name.equalsIgnoreCase(nameItem)) {
                        return true;
                    }
                }
            }
        } else {
            for (String nameItem : listItem) {
                if (nameItem.equals(material)) {
                    return true;
                }
            }
        }
        return false;
    }
}
