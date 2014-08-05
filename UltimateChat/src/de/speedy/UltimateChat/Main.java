package de.speedy.UltimateChat;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable(){
//		this.getConfig().addDefault("ReplaceSymbols", true);
//		
//		List<String> l = new ArrayList<>();
//		l.add("<3,♥");
//		this.getConfig().addDefault("Symbols", l);
//		
//		this.getConfig().addDefault("SetDeathMessage", true);
//		this.getConfig().addDefault("DeathMessageWithKiller", "{Player} wurde von {Killer} getötet");
//		this.getConfig().addDefault("DeathMessageWithoutKiller", "{Player} ist gestorben");
//		
//		this.getConfig().addDefault("SetUnknownCommandMessage", true);
//		this.getConfig().addDefault("UnknownCommandMessage", "Dieser Befehl existiert nicht!");
//		
//		this.getConfig().addDefault("SetJoinMessage", true);
//		this.getConfig().addDefault("JoinMessage", "{Player} ist dem Spiel beigetreten");
//		
//		this.getConfig().addDefault("SetLeaveMessage", true);
//		this.getConfig().addDefault("LeaveMessage", "{Player} hat das Spiel verlassen");
//		
//		this.getConfig().options().copyDefaults(true);
		this.saveDefaultConfig();
		
		try {
			Metrics m = new Metrics(this);
			m.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		registerEvents();
		
	}
	
	public void registerEvents(){
		Bukkit.getPluginManager().registerEvents(new PlayerChatListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerCommandPerProcessListener(this), this);
	}

}
