package me.nutzio.myMobileShop;

import java.util.logging.Logger;

import me.nutzio.Listener.MyBlockListener;
import me.nutzio.Listener.MyPlayerListener;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import org.bukkit.plugin.Plugin;

import com.iCo6.*;
import com.iCo6.system.Holdings;

import me.nutzio.commands.GetArmor;

public class MyMobileShop extends JavaPlugin {

	protected static final String configFile = "config.yml";
	// protected Configuration config;

	Logger log = Logger.getLogger("Minecraft");
	private final MyPlayerListener playerListener = new MyPlayerListener(this);
	private final MyBlockListener blockListener = new MyBlockListener(this);
	public static PermissionHandler permissionHandler;
	public iConomy iConomy = null;

	public static GetArmor getarmor = new GetArmor();

	public void onEnable() {
		// Config-Teil
		loadConfig();

		// Start-Message
		log.info("[MyMobileShop] plugin has been enabled!");

		// Events
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener,
				Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener,
				Event.Priority.Normal, this);

		// Permissions
		setupPermissions();
	}

	public void onDisable() {
		log.info("[MyMobileShop] plugin has been disabled.");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		boolean success = false;
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			Holdings holdings = new Holdings(player.getName());

			/*
			 * myHelp befehl zeigt Befehlsliste an
			 */

			if (cmd.getName().equalsIgnoreCase("myhelp")) {

				// TODO HIER HELP LISTE REINMACHEN



			} else if (cmd.getName().equalsIgnoreCase("myreload")) {

				this.reloadConfig();

				success = true;
				
			} else if (cmd.getName().equalsIgnoreCase("getarmor")) {

				getArmor(player, args, holdings);
				
			}
		} else {
			System.out.println("Non Player Object asked for Command "+ sender.toString());
			return false;
		}
		
		return success;

	}

	
	
	
	private void setupPermissions() {
		if (permissionHandler != null) {
			return;
		}

		Plugin permissionsPlugin = this.getServer().getPluginManager()
				.getPlugin("Permissions");

		if (permissionsPlugin == null) {
			log.info("Permission system not detected, defaulting to OP");
			return;
		}

		permissionHandler = ((Permissions) permissionsPlugin).getHandler();
		log.info("Found and will use plugin "
				+ ((Permissions) permissionsPlugin).getDescription()
						.getFullName());
	}

	private void loadConfig() {
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	
	
	
	/*
	 * 
	 * Command getArmor
	 * 
	 */
	
	private boolean getArmor(Player player, String[] args, Holdings holdings){

		boolean success = false;
		
		if(!this.getConfig().getBoolean("Config.getArmor.Commands.getarmor")){
			player.sendMessage(ChatColor.DARK_RED + "der Befehl /getarmor ist deaktiviert");
			
			return true;
		}
		
		if (args.length == 0) {

			double priceDefault = this.getConfig().getDouble("Config.getArmor.Prices." + this.getConfig().getString("Config.getArmor.Default"), 100);
			
			/*
			 * Wenn Permission dann kostenlos sonst Bezahlen
			 */
			if (permissionHandler.has(player, "MyMobileShop.armor.getFree.iron")) {

				player.sendMessage("You got a iron armor for FREE");
				success = getarmor.getArmor(player);

				holdings.subtract(100);

			} else if (permissionHandler.has(player, "MyMobileShop.armor.iron")) {

				if (holdings.hasEnough(priceDefault)) {
					player.sendMessage("You buyed a iron armor for " + priceDefault);

					holdings.subtract(priceDefault);
				}

			} else {

				player.sendMessage(ChatColor.DARK_RED + "YOU DON'T HAVE THE PERMISSION");
				success = false;
			}

			
			/*
			 * Wenn Argument vorhanden
			 */
		} else {
			
			if(!this.getConfig().getBoolean("Config.getArmor.Commands.getArmor." + args[0])){
				player.sendMessage(ChatColor.DARK_RED + "der Befehl /getarmor <"+args[0]+"> ist deaktiviert");
				
				return true;
			}
			
			double price = this.getConfig().getDouble("Config.getArmor.Prices." + args[0], 200);
			
			if (permissionHandler.has(player, "MyMobileShop.armor.getFree."+ args[0])) {
				if (getarmor.getArmor(args[0], player))
					player.sendMessage("You got a " + args[0] + " armor for FREE");

				success = true;
			}

			else if (permissionHandler.has(player, "MyMobileShop.armor." + args[0])) {

				if (holdings.hasEnough(price)) {
					if (success = getarmor.getArmor(args[0], player)){
						player.sendMessage("You buyed a " + args[0]	+ " armor for " + price);
						
						holdings.subtract(price);
					}
					
				}

			} else {

				player.sendMessage(ChatColor.DARK_RED + "YOU DON'T HAVE THE PERMISSION");
				success = false;
			}

		}
		return success;
	}
}

