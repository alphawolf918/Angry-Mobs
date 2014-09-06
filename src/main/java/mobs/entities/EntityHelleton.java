package angrymobs.mobs.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import angrymobs.items.Items;

public class EntityHelleton extends EntityMob {
	
	public EntityHelleton(World par1World) {
		super(par1World);
		this.setHealth(100F);
		this.setAIMoveSpeed(.8F);
		this.experienceValue = 275;
		this.isImmuneToFire = true;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this,
				EntityPlayer.class, this.getAIMoveSpeed(), false));
		this.tasks.addTask(2, new EntityAIWatchClosest(this,
				EntityPlayer.class, 8.0F));
		this.tasks.addTask(3, new EntityAIWander(this, this.getAIMoveSpeed()));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(
				this, EntityPlayer.class, 0, true));
		this.stepHeight = 2F;
		this.scoreValue = 450;
		this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 360, 2));
	}
	
	@Override
	protected Entity findPlayerToAttack()
	{
		double d0 = 16.0D;
		return this.worldObj.getClosestVulnerablePlayerToEntity(this, d0);
	}
	
	@Override
	protected void updateFallState(double par1, boolean par3) {
	}
	
	@Override
	protected void fall(float par1) {
		par1 = 0;
	}
	
	@Override
	public int getTotalArmorValue() {
		return 20;
	}
	
	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}
	
	@Override
	public boolean isAIEnabled() {
		return true;
	}
	
	@Override
	protected String getLivingSound() {
		return "mob.skeleton.say";
	}
	
	@Override
	protected String getHurtSound() {
		return "mob.skeleton.hurt";
	}
	
	@Override
	protected String getDeathSound() {
		return "mob.skeleton.death";
	}
	
	@Override
	protected float getSoundVolume() {
		return 4F;
	}
	
	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4) {
		this.playSound("mob.skeleton.step", 0.35F, 0.5F);
	}
	
	@Override
	protected int getDropItemId() {
		return Items.hellPearl.itemID;
	}
}