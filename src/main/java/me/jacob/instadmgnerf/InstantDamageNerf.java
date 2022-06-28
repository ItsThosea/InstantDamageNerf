package me.jacob.instadmgnerf;

import lombok.Getter;
import me.jacob.instadmgnerf.listeners.CraftEvent;
import me.jacob.instadmgnerf.listeners.DamageEvent;
import me.jacob.instadmgnerf.other.Config;
import me.jacob.instadmgnerf.other.ReloadCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public final class InstantDamageNerf extends JavaPlugin {

	@Getter
	private static InstantDamageNerf instance;

	public void onEnable() {
		instance = this;

		Config.reload();

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new CraftEvent(), this);
		pm.registerEvents(new DamageEvent(), this);

		getCommand("reloadharmingnerf").setExecutor(new ReloadCommand());
	}

	public boolean canUseHarmingArrows(Player p) {
		if(p.isOp() && Config.isBypassWithOp())
			return true;
		if(Config.getBypassPermission() != null && p.hasPermission(Config.getBypassPermission()))
			return true;

		return false;
	}

	public boolean isHarmingArrow(ItemStack item) {
		if(item == null)
			return false;

		ItemMeta meta = item.getItemMeta();

		if(meta == null)
			return false;

		if(!(meta instanceof PotionMeta))
			return false;

		PotionMeta pm = (PotionMeta) meta;
		PotionData data = pm.getBasePotionData();

		return data.getType() == PotionType.INSTANT_DAMAGE;
	}
}
