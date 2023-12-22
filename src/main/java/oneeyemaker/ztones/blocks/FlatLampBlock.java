package oneeyemaker.ztones.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import oneeyemaker.ztones.Tags;
import oneeyemaker.ztones.Ztones;

public class FlatLampBlock extends Block {

    private final boolean isTransparent;
    private final boolean isDarkened;

    public FlatLampBlock(boolean isTransparent, boolean isDarkened) {
        super(Material.circuits);
        this.isTransparent = isTransparent;
        this.isDarkened = isDarkened;
        this.setHardness(0.0f);
        this.setResistance(12.0f);
        this.setLightLevel(1.0f);
        this.setCreativeTab(Ztones.creativeTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        String suffix = this.isTransparent ? (this.isDarkened ? "b" : "t") : "f";
        this.blockIcon = iconRegister.registerIcon(String.format("%s:lamp%s", Tags.MODID, suffix));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        if (this.isTransparent) {
            Block neighborBlock = world.getBlock(x, y, z);
            if (this == neighborBlock) {
                return false;
            }
        }
        return super.shouldSideBeRendered(world, x, y, z, side);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return this.isTransparent ? 1 : 0;
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
        String name = this.isTransparent ? (this.isDarkened ? "Darkened" : "Transparent") : "";
        return String.format("%s.flat%sLamp", Tags.MODID, name);
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side) {
        ForgeDirection direction = ForgeDirection.getOrientation(side);
        return direction != ForgeDirection.UNKNOWN
            && world.isSideSolid(x - direction.offsetX, y - direction.offsetY, z - direction.offsetZ, direction);
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            if (world.isSideSolid(x - direction.offsetX, y - direction.offsetY, z - direction.offsetZ, direction)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float subX, float subY, float subZ,
        int metadata) {
        ForgeDirection direction = ForgeDirection.getOrientation(side);
        if (direction != ForgeDirection.UNKNOWN
            && world.isSideSolid(x - direction.offsetX, y - direction.offsetY, z - direction.offsetZ, direction)) {
            return 6 - direction.ordinal();
        }
        for (ForgeDirection validDirection : ForgeDirection.VALID_DIRECTIONS) {
            if (world.isSideSolid(
                x - validDirection.offsetX,
                y - validDirection.offsetY,
                z - validDirection.offsetZ,
                validDirection)) {
                return 6 - validDirection.ordinal();
            }
        }
        return 1;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        int metadata = world.getBlockMetadata(x, y, z);
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            if (metadata == 6 - direction.ordinal()
                && !world.isSideSolid(x - direction.offsetX, y - direction.offsetY, z - direction.offsetZ, direction)) {
                this.dropBlockAsItem(world, x, y, z, metadata, 0);
                world.setBlockToAir(x, y, z);
                return;
            }
        }
        if (!this.canPlaceBlockAt(world, x, y, z)) {
            this.dropBlockAsItem(world, x, y, z, metadata, 0);
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        int metadata = world.getBlockMetadata(x, y, z) & 0x7;
        final float offset = 0.0625f;
        switch (metadata) {
            case 1 -> this.setBlockBounds(0.0f, 0.0f, 0.0f, offset, 1.0f, 1.0f);
            case 2 -> this.setBlockBounds(1.0f - offset, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            case 3 -> this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, offset);
            case 4 -> this.setBlockBounds(0.0f, 0.0f, 1.0f - offset, 1.0f, 1.0f, 1.0f);
            case 5 -> this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, offset, 1.0f);
            case 6 -> this.setBlockBounds(0.0f, 1.0f - offset, 0.0f, 1.0f, 1.0f, 1.0f);
        }
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
}
