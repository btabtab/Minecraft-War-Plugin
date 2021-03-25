package war_plugin;

import java.io.Console;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.event.player.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity.Spigot;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;
import org.bukkit.World;

import Global_additions.*;
import Player_managment.playerManager;
import ServerManager.ServerManager;
import items.Shot_rod;

/*
 * TODO:
 * Fix golden apple issue
 * Fix damage stat issue
 * 
*/

//This is the main file for the entire plugin.

public class War_plugin_main extends JavaPlugin
{

	// Fired when plugin is first enabled
    @Override
    public void onEnable()
    {
    	this.getServer().getPluginManager().registerEvents(new World_details(), this);
    	this.getServer().getPluginManager().registerEvents(new ServerManager(), this);
    	this.getServer().getPluginManager().registerEvents(new Shot_rod(), this);
//    	ArrayList<Player> list = new ArrayList<Player>(Bukkit.getOnlinePlayers());
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable()
    {
    }
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
		
	//This is responsible for handling the command
	//that sends the player back to their bed
    	if(label.equalsIgnoreCase("warphome"))
    	{
    		if(sender instanceof Player)
    		{
    			
    			Player player = (Player) sender;
//    			Event evnt;
    			
    			player.teleport(player.getBedSpawnLocation());
    			return true;
    		}
    	}

    	if(label.equalsIgnoreCase("Save"))
    	{
    		if(sender instanceof Player)
    		{
    			return true;
    		}
    	}

    	if(label.equalsIgnoreCase("GetShotRod"))
    	{
    		if(sender instanceof Player)
    		{
    			Player plyr = ((Player) sender).getPlayer();
    			sender.sendMessage("Giving you the item");
    			
    			if(plyr.getGameMode().equals(GameMode.CREATIVE))
    			{
    				Shot_rod sht_rd = new Shot_rod();
    				sht_rd.giveShotRod(plyr);
    				sender.sendMessage("here ya go...");
    			}
    			return true;
    		}
    	}

    	if(label.equalsIgnoreCase("getServerInfo"))
    	{
    		ServerManager temp = new ServerManager();
    		temp.getServerInformation((Player) sender);
    	}


    	return false;
    }
	
}
