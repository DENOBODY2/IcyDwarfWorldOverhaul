package net.denobody2.icydwarfworldmod;

import com.mojang.logging.LogUtils;
import net.denobody2.icydwarfworldmod.registry.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(IcyDwarfWorldMod.MOD_ID)
public class IcyDwarfWorldMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "icydwarfworldmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    //Todo
    //.

    //Todo Art
    //.

    //Todo Sound
    //gooblino idle, walk, hurt, die
    //riftling idle, walk, hurt, die, digest
    //crate maybe?
    //rift close, idle, open
    //if sfx design isnt obnoxious potential for deirum block sounds, verdant stone sounds.

    //Todo Full Release (not including sounds)
    //british gooblino
    //-
    //eyeball, eye drop
    //-
    //ash
    //ashen dungeon, ash spider
    //ash lantern (+ ash particle)
    //ash bricks & ash tiles
    //ashen bellows (ash gust bellows, like gust bellows from zelda skyward sword)
    //-
    //demon
    //i pay for the more complex stuff in this
    //summoning altar block
    //burning ash block
    //demon mob (miniboss), demon shard, ash demon armor
    //lots of particles
    //-
    //pirate
    //fish - maybe easter egg skin coelecanth
    //pirate structure, pirate mob?
    //pirate cosmetics
    //pirate weaponry



    public IcyDwarfWorldMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModParticles.PARTICLES.register(modEventBus);
        ModFeatureTypes.FEATURES.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);




    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }



    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
