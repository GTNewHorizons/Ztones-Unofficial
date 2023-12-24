package oneeyemaker.ztones.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.IGuiHandler;

public class ModGui implements IGuiHandler {

    public static final int OFANIX_GUI_ID = 0;

    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        if (id == OFANIX_GUI_ID) {
            return new ContainerOfanix(entityPlayer.inventory, world, x, y, z);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        if (id == OFANIX_GUI_ID) {
            return new GuiOfanix(entityPlayer.inventory, world, x, y, z);
        }
        return null;
    }
}
