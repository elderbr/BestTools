package com.mc.besttools.dao;

import com.mc.besttools.interfaces.Data;
import com.mc.besttools.model.Machado;
import com.mc.besttools.model.Msg;
import com.mc.besttools.model.Pa;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PaDao implements Data {

    private Pa pa = new Pa();
    private final YamlConfiguration config = CONFIG_DEFAULT;

    public PaDao() {
    }

    public void create() {
        Iterator<String> iterator = PickaxeListMaterial.iterator();
        while (iterator.hasNext()) {
            String material = iterator.next();
            if (pa.pertence(material)) {
                pa.addListMaterial(material);
                iterator.remove();
            }
        }
        config.set(pa.getSufix(), pa.getListMaterial());
        config.setComments(pa.getSufix(), Arrays.asList("Lista de materiais que podem ser quebrados pela a pá"));
        save();
    }

    public boolean add(Block block) {
        int size = pa.getListMaterial().size();
        if (!pa.getListMaterial().contains(block.getType())) {
            pa.addListMaterial(block.getType());
            config.set(pa.getSufix(), pa.getListMaterial());
            save();
        }
        return (size < pa.getListMaterial().size());
    }

    public List<String> findAll() {
        ShovelListMaterial.clear();
        for (Object obj : config.getList(pa.getSufix())) {
            pa.addListMaterial(Material.getMaterial(obj.toString()));
        }
        return pa.getListMaterial();
    }

    public boolean findByBlock(Block block) {
        return ShovelListMaterial.contains(block.getType());
    }

    public String findByBlock(Material material) {
        for (Object block : config.getList(pa.getSufix())){
            String name = block.toString();
            if (name.equalsIgnoreCase(material.toString())){
                return name;
            }
        }
        return null;
    }

    public boolean remove(Block block) {
        int size = pa.getListMaterial().size();
        if (pa.getListMaterial().contains(block.getType())) {
            pa.removeListMaterial(block.getType());
            config.set(pa.getSufix(), pa.getListMaterial());
            save();
        }
        return (size > pa.getListMaterial().size());
    }

    public void update(){
        config.set(pa.getSufix(), pa.getListMaterial());
        save();
    }

    private void save() {
        try {
            config.save(Configuration.FILE_CONFIG);
        } catch (IOException e) {
            Msg.ServidorErro("Erro ao salvar o configuração para a pá!!!", "save()", getClass(), e);
        }
    }

}
