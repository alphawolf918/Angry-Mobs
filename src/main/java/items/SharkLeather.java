package angrymobs.items;

import angrymobs.lib.ModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SharkLeather extends Item {

	public SharkLeather(int par1) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setUnlocalizedName("sharkleather");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
		itemIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":"
				+ "sharkleather");
	}

}
