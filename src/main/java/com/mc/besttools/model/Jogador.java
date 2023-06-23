package com.mc.besttools.model;

import com.mc.besttools.interfaces.Data;
import org.bukkit.entity.Player;

public class Jogador implements Data {
    private String name;
    private boolean activated = false;

    public Jogador() {
    }

    public Jogador(Player player) {
        name = player.getName();
    }

    public Jogador(String name, boolean activated) {
        this.name = name;
        this.activated = activated;
    }

    public Jogador(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Jogador findByName(String name) {
        return JOGADOR_MAP.get(name);
    }

    public boolean add() {
        if (!JOGADOR_MAP.containsKey(name)) {
            JOGADOR_MAP.put(name, this);
            return true;
        }
        return false;
    }

    public boolean update() {
        if (JOGADOR_MAP.containsKey(name)) {
            JOGADOR_MAP.replace(name, this);
            return true;
        }
        return false;
    }

    public boolean remove() {
        if (JOGADOR_MAP.containsKey(name)) {
            JOGADOR_MAP.remove(name);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "name='" + name + '\'' +
                ", activated=" + activated +
                '}';
    }
}
