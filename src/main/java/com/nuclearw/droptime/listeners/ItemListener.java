package com.nuclearw.droptime.listeners;

import java.util.ArrayList;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
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

	@EventHandler
	public void onChunkUnload(ChunkUnloadEvent event) {
		ArrayList<Item> items = new ArrayList<Item>();

		for(Entity entity : event.getChunk().getEntities()) {
			if(entity instanceof Item) {
				items.add((Item) entity);
			}
		}

		for(ItemStack itemStack : plugin.items.keySet()) {
			for(Item item : items) {
				if(item.getItemStack().equals(itemStack)) {
					event.setCancelled(true);
					return;
				}
			}
		}
	}

	@EventHandler
	public void onItemPickup(PlayerPickupItemEvent event) {
		plugin.items.remove(event.getItem().getItemStack());
	}
}
