package angrymobs.proxies;

import net.minecraftforge.common.MinecraftForge;
import angrymobs.managers.SoundManager;
import angrymobs.mobs.entities.EntityEnderSpider;
import angrymobs.mobs.entities.EntityGhost;
import angrymobs.mobs.entities.EntityGhostWolf;
import angrymobs.mobs.entities.EntityHelleton;
import angrymobs.mobs.entities.EntityHog;
import angrymobs.mobs.entities.EntityHogZombie;
import angrymobs.mobs.entities.EntityMegaCreeper;
import angrymobs.mobs.entities.EntityScorpion;
import angrymobs.mobs.entities.EntityShadowSkeleton;
import angrymobs.mobs.entities.EntityShadowWolf;
import angrymobs.mobs.entities.EntityShark;
import angrymobs.mobs.models.ModelEnderSpider;
import angrymobs.mobs.models.ModelGhost;
import angrymobs.mobs.models.ModelGhostWolf;
import angrymobs.mobs.models.ModelHelleton;
import angrymobs.mobs.models.ModelHog;
import angrymobs.mobs.models.ModelHogZombie;
import angrymobs.mobs.models.ModelMegaCreeper;
import angrymobs.mobs.models.ModelScorpion;
import angrymobs.mobs.models.ModelShadowSkeleton;
import angrymobs.mobs.models.ModelShadowWolf;
import angrymobs.mobs.models.ModelShark;
import angrymobs.mobs.renders.RenderEnderSpider;
import angrymobs.mobs.renders.RenderGhost;
import angrymobs.mobs.renders.RenderGhostWolf;
import angrymobs.mobs.renders.RenderHelleton;
import angrymobs.mobs.renders.RenderHog;
import angrymobs.mobs.renders.RenderHogZombie;
import angrymobs.mobs.renders.RenderMegaCreeper;
import angrymobs.mobs.renders.RenderScorpion;
import angrymobs.mobs.renders.RenderShadowSkeleton;
import angrymobs.mobs.renders.RenderShadowWolf;
import angrymobs.mobs.renders.RenderShark;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void initRenderers() {
		
		RenderingRegistry.registerEntityRenderingHandler(
				EntityShadowWolf.class, new RenderShadowWolf(
						new ModelShadowWolf(), 0.5F));
		
		RenderingRegistry.registerEntityRenderingHandler(
				EntityShadowSkeleton.class, new RenderShadowSkeleton(
						new ModelShadowSkeleton(), 0.5F));
		
		RenderingRegistry.registerEntityRenderingHandler(
				EntityMegaCreeper.class, new RenderMegaCreeper(
						new ModelMegaCreeper(), 0.5F));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityGhost.class,
				new RenderGhost(new ModelGhost(), 0.5F));
		
		RenderingRegistry.registerEntityRenderingHandler(
				EntityEnderSpider.class, new RenderEnderSpider(
						new ModelEnderSpider(), 0.5F));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityShark.class,
				new RenderShark(new ModelShark(), 0.5F));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityHog.class,
				new RenderHog(new ModelHog(), 0.5F));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityScorpion.class,
				new RenderScorpion(new ModelScorpion(), 0.5F));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityHogZombie.class,
				new RenderHogZombie(new ModelHogZombie(), 0.5F));
		
		RenderingRegistry.registerEntityRenderingHandler(
				EntityHelleton.class, new RenderHelleton(
						new ModelHelleton(), 0.5F));
		
		RenderingRegistry.registerEntityRenderingHandler(
				EntityGhostWolf.class, new RenderGhostWolf(
						new ModelGhostWolf(), 0.5F));
	}
	
	@Override
	public void initSounds() {
		MinecraftForge.EVENT_BUS.register(new SoundManager());
	}
}