package angrymobs.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;
import angrymobs.lib.ModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaconRaw extends ItemFood {
	public BaconRaw(int par1) {
		super(par1, 1, false);
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setUnlocalizedName("baconraw");
		this.setPotionEffect(Potion.hunger.id, 60, 2, 98);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister icon) {
		itemIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":"
				+ "baconraw");
	}
}