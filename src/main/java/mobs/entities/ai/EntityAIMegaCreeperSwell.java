package angrymobs.mobs.entities.ai;

import angrymobs.mobs.entities.EntityMegaCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIMegaCreeperSwell extends EntityAIBase {
	/** The creeper that is swelling. */
	EntityMegaCreeper swellingCreeper;

	/**
	 * The creeper's attack target. This is used for the changing of the
	 * creeper's state.
	 */
	EntityLivingBase creeperAttackTarget;

	public EntityAIMegaCreeperSwell(EntityMegaCreeper par1EntityMegaCreeper) {
		this.swellingCreeper = par1EntityMegaCreeper;
		this.setMutexBits(1);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		EntityLivingBase entitylivingbase = this.swellingCreeper
				.getAttackTarget();
		return this.swellingCreeper.getCreeperState() > 0
				|| entitylivingbase != null
				&& this.swellingCreeper.getDistanceSqToEntity(entitylivingbase) < 9.0D;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		this.swellingCreeper.getNavigator().clearPathEntity();
		this.creeperAttackTarget = this.swellingCreeper.getAttackTarget();
	}

	/**
	 * Resets the task
	 */
	public void resetTask() {
		this.creeperAttackTarget = null;
	}

	/**
	 * Updates the task
	 */
	public void updateTask() {
		if (this.creeperAttackTarget == null) {
			this.swellingCreeper.setCreeperState(-1);
		} else if (this.swellingCreeper
				.getDistanceSqToEntity(this.creeperAttackTarget) > 49.0D) {
			this.swellingCreeper.setCreeperState(-1);
		} else if (!this.swellingCreeper.getEntitySenses().canSee(
				this.creeperAttackTarget)) {
			this.swellingCreeper.setCreeperState(-1);
		} else {
			this.swellingCreeper.setCreeperState(1);
		}
	}
}