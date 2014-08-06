package de.speedy.UltimateChat;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Utilities {
	
	private static Main plugin;

	public static void setPlugin(Main m){
		plugin = m;
	}
	
	public static void playTagSound(final Player p){
		p.playSound(p.getEyeLocation(), Sound.NOTE_PLING, 10, 1);
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				p.playSound(p.getEyeLocation(), Sound.NOTE_PLING, 10, 3);
				
			}
		}, 7);
	}

}
