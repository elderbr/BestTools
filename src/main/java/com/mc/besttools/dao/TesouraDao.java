package com.mc.besttools.dao;

import com.mc.besttools.interfaces.Data;
import com.mc.besttools.model.Tesoura;
import com.mc.besttools.model.Msg;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TesouraDao implements Data {

    private Tesoura tesoura = new Tesoura();
    private final YamlConfiguration config = CONFIG_DEFAULT;

    public TesouraDao() {
    }

    public void create() {
        ShearsListMaterail.clear();
        Iterator<String> iterator = PickaxeListMaterial.iterator();
        while (iterator.hasNext()) {
            String material = iterator.next();
            if (tesoura.pertence(material)) {
                ShearsListMaterail.add(material);
                iterator.remove();
            }
        }
        config.set(tesoura.getSufix(), tesoura.getListMaterial());
        config.setComments(tesoura.getSufix(), Arrays.asList("Lista de materiais que podem ser quebrados com a tesoura"));
        save();
    }

    public boolean add(Block block) {
        int size = tesoura.getListMaterial().size();
        if (!tesoura.contains(block)) {
            tesoura.addListMaterial(block.getType());
            config.set(tesoura.getSufix(), tesoura.getListMaterial());
            save();
        }
        return (size < tesoura.getListMaterial().size());
    }

    public List<String> findAll() {
        ShearsListMaterail.clear();
        for (Object obj : config.getList(tesoura.getSufix())) {
            tesoura.addListMaterial(Material.getMaterial(obj.toString()).name());
        }
        return tesoura.getListMaterial();
    }

    public String findByBlock(Block block) {
        for(String material : tesoura.getListMaterial()){
            if(material.equalsIgnoreCase(block.getType().toString())){
                return material;
            }
        }
        return null;
    }

    public String findByBlock(Material material) {
        for(Object obj : config.getList(tesoura.getSufix())){
            String block = obj.toString();
            if(block.equalsIgnoreCase(material.toString())){
                return block;
            }
        }
        return null;
    }

    public boolean remove(Block block) {
        int size = ShearsListMaterail.size();
        if (tesoura.removeListMaterial(block.getType())) {
            config.set(tesoura.getSufix(), tesoura.getListMaterial());
            save();
        }
        return (size > ShearsListMaterail.size());
    }

    public void update(){
        config.set(tesoura.getSufix(), tesoura.getListMaterial());
        save();
    }

    private void save() {
        try {
            config.save(Configuration.FILE_CONFIG);
        } catch (IOException e) {
            Msg.ServidorErro("Erro ao salvar o configuração para a tesoura!!!", "save()", getClass(), e);
        }
    }

}
