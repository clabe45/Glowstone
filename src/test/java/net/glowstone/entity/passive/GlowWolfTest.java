package net.glowstone.entity.passive;

import static org.junit.Assert.assertEquals;

import java.util.EnumSet;
import org.bukkit.Material;
import org.junit.Test;

public class GlowWolfTest extends GlowTameableTest<GlowWolf> {
    public GlowWolfTest() {
        super(GlowWolf::new);
    }

    @Test
    @Override
    public void testGetBreedingFoods() {
        assertEquals(EnumSet.of(Material.BEEF, Material.COOKED_BEEF,
                Material.RABBIT, Material.COOKED_RABBIT, Material.MUTTON,
                Material.COOKED_MUTTON, Material.PORKCHOP, Material.COOKED_PORKCHOP,
                Material.CHICKEN, Material.COOKED_CHICKEN, Material.ROTTEN_FLESH),
                entity.getBreedingFoods());
    }
}
