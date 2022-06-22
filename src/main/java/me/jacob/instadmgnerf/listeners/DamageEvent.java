package me.jacob.instadmgnerf.listeners;

import me.jacob.instadmgnerf.DamageNerfer;
import me.jacob.instadmgnerf.DamageNerfer.Disabled;
import me.jacob.instadmgnerf.other.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class DamageEvent implements Listener {
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if(event.getCause() != DamageCause.MAGIC)
			return;

		double damage = event.getDamage();

		if(damage <= 0)
			return;

		DamageNerfer nerfer = Config.getNerfer();

		if(nerfer == Disabled.INSTANCE)
			return;

		event.setDamage(nerfer.getDamage(damage));
	}
}
