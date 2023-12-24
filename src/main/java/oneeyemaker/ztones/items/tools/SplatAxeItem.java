package oneeyemaker.ztones.items.tools;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import oneeyemaker.ztones.Tags;
import oneeyemaker.ztones.Ztones;

public class SplatAxeItem extends ItemTool {

    public static final ToolMaterial GREEN_GOO = EnumHelper.addToolMaterial("greenGoo", 3, 1000, 50.0f, 1.0f, 32);
    private static final Set<Block> effectiveAgainstBlocks = Sets.newHashSet(
        Blocks.planks,
        Blocks.bookshelf,
        Blocks.log,
        Blocks.log2,
        Blocks.chest,
        Blocks.pumpkin,
        Blocks.lit_pumpkin);

    public SplatAxeItem() {
        super(2.0f, GREEN_GOO, effectiveAgainstBlocks);
        this.setMaxStackSize(1);
        this.setCreativeTab(Ztones.creativeTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(String.format("%s:splat_axe", Tags.MODID));
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("%s.splatAxe", Tags.MODID);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return this.getUnlocalizedName();
    }

    @Override
    public float func_150893_a(ItemStack itemStack, Block block) {
        Material material = block.getMaterial();
        return material == Material.wood || material == Material.plants || material == Material.vine
            ? this.efficiencyOnProperMaterial
            : super.func_150893_a(itemStack, block);
    }

    @Override
    public Set<String> getToolClasses(ItemStack itemStack) {
        return ImmutableSet.of("axe");
    }

    @Override
    public boolean onLeftClickEntity(ItemStack itemStack, EntityPlayer entityPlayer, Entity entity) {
        if (entity instanceof EntitySlime) {
            entity.setDead();
        }
        return false;
    }
}
