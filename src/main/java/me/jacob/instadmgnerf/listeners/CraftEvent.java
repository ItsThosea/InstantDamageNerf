package me.jacob.instadmgnerf.listeners;

import me.jacob.instadmgnerf.InstantDamageNerf;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.Recipe;

public class CraftEvent implements Listener {
	private static final InstantDamageNerf plugin = InstantDamageNerf.getInstance();

	@EventHandler
	public void onCraft(PrepareItemCraftEvent e) {
		// Is the player crafting harming arrows?
		if(e.isRepair())
			return;

		Recipe r = e.getRecipe();

		if(r == null)
			return;

		if(!plugin.isHarmingArrow(e.getInventory().getResult()))
			return;

		// Check if they are allowed to craft it
		Player p = (Player) e.getView().getPlayer();

		if(plugin.canUseHarmingArrows(p))
			return;

		// They are not allowed to craft it
		e.getInventory().setResult(null);
	}

}
