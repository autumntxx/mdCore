package com.meltdownmc.mdCore.staffchat;

import com.meltdownmc.mdCore.main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class hushCommand extends Command {
    public hushCommand() {
        super("hush");
    }

    public void execute(CommandSender sender, String[] args) {

        if (!(sender instanceof ProxiedPlayer)) {
            System.out.println("Console's staffchat is always on!");
            return;
        }


        ProxiedPlayer p = (ProxiedPlayer) sender;
        if (main.hushed.contains(p)) {
            p.sendMessage(ChatColor.GREEN + "Your staffchat is now unmuted.");
            main.hushed.remove(p);
        } else {
            p.sendMessage(ChatColor.RED + "Your staffchat is now muted.");
            main.hushed.add(p);
        }


    }
}
