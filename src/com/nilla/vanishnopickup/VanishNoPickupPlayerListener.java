package com.nilla.vanishnopickup;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerTeleportEvent;


/**
 * Handle events for all Player related events
 * 
 * @author Nodren
 * @updated EvilNilla
 */
public class VanishNoPickupPlayerListener extends PlayerListener
{
	private final VanishNoPickup plugin;

	public VanishNoPickupPlayerListener(VanishNoPickup instance)
	{
		plugin = instance;
	}

	@Override
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();

		if(plugin.nopickups.contains(player.getName())){
			player.sendMessage(ChatColor.RED + "You have item pickups disabled!");
		}

		if(plugin.invisible.contains(player.getName())){
			player.sendMessage(ChatColor.RED + "You are currently invisible!");
			plugin.vanish(player);
		}
	}

	@Override
	public void onPlayerTeleport(PlayerTeleportEvent event)
	{
		if (event.isCancelled()) { return; }
		plugin.updateInvisibleOnTimer();
	}
	
	
	@Override	
	public void onPlayerPickupItem(PlayerPickupItemEvent event){
		
		Player player = event.getPlayer();
	
		if(plugin.nopickups.contains(player.getName())) {
			event.setCancelled(true);
		}
		
	}
}
