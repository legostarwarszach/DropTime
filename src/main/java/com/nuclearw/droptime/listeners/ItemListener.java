package com.nuclearw.droptime.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Entity;
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

public class ItemListener implements Listener, Runnable {
	private DropTime plugin;

	public ItemListener(DropTime plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		for (World world : Bukkit.getWorlds()) {
			for (Chunk chunk : world.getLoadedChunks()) {
				for (Entity item : chunk.getEntities()) {
					if (item instanceof Item) {
						plugin.items.put(item, Config.persistSeconds);
					}
				}
			}
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
