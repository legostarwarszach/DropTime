package com.nuclearw.droptime;

import java.util.HashMap;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import com.nuclearw.droptime.listeners.ItemListener;
import com.nuclearw.droptime.runnables.ItemRemoverTask;

public class DropTime extends JavaPlugin {
	public HashMap<ItemStack, Integer> items = new HashMap<ItemStack, Integer>(100);

	@Override
	public void onEnable() {
		Config.load(this);

		BukkitScheduler bs = getServer().getScheduler();
		bs.scheduleSyncRepeatingTask(this, new ItemRemoverTask(this), 0L, 20L);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new ItemListener());

		PluginManager manager = getServer().getPluginManager();
		manager.registerEvents(new ItemListener(this), this);

		getLogger().info("Finished loading " + getDescription().getFullName());
	}

	@Override
	public void onDisable() {
		getLogger().info("Finished unloading " + getDescription().getFullName());
	}
}
