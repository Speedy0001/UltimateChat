package de.speedy.UltimateChat;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
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

	private List<Player> inCooldown = new ArrayList<>();

	@EventHandler
	public void onPlayerChat(final AsyncPlayerChatEvent e) {
		String msg = e.getMessage();
		msg = ChatColor.translateAlternateColorCodes('&', msg);
		String newMsg = msg;

		if (plugin.getConfig().getBoolean("Tagging")) {
			if (inCooldown.contains(e.getPlayer())) {
				e.getPlayer()
						.sendMessage(
								ChatColor.RED
										+ "Du musst "
										+ plugin.getConfig().getInt(
												"TagCooldown")
										+ " Sekunden warten bevor du wieder taggen darfst!");
				e.setCancelled(true);
				return;
			}
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (msg.contains("@" + p.getName())) {
					inCooldown.add(e.getPlayer());
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,
							new Runnable() {

								@Override
								public void run() {
									inCooldown.remove(e.getPlayer());

								}
							}, plugin.getConfig().getInt("TagCooldown") * 20);
					String nmsg = msg.replace(
							"@" + p.getName(),
							plugin.getConfig().getString("TagColor") + "@"
									+ p.getName()
									+ ChatColor.getLastColors(msg));
					nmsg = ChatColor.translateAlternateColorCodes('&', nmsg);
					Utilities.playTagSound(p);
					newMsg = nmsg;
				}
			}
		}

		if (!plugin.getConfig().getBoolean("ReplaceSymbols"))
			return;
		List<String> symbols = plugin.getConfig().getStringList("Symbols");
		for (String str : symbols) {
			String toReplace = str.split(",")[0];
			String symbol = str.split(",")[1];
			symbol = ChatColor.translateAlternateColorCodes('&', symbol);
			if (msg.contains(toReplace)) {
				newMsg = newMsg.replace(toReplace, symbol);
			}
		}
		e.setMessage(newMsg);
	}

}
