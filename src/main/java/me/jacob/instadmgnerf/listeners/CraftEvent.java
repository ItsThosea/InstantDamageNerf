package me.jacob.instadmgnerf.listeners;

import me.jacob.instadmgnerf.other.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class CraftEvent implements Listener {
	@EventHandler
	public void onCraft(PrepareItemCraftEvent e) {
		// Is the player crafting harming arrows?
		if(e.isRepair())
			return;

		Recipe r = e.getRecipe();

		if(r == null)
			return;

		ItemStack result = e.getInventory().getResult();

		if(result == null)
			return;

		ItemMeta meta = result.getItemMeta();

		if(meta == null)
			return;

		if(!(meta instanceof PotionMeta))
			return;

		PotionMeta pm = (PotionMeta) meta;
		PotionData data = pm.getBasePotionData();

		if(data.getType() != PotionType.INSTANT_DAMAGE)
			return;

		// Check if they are allowed to craft it
		Player p = (Player) e.getView().getPlayer();

		if(p.isOp() && Config.isBypassWithOp())
			return;
		if(Config.getBypassPermission() != null && p.hasPermission(Config.getBypassPermission()))
			return;

		// They are not allowed to craft it
		e.getInventory().setResult(null);
	}

}
