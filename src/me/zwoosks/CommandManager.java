package me.zwoosks;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor {
	
	private Main plugin;
	
	public CommandManager(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("setworld").setExecutor(this);
		plugin.getCommand("worldmanagerauthor").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("setworld")) {
				Player player = (Player) sender;
				if(player.hasPermission("zworlds.admin")) {
					Location location = player.getLocation();
					setLocation(location);
					player.sendMessage(Utils.chat("&aSuccessfully saved the spawn location."));
				} else {
					player.sendMessage(Utils.chat(plugin.getConfig().getString("noPerms").replace("%perm%", "zworlds.admin")));
				}
			}
		} else {
			sender.sendMessage(Utils.chat(plugin.getConfig().getString("mustBePlayer")));
		}
		if(cmd.getName().equalsIgnoreCase("worldmanagerauthor")) sender.sendMessage(Utils.chat("&e&lAuthor of the plugin: &azwoosks"));
		return true;
	}
	
	private boolean setLocation(Location location) {
		try {
			plugin.getConfig().set("world", location);
			plugin.saveConfig();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
