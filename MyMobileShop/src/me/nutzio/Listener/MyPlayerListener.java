package me.nutzio.Listener;

import me.nutzio.myMobileShop.MyMobileShop;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class MyPlayerListener extends PlayerListener {

	public MyMobileShop plugin;

	public MyPlayerListener(MyMobileShop instance) {
		plugin = instance;
	}
	
	public void onPlayerJoin(PlayerJoinEvent event){
		event.getPlayer().sendMessage("Welcome on MyServer");
	}

}
