package com.mc.besttools.model;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Collections;
import java.util.List;

public class Enxada extends Tools {

    public Enxada() {
        init();
    }

    @Override
    public String getSufix() {
        return "hoe";
    }

    @Override
    public boolean addListMaterial(Material material) {
        if (!HoeListMaterial.contains(material.name())) {
            HoeListMaterial.add(material.name());
            return true;
        }
        return false;
    }

    @Override
    public boolean addListMaterial(String material) {
        if (!HoeListMaterial.contains(material)) {
            HoeListMaterial.add(material);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeListMaterial(Material material) {
        if (HoeListMaterial.contains(material.name())) {
            HoeListMaterial.remove(material.name());
            return true;
        }
        return false;
    }

    @Override
    public List<String> getListMaterial() {
        Collections.sort(HoeListMaterial);
        return HoeListMaterial;
    }

    @Override
    public boolean contains(Block block) {
        for(String material : HoeListMaterial){
            if(material.equals(block.getType().toString())){
                return true;
            }
        }
        return false;
    }

    private void init() {

        addItem("leaves");
        addItem("sapling");
        addItem("seeds");
        addItem("wheat");
        addItem("carrot");
        addItem("potato");
        addItem("beetroot");
        addItem("wart");
        addItem("cactus");
        addItem("cocoa");
        addItem("loom");
        addItem("jack");
    }
}
