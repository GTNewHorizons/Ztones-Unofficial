package oneeyemaker.ztones.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ZtoneGenericItemBlock extends ItemBlock {

    private final Block block;

    public ZtoneGenericItemBlock(Block block) {
        super(block);
        this.block = block;
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int metadata) {
        return this.block.getIcon(ForgeDirection.UP.ordinal(), metadata);
    }

    @Override
    public int getMetadata(int metadata) {
        return metadata;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        int metadata = itemStack.getItemDamage();
        return String.format("%s.%d", this.block.getUnlocalizedName(), metadata);
    }
}
