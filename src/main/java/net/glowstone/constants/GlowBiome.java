package net.glowstone.constants;

import static com.google.common.base.Preconditions.checkNotNull;
import static net.glowstone.GlowServer.getWorldConfig;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_BIG_HILLS;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_BIG_HILLS2;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_DEEP_OCEAN;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_DEFAULT;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_DEFAULT_HILLS;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_EXTREME_HILLS;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_FLATLANDS;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_FLATLANDS_HILLS;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_FLAT_SHORE;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_HIGH_PLATEAU;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_HIGH_SPIKES;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_HILLS;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_LOW_HILLS;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_LOW_SPIKES;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_MID_HILLS;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_MID_HILLS2;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_MID_PLAINS;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_OCEAN;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_RIVER;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_ROCKY_SHORE;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_SWAMPLAND;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_HEIGHT_SWAMPLAND_HILLS;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_BIG_HILLS;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_BIG_HILLS2;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_DEEP_OCEAN;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_DEFAULT;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_DEFAULT_HILLS;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_EXTREME_HILLS;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_FLATLANDS;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_FLATLANDS_HILLS;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_FLAT_SHORE;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_HIGH_PLATEAU;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_HIGH_SPIKES;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_HILLS;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_LOW_HILLS;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_LOW_SPIKES;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_MID_HILLS;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_MID_HILLS2;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_MID_PLAINS;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_OCEAN;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_RIVER;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_ROCKY_SHORE;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_SWAMPLAND;
import static net.glowstone.util.config.WorldConfig.Key.BIOME_SCALE_SWAMPLAND_HILLS;
import static org.bukkit.block.Biome.ICE_SPIKES;
import static org.bukkit.block.Biome.SNOWY_TAIGA;
import static org.bukkit.block.Biome.SNOWY_TUNDRA;
import static org.bukkit.block.Biome.values;

import com.google.common.collect.ImmutableClassToInstanceMap;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.glowstone.generator.ground.GroundGenerator;
import net.glowstone.generator.ground.SnowyGroundGenerator;
import net.glowstone.generator.populators.overworld.BiomePopulator;
import net.glowstone.generator.populators.overworld.IcePlainsPopulator;
import net.glowstone.generator.populators.overworld.IcePlainsSpikesPopulator;
import net.glowstone.generator.populators.overworld.TaigaPopulator;
import net.glowstone.i18n.ConsoleMessages;
import org.bukkit.block.Biome;

/**
 * Mappings for Biome id values.
 */
@Builder
@Data
public final class GlowBiome {

    private static final int[] ids = new int[values().length];
    private static final GlowBiome[] biomes = new GlowBiome[256];
    private static final ImmutableClassToInstanceMap<BiomePopulator> populators;
    private static ImmutableClassToInstanceMap.Builder<BiomePopulator> populatorBuilder
            = ImmutableClassToInstanceMap.builder();

    static {
        register(
                builder()
                        .type(SNOWY_TUNDRA)
                        .id(12)
                        .temperature(0.0)
                        .populator(IcePlainsPopulator.class)
                        .scale(BiomeScale.FLATLANDS)
                        .build(),
                builder()
                        .type(ICE_SPIKES)
                        .id(140)
                        .temperature(0.0)
                        .populator(IcePlainsSpikesPopulator.class)
                        .ground(new SnowyGroundGenerator())
                        .scale(BiomeScale.BIG_HILLS)
                        .build(),
                builder()
                        .type(SNOWY_TAIGA)
                        .id(30)
                        .temperature(-0.5)
                        .populator(TaigaPopulator.class)
                        .scale(BiomeScale.MID_PLAINS)
                        .build()
        );
        populators = populatorBuilder.build();
        populatorBuilder = null;
    }

    private final int id;
    private final Biome type;
    private final double temperature;
    private final BiomePopulator populator;
    private final GroundGenerator ground;
    private final BiomeScale scale;

    /**
     * Get the biome ID for a specified Biome.
     *
     * @param biome the Biome.
     * @return the biome id, or -1
     */
    public static int getId(Biome biome) {
        checkNotNull(biome, "Biome cannot be null");
        return ids[biome.ordinal()];
    }

    /**
     * Get the GlowBiome for a specified id.
     *
     * @param id the id.
     * @return the Biome, or null
     */
    public static GlowBiome getBiome(int id) {
        if (id < biomes.length) {
            return biomes[id];
        } else {
            ConsoleMessages.Error.Biome.UNKNOWN.log(id);
            return null;
        }
    }

