package net.denobody2.icydwarfworldmod;

import com.github.alexthe666.citadel.server.event.EventReplaceBiome;
import com.github.alexthe666.citadel.server.generation.SurfaceRulesManager;
import com.github.alexthe666.citadel.server.world.ExpandedBiomes;
import com.mojang.logging.LogUtils;
import net.denobody2.icydwarfworldmod.event.ModClientEvents;
import net.denobody2.icydwarfworldmod.registry.*;
import net.denobody2.icydwarfworldmod.worldgen.surface.ModSurfaceRules;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(IcyDwarfWorldMod.MOD_ID)
public class IcyDwarfWorldMod
{
    public static CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "icydwarfworldmod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    //Todo
    //add citadel depedency, biome registry, surface rules

    //Todo Art
    //retextures - just sapling and crate left i think

    //Todo Sound
    //gooblino idle, walk, hurt, die
    //riftling idle, walk, hurt, die, digest
    //crate maybe?
    //rift close, idle, open
    //if sfx design isnt obnoxious potential for deirum block sounds, verdant stone sounds.

    //Todo Full Release (not including sounds)
    //eyeball, eye drop
    //-
    //ash
    //ashen dungeon, ash spider
    //ash lantern (+ ash particle)
    //ash bricks & ash tiles & chiseled ash brick
    //ash bellows (ash gust bellows, like gust bellows from zelda skyward sword)
    //-
    //demon
    //summoning altar block
    //burning ash block
    //demon mob (miniboss), demon shard, drop use (helmet? weapon? upgrade to ash bellows? not sure)
    //lots of particles
    //-
    //pirate
    //fish
    //fishing trophies and loot
    //pirate cosmetics
    //pirate weaponry
    //-
    //biomes
    //riftling caves
    //other small biomes with custom things?
    //-
    //idk
    //pirate structure, pirate mob?



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
        ModSurfaceRuleRegistry.SURFACE_RULES.register(modEventBus);
        ModBiomes.init();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);




    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(ModItems.MANDARIN_FLOWER.get(), 0.4f);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.MANDARIN_LEAVES.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.FLOWERED_MANDARIN_LEAVES.get(), 0.35f);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.MANDARIN_SAPLING.get(), 0.30f);
            ModSurfaceRules.setup();
        });
    }
    @SubscribeEvent
    public void onReplaceBiome(EventReplaceBiome event){
        if(event.weirdness > -1.0F && event.weirdness < 0.7F && event.depth > 0.6F && event.depth < 1.5F){
            event.setResult(Event.Result.ALLOW);
            event.setBiomeToGenerate(event.getBiomeSource().getResourceKeyMap().get(ModBiomes.TEST_BIOME));
        }
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
            event.enqueueWork(() -> PROXY.clientInit());
        }

    }
}
