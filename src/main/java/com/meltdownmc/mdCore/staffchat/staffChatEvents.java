package com.meltdownmc.mdCore.staffchat;

import com.meltdownmc.mdCore.main;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.scheduler.ScheduledTask;
import net.md_5.bungee.event.EventHandler;

import java.util.concurrent.TimeUnit;


public class staffChatEvents implements Listener {
    private ProxiedPlayer p;

    @EventHandler
    public void onChat(ChatEvent e) {
        ProxiedPlayer p = (ProxiedPlayer) e.getSender();
        if (!p.hasPermission("mcore.staffchat.chat")) {
            return;
        }

        if (!(main.toggledPlayers.contains(p))) {
            return;
        }

        if (e.getMessage().startsWith("#")) {
            e.setCancelled(true);

        } else {
            return;
        }

        String m = e.getMessage().substring(1);

        System.out.println("[StaffChat] " + p.getDisplayName() + " > " + m);

        for (ProxiedPlayer sp : ProxyServer.getInstance().getPlayers()) {
            if (!sp.hasPermission("mcore.staffchat.receive")) {
                return;
            }

            if (main.hushed.contains(sp)) {
                return;
            }

            String sentMessage = "&6&lStaff &r" + p.getDisplayName() + " &8» &r" + m;

            sp.sendMessage(ChatColor.translateAlternateColorCodes('&',sentMessage));

        }

    }

    @EventHandler
    public void playerDisconnect(PlayerDisconnectEvent e) {
        main.toggledPlayers.remove(e.getPlayer());
    }

    @EventHandler
    public void postLoginEvent(PostLoginEvent e) {
        ProxiedPlayer p = e.getPlayer();

        if (!(main.hushed.contains(p))) { return; }

        String hushedMessage = "&6You still have Staffchat hushed. To disable this, type &e/hush&6.";
        ScheduledTask a = ProxyServer.getInstance().getScheduler().schedule(main.instance, (Runnable) () -> p.sendMessage(ChatColor.translateAlternateColorCodes('&',hushedMessage)), 5, TimeUnit.SECONDS);
    }
}
