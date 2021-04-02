package Global_additions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;

import java.awt.List;
import java.lang.reflect.Constructor;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.IntToDoubleFunction;
import java.util.function.ToIntFunction;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.World.Spigot;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

public class World_details implements Listener
{
	
	int blocks_broken;
	int chunks_generated;
	int XP_earned;
	
	public World_details()
	{
		blocks_broken = 0;
		chunks_generated = 0;
		XP_earned = 0;
	}
	
	//This function handles how entities being damaged can have certain effects.
	@EventHandler
	public void entityDamageByEntity(EntityDamageByEntityEvent event)
	{
		World world = event.getEntity().getWorld();

		Player damaged_player;
		Player damaging_player;

		if(event.getEntity().getType().equals(EntityType.PLAYER))
		{
			damaged_player = (Player) (event.getEntity());
		}
		if(event.getDamager().getType().equals(EntityType.PLAYER))
		{
			damaging_player = (Player) (event.getDamager());
		}
		
		//This produces a heart particle on the damaged entity.
		Entity damaged = event.getDamager();		
		
		if(event.getDamager().getType().equals(EntityType.PLAYER))
		{
			Entity ent = event.getEntity();
			
			world.spawnParticle(Particle.HEART,
					event.getEntity().getLocation().getX(),
					event.getEntity().getLocation().getY() + 2,
					event.getEntity().getLocation().getZ(),
					(int) event.getDamage()
					);
			world.spawnParticle(Particle.TOTEM,
					event.getEntity().getLocation().getX(),
					event.getEntity().getLocation().getY() + 2,
					event.getEntity().getLocation().getZ(),
					(int) event.getDamage()
					);

			world.playSound(event.getDamager().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, (int) event.getDamage());

		}

		if(event.getDamager().getType().equals(EntityType.ARROW))
		{
			Entity ent = event.getEntity();
			
			world.spawnParticle(Particle.CRIMSON_SPORE,
					event.getDamager().getLocation().getX(),
					event.getDamager().getLocation().getY(),
					event.getDamager().getLocation().getZ(),
					(int) event.getDamage() * 10
					);

			world.playSound(event.getDamager().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, (int) event.getDamage());

		}
		
	}
	
	@EventHandler
	public void BlockGrow(BlockGrowEvent event)
	{
		if(1 == (int) (Math.random() * 10))
		{
			World world = Bukkit.getWorlds().get(0);

			Location spawn_pos = event.getBlock().getLocation();
			spawn_pos.setY(spawn_pos.getY() + 1);
		
			world.spawnEntity(spawn_pos, EntityType.THROWN_EXP_BOTTLE);
		}
	}
	
	@EventHandler
	public void BlockBreak(BlockBreakEvent event)
	{
		if(event.getPlayer().getGameMode() == GameMode.CREATIVE)
		{
			return;
		}
		
		boolean[] has_pick_condition = {
				(event.getPlayer().getInventory().getItem(event.getPlayer().getInventory().getHeldItemSlot()).getType() == Material.NETHERITE_PICKAXE),
				(event.getPlayer().getInventory().getItem(event.getPlayer().getInventory().getHeldItemSlot()).getType() == Material.DIAMOND_PICKAXE),
				(event.getPlayer().getInventory().getItem(event.getPlayer().getInventory().getHeldItemSlot()).getType() == Material.IRON_PICKAXE),
				(event.getPlayer().getInventory().getItem(event.getPlayer().getInventory().getHeldItemSlot()).getType() == Material.GOLDEN_PICKAXE),
				(event.getPlayer().getInventory().getItem(event.getPlayer().getInventory().getHeldItemSlot()).getType() == Material.STONE_PICKAXE),
				(event.getPlayer().getInventory().getItem(event.getPlayer().getInventory().getHeldItemSlot()).getType() == Material.WOODEN_PICKAXE)};
		
		for(int i = 0; i != 6; i++)
		{
			if(has_pick_condition[i] == true)
			{
				//This is used to increment the block_broken counter.
				blocks_broken++;
				
				if(50 < blocks_broken)
				{
					blocks_broken = 0;
					event.getPlayer().giveExp(1);
				}
				
				break;
			}
		}
		
	}

}
