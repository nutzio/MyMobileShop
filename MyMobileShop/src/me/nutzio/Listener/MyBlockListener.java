package me.nutzio.Listener;

import me.nutzio.myMobileShop.MyMobileShop;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;

public class MyBlockListener extends BlockListener{

	public MyMobileShop plugin;

	public MyBlockListener(MyMobileShop instance) {

		plugin = instance;

	}
	
	public void onBlockBreak(BlockBreakEvent event){
		
		event.getPlayer().sendMessage(event.getBlock().getType().name());
		
	}

}
