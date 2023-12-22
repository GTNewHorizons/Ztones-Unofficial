package oneeyemaker.ztones.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import oneeyemaker.ztones.ModConfiguration;
import oneeyemaker.ztones.ZtoneType;
import oneeyemaker.ztones.items.ZtoneGenericItemBlock;

public class ZtoneCycleHandler implements IMessageHandler<ZtoneCycleMessage, IMessage> {

    public static final ZtoneCycleHandler INSTANCE = new ZtoneCycleHandler();

    private ZtoneCycleHandler() {}

    @Override
    public IMessage onMessage(ZtoneCycleMessage message, MessageContext context) {
        if (!ModConfiguration.isVariantCyclingEnabled) {
            return null;
        }
        EntityPlayer entityPlayer = context.getServerHandler().playerEntity;
        if (entityPlayer.inventory.currentItem == message.getExpectedSlot()) {
            ItemStack itemStack = entityPlayer.getHeldItem();
            if (itemStack != null && itemStack.getItem() instanceof ZtoneGenericItemBlock) {
                int metadata = (itemStack.getItemDamage() + ZtoneType.Variants + message.getOffset())
                    % ZtoneType.Variants;
                itemStack.setItemDamage(metadata);
            }
        }
        return null;
    }
}
