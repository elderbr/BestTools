package com.mc.besttools.model;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Msg {

    public static void ServidorGreen(String msg) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + " BestTools >> " + Color(msg));
    }

    public static void ServidorGreen(String msg, Class getClass) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + " BestTools >> " + Color(msg) + " - Class: " + getClass.getSimpleName());
    }

    public static void ServidorGreen(String msg, String metodo, Class getClass) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + " BestTools >> " + Color(msg) + " - Class: " + getClass.getSimpleName());
    }

    public static void ServidorRed(String msg) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + " BestTools >> " + Color(msg));
    }

    public static void ServidorGold(String msg) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + " BestTools >> " + Color(msg));
    }

    public static void ServidorGold(String msg, Class getClass) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + " BestTools >> " + Color(msg) + " - Class: " + getClass.getSimpleName());
    }

    public static void ServidorWhite(String msg) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.WHITE + " BestTools >> " + Color(msg));
    }

    public static void ServidorColored(String msg, String msg1, String msg2) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + " BestTools >> " + Color(msg) + " - " + ChatColor.YELLOW + msg1 + " - " + ChatColor.AQUA + msg2);
    }

    public static void ServidorBlue(String msg) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + " BestTools >> " + Color(msg));
    }

    public static void ServidorBlue(@NotNull String msg, Class getClass) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + " BestTools >> " + Color(msg) + " - Class: " + getClass.getSimpleName());
    }

    /**
     * @param getClass
     */
    public static void PularLinha(@NotNull Class getClass) {
        Bukkit.getServer().getConsoleSender().sendMessage("BestTools >> #====================================# - Class: " + getClass.getSimpleName() + "\n");
    }

    //========================== PLAYERS =================================================//

    //Mensagem do player
    public static void PlayerGreen(@NotNull Player player,@NotNull  String msg) {
        player.sendMessage(ChatColor.GREEN + Color(msg));
    }

    public static void PlayerGold(@NotNull Player player,@NotNull  String msg) {
        player.sendMessage(ChatColor.GOLD + Color(msg));
    }

    public static void PlayerRed(@NotNull Player player,@NotNull  String msg) {
        player.sendMessage(ChatColor.RED + Color(msg));
    }

    public static void PlayerTodos(@NotNull String msg) {
        Bukkit.getServer().broadcastMessage(Color(msg));
    }


    public static void PulaPlayer(Player player) {
        player.sendMessage("=====================================================");
    }

    //======================= ERRROS ====================================================//

    public static void ServidorErro(Exception error, String metodo, Class classe) {
        Bukkit.getServer().getConsoleSender().sendMessage(
                Color("$dErro: " + error.getMessage() +
                        "\nmetodo: " + metodo +
                        "\nCausa: " + error.getCause() +
                        "\nClasse: " + classe.getSimpleName()));
    }

    public static void ServidorErro(String msg, String metodo, Class classe, Exception error) {
        Bukkit.getServer().getConsoleSender().sendMessage(Color("$dErro: " + error.getMessage()
                + "\nmetodo: " + metodo
                + "\nCausa: " + error.getCause()
                + "\nClasse: " + classe.getSimpleName()));
    }

    /***
     * Adiciona cores aos textos substituindo o $ por $
     * @param text texto que vai ser modificado
     * @return o texto com a cor setada
     */
    public static String Color(String text) {
        return ChatColor.translateAlternateColorCodes('$', text);
    }


}