package de.speedy.UltimateChat;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinLeaveListener implements Listener {

	private Main plugin;

	public PlayerJoinLeaveListener(Main main) {
		plugin = main;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (!plugin.getConfig().getBoolean("SetJoinMessage"))
			return;
		e.setJoinMessage(ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("JoinMessage")).replace(
				"{Player}", e.getPlayer().getName()));
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		if (!plugin.getConfig().getBoolean("SetLeaveMessage"))
			return;
		e.setQuitMessage(ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("LeaveMessage")).replace(
				"{Player}", e.getPlayer().getName()));
	}

}
