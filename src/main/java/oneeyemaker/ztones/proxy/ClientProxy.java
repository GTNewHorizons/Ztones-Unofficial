package oneeyemaker.ztones.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public World getClientWorld() {
        return Minecraft.getMinecraft().theWorld;
    }
}
