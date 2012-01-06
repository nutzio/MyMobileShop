package me.nutzio.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetArmor implements Command{

	private final int ID_HELMET_LEATHER = 298;
	private final int ID_CHESTPLATE_LEATHER = 299;
	private final int ID_LEGGINS_LEATHER = 299;
	private final int ID_BOOTS_LEATHER = 299;
	private final int ID_SWORD_WOOD = 268;

	private final int ID_HELMET_CHAIN = 302;
	private final int ID_CHESTPLATE_CHAIN = 303;
	private final int ID_LEGGINS_CHAIN = 304;
	private final int ID_BOOTS_CHAIN = 305;
	private final int ID_SWORD_STONE = 272;

	private final int ID_HELMET_IRON = 306;
	private final int ID_CHESTPLATE_IRON = 307;
	private final int ID_LEGGINS_IRON = 308;
	private final int ID_BOOTS_IRON = 309;
	private final int ID_SWORD_IRON = 267;

	private final int ID_HELMET_GOLD = 314;
	private final int ID_CHESTPLATE_GOLD = 315;
	private final int ID_LEGGINS_GOLD = 316;
	private final int ID_BOOTS_GOLD = 317;
	private final int ID_SWORD_GOLD = 283;

	private final int ID_HELMET_DIAMOND = 310;
	private final int ID_CHESTPLATE_DIAMOND = 311;
	private final int ID_LEGGINS_DIAMOND = 312;
	private final int ID_BOOTS_DIAMOND = 313;
	private final int ID_SWORD_DIAMOND = 276;
	
	private final String l = ChatColor.DARK_RED + "|";

	public boolean getArmor(Player player) {
		getIronArmor(player);
		return true;
	}

	public boolean getArmor(String type, Player player) {

		if (type.equalsIgnoreCase("leather"))
			getLeatherArmor(player);
		else if (type.equalsIgnoreCase("chain"))
			getChainArmor(player);
		else if (type.equalsIgnoreCase("iron"))
			getIronArmor(player);
		else if (type.equalsIgnoreCase("gold"))
			getGoldArmor(player);
		else if (type.equalsIgnoreCase("diamond"))
			getDiamondarmor(player);
		else if(type.equalsIgnoreCase("?"))
			help(player);
		else {player.sendMessage(ChatColor.DARK_RED + "/getarmor "
				+ ChatColor.DARK_GREEN + "leather" + l
				+ ChatColor.DARK_GREEN + "chain" + l
				+ ChatColor.DARK_GREEN + "iron" + l
				+ ChatColor.DARK_GREEN + "gold" + l
				+ ChatColor.DARK_GREEN + "diamond");
			return false;
		}
		return true;
	}
	
	
	
	public void help(Player player){
		player.sendMessage(ChatColor.AQUA + "/getarmor <type>" + ChatColor.DARK_AQUA + " gives you default armor");
		player.sendMessage(ChatColor.DARK_AQUA + "Different types are: ");
		player.sendMessage(ChatColor.AQUA + "leather\t" + ChatColor.DARK_AQUA + "     leather-armor and wood-sword");
		player.sendMessage(ChatColor.AQUA + "iron\t" + ChatColor.DARK_AQUA + "        iron-armor and iron-sword");
		player.sendMessage(ChatColor.AQUA + "chain\t" + ChatColor.DARK_AQUA + "       chain-armor and stone-sword");
		player.sendMessage(ChatColor.AQUA + "gold\t" + ChatColor.DARK_AQUA + "        gold-armor and gold-sword");
		player.sendMessage(ChatColor.AQUA + "diamond\t" + ChatColor.DARK_AQUA + "     diamond-armor and diamond-sword");

	}

	private void getLeatherArmor(Player player) {
		player.getInventory().setHelmet(new ItemStack(ID_HELMET_LEATHER, 1));
		player.getInventory().setChestplate(new ItemStack(ID_CHESTPLATE_LEATHER, 1));
		player.getInventory().setLeggings(new ItemStack(ID_LEGGINS_LEATHER, 1));
		player.getInventory().setBoots(new ItemStack(ID_BOOTS_LEATHER, 1));
		player.getInventory().addItem(new ItemStack(ID_SWORD_WOOD, 1));
	}

	private void getChainArmor(Player player) {
		player.getInventory().setHelmet(new ItemStack(ID_HELMET_CHAIN, 1));
		player.getInventory().setChestplate(new ItemStack(ID_CHESTPLATE_CHAIN, 1));
		player.getInventory().setLeggings(new ItemStack(ID_LEGGINS_CHAIN, 1));
		player.getInventory().setBoots(new ItemStack(ID_BOOTS_CHAIN, 1));
		player.getInventory().addItem(new ItemStack(ID_SWORD_STONE, 1));
	}

	private void getIronArmor(Player player) {
		player.getInventory().setHelmet(new ItemStack(ID_HELMET_IRON, 1));
		player.getInventory().setChestplate(new ItemStack(ID_CHESTPLATE_IRON, 1));
		player.getInventory().setLeggings(new ItemStack(ID_LEGGINS_IRON, 1));
		player.getInventory().setBoots(new ItemStack(ID_BOOTS_IRON, 1));
		player.getInventory().addItem(new ItemStack(ID_SWORD_IRON, 1));
	}

	private void getGoldArmor(Player player) {
		player.getInventory().setHelmet(new ItemStack(ID_HELMET_GOLD, 1));
		player.getInventory().setChestplate(new ItemStack(ID_CHESTPLATE_GOLD, 1));
		player.getInventory().setLeggings(new ItemStack(ID_LEGGINS_GOLD, 1));
		player.getInventory().setBoots(new ItemStack(ID_BOOTS_GOLD, 1));
		player.getInventory().addItem(new ItemStack(ID_SWORD_GOLD, 1));
	}

	private void getDiamondarmor(Player player) {
		player.getInventory().setHelmet(new ItemStack(ID_HELMET_DIAMOND, 1));
		player.getInventory().setChestplate(new ItemStack(ID_CHESTPLATE_DIAMOND, 1));
		player.getInventory().setLeggings(new ItemStack(ID_LEGGINS_DIAMOND, 1));
		player.getInventory().setBoots(new ItemStack(ID_BOOTS_DIAMOND, 1));
		player.getInventory().addItem(new ItemStack(ID_SWORD_DIAMOND, 1));
	}

}
