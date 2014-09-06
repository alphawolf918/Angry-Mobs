package angrymobs.mobs.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpiderEffectsGroupData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntityEnderSpider extends EntityMob {
	public EntityEnderSpider(World par1World) {
		super(par1World);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte) 0));
	}
	
	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if (!this.worldObj.isRemote) {
			this.setBesideClimbableBlock(this.isCollidedHorizontally);
		}
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
				.setAttribute(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
				.setAttribute(0.800000011920929D);
	}
	
	/**
	 * Finds the closest player within 16 blocks to attack, or null if this
	 * Entity isn't interested in attacking (Animals, Spiders at day, peaceful
	 * PigZombies).
	 */
	@Override
	protected Entity findPlayerToAttack() {
		EntityPlayer entityplayer = this.worldObj
				.getClosestVulnerablePlayerToEntity(this, 64.0D);
		
		if (entityplayer != null)
		{
			return entityplayer;
		} else {
			return null;
		}
	}
	
	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound() {
		return "mob.spider.say";
	}
	
	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound() {
		return "mob.spider.say";
	}
	
	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected String getDeathSound() {
		return "mob.spider.death";
	}
	
	/**
	 * Plays step sound at given x, y, z for the entity
	 */
	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4) {
		this.playSound("mob.spider.step", 0.20F, 2.0F);
	}
	
	/**
	 * Basic mob attack. Default to touch of death in EntityCreature. Overridden
	 * by each mob to define their attack.
	 */
	@Override
	protected void attackEntity(Entity par1Entity, float par2) {
		float f1 = this.getBrightness(1.0F);
		if (par2 > 2.0F && par2 < 6.0F && this.rand.nextInt(10) == 0) {
			if (this.onGround) {
				double d0 = par1Entity.posX - this.posX;
				double d1 = par1Entity.posZ - this.posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				this.motionX = d0 / f2 * 0.5D * 0.800000011920929D
						+ this.motionX * 0.20000000298023224D;
				this.motionZ = d1 / f2 * 0.5D * 0.800000011920929D
						+ this.motionZ * 0.20000000298023224D;
				this.motionY = 0.4000000059604645D;
			}
		} else {
			super.attackEntity(par1Entity, par2);
		}
	}
	
	@Override
	public void onLivingUpdate() {
		int i;
		for (i = 0; i < 2; ++i) {
			this.worldObj.spawnParticle("portal",
					this.posX + (this.rand.nextDouble() - 0.5D)
							* this.width,
					this.posY + this.rand.nextDouble() * this.height - 0.25D,
					this.posZ
							+ (this.rand.nextDouble() - 0.5D)
							* this.width,
					(this.rand.nextDouble() - 0.5D) * 2.0D,
					-this.rand.nextDouble(),
					(this.rand.nextDouble() - 0.5D) * 2.0D);
		}
		float f = this.getBrightness(1.0F);
		
		if (f > 0.5F
				&& this.worldObj.canBlockSeeTheSky(
						MathHelper.floor_double(this.posX),
						MathHelper.floor_double(this.posY),
						MathHelper.floor_double(this.posZ))
				&& this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
			this.entityToAttack = null;
			this.teleportRandomly();
		}
	}
	
	protected boolean teleportRandomly() {
		double d0 = this.posX + (this.rand.nextDouble() - 1.5D) * 64.0D;
		double d1 = this.posY + (this.rand.nextInt(64) - 32);
		double d2 = this.posZ + (this.rand.nextDouble() - 1.5D) * 64.0D;
		return this.teleportTo(d0, d1, d2);
	}
	
	protected boolean teleportTo(double par1, double par3, double par5) {
		EnderTeleportEvent event = new EnderTeleportEvent(this, par1, par3,
				par5, 0);
		if (MinecraftForge.EVENT_BUS.post(event)) {
			return false;
		}
		
		double d3 = this.posX;
		double d4 = this.posY;
		double d5 = this.posZ;
		this.posX = event.targetX;
		this.posY = event.targetY;
		this.posZ = event.targetZ;
		boolean flag = false;
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.posY);
		int k = MathHelper.floor_double(this.posZ);
		int l;
		
		if (this.worldObj.blockExists(i, j, k)) {
			boolean flag1 = false;
			
			while (!flag1 && j > 0) {
				l = this.worldObj.getBlockId(i, j - 1, k);
				
				if (l != 0 && Block.blocksList
						
						[l].blockMaterial.blocksMovement()) {
					flag1 = true;
				} else {
					--this.posY;
					--j;
				}
			}
			
			if (flag1) {
				this.setPosition(this.posX, this.posY, this.posZ);
				
				if (this.worldObj.getCollidingBoundingBoxes(this,
						this.boundingBox).isEmpty()
						&& !this.worldObj.isAnyLiquid
								
								(this.boundingBox)) {
					flag = true;
				}
			}
		}
		
		if (!flag) {
			this.setPosition(d3, d4, d5);
			return false;
		} else {
			short short1 = 128;
			
			for (l = 0; l < short1; ++l) {
				double d6 = l / (short1 - 1.0D);
				float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				double d7 = d3 + (this.posX - d3) * d6
						+ (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
				double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble
						
						() * this.height;
				double d9 = d5 + (this.posZ - d5) * d6
						+ (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
				this.worldObj.spawnParticle("portal", d7, d8, d9, f,
						f1, f2);
			}
			
			this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal",
					1.0F, 1.0F);
			this.playSound("mob.endermen.portal", 1.0F, 1.0F);
			return true;
		}
	}
	
	/**
	 * Returns the item ID for the item the mob drops on death.
	 */
	@Override
	protected int getDropItemId() {
		return Item.silk.itemID;
	}
	
	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity
	 * has recently been hit by a player. @param par2 - Level of Looting used to
	 * kill this mob.
	 */
	@Override
	protected void dropFewItems(boolean par1, int par2) {
		super.dropFewItems(par1, par2);
		
		if (par1
				&& (this.rand.nextInt(3) == 0 || this.rand.nextInt(1 + par2) > 0)) {
			this.dropItem(Item.spiderEye.itemID, 1);
			this.dropItem(Item.enderPearl.itemID, 1);
		}
	}
	
	/**
	 * returns true if this entity is by a ladder, false otherwise
	 */
	@Override
	public boolean isOnLadder() {
		return this.isBesideClimbableBlock();
	}
	
	/**
	 * Sets the Entity inside a web block.
	 */
	@Override
	public void setInWeb() {
	}
	
	/**
	 * Get this Entity's EnumCreatureAttribute
	 */
	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}
	
	@Override
	public boolean isPotionApplicable(PotionEffect par1PotionEffect) {
		return par1PotionEffect.getPotionID() == Potion.poison.id ? false
				: super.isPotionApplicable(par1PotionEffect);
	}
	
	/**
	 * Returns true if the WatchableObject (Byte) is 0x01 otherwise returns
	 * false. The WatchableObject is updated using setBesideClimableBlock.
	 */
	public boolean isBesideClimbableBlock() {
		return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
	}
	
	/**
	 * Updates the WatchableObject (Byte) created in entityInit(), setting it to
	 * 0x01 if par1 is true or 0x00 if it is false.
	 */
	public void setBesideClimbableBlock(boolean par1) {
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);
		
		if (par1) {
			b0 = (byte) (b0 | 1);
		} else {
			b0 &= -2;
		}
		
		this.dataWatcher.updateObject(16, Byte.valueOf(b0));
	}
	
	@Override
	public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
		Object par1EntityLivingData1 = super
				.onSpawnWithEgg(par1EntityLivingData);
		
		if (this.worldObj.rand.nextInt(100) == 0) {
			EntityShadowSkeleton entityskeleton = new EntityShadowSkeleton(
					this.worldObj);
			entityskeleton.setLocationAndAngles(this.posX, this.posY,
					this.posZ, this.rotationYaw, 0.0F);
			entityskeleton.onSpawnWithEgg((EntityLivingData) null);
			this.worldObj.spawnEntityInWorld(entityskeleton);
			entityskeleton.mountEntity(this);
		}
		
		if (par1EntityLivingData1 == null) {
			par1EntityLivingData1 = new SpiderEffectsGroupData();
			
			if (this.worldObj.difficultySetting > 2
					&& this.worldObj.rand.nextFloat() < 0.1F * this.worldObj
							.getLocationTensionFactor(this.posX, this.posY,
									this.posZ)) {
				((SpiderEffectsGroupData) par1EntityLivingData1)
						.func_111104_a(this.worldObj.rand);
			}
		}
		
		if (par1EntityLivingData1 instanceof SpiderEffectsGroupData) {
			int i = ((SpiderEffectsGroupData) par1EntityLivingData1).field_111105_a;
			
			if (i > 0 && Potion.potionTypes[i] != null) {
				this.addPotionEffect(new PotionEffect(i, Integer.MAX_VALUE));
			}
		}
		
		return (EntityLivingData) par1EntityLivingData1;
	}
}