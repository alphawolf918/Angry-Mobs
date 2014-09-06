package angrymobs.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class ModelShark extends ModelBase {
	ModelRenderer WolfHead;
	ModelRenderer Body;
	ModelRenderer Mane;
	ModelRenderer Tail;
	ModelRenderer Nose;
	ModelRenderer Tail2;
	ModelRenderer FinTop;
	ModelRenderer FinRight;
	ModelRenderer FinLeft;
	
	public ModelShark() {
		textureWidth = 64;
		textureHeight = 32;
		
		WolfHead = new ModelRenderer(this, 15, 4);
		WolfHead.addBox(-3F, -3F, -2F, 6, 6, 4);
		WolfHead.setRotationPoint(-1F, 13.5F, -7F);
		WolfHead.setTextureSize(64, 32);
		WolfHead.mirror = true;
		setRotation(WolfHead, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 18, 14);
		Body.addBox(-4F, -2F, -3F, 6, 9, 6);
		Body.setRotationPoint(0F, 14F, 2F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 1.570796F, 0F, 0F);
		Mane = new ModelRenderer(this, 0, 0);
		Mane.addBox(-4F, -3F, -3F, 8, 6, 7);
		Mane.setRotationPoint(-1F, 14F, -3F);
		Mane.setTextureSize(64, 32);
		Mane.mirror = true;
		setRotation(Mane, 1.570796F, 0F, 0F);
		Tail = new ModelRenderer(this, 9, 18);
		Tail.addBox(-1F, 0F, -1F, 2, 8, 2);
		Tail.setRotationPoint(-1F, 12F, 8F);
		Tail.setTextureSize(64, 32);
		Tail.mirror = true;
		setRotation(Tail, 1.130069F, 0F, 0F);
		Nose = new ModelRenderer(this, 0, 0);
		Nose.addBox(-2F, 0F, -5F, 5, 5, 9);
		Nose.setRotationPoint(-1.5F, 11.5F, -12F);
		Nose.setTextureSize(64, 32);
		Nose.mirror = true;
		setRotation(Nose, 0F, 0F, 0F);
		Tail2 = new ModelRenderer(this, 9, 18);
		Tail2.addBox(0F, 0F, 0F, 2, 8, 2);
		Tail2.setRotationPoint(-2F, 13F, 9F);
		Tail2.setTextureSize(64, 32);
		Tail2.mirror = true;
		setRotation(Tail2, -3.869931F, 0F, 0F);
		FinTop = new ModelRenderer(this, 0, 0);
		FinTop.addBox(0F, 0F, 0F, 2, 5, 7);
		FinTop.setRotationPoint(-2F, 11F, -2F);
		FinTop.setTextureSize(64, 32);
		FinTop.mirror = true;
		setRotation(FinTop, 1F, 0F, 0F);
		FinRight = new ModelRenderer(this, 0, 0);
		FinRight.addBox(0F, 0F, 0F, 2, 5, 10);
		FinRight.setRotationPoint(0F, 12F, 0F);
		FinRight.setTextureSize(64, 32);
		FinRight.mirror = true;
		setRotation(FinRight, 8F, 0F, 2F);
		FinLeft = new ModelRenderer(this, 0, 0);
		FinLeft.addBox(0F, 0F, 0F, 2, 5, 10);
		FinLeft.setRotationPoint(-2F, 14F, 0F);
		FinLeft.setTextureSize(64, 32);
		FinLeft.mirror = true;
		setRotation(FinLeft, 8F, 0F, -2F);
	}
	
	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4,
			float par5, float par6, float par7) {
		
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GL11.glPushMatrix();
		GL11.glScalef(2, 2, 2);
		WolfHead.render(par7);
		Body.render(par7);
		Mane.render(par7);
		Tail.render(par7);
		Nose.render(par7);
		Tail2.render(par7);
		FinTop.render(par7);
		FinRight.render(par7);
		FinLeft.render(par7);
		GL11.glPopMatrix();
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(float par1, float par2, float par3,
			float par4, float par5, float par6, Entity par7Entity) {
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
		this.Tail.setRotationPoint(-1.0F, 12.0F, 8.0F);
		this.Tail2.setRotationPoint(-1.0F, 12.0F, 8.0F);
	}
	
}
