package oneeyemaker.ztones.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import oneeyemaker.ztones.Tags;
import oneeyemaker.ztones.Ztones;

public class BoosterBlock extends Block {

    public BoosterBlock() {
        super(Material.circuits);
        this.setHardness(0.0f);
        this.setResistance(12.0f);
        this.setCreativeTab(Ztones.creativeTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(String.format("%s:booster", Tags.MODID));
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("%s.booster", Tags.MODID);
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return world.isSideSolid(x, y - 1, z, ForgeDirection.UP);
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side) {
        ForgeDirection direction = ForgeDirection.getOrientation(side);
        return direction == ForgeDirection.UP && world.isSideSolid(x, y - 1, z, ForgeDirection.UP);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        if (!this.canBlockStay(world, x, y, z)) {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, int x, int y, int z) {
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.0625f, 1.0f);
    }

    @Override
    public void setBlockBoundsForItemRender() {
        final float offset = 0.0625f;
        this.setBlockBounds(0.0f, 0.5f - offset, 0.0f, 1.0f, 0.5f + offset, 1.0f);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List<AxisAlignedBB> list,
        Entity collider) {}

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (entity instanceof EntityPlayer entityPlayer) {
            world.playSoundAtEntity(entityPlayer, String.format("%s:booster", Tags.MODID), 1.0f, 1.0f);
            entityPlayer.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 32, 3));
            entityPlayer.motionX *= 1.5;
            entityPlayer.motionZ *= 1.5;
        }
    }
}
