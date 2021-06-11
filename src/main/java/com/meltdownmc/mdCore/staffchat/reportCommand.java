package com.meltdownmc.mdCore.staffchat;

import com.meltdownmc.mdCore.main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class reportCommand extends Command {
    public reportCommand() { super("report"); }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) { // Stopping console from doing /report
            System.out.println("Only players can report other players!");
            return;
        }

        ProxiedPlayer p = (ProxiedPlayer) sender;

        if (args.length <= 1) { // Making sure that they have a reason
            p.sendMessage(ChatColor.RED + "Usage: /report <player> <reason>");
            return;
        }

        StringBuilder reason = new StringBuilder(); // Building the reason

        for (String i : args) {
            if (!(i == args[0])) {
                reason.append(i + " ");
            }
        }


        p.sendMessage(ChatColor.GOLD + "Report sent");

        for (ProxiedPlayer sp : ProxyServer.getInstance().getPlayers()) {
            if (!sp.hasPermission("mcore.reports!")) {
                return;
            }

            if (main.hushed.contains(sp)) {
                return;
            }

            String sentMessage = "&e" + p.getDisplayName() + " &6reported &e" + args[0] + "&6 for &e" + reason + "&6in server &e" + p.getServer().getInfo().getName() + "&6.";

            sp.sendMessage(ChatColor.translateAlternateColorCodes('&',sentMessage));
        }


    }
}
