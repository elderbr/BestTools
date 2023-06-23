package com.mc.besttools.model;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;

public class Machado extends Tools {
    public Machado() {
        init();
    }

    public Machado(ItemStack itemStack) {
        super(itemStack);
        init();
    }

    @Override
    public String getSufix() {
        return "axe";
    }

    @Override
    public boolean addListMaterial(Material material) {
        if (!AxeListMaterial.contains(material.name())) {
            AxeListMaterial.add(material.name());
            return true;
        }
        return false;
    }

    @Override
    public boolean addListMaterial(String material) {
        if (!AxeListMaterial.contains(material)) {
            AxeListMaterial.add(material);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeListMaterial(Material material) {
        if (AxeListMaterial.contains(material.name())) {
            AxeListMaterial.remove(material.name());
            return true;
        }
        return false;
    }

    @Override
    public List<String> getListMaterial() {
        Collections.sort(AxeListMaterial);
        return AxeListMaterial;
    }

    @Override
    public boolean contains(Block block) {
        for(String material : AxeListMaterial){
            if(material.equals(block.getType().toString())){
                return true;
            }
        }
        return false;
    }

    private void init() {
        addItem("oak");
        addItem("dark oak");
        addItem("acacia");
        addItem("birch");
        addItem("jungle");
        addItem("spruce");
        addItem("mangrove");
        addItem("bamboo");
        addItem("bed");
        addItem("banner");
        addItem("pumpkin");
        addItem("campfire");
        addItem("ladder");
        addItem("chest");
        addItem("warped");
    }

}
