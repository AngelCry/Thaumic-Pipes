package me.jezza.thaumicpipes.common.multipart;

import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.TileMultipart;
import me.jezza.oc.common.items.ItemAbstract;
import net.minecraft.block.Block.SoundType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class ItemMultiPartAbstract extends ItemAbstract {

    protected ItemMultiPartAbstract(String name) {
        super(name);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitVecX, float hitVecY, float hitVecZ) {
        BlockCoord pos = new BlockCoord(x, y, z);
        Vector3 vHit = new Vector3(hitVecX, hitVecY, hitVecZ);
        double d = getHitDepth(vHit, side);

        if (d < 1.0D && placePart(itemStack, player, world, pos, side, vHit))
            return true;
        pos.offset(side);
        return placePart(itemStack, player, world, pos, side, vHit);
    }

    public boolean placePart(ItemStack item, EntityPlayer player, World world, BlockCoord pos, int side, Vector3 vHit) {
        TMultiPart part = getPart();
        if (part == null || !TileMultipart.canPlacePart(world, pos, part))
            return false;

        if (!world.isRemote) {
            TileMultipart.addPart(world, pos, part);
            SoundType type = getSoundType();
            if (type != null)
                world.playSoundEffect(pos.x + 0.5F, pos.y + 0.5F, pos.z + 0.5F, type.func_150496_b(), (type.getVolume() + 1.0F) / 2.0F, type.getPitch() * 0.8F);
        }

        if (!player.capabilities.isCreativeMode)
            item.stackSize--;
        return true;
    }

    double getHitDepth(Vector3 vHit, int side) {
        return vHit.copy().scalarProject(codechicken.lib.vec.Rotation.axes[side]) + (side % 2 ^ 0x1);
    }

    protected abstract TMultiPart getPart();

    protected abstract SoundType getSoundType();
}
