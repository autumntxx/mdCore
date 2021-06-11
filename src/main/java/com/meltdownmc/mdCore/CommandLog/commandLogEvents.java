package com.meltdownmc.mdCore.CommandLog;

import com.meltdownmc.mdCore.main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class commandLogEvents implements Listener {
    @EventHandler
    public void onChatEvent(ChatEvent e) {
        if (!e.isCommand()) {
            return;
        }
        if (!(e.getSender() instanceof ProxiedPlayer)) {
            return;
        }

        ProxiedPlayer p = (ProxiedPlayer) e.getSender();

        if (!(p.hasPermission("mdcore.spied"))) {
            return;
        }


        for (ProxiedPlayer i : ProxyServer.getInstance().getPlayers()) {

            if (!(main.spyPlayers.contains(i))) {
                return;
            }

            if (i == p) { // Making sure that you can't see your own commands
                return;
            }

            String m = "&8>> &7" + p.getDisplayName() + " &8has executed &7" + e.getMessage() + "&8 in server &7" + p.getServer().getInfo().getName();
            i.sendMessage(ChatColor.translateAlternateColorCodes('&',m));

        }
    }
}