    /**
     * Get the GlowBiome for a specified biome type.
     *
     * @param biome the biome type.
     * @return the Biome, or null
     */
    public static GlowBiome getBiome(Biome biome) {
        return getBiome(getId(biome));
    }

    private static void register(GlowBiome... biomes) {
        for (GlowBiome biome : biomes) {
            GlowBiome.ids[biome.type.ordinal()] = biome.id;
            GlowBiome.biomes[biome.id] = biome;
        }
    }

    public static class GlowBiomeBuilder {
        public GlowBiomeBuilder populator(Class<? extends BiomePopulator> populatorClass) {
            try {
                this.populator = populators.putIfAbsent(populatorClass, populatorClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return this;
        }
    }

    @RequiredArgsConstructor
    private static class BiomeScale {
        public static final BiomeScale DEFAULT = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_DEFAULT),
                getWorldConfig().getDouble(BIOME_SCALE_DEFAULT));
        public static final BiomeScale FLAT_SHORE = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_FLAT_SHORE),
                getWorldConfig().getDouble(BIOME_SCALE_FLAT_SHORE));
        public static final BiomeScale HIGH_PLATEAU = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_HIGH_PLATEAU),
                getWorldConfig().getDouble(BIOME_SCALE_HIGH_PLATEAU));
        public static final BiomeScale FLATLANDS = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_FLATLANDS),
                getWorldConfig().getDouble(BIOME_SCALE_FLATLANDS));
        public static final BiomeScale SWAMPLAND = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_SWAMPLAND),
                getWorldConfig().getDouble(BIOME_SCALE_SWAMPLAND));
        public static final BiomeScale MID_PLAINS = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_MID_PLAINS),
                getWorldConfig().getDouble(BIOME_SCALE_MID_PLAINS));
        public static final BiomeScale FLATLANDS_HILLS = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_FLATLANDS_HILLS),
                getWorldConfig().getDouble(BIOME_SCALE_FLATLANDS_HILLS));
        public static final BiomeScale SWAMPLAND_HILLS = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_SWAMPLAND_HILLS),
                getWorldConfig().getDouble(BIOME_SCALE_SWAMPLAND_HILLS));
        public static final BiomeScale LOW_HILLS = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_LOW_HILLS),
                getWorldConfig().getDouble(BIOME_SCALE_LOW_HILLS));
        public static final BiomeScale HILLS = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_HILLS),
                getWorldConfig().getDouble(BIOME_SCALE_HILLS));
        public static final BiomeScale MID_HILLS2 = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_MID_HILLS2),
                getWorldConfig().getDouble(BIOME_SCALE_MID_HILLS2));
        public static final BiomeScale DEFAULT_HILLS = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_DEFAULT_HILLS),
                getWorldConfig().getDouble(BIOME_SCALE_DEFAULT_HILLS));
        public static final BiomeScale MID_HILLS = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_MID_HILLS),
                getWorldConfig().getDouble(BIOME_SCALE_MID_HILLS));
        public static final BiomeScale BIG_HILLS = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_BIG_HILLS),
                getWorldConfig().getDouble(BIOME_SCALE_BIG_HILLS));
        public static final BiomeScale BIG_HILLS2 = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_BIG_HILLS2),
                getWorldConfig().getDouble(BIOME_SCALE_BIG_HILLS2));
        public static final BiomeScale EXTREME_HILLS = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_EXTREME_HILLS),
                getWorldConfig().getDouble(BIOME_SCALE_EXTREME_HILLS));
        public static final BiomeScale ROCKY_SHORE = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_ROCKY_SHORE),
                getWorldConfig().getDouble(BIOME_SCALE_ROCKY_SHORE));
        public static final BiomeScale LOW_SPIKES = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_LOW_SPIKES),
                getWorldConfig().getDouble(BIOME_SCALE_LOW_SPIKES));
        public static final BiomeScale HIGH_SPIKES = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_HIGH_SPIKES),
                getWorldConfig().getDouble(BIOME_SCALE_HIGH_SPIKES));
        public static final BiomeScale RIVER = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_RIVER),
                getWorldConfig().getDouble(BIOME_SCALE_RIVER));
        public static final BiomeScale OCEAN = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_OCEAN),
                getWorldConfig().getDouble(BIOME_SCALE_OCEAN));
        public static final BiomeScale DEEP_OCEAN = new BiomeScale(
                getWorldConfig().getDouble(BIOME_HEIGHT_DEEP_OCEAN),
                getWorldConfig().getDouble(BIOME_SCALE_DEEP_OCEAN));

        @Getter
        private final double height;
        @Getter
        private final double scale;
    }
}
