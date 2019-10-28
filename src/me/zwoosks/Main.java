package me.zwoosks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		new CommandManager(this);
		Bukkit.getServer().getPluginManager().registerEvents(new Events(this), this);
		saveDefaultConfig();
	}
	
}