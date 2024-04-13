package net.denobody2.icydwarfworldmod;

import com.github.alexthe666.citadel.server.event.EventReplaceBiome;
import com.mojang.logging.LogUtils;
import net.denobody2.icydwarfworldmod.config.BiomeGenerationConfig;
import net.denobody2.icydwarfworldmod.event.ModCommonEvents;
import net.denobody2.icydwarfworldmod.registry.*;
import net.denobody2.icydwarfworldmod.worldgen.structure.ModStructures;
import net.denobody2.icydwarfworldmod.worldgen.structure.piece.ModStructurePieces;
import net.denobody2.icydwarfworldmod.worldgen.surface.ModSurfaceRules;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(IcyDwarfWorldMod.MOD_ID)
public class IcyDwarfWorldMod
{
    public static final String MOD_ID = "icydwarfworldmod";
    public static CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    // Define mod id in a common place for everything to reference
    // Directly reference a slf4j logger
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    private static final ResourceLocation PACKET_NETWORK_NAME = new ResourceLocation("icydwarfworldmod:main_channel");
    public static final SimpleChannel NETWORK_WRAPPER = NetworkRegistry.ChannelBuilder
            .named(PACKET_NETWORK_NAME)
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();
    public static final Logger LOGGER = LogUtils.getLogger();

    //Todo
    //surface rules
    //custom cave shape
    /*"air": [
            "minecraft:cave",
            "minecraft:cave_extra_underground",
            "minecraft:canyon"
            ]*/
    //Todo Art


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
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ModCommonEvents());
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::loadConfig);
        modEventBus.addListener(this::reloadConfig);
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModParticles.PARTICLES.register(modEventBus);
        ModFeatureTypes.FEATURES.register(modEventBus);
        ModSurfaceRuleRegistry.SURFACE_RULES.register(modEventBus);
        ModStructures.STRUCTURE_TYPES.register(modEventBus);
        ModStructurePieces.STRUCTURE_PIECE_TYPE_DEFERRED_REGISTER.register(modEventBus);
        BiomeGenerationConfig.reloadConfig();
        ModBiomes.init();
    }
    private void loadConfig(final ModConfigEvent.Loading event) {
        BiomeGenerationConfig.reloadConfig();
    }

    private void reloadConfig(final ModConfigEvent.Reloading event) {
        BiomeGenerationConfig.reloadConfig();
    }
    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(ModItems.MANDARIN_FLOWER.get(), 0.4f);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.MANDARIN_LEAVES.get().asItem(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.FLOWERED_MANDARIN_LEAVES.get().asItem(), 0.4f);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.MANDARIN_SAPLING.get().asItem(), 0.3f);
            ModSurfaceRules.setup();

        });
    }

   /*@SubscribeEvent
    public void onReplaceBiome(EventReplaceBiome event){
        ResourceKey<Biome> biome = BiomeGenerationConfig.getBiomeForEvent(event);
        if (biome != null) {
            Holder<Biome> biomeHolder = event.getBiomeSource().getResourceKeyMap().get(biome);
            if (biomeHolder != null) {
                event.setResult(Event.Result.ALLOW);
                event.setBiomeToGenerate(biomeHolder);
            }
        }
    }*/
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> PROXY.clientInit());
    }
}
