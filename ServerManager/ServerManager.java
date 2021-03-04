package ServerManager;

import java.lang.reflect.Array;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import Player_managment.playerManager;

public class ServerManager implements Listener
{
	
	playerManager plyrmngr;
	World world;
	
	int entity_count;
	
	public ServerManager()
	{
		// TODO Auto-generated constructor stub
		plyrmngr = new playerManager();
		world = Bukkit.getWorlds().get(0);
	}
	
	public void updateServerManager()
	{
		plyrmngr.updatePlayerManager();
		entity_count = world.getEntities().size();
	}
	
	public void HandleRandomTickSpeed(playerManager mngr)
	{
		
		mngr.updatePlayerManager();
		
		if(3 < mngr.players_online)
		{
			world.setGameRule(GameRule.RANDOM_TICK_SPEED, 2);
		}
		else
		{
			world.setGameRule(GameRule.RANDOM_TICK_SPEED, 3);
		}
	}

	public void HandleEntityLimit(playerManager mngr)
	{

		if(30 < world.getEntities().size())
		{
			world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
		}
		else
		{
			world.setGameRule(GameRule.DO_MOB_SPAWNING, true);
		}
		
	}
	
	public void getServerInformation(Player sender)
	{
		updateServerManager();
		
		String[] messages = {
				"<SERVER INFORMATION>",
			"",
			"players online = " + String.valueOf(plyrmngr.players_online),
			"",
			"Entity count = " + String.valueOf(entity_count),
		};
		
		for(int i = 0; i != Array.getLength(messages); i++)
		{
			sender.sendMessage(messages[i]);
		}

		for(int i = 0; i != Array.getLength(messages); i++)
		{
			Bukkit.getConsoleSender().sendMessage(messages[i]);
		}
	}

	
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent event)
	{
		plyrmngr.updatePlayerManager();
	}
	
	@EventHandler
	public void EntitySpawn(EntitySpawnEvent event)
	{
		entity_count = world.getEntities().size();
	}
	
	@EventHandler
	public void EntityDeath(EntityDeathEvent event)
	{
		entity_count = world.getEntities().size();
	}

	
}
