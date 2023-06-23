package com.mc.besttools.model;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Collections;
import java.util.List;

public class Picareta extends Tools {

    @Override
    public String getSufix() {
        return "pickaxe";
    }

    @Override
    public boolean addListMaterial(Material material) {
        if(!PickaxeListMaterial.contains(material.name())){
            PickaxeListMaterial.add(material.name());
            return true;
        }
        return false;
    }

    @Override
    public boolean addListMaterial(String material) {
        if(!PickaxeListMaterial.contains(material)){
            PickaxeListMaterial.add(material);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeListMaterial(Material material) {
        if(PickaxeListMaterial.contains(material.name())){
            PickaxeListMaterial.add(material.name());
            return true;
        }
        return false;
    }

    @Override
    public List<String> getListMaterial() {
        Collections.sort(PickaxeListMaterial);
        return PickaxeListMaterial;
    }

    @Override
    public boolean contains(Block block) {
        for(String material : PickaxeListMaterial){
            if(material.equals(block.getType().toString())){
                return true;
            }
        }
        return false;
    }
}
