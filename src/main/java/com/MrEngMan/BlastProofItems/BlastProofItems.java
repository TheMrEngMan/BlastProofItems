package com.MrEngMan.BlastProofItems;

import com.MrEngMan.BlastProofItems.listeners.Listeners;
import com.MrEngMan.BlastProofItems.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.File;

public class BlastProofItems extends JavaPlugin implements Listener {

    private static BlastProofItems plugin;

    private boolean debug;

    // When plugin is first enabled
    @SuppressWarnings("static-access")
    @Override
    public void onEnable() {
        this.plugin = this;
        reloadTheConfig();

        // Register stuff
        getCommand("bpireload").setExecutor(new ReloadCommandHandler());
        Bukkit.getPluginManager().registerEvents(new Listeners(), this);

    }

    public void reloadTheConfig() {

        // Generate the config file if it was deleted
        if (!(new File(this.getDataFolder(), "config.yml").exists())) {
            this.saveDefaultConfig();
        }

        // Load new config values
        reloadConfig();
        debug = getConfig().getBoolean("debug", false);

    }

    public class ReloadCommandHandler implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

            // Player issued command
            if (sender instanceof Player) {
                Player player = (Player) sender;

                // Make sure they have permission
                if (player.hasPermission("bpireload.reload")) {
                    plugin.reloadTheConfig();
                    player.sendMessage(Utils.SendChatMessage(plugin.getConfig().getString("reloaded-message")));
                } else {
                    player.sendMessage(Utils.SendChatMessage(plugin.getConfig().getString("no-permission-message")));
                }

            }

            // Console issued command
            else if (sender instanceof ConsoleCommandSender) {
                plugin.reloadTheConfig();
                ConsoleCommandSender console = getServer().getConsoleSender();
                console.sendMessage(Utils.SendChatMessage(plugin.getConfig().getString("reloaded-message")));
            }

            return true;
        }

    }

    // Getters
    public static BlastProofItems getPlugin() {
        return plugin;
    }

    public boolean isDebugEnabled() {
        return debug;
    }


}