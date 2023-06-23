package com.mc.besttools.model;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Collections;
import java.util.List;

public class Tesoura extends Tools {

    public Tesoura() {
        init();
    }

    @Override
    public String getSufix() {
        return "shears";
    }

    @Override
    public boolean addListMaterial(Material material) {
        if(!ShearsListMaterail.contains(material.name())){
            ShearsListMaterail.add(material.name());
            return true;
        }
        return false;
    }

    @Override
    public boolean addListMaterial(String material) {
        if(!ShearsListMaterail.contains(material)){
            ShearsListMaterail.add(material);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeListMaterial(Material material) {
        if(ShearsListMaterail.contains(material.name())){
            ShearsListMaterail.remove(material.name());
            return true;
        }
        return false;
    }

    @Override
    public List<String> getListMaterial() {
        Collections.sort(ShearsListMaterail);
        return ShearsListMaterail;
    }

    @Override
    public boolean contains(Block block) {
        for(String material : ShearsListMaterail){
            if(material.equals(block.getType().toString())){
                return true;
            }
        }
        return false;
    }

    private void init(){
        addItem("wool");
        addItem("carpet");
        addItem("vine");
        addItem("lichen");
        addItem("grass");
        addItem("seagrass");
        addItem("fern");
        addItem("tulip");
        addItem("cobweb");
    }
}
