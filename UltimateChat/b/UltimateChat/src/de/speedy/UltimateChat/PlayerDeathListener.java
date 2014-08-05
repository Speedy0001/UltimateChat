package de.speedy.UltimateChat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

	private Main plugin;

	public PlayerDeathListener(Main main) {
		this.plugin = main;
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if (plugin.getConfig().getBoolean("SetDeathMessage")) {
			String msgWithKiller = plugin.getConfig()
					.getString("DeathMessageWithKiller")
					.replace("{Player}", p.getName());
			String msgWithoutKiller = plugin.getConfig()
					.getString("DeathMessageWithoutKiller")
					.replace("{Player}", p.getName());

			if (p.getKiller() == null || !(p.getKiller() instanceof Player)) {
				e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', msgWithoutKiller));
			} else {
				Player killer = p.getKiller();
				msgWithKiller = msgWithKiller.replace("{Killer}", killer.getName());
				e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', msgWithKiller));
			}
		}

	}

}
