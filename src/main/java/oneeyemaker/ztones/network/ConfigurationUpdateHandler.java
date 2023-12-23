package oneeyemaker.ztones.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import oneeyemaker.ztones.Ztones;

public class ConfigurationUpdateHandler implements IMessageHandler<ConfigurationUpdateMessage, IMessage> {

    public static final ConfigurationUpdateHandler INSTANCE = new ConfigurationUpdateHandler();

    private ConfigurationUpdateHandler() {}

    @Override
    public IMessage onMessage(ConfigurationUpdateMessage message, MessageContext context) {
        Ztones.proxy.setVariantCyclingEnabled(message.isVariantCyclingEnabled());
        return null;
    }
}
