package com.nuclearw.droptime;

import java.io.File;

public class Config {
	public static int persistSeconds;

	public static void load(DropTime plugin) {
		if(!new File(plugin.getDataFolder(), "config.yml").exists()) {
			plugin.saveDefaultConfig();
		}

		persistSeconds = plugin.getConfig().getInt("persist-seconds");
	}
}
