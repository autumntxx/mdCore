package com.meltdownmc.mdCore;

import com.meltdownmc.mdCore.CommandLog.spyCommand;
import com.meltdownmc.mdCore.staffchat.hushCommand;
import com.meltdownmc.mdCore.staffchat.reportCommand;
import com.meltdownmc.mdCore.staffchat.staffChatCommand;
import com.meltdownmc.mdCore.staffchat.staffChatEvents;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public final class main extends Plugin {

    public static main instance;


    public static List<ProxiedPlayer> hushed;
    public static List<ProxiedPlayer> toggledPlayers;
    public static List<ProxiedPlayer> spyPlayers;

    @Override
    public void onEnable() {


        System.out.println("▬ ▬ ▬ ▬ ▬ ▬ ▬ ▬ ▬ ▬ ");
        System.out.println("███╗░░░███╗██████╗░░█████╗░░█████╗░██████╗░███████╗\n" +
                "████╗░████║██╔══██╗██╔══██╗██╔══██╗██╔══██╗██╔════╝\n" +
                "██╔████╔██║██║░░██║██║░░╚═╝██║░░██║██████╔╝█████╗░░\n" +
                "██║╚██╔╝██║██║░░██║██║░░██╗██║░░██║██╔══██╗██╔══╝░░\n" +
                "██║░╚═╝░██║██████╔╝╚█████╔╝╚█████╔╝██║░░██║███████╗\n" +
                "╚═╝░░░░░╚═╝╚═════╝░░╚════╝░░╚════╝░╚═╝░░╚═╝╚══════╝");

        try {
            instance = this;

            hushed = new ArrayList<>();
            toggledPlayers = new ArrayList<>();
            spyPlayers = new ArrayList<>();

            System.out.println("Global instances created");
        } catch (Exception e) {
            System.out.println("Error while creating global instances:");
            e.printStackTrace();
            ProxyServer.getInstance().stop();
        }


        try {
            this.getProxy().getPluginManager().registerListener(this, new staffChatEvents());
            System.out.println("Events registered");
        } catch (Exception e) {
            System.out.println("Error while registering events:");
            e.printStackTrace();
            ProxyServer.getInstance().stop();
        }



        try {
            this.getProxy().getPluginManager().registerCommand(this, new hushCommand());
            this.getProxy().getPluginManager().registerCommand(this, new reportCommand());
            this.getProxy().getPluginManager().registerCommand(this, new staffChatCommand());
            System.out.println("Commands registered");
        } catch (Exception e) {
            System.out.println("Error while registering commands:");
            e.printStackTrace();
            ProxyServer.getInstance().stop();
        }


        System.out.println("▬ ▬ ▬ ▬ ▬ ▬ ▬ ▬ ▬ ▬ ");

    }

}
