package angrymobs.items;

import net.minecraft.item.Item;
import angrymobs.lib.DataInfo;
import angrymobs.lib.Names;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items {
	
	public static final Item ShadowBone = new ShadowBone(DataInfo.shadowBoneID);
	public static final Item sharkTooth = new SharkTooth(DataInfo.sharkToothID);
	public static final Item sharkLeather = new SharkLeather(
			DataInfo.sharkLeatherID);
	public static final Item baconRaw = new BaconRaw(DataInfo.baconRawID);
	public static final Item baconCooked = new BaconCooked(
			DataInfo.baconCookedID);
	public static final Item hellPearl = new HellPearl(DataInfo.hellPearlID);
	
	public static void init() {
		LanguageRegistry.addName(ShadowBone, Names.shadowBone);
		LanguageRegistry.addName(sharkTooth, Names.sharkTooth);
		LanguageRegistry.addName(sharkLeather, Names.sharkLeather);
		LanguageRegistry.addName(baconRaw, "Raw Bacon");
		LanguageRegistry.addName(baconCooked, "Cooked Bacon");
		LanguageRegistry.addName(hellPearl, "Hell Pearl");
	}
}