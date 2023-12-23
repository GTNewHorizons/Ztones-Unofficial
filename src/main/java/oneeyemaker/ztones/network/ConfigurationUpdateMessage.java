package oneeyemaker.ztones.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class ConfigurationUpdateMessage implements IMessage {

    private boolean isVariantCyclingEnabled;

    public ConfigurationUpdateMessage() {
        this.isVariantCyclingEnabled = true;
    }

    public ConfigurationUpdateMessage(boolean isVariantCyclingEnabled) {
        this.isVariantCyclingEnabled = isVariantCyclingEnabled;
    }

    public boolean isVariantCyclingEnabled() {
        return this.isVariantCyclingEnabled;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.isVariantCyclingEnabled = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.isVariantCyclingEnabled);
    }
}
