package de.speedy.UltimateChat;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {
	
	private Main plugin;

	public PlayerChatListener(Main main) {
		this.plugin = main;
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		String msg = e.getMessage();
		String newMsg = msg;
		if(!plugin.getConfig().getBoolean("ReplaceSymbols")) return;
		List<String> symbols = plugin.getConfig().getStringList("Symbols");
		for(String str : symbols){
			String toReplace = str.split(",")[0];
			String symbol = str.split(",")[1];
			symbol = ChatColor.translateAlternateColorCodes('&', symbol);
			if(msg.contains(toReplace)){
				newMsg = newMsg.replace(toReplace, symbol);
			}
		}
		e.setMessage(newMsg);
	}

}
