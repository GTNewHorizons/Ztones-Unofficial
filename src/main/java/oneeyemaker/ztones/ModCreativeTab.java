package oneeyemaker.ztones;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import oneeyemaker.ztones.blocks.ModBlocks;

public class ModCreativeTab extends CreativeTabs {

    public ModCreativeTab() {
        super(String.format("%s.creativeTab", Tags.MODID));
    }

    @Override
    public Item getTabIconItem() {
        Block block = ZtoneType.Tile.isEnabled() ? ModBlocks.getBlock(ZtoneType.Tile) : Blocks.double_stone_slab;
        return Item.getItemFromBlock(block);
    }
}
