package me.zwoosks;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener {
	
	private Main plugin;
	
	public Events(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if(pathExists("world")) {
			Location location = (Location) plugin.getConfig().get("world");
			player.teleport(location);
		} else {
			player.sendMessage(Utils.chat(plugin.getConfig().getString("noLocationDefined")));
		}
	}
	
	private boolean pathExists(String path) {
		try {
			Location loc = (Location) plugin.getConfig().get("world");
			if(loc != null) return true;
		} catch(Exception e) {
			
		}
		return false;
	}
	
}
