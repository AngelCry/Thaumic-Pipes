package me.jezza.thaumicpipes.common.core.multipart;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import codechicken.lib.vec.Cuboid6;
import codechicken.multipart.IconHitEffects;
import codechicken.multipart.JCuboidPart;
import codechicken.multipart.JIconHitEffects;
import codechicken.multipart.JNormalOcclusion;
import codechicken.multipart.NormalOcclusionTest;
import codechicken.multipart.TMultiPart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class MultiPartAbstract extends JCuboidPart implements JNormalOcclusion, JIconHitEffects {

    public abstract Block getBlock();

    @Override
    public void onWorldJoin() {
        onNeighborChanged();
    }

    @Override
    public ItemStack pickItem(MovingObjectPosition hit) {
        return getBlock().getPickBlock(hit, tile().getWorldObj(), tile().xCoord, tile().yCoord, tile().zCoord);
    }

    @Override
    public Iterable<ItemStack> getDrops() {
        return Arrays.asList(new ItemStack(getBlock()));
    }

    @Override
    public Iterable<Cuboid6> getCollisionBoxes() {
        return getOcclusionBoxes();
    }

    @Override
    public void addHitEffects(MovingObjectPosition hit, EffectRenderer effectRenderer) {
        IconHitEffects.addHitEffects(this, hit, effectRenderer);
    }

    @Override
    public void addDestroyEffects(MovingObjectPosition hit, EffectRenderer effectRenderer) {
        IconHitEffects.addDestroyEffects(this, effectRenderer, false);
    }

    @Override
    public float getStrength(MovingObjectPosition hit, EntityPlayer player) {
        return getBlock().getPlayerRelativeBlockHardness(player, player.worldObj, hit.blockX, hit.blockY, hit.blockZ) * 30;
    }

    @Override
    public boolean occlusionTest(TMultiPart part) {
        return NormalOcclusionTest.apply(this, part);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getBreakingIcon(Object arg0, int arg1) {
        return getBlock().getIcon(0, 0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getBrokenIcon(int arg0) {
        return getBlock().getIcon(0, 0);
    }

}
