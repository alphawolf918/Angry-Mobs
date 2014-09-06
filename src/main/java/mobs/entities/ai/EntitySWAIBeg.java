package angrymobs.mobs.entities.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import angrymobs.items.Items;
import angrymobs.mobs.entities.EntityShadowWolf;

public class EntitySWAIBeg extends EntityAIBase {
	private EntityShadowWolf theWolf;
	private EntityPlayer thePlayer;
	private World worldObject;
	private float minPlayerDistance;
	private int field_75384_e;

	public EntitySWAIBeg(EntityShadowWolf entityShadowWolf, float par2) {
		this.theWolf = entityShadowWolf;
		this.worldObject = entityShadowWolf.worldObj;
		this.minPlayerDistance = par2;
		this.setMutexBits(2);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		this.thePlayer = this.worldObject.getClosestPlayerToEntity(
				this.theWolf, (double) this.minPlayerDistance);
		return this.thePlayer == null ? false : this
				.hasPlayerGotBoneInHand(this.thePlayer);
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting() {
		return !this.thePlayer.isEntityAlive() ? false
				: (this.theWolf.getDistanceSqToEntity(this.thePlayer) > (double) (this.minPlayerDistance * this.minPlayerDistance) ? false
						: this.field_75384_e > 0
								&& this.hasPlayerGotBoneInHand(this.thePlayer));
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		this.theWolf.func_70918_i(true);
		this.field_75384_e = 40 + this.theWolf.getRNG().nextInt(40);
	}

	/**
	 * Resets the task
	 */
	public void resetTask() {
		this.theWolf.func_70918_i(false);
		this.thePlayer = null;
	}

	/**
	 * Updates the task
	 */
	public void updateTask() {
		this.theWolf.getLookHelper().setLookPosition(this.thePlayer.posX,
				this.thePlayer.posY + (double) this.thePlayer.getEyeHeight(),
				this.thePlayer.posZ, 10.0F,
				(float) this.theWolf.getVerticalFaceSpeed());
		--this.field_75384_e;
	}

	/**
	 * Gets if the Player has the Bone in the hand.
	 */
	private boolean hasPlayerGotBoneInHand(EntityPlayer par1EntityPlayer) {
		ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
		return itemstack == null ? false : (!this.theWolf.isTamed()
				&& itemstack.itemID == Items.ShadowBone.itemID ? true
				: this.theWolf.isBreedingItem(itemstack));
	}
}
