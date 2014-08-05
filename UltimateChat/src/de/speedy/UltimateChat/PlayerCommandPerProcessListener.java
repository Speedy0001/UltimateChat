package de.speedy.UltimateChat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

public class PlayerCommandPerProcessListener implements Listener {
	
	private Main plugin;

	public PlayerCommandPerProcessListener(Main main) {
		this.plugin = main;
	}

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e){
		if(!plugin.getConfig().getBoolean("SetUnknownCommandMessage")) return;
		Player p = e.getPlayer();
		String command = e.getMessage().split(" ")[0];
		HelpTopic htopic = Bukkit.getServer().getHelpMap().getHelpTopic(command);
	      if (htopic == null) {
	        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("UnknownCommandMessage")));
	        e.setCancelled(true);
	      }
	}

}
