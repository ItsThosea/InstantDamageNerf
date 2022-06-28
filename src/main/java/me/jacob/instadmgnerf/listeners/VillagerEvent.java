package me.jacob.instadmgnerf.listeners;

import me.jacob.instadmgnerf.InstantDamageNerf;
import me.jacob.instadmgnerf.other.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class VillagerEvent implements Listener {
	private static final InstantDamageNerf plugin = InstantDamageNerf.getInstance();

	@EventHandler
	public void onTrade(InventoryClickEvent e) {
		if(!Config.isDisableBuyingHarmingArrows())
			return;

		Inventory inv = e.getClickedInventory();

		if(inv == null || inv.getType() != InventoryType.MERCHANT)
			return;

		Player p = (Player) e.getWhoClicked();

		if(plugin.canUseHarmingArrows(p))
			return;

		if(!plugin.isHarmingArrow(e.getCurrentItem()))
			return;

		e.setCancelled(true);
	}
}
