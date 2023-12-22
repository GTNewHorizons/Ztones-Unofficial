package oneeyemaker.ztones.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import oneeyemaker.ztones.ModConfiguration;
import oneeyemaker.ztones.Tags;

public class SimpleItemBlock extends ItemBlock {

    public SimpleItemBlock(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(false);
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
