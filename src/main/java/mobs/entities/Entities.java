package angrymobs.mobs.entities;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DungeonHooks;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Entities {
	static int startEntityId = 300;
	static ArrayList<String> entityList = new ArrayList<String>();
	
	public static void init() {
		
		// Shadow Wolf
		EntityRegistry.registerGlobalEntityID(EntityShadowWolf.class,
				"shadowwolf", 20);
		LanguageRegistry.instance().addStringLocalization(
				"entity.shadowwolf.name", "en_US", "Shadow Wolf");
		EntityRegistry.addSpawn(EntityShadowWolf.class, 20, 2, 5,
				EnumCreatureType.creature, BiomeGenBase.plains,
				BiomeGenBase.forest, BiomeGenBase.desert,
				BiomeGenBase.extremeHills, BiomeGenBase.jungle,
				BiomeGenBase.swampland, BiomeGenBase.taiga);
		registerEntityEgg(EntityShadowWolf.class, 0x000000, 0x00009e);
		
		// Shadow Skeleton
		EntityRegistry.registerGlobalEntityID(EntityShadowSkeleton.class,
				"shadowskeleton", 21);
		LanguageRegistry.instance().addStringLocalization(
				"entity.shadowskeleton.name", "en_US", "Shadow Skeleton");
		EntityRegistry.addSpawn(EntityShadowSkeleton.class, 20, 2, 6,
				EnumCreatureType.monster, BiomeGenBase.plains,
				BiomeGenBase.forest, BiomeGenBase.desert,
				BiomeGenBase.extremeHills, BiomeGenBase.jungle,
				BiomeGenBase.swampland, BiomeGenBase.taiga);
		registerEntityEgg(EntityShadowSkeleton.class, 0xeeeeee, 0x00009e);
		entityList.add("Shadow Skeleton");
		
		// Mega Creeper
		EntityRegistry.registerGlobalEntityID(EntityMegaCreeper.class,
				"megacreeper", 22);
		LanguageRegistry.instance().addStringLocalization(
				"entity.megacreeper.name", "en_US", "Mega Creeper");
		EntityRegistry.addSpawn(EntityMegaCreeper.class, 20, 2, 5,
				EnumCreatureType.monster, BiomeGenBase.plains,
				BiomeGenBase.forest, BiomeGenBase.desert,
				BiomeGenBase.extremeHills, BiomeGenBase.jungle,
				BiomeGenBase.swampland, BiomeGenBase.taiga);
		registerEntityEgg(EntityMegaCreeper.class, 0xee00ee, 0x00ee00);
		
		// Ghost
		EntityRegistry.registerGlobalEntityID(EntityGhost.class, "ghost", 23);
		LanguageRegistry.instance().addStringLocalization("entity.ghost.name",
				"en_US",
				"Ghost");
		EntityRegistry.addSpawn(EntityGhost.class, 20, 2, 4,
				EnumCreatureType.monster, BiomeGenBase.plains,
				BiomeGenBase.forest, BiomeGenBase.desert,
				BiomeGenBase.extremeHills, BiomeGenBase.jungle,
				BiomeGenBase.swampland, BiomeGenBase.taiga);
		registerEntityEgg(EntityGhost.class, 0xeeeeee, 0xaa2222);
		entityList.add("Ghost");
		
		// Ender Spider
		EntityRegistry.registerGlobalEntityID(EntityEnderSpider.class,
				"enderspider", 24);
		LanguageRegistry.instance().addStringLocalization(
				"entity.enderspider.name", "en_US", "Ender Spider");
		EntityRegistry.addSpawn(EntityEnderSpider.class, 4, 1, 4,
				EnumCreatureType.monster, BiomeGenBase.sky);
		registerEntityEgg(EntityEnderSpider.class, 0x444444, 0x22aa22);
		
		// Shark
		EntityRegistry.registerGlobalEntityID(EntityShark.class, "shark", 25);
		LanguageRegistry.instance().addStringLocalization("entity.shark.name",
				"en_US",
				"Blood Shark");
		EntityRegistry.addSpawn(EntityShark.class, 8, 2, 4,
				EnumCreatureType.waterCreature, BiomeGenBase.plains,
				BiomeGenBase.forest, BiomeGenBase.desert,
				BiomeGenBase.extremeHills, BiomeGenBase.jungle,
				BiomeGenBase.swampland, BiomeGenBase.taiga, BiomeGenBase.ocean);
		registerEntityEgg(EntityShark.class, 0x444444, 0x8b0000);
		
		// Hog
		EntityRegistry.registerGlobalEntityID(EntityHog.class, "hog", 26);
		LanguageRegistry.instance().addStringLocalization("entity.hog.name",
				"en_US",
				"Hog");
		EntityRegistry.addSpawn(EntityHog.class, 15, 4, 8,
				EnumCreatureType.creature, BiomeGenBase.plains,
				BiomeGenBase.forest, BiomeGenBase.desert,
				BiomeGenBase.extremeHills, BiomeGenBase.jungle,
				BiomeGenBase.swampland, BiomeGenBase.taiga, BiomeGenBase.ocean);
		registerEntityEgg(EntityHog.class, 0x8b5a00, 0xf5eeb3);
		
		// Scorpion
		EntityRegistry.registerGlobalEntityID(EntityScorpion.class,
				"scorpion", 27);
		LanguageRegistry.instance().addStringLocalization(
				"entity.scorpion.name", "en_US", "Scorpion");
		EntityRegistry.addSpawn(EntityScorpion.class, 20, 2, 5,
				EnumCreatureType.monster, BiomeGenBase.plains,
				BiomeGenBase.forest, BiomeGenBase.desert,
				BiomeGenBase.extremeHills, BiomeGenBase.jungle,
				BiomeGenBase.swampland, BiomeGenBase.taiga, BiomeGenBase.ocean);
		registerEntityEgg(EntityScorpion.class, 0xf5eeb3, 0x8b9900);
		entityList.add("Scorpion");
		
		// Hog Zombie
		EntityRegistry.registerGlobalEntityID(EntityHogZombie.class,
				"hogzombie", 28);
		LanguageRegistry.instance().addStringLocalization(
				"entity.hogzombie.name", "en_US", "Zombie Hogman");
		EntityRegistry.addSpawn(EntityHogZombie.class, 100, 8, 10,
				EnumCreatureType.monster, BiomeGenBase.hell);
		registerEntityEgg(EntityHogZombie.class, 0x8b9900, 0x00ee00);
		entityList.add("Zombie Hogman");
		
		// Helleton
		EntityRegistry.registerGlobalEntityID(EntityHelleton.class,
				"helleton", 41);
		LanguageRegistry.instance().addStringLocalization(
				"entity.helleton.name", "en_US", "Helleton");
		EntityRegistry.addSpawn(EntityHelleton.class, 20, 1, 1,
				EnumCreatureType.monster, BiomeGenBase.hell);
		registerEntityEgg(EntityHelleton.class, 0x5e0000, 0x444444);
		
		// Ghost Wolf
		EntityRegistry.registerGlobalEntityID(EntityGhostWolf.class,
				"ghostwolf", 42);
		LanguageRegistry.instance().addStringLocalization(
				"entity.ghostwolf.name", "en_US", "Hell Hound");
		
		addSpawners();
	}
	
	/**
	 * Adds mobs to dungeon spawners..
	 */
	public static void addSpawners() {
		for (int i = 0; i < entityList.size(); i++) {
			DungeonHooks.addDungeonMob(entityList.get(i), 100);
		}
	}
	
	public static void registerEntityEgg(Class<? extends Entity> entity,
			int primaryColor, int secondaryColor) {
		int id = getUniqueEntityId();
		EntityList.IDtoClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor,
				secondaryColor));
	}
	
	public static int getUniqueEntityId() {
		do {
			startEntityId++;
		} while (EntityList.getStringFromID(startEntityId) != null);
		return startEntityId;
	}
}