package angrymobs.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import angrymobs.lib.ModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HellPearl extends Item {
	public HellPearl(int par1) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setUnlocalizedName("hellpearl");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
		itemIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":"
				+ "hellpearl");
	}
}