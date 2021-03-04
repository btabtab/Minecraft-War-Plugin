package Player_managment;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class playerManager implements Listener
{
	public int players_online;
	
	public playerManager()
	{
		// TODO Auto-generated constructor stub
		players_online = 0;
	}
	
	public void updatePlayerManager()
	{
		players_online = Bukkit.getServer().getOnlinePlayers().size();

	}
	
}
