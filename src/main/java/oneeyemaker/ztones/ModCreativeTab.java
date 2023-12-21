package oneeyemaker.ztones;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import oneeyemaker.ztones.blocks.ModBlocks;

public class ModCreativeTab extends CreativeTabs {

    private static final int ICON_UPDATE_INTERVAL = 80;
    private long lastUpdateTime = Long.MIN_VALUE;
    private int currentMetadata = 0;

    public ModCreativeTab() {
        super(String.format("%s.creativeTab", Tags.MODID));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        Block block = ZtoneType.Tile.isEnabled() ? ModBlocks.getBlock(ZtoneType.Tile) : Blocks.double_stone_slab;
        return Item.getItemFromBlock(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {
        if (ZtoneType.Tile.isEnabled()) {
            updateMetadata();
            return new ItemStack(ModBlocks.getBlock(ZtoneType.Tile), 1, currentMetadata);
        }
        return new ItemStack(Blocks.double_stone_slab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel() {
        return this.getTabLabel();
    }

    private void updateMetadata() {
        World world = Ztones.proxy.getClientWorld();
        if (world == null) {
            return;
        }
        long currentTime = world.getTotalWorldTime();
        if (currentTime < this.lastUpdateTime) {
            this.lastUpdateTime = currentTime;
        } else if (currentTime >= this.lastUpdateTime + ICON_UPDATE_INTERVAL) {
            this.lastUpdateTime = currentTime;
            this.currentMetadata = world.rand.nextInt(ZtoneType.Variants);
        }
    }
}
