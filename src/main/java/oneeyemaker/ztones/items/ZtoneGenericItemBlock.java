package oneeyemaker.ztones.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import oneeyemaker.ztones.ModConfiguration;
import oneeyemaker.ztones.Tags;

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

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean flag) {
        if (!ModConfiguration.isCreatureSpawnOnZtonesEnabled) {
            list.add(
                EnumChatFormatting.ITALIC
                    + StatCollector.translateToLocal(String.format("%s.disabledCreatureSpawn.tooltip", Tags.MODID)));
        }
    }
}
