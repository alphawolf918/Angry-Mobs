package angrymobs.mobs.renders;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import angrymobs.mobs.entities.EntityShadowSkeleton;
import angrymobs.mobs.models.ModelShadowSkeleton;

public class RenderShadowSkeleton extends RenderLiving {
	public ModelShadowSkeleton model;

	public RenderShadowSkeleton(ModelShadowSkeleton par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		model = (ModelShadowSkeleton) mainModel;
	}

	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("awam:textures/entity/shadowskeleton.png");
	}

	public void renderTutorial(EntityShadowSkeleton entity, double par2,
			double par4, double par6, float par8, float par9) {
		super.doRenderLiving(entity, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2,
			double par4, double par6, float par8, float par9) {
		renderTutorial((EntityShadowSkeleton) par1EntityLiving, par2, par4,
				par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4,
			double par6, float par8, float par9) {
		renderTutorial((EntityShadowSkeleton) par1Entity, par2, par4, par6,
				par8, par9);
	}
}