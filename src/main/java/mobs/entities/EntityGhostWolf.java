package angrymobs.mobs.entities;

import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import angrymobs.items.Items;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityGhostWolf extends EntityTameable {
	private float field_70926_e;
	private float field_70924_f;
	
	/** true is the wolf is wet else false */
	private boolean isShaking;
	private boolean field_70928_h;
	
	/**
	 * This time increases while wolf is shaking and emitting water particles.
	 */
	private float timeWolfIsShaking;
	private float prevTimeWolfIsShaking;
	
	public EntityGhostWolf(World par1World) {
		super(par1World);
		this.setSize(0.6F, 0.8F);
		this.stepHeight = 2F;
		this.isImmuneToFire = true;
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.5F));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 2.0D, true));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this,
				EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				EntitySkeleton.class, 400, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this,
				EntityShadowSkeleton.class, 400, true));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this,
				EntityHelleton.class, 400, true));
		this.setAlwaysRenderNameTag(true);
		this.setTamed(false);
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
				.setAttribute(0.30000001192093896D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
				.setAttribute(80.0D);
	}
	
	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	@Override
	public boolean isAIEnabled() {
		return true;
	}
	
	/**
	 * Sets the active target the Task system uses for tracking
	 */
	@Override
	public void setAttackTarget(EntityLivingBase par1EntityLivingBase) {
		super.setAttackTarget(par1EntityLivingBase);
		this.setAngry(true);
	}
	
	/**
	 * main AI tick function, replaces updateEntityActionState
	 */
	@Override
	protected void updateAITick() {
		this.dataWatcher.updateObject(18, Float.valueOf(this.getHealth()));
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(18, new Float(this.getHealth()));
		this.dataWatcher.addObject(19, new Byte((byte) 0));
		this.dataWatcher.addObject(20,
				new Byte((byte) BlockColored.getBlockFromDye(1)));
	}
	
	/**
	 * Plays step sound at given x, y, z for the entity
	 */
	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4) {
		this.playSound("mob.wolf.step", 0.40F, 4.0F);
	}
	
	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setBoolean("Angry", this.isAngry());
		par1NBTTagCompound.setByte("CollarColor", (byte) this.getCollarColor());
	}
	
	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		this.setAngry(par1NBTTagCompound.getBoolean("Angry"));
		
		if (par1NBTTagCompound.hasKey("CollarColor")) {
			this.setCollarColor(par1NBTTagCompound.getByte("CollarColor"));
		}
	}
	
	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound() {
		return this.isAngry() ? "mob.wolf.growl"
				: (this.rand.nextInt(3) == 0 ? (this.isTamed()
						&& this.dataWatcher.getWatchableObjectFloat(18) < 10.0F ? "mob.wolf.whine"
						: "mob.wolf.panting")
						: "mob.wolf.bark");
	}
	
	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound() {
		return "mob.wolf.hurt";
	}
	
	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected String getDeathSound() {
		return "mob.wolf.death";
	}
	
	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	@Override
	protected float getSoundVolume() {
		return 2.1F;
	}
	
	@Override
	protected float getSoundPitch() {
		return 0.5F;
	}
	
	/**
	 * Returns the item ID for the item the mob drops on death.
	 */
	@Override
	protected int getDropItemId() {
		return -1;
	}
	
	/**
	 * Called frequently so the entity can update its state every tick as
	 * required. For example, zombies and skeletons use this to react to
	 * sunlight and start to burn.
	 */
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		
		if (!this.worldObj.isRemote && this.isShaking && !this.field_70928_h
				&& !this.hasPath() && this.onGround) {
			this.field_70928_h = true;
			this.timeWolfIsShaking = 0.0F;
			this.prevTimeWolfIsShaking = 0.0F;
			this.worldObj.setEntityState(this, (byte) 8);
		}
	}
	
	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();
		this.field_70924_f = this.field_70926_e;
		
		if (this.func_70922_bv()) {
			this.field_70926_e += (1.0F - this.field_70926_e) * 0.4F;
		} else {
			this.field_70926_e += (0.0F - this.field_70926_e) * 0.4F;
		}
		
		if (this.func_70922_bv()) {
			this.numTicksToChaseTarget = 10;
		}
		
		if (this.isWet()) {
			this.isShaking = true;
			this.field_70928_h = false;
			this.timeWolfIsShaking = 0.0F;
			this.prevTimeWolfIsShaking = 0.0F;
		} else if ((this.isShaking || this.field_70928_h) && this.field_70928_h) {
			if (this.timeWolfIsShaking == 0.0F) {
				this.playSound(
						"mob.wolf.shake",
						this.getSoundVolume(),
						(this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			}
			
			this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
			this.timeWolfIsShaking += 0.05F;
			
			if (this.prevTimeWolfIsShaking >= 2.0F) {
				this.isShaking = false;
				this.field_70928_h = false;
				this.prevTimeWolfIsShaking = 0.0F;
				this.timeWolfIsShaking = 0.0F;
			}
			
			if (this.timeWolfIsShaking > 0.4F) {
				float f = (float) this.boundingBox.minY;
				int i = (int) (MathHelper.sin((this.timeWolfIsShaking - 0.4F)
						* (float) Math.PI) * 7.0F);
				
				for (int j = 0; j < i; ++j) {
					float f1 = (this.rand.nextFloat() * 2.0F - 1.0F)
							* this.width * 0.5F;
					float f2 = (this.rand.nextFloat() * 2.0F - 1.0F)
							* this.width * 0.5F;
					this.worldObj.spawnParticle("splash", this.posX
							+ f1, f + 0.8F, this.posZ
							+ f2, this.motionX, this.motionY,
							this.motionZ);
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public boolean getWolfShaking() {
		return this.isShaking;
	}
	
	@SideOnly(Side.CLIENT)
	/**
	 * Used when calculating the amount of shading to apply while the wolf is shaking.
	 */
	public float getShadingWhileShaking(float par1) {
		return 0.75F + (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking)
				* par1) / 2.0F * 0.25F;
	}
	
	@SideOnly(Side.CLIENT)
	public float getShakeAngle(float par1, float par2) {
		float f2 = (this.prevTimeWolfIsShaking
				+ (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * par1 + par2) / 1.8F;
		
		if (f2 < 0.0F) {
			f2 = 0.0F;
		} else if (f2 > 1.0F) {
			f2 = 1.0F;
		}
		
		return MathHelper.sin(f2 * (float) Math.PI)
				* MathHelper.sin(f2 * (float) Math.PI * 11.0F) * 0.15F
				* (float) Math.PI;
	}
	
	@SideOnly(Side.CLIENT)
	public float getInterestedAngle(float par1) {
		return (this.field_70924_f + (this.field_70926_e - this.field_70924_f)
				* par1)
				* 0.15F * (float) Math.PI;
	}
	
	@Override
	public float getEyeHeight() {
		return this.height * 0.8F;
	}
	
	/**
	 * The speed it takes to move the entityliving's rotationPitch through the
	 * faceEntity method. This is only currently use in wolves.
	 */
	@Override
	public int getVerticalFaceSpeed() {
		return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
	}
	
	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		if (this.isEntityInvulnerable()) {
			return false;
		} else {
			Entity entity = par1DamageSource.getEntity();
			this.aiSit.setSitting(false);
			
			if (entity != null && !(entity instanceof EntityPlayer)
					&& !(entity instanceof EntityArrow)) {
				par2 = (par2 + 1.0F) / 2.0F;
			}
			
			return super.attackEntityFrom(par1DamageSource, par2);
		}
	}
	
	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		int i = this.isTamed() ? 10 : 5;
		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this),
				i);
	}
	
	@Override
	public void setTamed(boolean par1) {
		super.setTamed(par1);
		
		if (par1) {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
					.setAttribute(40.0D);
		} else {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
					.setAttribute(20.0D);
		}
	}
	
	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow,
	 * gets into the saddle on a pig.
	 */
	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
		
		if (this.isTamed()) {
			if (itemstack != null) {
				if (Item.itemsList[itemstack.itemID] instanceof ItemFood) {
					ItemFood itemfood = (ItemFood) Item.itemsList[itemstack.itemID];
					
					if (itemfood.isWolfsFavoriteMeat()
							&& this.dataWatcher.getWatchableObjectFloat(18) < 20.0F) {
						if (!par1EntityPlayer.capabilities.isCreativeMode) {
							--itemstack.stackSize;
						}
						
						this.heal(itemfood.getHealAmount());
						
						if (itemstack.stackSize <= 0) {
							par1EntityPlayer.inventory
									.setInventorySlotContents(
											par1EntityPlayer.inventory.currentItem,
											(ItemStack) null);
						}
						
						return true;
					}
				} else if (itemstack.itemID == Item.dyePowder.itemID) {
					int i = BlockColored.getBlockFromDye(itemstack
							.getItemDamage());
					
					if (i != this.getCollarColor()) {
						this.setCollarColor(i);
						
						if (!par1EntityPlayer.capabilities.isCreativeMode
								&& --itemstack.stackSize <= 0) {
							par1EntityPlayer.inventory
									.setInventorySlotContents(
											par1EntityPlayer.inventory.currentItem,
											(ItemStack) null);
						}
						
						return true;
					}
				}
			}
			
			if (par1EntityPlayer.getCommandSenderName().equalsIgnoreCase(
					this.getOwnerName())
					&& !this.worldObj.isRemote
					&& !this.isBreedingItem(itemstack)) {
				this.aiSit.setSitting(!this.isSitting());
				this.isJumping = false;
				this.setPathToEntity((PathEntity) null);
				this.setTarget((Entity) null);
				this.setAttackTarget((EntityLivingBase) null);
			}
		} else if (itemstack != null
				&& itemstack.itemID == Items.hellPearl.itemID) {
			if (!par1EntityPlayer.capabilities.isCreativeMode) {
				--itemstack.stackSize;
			}
			
			if (itemstack.stackSize <= 0) {
				par1EntityPlayer.inventory.setInventorySlotContents(
						par1EntityPlayer.inventory.currentItem,
						(ItemStack) null);
			}
			
			if (!this.worldObj.isRemote) {
				if (this.rand.nextInt(3) == 0) {
					EntityShadowWolf sw = new EntityShadowWolf(worldObj);
					sw.setLocationAndAngles(this.posX, this.posY,
							this.posZ, this.rotationYaw, this.rotationPitch);
					if (this.hasCustomNameTag()) {
						sw.setCustomNameTag(this.getCustomNameTag());
					}
					sw.setTamed(true);
					sw.setPathToEntity((PathEntity) null);
					sw.setAttackTarget((EntityLivingBase) null);
					sw.setHealth(40.0F);
					sw.setOwner(par1EntityPlayer.getCommandSenderName());
					sw.worldObj.setEntityState(this, (byte) 7);
					this.worldObj.spawnEntityInWorld(sw);
					this.setDead();
				} else {
					this.playTameEffect(false);
					this.worldObj.setEntityState(this, (byte) 6);
				}
			}
			return true;
		}
		
		return super.interact(par1EntityPlayer);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void handleHealthUpdate(byte par1) {
		if (par1 == 8) {
			this.field_70928_h = true;
			this.timeWolfIsShaking = 0.0F;
			this.prevTimeWolfIsShaking = 0.0F;
		} else {
			super.handleHealthUpdate(par1);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public float getTailRotation() {
		return this.isAngry() ? 1.5393804F
				: (this.isTamed() ? (0.55F - (20.0F - this.dataWatcher
						.getWatchableObjectFloat(18)) * 0.02F)
						* (float) Math.PI : ((float) Math.PI / 5F));
	}
	
	/**
	 * Checks if the parameter is an item which this animal can be fed to breed
	 * it (wheat, carrots or seeds depending on the animal type)
	 */
	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack) {
		return par1ItemStack == null ? false
				: (!(Item.itemsList[par1ItemStack.itemID] instanceof ItemFood) ? false
						: ((ItemFood) Item.itemsList[par1ItemStack.itemID])
								.isWolfsFavoriteMeat());
	}
	
	/**
	 * Will return how many at most can spawn in a chunk at once.
	 */
	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}
	
	/**
	 * Determines whether this wolf is angry or not.
	 */
	public boolean isAngry() {
		return (this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
	}
	
	/**
	 * Sets whether this wolf is angry or not.
	 */
	public void setAngry(boolean par1) {
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);
		
		if (par1) {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 | 2)));
		} else {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 & -3)));
		}
	}
	
	/**
	 * Return this wolf's collar color.
	 */
	public int getCollarColor() {
		return this.dataWatcher.getWatchableObjectByte(20) & 15;
	}
	
	/**
	 * Set this wolf's collar color.
	 */
	public void setCollarColor(int par1) {
		this.dataWatcher.updateObject(20, Byte.valueOf((byte) (par1 & 15)));
	}
	
	/**
	 * This function is used when two same-species animals in 'love mode' breed
	 * to generate the new baby animal.
	 */
	public EntityGhostWolf spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
		EntityGhostWolf entitywolf = new EntityGhostWolf(this.worldObj);
		String s = this.getOwnerName();
		
		if (s != null && s.trim().length() > 0) {
			entitywolf.setOwner(s);
			entitywolf.setTamed(true);
		}
		
		return entitywolf;
	}
	
	public void func_70918_i(boolean par1) {
		if (par1) {
			this.dataWatcher.updateObject(19, Byte.valueOf((byte) 1));
		} else {
			this.dataWatcher.updateObject(19, Byte.valueOf((byte) 0));
		}
	}
	
	/**
	 * Returns true if the mob is currently able to mate with the specified mob.
	 */
	@Override
	public boolean canMateWith(EntityAnimal par1EntityAnimal) {
		if (par1EntityAnimal == this) {
			return false;
		} else if (!this.isTamed()) {
			return false;
		} else if (!(par1EntityAnimal instanceof EntityGhostWolf)) {
			return false;
		} else {
			EntityGhostWolf entitywolf = (EntityGhostWolf) par1EntityAnimal;
			return !entitywolf.isTamed() ? false
					: (entitywolf.isSitting() ? false : this.isInLove()
							&& entitywolf.isInLove());
		}
	}
	
	public boolean func_70922_bv() {
		return this.dataWatcher.getWatchableObjectByte(19) == 1;
	}
	
	/**
	 * Determines if an entity can be despawned, used on idle far away entities
	 */
	@Override
	protected boolean canDespawn() {
		return !this.isTamed() && this.ticksExisted > 2400;
	}
	
	@Override
	public boolean func_142018_a(EntityLivingBase par1EntityLivingBase,
			EntityLivingBase par2EntityLivingBase) {
		if (!(par1EntityLivingBase instanceof EntityCreeper)
				&& !(par1EntityLivingBase instanceof EntityGhast)) {
			if (par1EntityLivingBase instanceof EntityGhostWolf) {
				EntityGhostWolf entitywolf = (EntityGhostWolf) par1EntityLivingBase;
				
				if (entitywolf.isTamed()
						&& entitywolf.func_130012_q() == par2EntityLivingBase) {
					return false;
				}
			}
			
			return par1EntityLivingBase instanceof EntityPlayer
					&& par2EntityLivingBase instanceof EntityPlayer
					&& !((EntityPlayer) par2EntityLivingBase)
							.canAttackPlayer((EntityPlayer) par1EntityLivingBase) ? false
					: !(par1EntityLivingBase instanceof EntityHorse)
							|| !((EntityHorse) par1EntityLivingBase).isTame();
		} else {
			return false;
		}
	}
	
	@Override
	public EntityAgeable createChild(EntityAgeable par1EntityAgeable) {
		return this.spawnBabyAnimal(par1EntityAgeable);
	}
}