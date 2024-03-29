package angrymobs.mobs.entities;

import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import angrymobs.items.Items;

public class EntityShark extends EntityWaterMob {
	public float squidPitch;
	public float prevSquidPitch;
	public float squidYaw;
	public float prevSquidYaw;
	public EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(
			this, EntityPlayer.class, 2.2D, true);
	
	/**
	 * appears to be rotation in radians; we already have pitch & yaw, so this
	 * completes the triumvirate.
	 */
	public float squidRotation;
	
	/** previous squidRotation in radians. */
	public float prevSquidRotation;
	
	/** angle of the tentacles in radians */
	public float tentacleAngle;
	
	/** the last calculated angle of the tentacles in radians */
	public float prevTentacleAngle;
	private float randomMotionSpeed;
	
	/** change in squidRotation in radians. */
	private float rotationVelocity;
	private float field_70871_bB;
	private float randomMotionVecX;
	private float randomMotionVecY;
	private float randomMotionVecZ;
	
	public EntityShark(World par1World) {
		super(par1World);
		this.setSize(0.95F, 0.95F);
		this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
		this.tasks.addTask(1, new EntityAIWatchClosest(this,
				EntityPlayer.class, 6.0F));
		this.tasks.addTask(2, new EntityAIWander(this, this.getAIMoveSpeed()));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this,
				EntityPlayer.class, 4, true));
		this.targetTasks.addTask(2, aiAttackOnCollide);
		this.tasks.addTask(2, new EntityAILookIdle(this));
		this.setHealth(40F);
		this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
		this.setAIMoveSpeed(2.0F);
		this.experienceValue = 100;
		this.scoreValue = 300;
		
		if (par1World != null && !par1World.isRemote) {
			this.setCombatTask();
		}
	}
	
	@Override
	public boolean isAIEnabled() {
		return true;
	}
	
	/**
	 * sets this entity's combat AI.
	 */
	public void setCombatTask() {
		this.tasks.removeTask(this.aiAttackOnCollide);
		this.tasks.addTask(2, this.aiAttackOnCollide);
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
				.setAttribute(40.0D);
	}
	
	/**
	 * Returns the item ID for the item the mob drops on death.
	 */
	@Override
	protected int getDropItemId() {
		return Items.sharkLeather.itemID;
	}
	
	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they
	 * walk on. used for spiders and wolves to prevent them from trampling crops
	 */
	@Override
	protected boolean canTriggerWalking() {
		return false;
	}
	
	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity
	 * has recently been hit by a player. @param par2 - Level of Looting used to
	 * kill this mob.
	 */
	@Override
	protected void dropFewItems(boolean par1, int par2) {
		int j = this.rand.nextInt(3 + par2) + 1;
		int l = (int) (Math.random() * Math.floor(4));
		Item droppedItem = (l > 2) ? Items.sharkTooth : Items.sharkLeather;
		for (int k = 0; k < j; ++k) {
			this.entityDropItem(new ItemStack(droppedItem, 1, 0), 0.0F);
		}
	}
	
	/**
	 * Checks if this entity is inside water (if inWater field is true as a
	 * result of handleWaterMovement() returning true)
	 */
	@Override
	public boolean isInWater() {
		return this.worldObj.handleMaterialAcceleration(
				this.boundingBox.expand(0.0D, -0.6000000238418579D, 0.0D),
				Material.water, this);
	}
	
	/**
	 * Called frequently so the entity can update its state every tick as
	 * required. For example, zombies and skeletons use this to react to
	 * sunlight and start to burn.
	 */
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.prevSquidPitch = this.squidPitch;
		this.prevSquidYaw = this.squidYaw;
		this.prevSquidRotation = this.squidRotation;
		this.prevTentacleAngle = this.tentacleAngle;
		this.squidRotation += this.rotationVelocity;
		
		if (this.squidRotation > ((float) Math.PI * 2F)) {
			this.squidRotation -= ((float) Math.PI * 2F);
			
			if (this.rand.nextInt(10) == 0) {
				this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
			}
		}
		
		if (this.isInWater()) {
			float f;
			
			if (this.squidRotation < (float) Math.PI) {
				f = this.squidRotation / (float) Math.PI;
				this.tentacleAngle = MathHelper.sin(f * f * (float) Math.PI)
						* (float) Math.PI * 0.25F;
				
				if (f > 0.75D) {
					this.randomMotionSpeed = 3.0F;
					this.field_70871_bB = 1.0F;
				} else {
					this.field_70871_bB *= 0.8F;
				}
			} else {
				this.tentacleAngle = 0.0F;
				this.randomMotionSpeed *= 1.9F;
				this.field_70871_bB *= 0.99F;
			}
			
			if (!this.worldObj.isRemote) {
				this.motionX = this.randomMotionVecX * this.randomMotionSpeed;
				this.motionY = this.randomMotionVecY * this.randomMotionSpeed;
				this.motionZ = this.randomMotionVecZ * this.randomMotionSpeed;
			}
			
			f = MathHelper.sqrt_double(this.motionX * this.motionX
					+ this.motionZ * this.motionZ);
			this.renderYawOffset += (-((float) Math.atan2(this.motionX,
					this.motionZ)) * 180.0F / (float) Math.PI - this.renderYawOffset) * 0.1F;
			this.rotationYaw = this.renderYawOffset;
			this.squidYaw += (float) Math.PI * this.field_70871_bB * 1.5F;
			this.squidPitch += (-((float) Math.atan2(f, this.motionY))
					* 180.0F / (float) Math.PI - this.squidPitch) * 0.1F;
		} else {
			this.tentacleAngle = MathHelper.abs(MathHelper
					.sin(this.squidRotation)) * (float) Math.PI * 0.25F;
			
			if (!this.worldObj.isRemote) {
				this.motionX = 0.0D;
				this.motionY -= 0.08D;
				this.motionY *= 0.9800000190734863D;
				this.motionZ = 0.0D;
			}
			
			this.squidPitch = (float) (this.squidPitch + (-90.0F - this.squidPitch) * 0.02D);
		}
	}
	
	/**
	 * Moves the entity based on the specified heading. Args: strafe, forward
	 */
	@Override
	public void moveEntityWithHeading(float par1, float par2) {
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
	}
	
	@Override
	protected void updateEntityActionState() {
		++this.entityAge;
		
		if (this.entityAge > 100) {
			this.randomMotionVecX = this.randomMotionVecY = this.randomMotionVecZ = 0.0F;
		} else if (this.rand.nextInt(50) == 0 || !this.inWater
				|| this.randomMotionVecX == 0.0F
				&& this.randomMotionVecY == 0.0F
				&& this.randomMotionVecZ == 0.0F) {
			float f = this.rand.nextFloat() * (float) Math.PI * 2.0F;
			this.randomMotionVecX = MathHelper.cos(f) * 0.2F;
			this.randomMotionVecY = -0.1F + this.rand.nextFloat() * 0.2F;
			this.randomMotionVecZ = MathHelper.sin(f) * 0.2F;
		}
		
		this.despawnEntity();
	}
	
	/**
	 * Checks if the entity's current position is a valid location to spawn this
	 * entity.
	 */
	@Override
	public boolean getCanSpawnHere() {
		return this.posY > 45.0D && this.posY < 63.0D
				&& super.getCanSpawnHere();
	}
}