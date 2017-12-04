package solonarv.mods.craftinghoppers;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import solonarv.mods.craftinghoppers.block.BlockCraftingHopper;
import net.minecraft.block.Block;

@Mod(modid = CraftingHoppers.MODID, version = CraftingHoppers.VERSION)
@Mod.EventBusSubscriber
public class CraftingHoppers {

    @Mod.Instance
    public static CraftingHoppers INSTANCE;

    public static final String MODID = "craftinghoppers";
    public static final String VERSION = "1.0";

    public static final BlockCraftingHopper CRAFTING_HOPPER = new BlockCraftingHopper();


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        System.out.println("Registering the Crafting Hopper!");
        ResourceLocation hopperName = new ResourceLocation("minecraft", "hopper");
        if (!(event.getRegistry() instanceof ForgeRegistry)) {
            System.out.println("registerBlocks was passed an IForgeRegistry that isn't a ForgeRegistry??????");
            return;
        }
        ForgeRegistry<Block> registry = (ForgeRegistry<Block>) event.getRegistry();
        // registry.remove(hopperName);
        CRAFTING_HOPPER.setRegistryName(hopperName);
        registry.register(CRAFTING_HOPPER);
    }
}
