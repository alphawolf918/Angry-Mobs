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

public class RawBaconBlock extends Block {
	public RawBaconBlock(int par1) {
		super(par1, Material.pumpkin);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setUnlocalizedName("rawbaconblock");
		this.setHardness(1F);
		this.setResistance(0F);
		this.setStepSound(Block.soundClothFootstep);
	}
	
	@Override
	public int idDropped(int par1, Random par2random, int par3) {
		return Items.baconRaw.itemID;
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
				+ "rawbaconblock");
	}
}
