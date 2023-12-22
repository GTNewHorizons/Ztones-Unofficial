package oneeyemaker.ztones.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import oneeyemaker.ztones.ModConfiguration;
import oneeyemaker.ztones.Tags;
import oneeyemaker.ztones.Ztones;

public class CleanDirtBlock extends Block {

    @SideOnly(Side.CLIENT)
    private IIcon[] textures;

    public CleanDirtBlock() {
        super(Material.grass);
        this.setHarvestLevel("shovel", 1);
        this.setHardness(0.5f);
        this.setResistance(15.0f);
        this.setStepSound(Block.soundTypeGrass);
        this.setCreativeTab(Ztones.creativeTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.textures = new IIcon[2];
        this.textures[0] = iconRegister.registerIcon(String.format("%s:cleanDirt", Tags.MODID));
        this.textures[1] = iconRegister.registerIcon(String.format("%s:cleanDirt1", Tags.MODID));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return side == ForgeDirection.UP.ordinal() ? this.textures[1] : this.textures[0];
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("%s.cleanDirt", Tags.MODID);
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction,
        IPlantable plantable) {
        return true;
    }

    @Override
    public boolean isFertile(World world, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        return ModConfiguration.isCreatureSpawnOnZtonesEnabled;
    }
}
