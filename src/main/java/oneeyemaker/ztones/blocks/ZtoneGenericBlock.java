package oneeyemaker.ztones.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import oneeyemaker.ztones.Tags;
import oneeyemaker.ztones.ZtoneType;
import oneeyemaker.ztones.Ztones;

public class ZtoneGenericBlock extends Block {

    private final ZtoneType type;
    @SideOnly(Side.CLIENT)
    private IIcon[] textures;

    public ZtoneGenericBlock(ZtoneType type) {
        super(type.isGlassLike() ? Material.glass : Material.rock);
        this.type = type;
        this.setHardness(1.5f);
        if (this.type.isGlassLike()) {
            this.setResistance(15.0f);
            this.setStepSound(Block.soundTypeGlass);
        } else {
            this.setResistance(30.0f);
        }
        this.setCreativeTab(Ztones.creativeTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
        for (int index = 0; index < ZtoneType.Variants; index++) {
            list.add(new ItemStack(item, 1, index));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        String name = this.type.toString();
        this.textures = new IIcon[ZtoneType.Variants];
        for (int index = 0; index < ZtoneType.Variants; index++) {
            this.textures[index] = iconRegister
                .registerIcon(String.format("%s:sets/%s/%s_ (%d)", Tags.MODID, name, name, index));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        if (metadata < 0 || metadata >= ZtoneType.Variants) {
            metadata = 0;
        }
        return this.textures[metadata];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return this.type.isGlassLike() ? 1 : 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        if (this.type.isGlassLike()) {
            Block block = world.getBlock(x, y, z);
            if (this == block) {
                int metadata = world.getBlockMetadata(x, y, z);
                int neighborMetadata = world.getBlockMetadata(
                    x - Facing.offsetsXForSide[side],
                    y - Facing.offsetsYForSide[side],
                    z - Facing.offsetsZForSide[side]);
                return metadata != neighborMetadata;
            }
        }
        return super.shouldSideBeRendered(world, x, y, z, side);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return this.blockMaterial != Material.glass;
    }

    @Override
    public boolean isOpaqueCube() {
        return this.blockMaterial != Material.glass;
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("%s.%s", Tags.MODID, this.type.toString());
    }

    @Override
    public int damageDropped(int metadata) {
        return metadata;
    }

    public String getRegistryName() {
        return String.format("tile.%sBlock", this.type != ZtoneType.Tile ? this.type.toString() : "stoneTile");
    }
}
