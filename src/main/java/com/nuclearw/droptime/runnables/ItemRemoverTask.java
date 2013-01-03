package com.nuclearw.droptime.runnables;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import com.nuclearw.droptime.DropTime;

public class ItemRemoverTask implements Runnable {
	private DropTime plugin;

	public ItemRemoverTask(DropTime plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		ArrayList<ItemStack> remove = new ArrayList<ItemStack>();

		Iterator<ItemStack> iterator = plugin.items.keySet().iterator();

		while(iterator.hasNext()) {
			ItemStack key = iterator.next();

			if(plugin.items.get(key) != null && plugin.items.get(key) == 0) {
				remove.add(key);
				iterator.remove();
			} else if(plugin.items.get(key) != null) {
				int newTime = plugin.items.get(key) - 1;
				plugin.items.put(key, newTime);
			} else {
				plugin.getLogger().warning("Encoutered a situation I have no idea what I am doing in.  Save me.");
			}
		}

		if(remove.size() > 0) {
			ArrayList<Item> items = new ArrayList<Item>();

			for(World world : Bukkit.getWorlds()) {
				for(Entity entity: world.getEntities()) {
					if(entity instanceof Item) {
						items.add((Item) entity);
					}
				}
			}

			for(ItemStack itemStack : remove) {
				for(Item item : items) {
					if(item.getItemStack().equals(itemStack)) {
						item.remove();
					}
				}
			}
		}
	}
}
