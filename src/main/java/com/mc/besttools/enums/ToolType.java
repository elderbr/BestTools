package com.mc.besttools.enums;

public enum ToolType {

    Pickaxe, Axe, Hovel, Hae, Shears;

    @Override
    public String toString(){
        return name().toLowerCase();
    }

    public static int index(ToolType toolType){
        int index = -1;
        for(ToolType value : ToolType.values()){
            if(value.equals(toolType)){
                return index;
            }
            index++;
        }
        return index;
    }

}
