package angrymobs.mobs.renders;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import angrymobs.mobs.entities.EntityGhostWolf;
import angrymobs.mobs.models.ModelGhostWolf;

public class RenderGhostWolf extends RenderLiving {
	public ModelGhostWolf model;
	
	public RenderGhostWolf(ModelGhostWolf par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		model = (ModelGhostWolf) mainModel;
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("awam:textures/entity/ghostwolf.png");
	}
	
	public void renderTutorial(EntityGhostWolf entity, double par2,
			double par4, double par6, float par8, float par9) {
		super.doRenderLiving(entity, par2, par4, par6, par8, par9);
	}
	
	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2,
			double par4, double par6, float par8, float par9) {
		renderTutorial((EntityGhostWolf) par1EntityLiving, par2, par4, par6,
				par8, par9);
	}
	
	@Override
	public void doRender(Entity par1Entity, double par2, double par4,
			double par6, float par8, float par9) {
		renderTutorial((EntityGhostWolf) par1Entity, par2, par4, par6, par8,
				par9);
	}
}