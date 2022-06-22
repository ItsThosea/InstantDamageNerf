package me.jacob.instadmgnerf;

import me.jacob.instadmgnerf.listeners.CraftEvent;
import me.jacob.instadmgnerf.listeners.DamageEvent;
import me.jacob.instadmgnerf.other.Config;
import me.jacob.instadmgnerf.other.ReloadCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class InstantDamageNerf extends JavaPlugin {

	private static InstantDamageNerf instance;

	public void onEnable() {
		instance = this;

		Config.reload();

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new CraftEvent(), this);
		pm.registerEvents(new DamageEvent(), this);

		getCommand("reloadharmingnerf").setExecutor(new ReloadCommand());
	}

	public static InstantDamageNerf getInstance() {
		return instance;
	}
}
