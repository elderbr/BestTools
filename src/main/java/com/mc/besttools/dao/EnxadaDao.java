package com.mc.besttools.dao;

import com.mc.besttools.interfaces.Data;
import com.mc.besttools.model.Msg;
import com.mc.besttools.model.Enxada;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class EnxadaDao implements Data {

    private Enxada enxada = new Enxada();
    private final YamlConfiguration config = CONFIG_DEFAULT;

    public EnxadaDao() {
    }

    public void create() {
        HoeListMaterial.clear();
        Iterator<String> iterator = PickaxeListMaterial.iterator();
        while (iterator.hasNext()) {
            String material = iterator.next();
            if (enxada.pertence(material)) {
                HoeListMaterial.add(material);
                iterator.remove();
            }
        }
        config.set(enxada.getSufix(), enxada.getListMaterial());
        config.setComments(enxada.getSufix(), Arrays.asList("Lista de materiais que podem ser quebrados com a enxada"));
        save();
    }

    public boolean add(Block block) {
        int size = HoeListMaterial.size();
        if (!HoeListMaterial.contains(block.getType())) {
            enxada.addListMaterial(block.getType());
            config.set(enxada.getSufix(), enxada.getListMaterial());
            save();
        }
        return (size < HoeListMaterial.size());
    }

    public List<String> findAll() {
        HoeListMaterial.clear();
        for (Object obj : config.getList(enxada.getSufix())) {
            HoeListMaterial.add(Material.getMaterial(obj.toString()).name());
        }
        return HoeListMaterial;
    }

    public String findByBlock(Block block) {
        for (Object obj : config.getList(enxada.getSufix())) {
            String material = obj.toString();
            if (material.equalsIgnoreCase(block.getType().toString())) {
                return material;
            }
        }
        return null;
    }

    public String findByBlock(Material material) {
        return config.getString(enxada.getSufix() + "." + material.name());
    }

    public boolean remove(Block block) {
        int size = HoeListMaterial.size();
        if (enxada.removeListMaterial(block.getType())) {
            config.set(enxada.getSufix(), enxada.getListMaterial());
            save();
        }
        return (size > HoeListMaterial.size());
    }

    public void update() {
        config.set(enxada.getSufix(), enxada.getListMaterial());
        save();
    }

    private void save() {
        try {
            config.save(Configuration.FILE_CONFIG);
        } catch (IOException e) {
            Msg.ServidorErro("Erro ao salvar o configuração para a enxada!!!", "save()", getClass(), e);
        }
    }
}
