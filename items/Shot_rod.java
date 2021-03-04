package items;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Shot_rod implements Listener {

	public void giveShotRod(Player player)
	{
		ItemStack item = new ItemStack(Material.FISHING_ROD, 1);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(ChatColor.AQUA + "Shot Rod");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.DARK_AQUA + "Launches a projectile");
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		player.getInventory().addItem(item);
	}
	
	public void playerItemHeld(PlayerItemHeldEvent event)
	{
		event.getPlayer().sendMessage("change...");
	}
}
