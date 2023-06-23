package com.mc.besttools.model;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Collections;
import java.util.List;

public class Pa extends Tools {

    public Pa() {
        init();
    }

    @Override
    public String getSufix() {
        return "shovel";
    }

    @Override
    public boolean addListMaterial(Material material) {
        if(!ShovelListMaterial.contains(material.name())){
            ShovelListMaterial.add(material.name());
            return true;
        }
        return false;
    }

    @Override
    public boolean addListMaterial(String material) {
        if (!ShovelListMaterial.contains(material)) {
            ShovelListMaterial.add(material);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeListMaterial(Material material) {
        if(ShovelListMaterial.contains(material.name())){
            ShovelListMaterial.remove(material.name());
            return true;
        }
        return false;
    }

    @Override
    public List<String> getListMaterial() {
        Collections.sort(ShovelListMaterial);
        return ShovelListMaterial;
    }

    @Override
    public boolean contains(Block block) {
        for(String material : ShovelListMaterial){
            if(material.equals(block.getType().toString())){
                return true;
            }
        }
        return false;
    }

    private void init(){
        addItem("dirt");
        addItem("sand");
        addItem("gravel");
        addItem("farmland");
        addItem("clay");
        addItem("GRASS_BLOCK");
    }

}
