package net.denobody2.icydwarfworldmod.client.model.layer;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation MANDARIN_BOAT_LAYER = create("boat/mandarin");
    public static final ModelLayerLocation MANDARIN_CHEST_BOAT_LAYER = create("chest_boat/mandarin");


    private static ModelLayerLocation create(String name) {
        return new ModelLayerLocation(new ResourceLocation(IcyDwarfWorldMod.MOD_ID, name), "main");
    }
}
