package oneeyemaker.ztones.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class ZtoneCycleMessage implements IMessage {

    private short slot;
    private boolean shouldCycleForward;

    public ZtoneCycleMessage() {
        this.slot = -1;
        this.shouldCycleForward = false;
    }

    public ZtoneCycleMessage(short slot, boolean shouldCycleForward) {
        this.slot = slot;
        this.shouldCycleForward = shouldCycleForward;
    }

    public short getExpectedSlot() {
        return this.slot;
    }

    public int getOffset() {
        return this.shouldCycleForward ? 1 : -1;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.slot = buf.readShort();
        this.shouldCycleForward = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeShort(this.slot);
        buf.writeBoolean(this.shouldCycleForward);
    }
}
