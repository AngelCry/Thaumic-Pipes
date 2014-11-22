package me.jezza.thaumicpipes.common.multipart;

import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.MultipartGenerator;
import codechicken.multipart.TMultiPart;
import me.jezza.thaumicpipes.common.core.interfaces.IThaumicPipe;
import me.jezza.thaumicpipes.common.multipart.part.thaumic.ThaumicPipePart;

public class MultiPartFactory implements IPartFactory {

    public static final String thaumicPipe = "tp_thaumicPipe";

    public void init() {
        MultipartGenerator.registerPassThroughInterface(IThaumicPipe.class.getCanonicalName());
    }

    @Override
    public TMultiPart createPart(String name, boolean client) {
        if (thaumicPipe.equals(name))
            return new ThaumicPipePart();
        return null;
    }
}
