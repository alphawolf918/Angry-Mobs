package angrymobs.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHelleton extends ModelBase
{
	ModelRenderer Head;
	ModelRenderer Headwear;
	ModelRenderer Body;
	ModelRenderer RightArm;
	ModelRenderer LeftArm;
	ModelRenderer RightLeg;
	ModelRenderer LeftLeg;
	ModelRenderer c;
	
	public ModelHelleton()
	{
		textureWidth = 64;
		textureHeight = 32;
		
		Head = new ModelRenderer(this, 0, 0);
		Head.addBox(-4F, -8F, -4F, 8, 8, 15);
		Head.setRotationPoint(0F, -14F, 0F);
		Head.setTextureSize(64, 32);
		setRotation(Head, 0F, 0F, 0F);
		Headwear = new ModelRenderer(this, 0, 0);
		Headwear.addBox(-4F, -8F, -4F, 8, 8, 8);
		Headwear.setRotationPoint(0F, -14F, 0F);
		Headwear.setTextureSize(64, 32);
		setRotation(Headwear, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 28, 13);
		Body.addBox(-4F, 0F, -2F, 8, 12, 4);
		Body.setRotationPoint(0F, -14F, 0F);
		Body.setTextureSize(64, 32);
		setRotation(Body, 0F, 0F, 0F);
		RightArm = new ModelRenderer(this, 0, 4);
		RightArm.addBox(-1F, -2F, -1F, 3, 24, 2);
		RightArm.setRotationPoint(-5F, -12F, 0F);
		RightArm.setTextureSize(64, 32);
		setRotation(RightArm, -1F, 0F, 0F);
		LeftArm = new ModelRenderer(this, 0, 4);
		LeftArm.addBox(-1F, -2F, -1F, 3, 24, 2);
		LeftArm.setRotationPoint(5F, -12F, 0F);
		LeftArm.setTextureSize(64, 32);
		setRotation(LeftArm, -1F, 0F, 0F);
		RightLeg = new ModelRenderer(this, 0, 5);
		RightLeg.addBox(-1F, 0F, -1F, 2, 30, 2);
		RightLeg.setRotationPoint(-2F, -2F, 0F);
		RightLeg.setTextureSize(64, 32);
		setRotation(RightLeg, 0F, 0F, 0F);
		LeftLeg = new ModelRenderer(this, 0, 5);
		LeftLeg.addBox(-1F, 0F, -1F, 2, 30, 2);
		LeftLeg.setRotationPoint(2F, -2F, 0F);
		LeftLeg.setTextureSize(64, 32);
		setRotation(LeftLeg, 0F, 0F, 0F);
		c = new ModelRenderer(this, 0, 0);
		c.addBox(0F, 0F, 0F, 1, 1, 1);
		c.setRotationPoint(0F, -19F, 0F);
		c.setTextureSize(64, 32);
		setRotation(c, 0F, 0F, 0F);
	}
	
	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4,
			float par5, float par6, float par7) {
		
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Head.render(par7);
		Headwear.render(par7);
		Body.render(par7);
		RightArm.render(par7);
		LeftArm.render(par7);
		RightLeg.render(par7);
		LeftLeg.render(par7);
		c.render(par7);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	/*
	 * Sets the model's various rotation angles. For s, par1 and par2 are used
	 * for animating the movement of arms and legs, where par1 represents the
	 * time(so that arms and legs swing back and forth) and par2 represents how
	 * "far" arms and legs can swing at most.
	 */
	@Override
	public void setRotationAngles(float par1, float par2, float par3,
			float par4, float par5, float par6, Entity par7Entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
		this.Head.showModel = true;
		float f6 = -14.0F;
		this.Body.rotateAngleX = 0.0F;
		this.Body.rotationPointY = f6;
		this.Body.rotationPointZ = -0.0F;
		this.RightLeg.rotateAngleX -= 0.0F;
		this.LeftLeg.rotateAngleX -= 0.0F;
		this.RightArm.rotateAngleX = (float) (this.RightArm.rotateAngleX * 0.5D);
		this.LeftArm.rotateAngleX = (float) (this.LeftArm.rotateAngleX * 0.5D);
		this.RightLeg.rotateAngleX = (float) (this.RightLeg.rotateAngleX * 0.5D);
		this.LeftLeg.rotateAngleX = (float) (this.LeftLeg.rotateAngleX * 0.5D);
		float f7 = 0.4F;
		
		if (this.RightArm.rotateAngleX > f7)
		{
			this.RightArm.rotateAngleX = f7;
		}
		
		if (this.LeftArm.rotateAngleX > f7)
		{
			this.LeftArm.rotateAngleX = f7;
		}
		
		if (this.RightArm.rotateAngleX < -f7)
		{
			this.RightArm.rotateAngleX = -f7;
		}
		
		if (this.LeftArm.rotateAngleX < -f7)
		{
			this.LeftArm.rotateAngleX = -f7;
		}
		
		if (this.RightLeg.rotateAngleX > f7)
		{
			this.RightLeg.rotateAngleX = f7;
		}
		
		if (this.LeftLeg.rotateAngleX > f7)
		{
			this.LeftLeg.rotateAngleX = f7;
		}
		
		if (this.RightLeg.rotateAngleX < -f7)
		{
			this.RightLeg.rotateAngleX = -f7;
		}
		
		if (this.LeftLeg.rotateAngleX < -f7)
		{
			this.LeftLeg.rotateAngleX = -f7;
		}
		this.RightArm.rotationPointZ = 0.0F;
		this.LeftArm.rotationPointZ = 0.0F;
		this.RightLeg.rotationPointZ = 0.0F;
		this.LeftLeg.rotationPointZ = 0.0F;
		this.RightLeg.rotationPointY = 9.0F + f6;
		this.LeftLeg.rotationPointY = 9.0F + f6;
		this.Head.rotationPointZ = -0.0F;
		this.Head.rotationPointY = f6 + 1.0F;
		this.Headwear.rotationPointX = this.Head.rotationPointX;
		this.Headwear.rotationPointY = this.Head.rotationPointY;
		this.Headwear.rotationPointZ = this.Head.rotationPointZ;
		this.Headwear.rotateAngleX = this.Head.rotateAngleX;
		this.Headwear.rotateAngleY = this.Head.rotateAngleY;
		this.Headwear.rotateAngleZ = this.Head.rotateAngleZ;
	}
}