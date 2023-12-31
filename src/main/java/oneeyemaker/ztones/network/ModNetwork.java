package oneeyemaker.ztones.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import oneeyemaker.ztones.ModConfiguration;
import oneeyemaker.ztones.Tags;

public class ModNetwork {

    private static final SimpleNetworkWrapper WRAPPER = new SimpleNetworkWrapper(Tags.MODID);

    public static void registerMessages() {
        WRAPPER.registerMessage(ZtoneCycleHandler.INSTANCE, ZtoneCycleMessage.class, 0, Side.SERVER);
        WRAPPER.registerMessage(ConfigurationUpdateHandler.INSTANCE, ConfigurationUpdateMessage.class, 1, Side.CLIENT);
    }

    public static void cycleZtone(EntityPlayer entityPlayer, boolean shouldCycleForward) {
        WRAPPER.sendToServer(new ZtoneCycleMessage((short) entityPlayer.inventory.currentItem, shouldCycleForward));
    }

    public static void updateConfiguration(EntityPlayerMP entityPlayer) {
        WRAPPER.sendTo(new ConfigurationUpdateMessage(ModConfiguration.isVariantCyclingEnabled), entityPlayer);
    }
}
