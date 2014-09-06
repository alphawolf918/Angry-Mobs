package angrymobs.mobs.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGhost extends EntityMob {
	
	public EntityGhost(World par1World) {
		super(par1World);
		this.setHealth(40F);
		this.setAIMoveSpeed(.4F);
		this.experienceValue = 75;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIBreakDoor(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this,
				EntityPlayer.class, this.getAIMoveSpeed(), false));
		this.tasks.addTask(3, new EntityAIWatchClosest(this,
				EntityPlayer.class, 6.0F));
		this.tasks.addTask(4, new EntityAIWander(this, this.getAIMoveSpeed()));
		this.tasks.addTask(5, new EntityAIRestrictSun(this));
		this.tasks.addTask(6, new EntityAIFleeSun(this, 1.5D));
		EntityAINearestAttackableTarget et = new EntityAINearestAttackableTarget(
				this, EntityPlayer.class, 0, true);
		if (!this.worldObj.isDaytime()) {
			this.targetTasks.addTask(1, et);
		}
		this.stepHeight = 2F;
		this.isAirBorne = true;
		this.scoreValue = 250;
		this.onGround = false;
	}
	
	/**
	 * Makes entity wear random armor based on difficulty
	 */
	@Override
	protected void addRandomArmor()
	{
		super.addRandomArmor();
		
		if (this.rand.nextFloat() < (this.worldObj.difficultySetting == 3 ? 0.05F
				: 0.01F))
		{
			int i = this.rand.nextInt(3);
			
			if (i == 0)
			{
				this.setCurrentItemOrArmor(0, new ItemStack(Item.swordDiamond));
			}
			else
			{
				this.setCurrentItemOrArmor(0, new ItemStack(Item.swordIron));
			}
		}
	}
	
	@Override
	public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
		this.addRandomArmor();
		return par1EntityLivingData;
	}
	
	@Override
	protected void updateFallState(double par1, boolean par3) {
	}
	
	@Override
	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}
	
	@Override
	public boolean canBePushed() {
		return false;
	}
	
	@Override
	protected boolean canTriggerWalking() {
		return false;
	}
	
	@Override
	protected void fall(float par1) {
		par1 = 0;
	}
	
	@Override
	public void onLivingUpdate() {
		if (this.worldObj.isDaytime() && !this.worldObj.isRemote
				&& !this.isChild()) {
			float f = this.getBrightness(1.0F);
			
			if (f > 0.5F
					&& this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F
					&& this.worldObj.canBlockSeeTheSky(
							MathHelper.floor_double(this.posX),
							MathHelper.floor_double(this.posY),
							MathHelper.floor_double(this.posZ))) {
				boolean flag = true;
				ItemStack itemstack = this.getCurrentItemOrArmor(0);
				
				if (itemstack != null) {
					if (itemstack.isItemStackDamageable()) {
						itemstack.setItemDamage(itemstack
								.getItemDamageForDisplay()
								+ this.rand.nextInt(2));
						
						if (itemstack.getItemDamageForDisplay() >= itemstack
								.getMaxDamage()) {
							this.renderBrokenItemStack(itemstack);
							this.setCurrentItemOrArmor(0,
									(ItemStack) null);
						}
					}
					
					flag = false;
				}
				this.toggleVisible();
			}
		}
		super.onLivingUpdate();
	}
	
	/**
	 * Updates the ghost's state, i.e makes it invisible during the day, and
	 * plays a sound if it's invisible.
	 */
	public void toggleVisible() {
		int numTimes = 0;
		this.addPotionEffect(new PotionEffect(Potion.invisibility.id, 100, 2));
		if (numTimes < 100) {
			this.playSound("awam:mob.ghost.change", 0.5F, 0.5F);
			numTimes++;
		} else {
			numTimes = 0;
		}
	}
	
	@Override
	public int getTotalArmorValue() {
		return 14;
	}
	
	@Override
	protected String getLivingSound() {
		return "awam:mob.ghost.say";
	}
	
	@Override
	protected String getHurtSound() {
		return "awam:mob.ghost.hurt";
	}
	
	@Override
	protected String getDeathSound() {
		return "awam:mob.ghost.death";
	}
	
	@Override
	protected boolean isAIEnabled() {
		return true;
	}
	
	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}
	
	public int getAttackStrength(Entity par1Entity) {
		return 10;
	}
	
}