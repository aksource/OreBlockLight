package OreBlockLight;

import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameData;

@Mod(modid = OreBlockLight.MOD_ID,
		name = OreBlockLight.MOD_NAME,
		version = OreBlockLight.MOD_VERSION,
		dependencies = OreBlockLight.MOD_DEPENDENCIES,
		useMetadata = true,
		acceptedMinecraftVersions = OreBlockLight.MOD_MC_VERSION)
public class OreBlockLight {
	public static final String MOD_ID = "OreBlockLight";
	public static final String MOD_NAME = "OreBlockLight";
	public static final String MOD_VERSION = "@VERSION@";
	public static final String MOD_DEPENDENCIES = "required-after:Forge@[11.14.0.1237,)";
	public static final String MOD_MC_VERSION = "[1.8,1.8.9]";
	
	public static String[] LightBlockIDs;
	public static String[] Light7BlockIDs;
	public static String[] Light0BlockIDs;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		LightBlockIDs = config.get(Configuration.CATEGORY_GENERAL, "LightBlockIDs", new String[]{"minecraft:lapis_block", "minecraft:gold_block", "minecraft:iron_block", "minecraft:diamond_block", "minecraft:coal_block"}, "set Block Light Level = 15").getStringList();
		Light7BlockIDs = config.get(Configuration.CATEGORY_GENERAL, "Light7BlockIDs", new String[]{""}, "set Block Light Level = 7").getStringList();
		Light0BlockIDs = config.get(Configuration.CATEGORY_GENERAL, "Light0BlockIDs", new String[]{""}, "set Block Light Level = 0").getStringList();
		config.save();
	}
	@Mod.EventHandler
	public void load(FMLInitializationEvent event)
	{
		setLight(LightBlockIDs, 1.0F);
		setLight(Light7BlockIDs, 0.5F);
		setLight(Light0BlockIDs, 0.0F);
	}
	public void setLight(String[] IDs, float Lv)
	{
		Block block;
		for(String string : IDs){
			if(!string.isEmpty() && GameData.getBlockRegistry().getObject(string) != null){
				block = GameData.getBlockRegistry().getObject(string);
				block.setLightLevel(Lv);
			}
		}
	}
}