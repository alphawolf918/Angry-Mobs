package angrymobs.lib;

import net.minecraft.item.ItemStack;
import angrymobs.blocks.Blocks;
import angrymobs.items.Items;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {
	public static void init() {
		
		/*
		 * Crafting
		 */
		
		// Cooked Block
		GameRegistry.addRecipe(new ItemStack(Blocks.cookedBaconBlock, 1),
				new Object[] {
						"CCC", "CCC", "CCC", 'C', Items.baconCooked });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.baconCooked, 9),
				new Object[] {
				Blocks.cookedBaconBlock
				});
		
		// Raw Block
		GameRegistry.addRecipe(new ItemStack(Blocks.rawBaconBlock, 1),
				new Object[] {
						"RRR", "RRR", "RRR", 'R', Items.baconRaw });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.baconRaw, 9),
				new Object[] {
				Blocks.rawBaconBlock
				});
		
		/*
		 * Smelting
		 */
		
		// Bacon Item
		int baconRaw = Items.baconRaw.itemID;
		ItemStack baconCooked = new ItemStack(Items.baconCooked, 1);
		float bcXP = 25F;
		GameRegistry.addSmelting(baconRaw, baconCooked, bcXP);
		
		// Bacon Block
		int baconBlockRaw = Blocks.rawBaconBlock.blockID;
		ItemStack baconBlockCooked = new ItemStack(Blocks.cookedBaconBlock, 1);
		float bcXP2 = 45F;
		GameRegistry.addSmelting(baconBlockRaw, baconBlockCooked, bcXP2);
	}
}
