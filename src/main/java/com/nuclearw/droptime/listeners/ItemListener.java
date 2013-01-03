package com.nuclearw.droptime.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import com.nuclearw.droptime.Config;
import com.nuclearw.droptime.DropTime;

public class ItemListener implements Listener {
	private DropTime plugin;

	public ItemListener(DropTime plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		for(ItemStack drop : event.getDrops()) {
			plugin.items.put(drop, Config.persistSeconds);
		}
	}

/*
	@EventHandler
	public void onItemPickup(PlayerPickupItemEvent event) {
	}
*/
}
