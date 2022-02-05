package com.MrEngMan.BlastProofItems.util;

import com.MrEngMan.BlastProofItems.BlastProofItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Utils {

    // Translate '&' as formatting codes
    public static String SendChatMessage(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    // Print debug messages only if enabled
    public static void debugPrint(String msg) {
        if(BlastProofItems.getPlugin().isDebugEnabled()) {
            Bukkit.getLogger().info("[BlastProofItems] " + msg);
        }
    }

}

