package angrymobs.blocks;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {
	public static final Block rawBaconBlock = new RawBaconBlock(230);
	public static final Block cookedBaconBlock = new CookedBaconBlock(231);
	
	public static void init() {
		addNames(rawBaconBlock, "Raw Bacon Block");
		addNames(cookedBaconBlock, "Cooked Bacon Block");
	}
	
	public static void addNames(Block unlocalizedName, String formattedName) {
		GameRegistry.registerBlock(unlocalizedName, formattedName);
		LanguageRegistry.addName(unlocalizedName, formattedName);
	}
}
