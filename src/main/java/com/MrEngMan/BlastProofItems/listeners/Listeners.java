package com.MrEngMan.BlastProofItems.listeners;

import com.MrEngMan.BlastProofItems.util.Utils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class Listeners implements Listener {

    public Listeners() {}

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity damagerEntity = event.getDamager();
        Entity damagedEntity = event.getEntity();

        // Item damaged by entity explosion
        if (damagedEntity.getType() == EntityType.DROPPED_ITEM && (damagerEntity.getType() == EntityType.ENDER_CRYSTAL || damagerEntity.getType() == EntityType.MINECART_TNT || damagerEntity.getType() == EntityType.PRIMED_TNT)) {
            Utils.debugPrint(Utils.SendChatMessage("&eDid NOT damage: " + damagedEntity.getName()));
            event.setCancelled(true);
        }

    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
        Entity damagedEntity = event.getEntity();

        // Item damaged by block explosion
        if (damagedEntity.getType() == EntityType.DROPPED_ITEM && event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
            Utils.debugPrint(Utils.SendChatMessage("&eDid NOT damage: " + damagedEntity.getName()));
            event.setCancelled(true);
        }

    }

}
