package me.jacob.instadmgnerf.other;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import me.jacob.instadmgnerf.DamageNerfer;
import me.jacob.instadmgnerf.DamageNerfer.Disabled;
import me.jacob.instadmgnerf.DamageNerfer.DivideDamage;
import me.jacob.instadmgnerf.DamageNerfer.SubtractDamage;
import me.jacob.instadmgnerf.DamageNerfer.ZeroDamage;
import me.jacob.instadmgnerf.InstantDamageNerf;
import org.bukkit.configuration.file.FileConfiguration;

@UtilityClass
public class Config {

	private final InstantDamageNerf plugin = InstantDamageNerf.getInstance();

	@Getter
	private DamageNerfer nerfer = Disabled.INSTANCE;

	@Getter
	private boolean disableInstantDamageArrows;
	@Getter
	private boolean bypassWithOp;
	@Getter
	private String bypassPermission;

	public void reload() {
		plugin.saveDefaultConfig();
		plugin.reloadConfig();

		FileConfiguration config = plugin.getConfig();

		disableInstantDamageArrows = config.getBoolean("disable-instant-damage-arrows");
		bypassWithOp = config.getBoolean("bypass-with-op");
		bypassPermission = config.getString("bypass-permission");

		if(bypassPermission != null && bypassPermission.isEmpty()) {
			bypassPermission = null;
		}

		String nerfType = config.getString("nerf-type");
		int arg = config.getInt("argument");

		if(nerfType == null) {
			plugin.getLogger().warning("No nerf type specified, disabling nerf");
			nerfer = Disabled.INSTANCE;
			return;
		}

		switch(nerfType) {
			case "disabled":
				nerfer = Disabled.INSTANCE;
				break;
			case "zero":
				nerfer = ZeroDamage.INSTANCE;
				break;
			case "subtract":
				if(arg <= 0)
					nerfer = Disabled.INSTANCE;
				else
					nerfer = new SubtractDamage(arg);
				break;
			case "divide":
				if(arg <= 1)
					nerfer = Disabled.INSTANCE;
				else
					nerfer = new DivideDamage(arg);
				break;
			default:
				nerfer = Disabled.INSTANCE;
				plugin.getLogger().warning("Invalid nerf type: " + nerfType);
				break;
		}
	}

}
