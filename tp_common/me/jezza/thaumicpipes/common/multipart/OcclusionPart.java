package me.jezza.thaumicpipes.common.multipart;

import java.util.ArrayList;

import me.jezza.thaumicpipes.api.interfaces.IThaumicPipe;
import codechicken.lib.vec.Cuboid6;
import codechicken.multipart.JCuboidPart;
import codechicken.multipart.JNormalOcclusion;
import codechicken.multipart.NormalOcclusionTest;
import codechicken.multipart.TMultiPart;
import me.jezza.thaumicpipes.common.core.TPLogger;

public class OcclusionPart extends JCuboidPart implements JNormalOcclusion {
    private ArrayList<Cuboid6> occlusions = new ArrayList<Cuboid6>();
    private Cuboid6 bounds;

    public OcclusionPart(Cuboid6 cuboid) {
        bounds = cuboid;
        occlusions.add(bounds);
    }

    @Override
    public String getType() {
        return "-----";
    }

    @Override
    public Cuboid6 getBounds() {
        return this.bounds;
    }

    @Override
    public Iterable<Cuboid6> getOcclusionBoxes() {
        return this.occlusions;
    }

    @Override
    public boolean occlusionTest(TMultiPart npart) {
        return (npart instanceof IThaumicPipe) ? true : NormalOcclusionTest.apply(this, npart);
    }
}