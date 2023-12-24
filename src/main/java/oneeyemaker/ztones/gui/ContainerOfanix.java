package oneeyemaker.ztones.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.world.World;

public class ContainerOfanix extends ContainerWorkbench {

    public ContainerOfanix(InventoryPlayer playerInventory, World world, int x, int y, int z) {
        super(playerInventory, world, x, y, z);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}
