package me.jacob.instadmgnerf;

import lombok.AllArgsConstructor;

public interface DamageNerfer {
	double getDamage(double damage);

	final class Disabled implements DamageNerfer { // Don't change the damage
		private Disabled() {}

		public static final Disabled INSTANCE = new Disabled();

		public double getDamage(double damage) {
			return damage;
		}
	}

	final class ZeroDamage implements DamageNerfer { // Set damage to 0
		private ZeroDamage() {}

		public static final ZeroDamage INSTANCE = new ZeroDamage();

		public double getDamage(double damage) {
			return 0;
		}
	}

	@AllArgsConstructor
	final class SubtractDamage implements DamageNerfer { // Subtract damage from the damage
		private final double damageToSubtract;

		public double getDamage(double damage) {
			double result = damage - damageToSubtract;

			if(result < 0)
				return 0;

			return result;
		}
	}

	@AllArgsConstructor
	final class DivideDamage implements DamageNerfer { // Divide damage by a number
		private final double divisor;

		public double getDamage(double damage) {
			double result = damage / divisor;

			if(result < 0)
				return 0;

			return result;
		}
	}

}
