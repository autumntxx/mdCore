package com.meltdownmc.mdCore.staffchat;

import com.meltdownmc.mdCore.main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class staffChatCommand extends Command {
    public staffChatCommand() { super("sc"); }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            return;

        }

        ProxiedPlayer p = (ProxiedPlayer) sender;

        if (main.toggledPlayers.contains(p)) {
            p.sendMessage(ChatColor.RED + "Staffchat disabled.");
            main.toggledPlayers.remove(p);
        } else {
            p.sendMessage(ChatColor.GREEN + "Staffchat enabled.");
            main.toggledPlayers.add(p);
        }


    }
}
