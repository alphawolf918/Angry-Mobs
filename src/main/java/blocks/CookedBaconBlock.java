package angrymobs.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import angrymobs.items.Items;
import angrymobs.lib.ModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CookedBaconBlock extends Block {
	public CookedBaconBlock(int par1) {
		super(par1, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setUnlocalizedName("cookedbaconblock");
		this.setHardness(4F);
		this.setResistance(4F);
		this.setStepSound(Block.soundStoneFootstep);
	}
	
	@Override
	public int idDropped(int par1, Random par2random, int par3) {
		return Items.baconCooked.itemID;
	}
	
	@Override
	public int quantityDropped(Random par1) {
		int rand = (int) (Math.floor(10) * Math.random());
		if (rand >= 5)
			return 9;
		else
			return 7;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
		blockIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":"
				+ "cookedbaconblock");
	}
}
