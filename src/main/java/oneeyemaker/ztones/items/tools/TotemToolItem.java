package oneeyemaker.ztones.items.tools;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import oneeyemaker.ztones.Tags;
import oneeyemaker.ztones.Ztones;
import oneeyemaker.ztones.blocks.ModBlocks;

public class TotemToolItem extends Item {

    private static final HashMap<Block, Block> replacements = new HashMap<>();
    static {
        replacements.put(Blocks.grass, ModBlocks.cleanDirt);
        replacements.put(Blocks.dirt, ModBlocks.cleanDirt);
        replacements.put(ModBlocks.cleanDirt, Blocks.dirt);
        replacements.put(Blocks.coal_ore, Blocks.coal_block);
        replacements.put(Blocks.iron_ore, Blocks.iron_block);
        replacements.put(Blocks.gold_ore, Blocks.gold_block);
        replacements.put(Blocks.redstone_ore, Blocks.redstone_block);
        replacements.put(Blocks.lit_redstone_ore, Blocks.redstone_block);
        replacements.put(Blocks.lapis_ore, Blocks.lapis_block);
        replacements.put(Blocks.diamond_ore, Blocks.diamond_block);
        replacements.put(Blocks.emerald_ore, Blocks.emerald_block);
        replacements.put(Blocks.quartz_ore, Blocks.quartz_block);
    }

    public TotemToolItem() {
        super();
        this.setMaxStackSize(1);
        this.setMaxDamage(360);
        this.setCreativeTab(Ztones.creativeTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(String.format("%s:totem_tool", Tags.MODID));
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("%s.totemTool", Tags.MODID);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return this.getUnlocalizedName();
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side,
        float subX, float subY, float subZ) {
        if (side == ForgeDirection.DOWN.ordinal()) {
            return false;
        }
        Block block = world.getBlock(x, y, z);
        Block replacement = replacements.get(block);
        if (replacement == null) {
            return false;
        }
        world.playSoundEffect(
            x + 0.5,
            y + 0.5,
            z + 0.5,
            "random.successful_hit",
            1.0f,
            world.rand.nextFloat() * 0.4f + 0.8f);
        if (!world.isRemote) {
            world.setBlock(x, y, z, replacement);
            itemStack.damageItem(1, entityPlayer);
        }
        return true;
    }
}
