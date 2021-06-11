package com.meltdownmc.mdCore.CommandLog;

import com.meltdownmc.mdCore.main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class spyCommand extends Command {

    public spyCommand() {
        super("spy");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (!(sender instanceof ProxiedPlayer)) {
            System.out.println("Console's notifications are always enabled!");
        }

        ProxiedPlayer p = (ProxiedPlayer) sender;
        if (!(p.hasPermission("mcore.spy"))) {
            p.sendMessage("Unknown command. Type \"/help\" for help.");
            return;
        }

        if (main.spyPlayers.contains(p)) {
            String m = "&8>> &7You have disabled notifications";
            main.spyPlayers.remove(p);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',m));
        } else {
            String m = "&8>> &7You have enabled notifications";
            main.spyPlayers.add(p);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',m));
        }


        return;
    }
}
