package oneeyemaker.ztones.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import oneeyemaker.ztones.ModConfiguration;
import oneeyemaker.ztones.Tags;
import oneeyemaker.ztones.Ztones;

public class AuroraBlock extends Block {

    public AuroraBlock() {
        super(Material.rock);
        this.setHardness(1.5f);
        this.setResistance(12.0f);
        this.setStepSound(Block.soundTypeSnow);
        this.setCreativeTab(Ztones.creativeTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(String.format("%s:aurora", Tags.MODID));
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("%s.aurora", Tags.MODID);
    }

    @Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        return ModConfiguration.enableCreatureSpawnOnZtones;
    }
}
