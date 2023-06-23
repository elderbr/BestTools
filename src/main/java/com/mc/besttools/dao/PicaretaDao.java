package com.mc.besttools.dao;

import com.mc.besttools.interfaces.Data;
import com.mc.besttools.model.Msg;
import com.mc.besttools.model.Picareta;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PicaretaDao implements Data {

    private Picareta picareta = new Picareta();
    private final YamlConfiguration config = CONFIG_DEFAULT;

    public PicaretaDao() {
    }

    public void create() {

        for (String material : PickaxeListMaterial) {
            picareta.addListMaterial(material);
        }

        config.set(picareta.getSufix(), picareta.getListMaterial());
        config.setComments(picareta.getSufix(), Arrays.asList("Lista de blocos que a picareta pode quebrar"));
        save();
    }

    public boolean add(Block block) {
        int size = picareta.getListMaterial().size();
        if (!picareta.getListMaterial().contains(block.getType())) {
            picareta.addListMaterial(block.getType());
            config.set(picareta.getSufix(), picareta.getListMaterial());
            save();
        }
        return (size < picareta.getListMaterial().size());
    }

    public List<String> findAll() {
        PickaxeListMaterial.clear();
        for (Object obj : config.getList(picareta.getSufix())) {
            picareta.addListMaterial(Material.getMaterial(obj.toString()));
        }
        return picareta.getListMaterial();
    }

    public boolean findByBlock(Block block) {
        return PickaxeListMaterial.contains(block.getType());
    }

    public String findByMaterial(Material material) {
        for(Object obj : config.getList(picareta.getSufix())){
            String block = obj.toString();
            if(block.equalsIgnoreCase(material.toString())){
                return block;
            }
        }
        return null;
    }

    public boolean remove(Block block) {
        int size = PickaxeListMaterial.size();
        if (PickaxeListMaterial.contains(block.getType())) {
            picareta.removeListMaterial(block.getType());
            config.set(picareta.getSufix(), picareta.getListMaterial());
            save();
        }
        return (size > PickaxeListMaterial.size());
    }

    public void update(){
        config.set(picareta.getSufix(), picareta.getListMaterial());
        save();
    }

    private void save() {
        try {
            config.save(Configuration.FILE_CONFIG);
        } catch (IOException e) {
            Msg.ServidorErro("Erro ao salvar o configuração!!!", "save()", getClass(), e);
        }
    }

}
