package com.mc.besttools.dao;

import com.mc.besttools.interfaces.Data;
import com.mc.besttools.model.Machado;
import com.mc.besttools.model.Msg;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.*;

public class MachadoDao implements Data {

    private Machado machado = new Machado();
    private final YamlConfiguration config = CONFIG_DEFAULT;

    public MachadoDao() {
    }

    public void create() {
        Iterator<String> iterator = PickaxeListMaterial.iterator();
        while (iterator.hasNext()) {
            String material = iterator.next();
            if (machado.pertence(material)) {
                machado.addListMaterial(material);
                iterator.remove();
            }
        }
        config.set(machado.getSufix(), machado.getListMaterial());
        config.setComments(machado.getSufix(), Arrays.asList("Lista de materiais que podem ser quebrados pelo o machado"));
        save();
    }

    public boolean add(Block block) {
        int size = machado.getListMaterial().size();
        if (machado.getListMaterial().contains(block.getType().name())) {
            machado.addListMaterial(block.getType());
            config.set(machado.getSufix(), machado.getListItem());
            save();
        }
        return (size < machado.getListMaterial().size());
    }

    public List<String> findAll() {
        AxeListMaterial.clear();
        for (Object obj : config.getList(machado.getSufix())) {
            machado.addListMaterial(Material.getMaterial(obj.toString()));
        }
        return machado.getListMaterial();
    }

    public boolean findByBlock(Block block) {
        return AxeListMaterial.contains(block.getType());
    }

    public String findByBlock(Material material) {
        for(Object obj : config.getList(machado.getSufix())){
            String block = obj.toString();
            if(block.equalsIgnoreCase(material.toString())){
                return block;
            }
        }
        return null;
    }

    public boolean remove(Block block) {
        int size = AxeListMaterial.size();
        if (machado.removeListMaterial(block.getType())) {
            config.set(machado.getSufix(), machado.getListMaterial());
            save();
        }
        return (size > AxeListMaterial.size());
    }

    public void update(){
        config.set(machado.getSufix(), machado.getListMaterial());
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
