package com.mc.besttools.exceptions;

import com.mc.besttools.interfaces.Data;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ToolsException extends Exception{

    private String msg;

    public ToolsException(String message) {
        super(message);
        msg = getMessage();
    }

    @Override
    public String toString() {
        return msg;
    }
}
