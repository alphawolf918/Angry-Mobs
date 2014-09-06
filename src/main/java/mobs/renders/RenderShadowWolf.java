package angrymobs.mobs.renders;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import angrymobs.mobs.entities.EntityShadowWolf;
import angrymobs.mobs.models.ModelShadowWolf;

public class RenderShadowWolf extends RenderLiving {
	public ModelShadowWolf model;

	public RenderShadowWolf(ModelShadowWolf par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		model = (ModelShadowWolf) mainModel;
	}

	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("awam:textures/entity/shadowwolf.png");
	}

	public void renderTutorial(EntityShadowWolf entity, double par2,
			double par4, double par6, float par8, float par9) {
		super.doRenderLiving(entity, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2,
			double par4, double par6, float par8, float par9) {
		renderTutorial((EntityShadowWolf) par1EntityLiving, par2, par4, par6,
				par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4,
			double par6, float par8, float par9) {
		renderTutorial((EntityShadowWolf) par1Entity, par2, par4, par6, par8,
				par9);
	}
}