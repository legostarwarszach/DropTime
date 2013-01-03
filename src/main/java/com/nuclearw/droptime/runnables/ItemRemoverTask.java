package com.nuclearw.droptime.runnables;

import org.bukkit.inventory.ItemStack;

import com.nuclearw.droptime.DropTime;

public class ItemRemoverTask implements Runnable {
	private DropTime plugin;

	public ItemRemoverTask(DropTime plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		for(ItemStack key : plugin.items.keySet()) {
			if(plugin.items.get(key) == 0) {
				key.setAmount(0);
				plugin.items.remove(key);
			} else {
				int newTime = plugin.items.get(key) - 1;
				plugin.items.put(key, newTime);
			}
		}
	}
}
