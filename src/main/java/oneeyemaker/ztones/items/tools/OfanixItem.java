package oneeyemaker.ztones.items.tools;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import oneeyemaker.ztones.Tags;
import oneeyemaker.ztones.Ztones;
import oneeyemaker.ztones.gui.ModGui;

public class OfanixItem extends Item {

    public OfanixItem() {
        super();
        this.setMaxStackSize(1);
        this.setContainerItem(this);
        this.setCreativeTab(Ztones.creativeTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(String.format("%s:ofanix", Tags.MODID));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean flag) {
        if (GuiScreen.isShiftKeyDown()) {
            list.add(StatCollector.translateToLocal(String.format("%s.ofanix.tooltip", Tags.MODID)));
            list.add(StatCollector.translateToLocal(String.format("%s.ofanix.sneak.tooltip", Tags.MODID)));
        } else {
            list.add(
                EnumChatFormatting.ITALIC
                    + StatCollector.translateToLocal(String.format("%s.ofanix.show.tooltip", Tags.MODID)));
        }
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("%s.ofanix", Tags.MODID);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return this.getUnlocalizedName();
    }

    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack) {
        return false;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side,
        float subX, float subY, float subZ) {
        ForgeDirection direction = ForgeDirection.getOrientation(side);
        if (direction == ForgeDirection.UNKNOWN) {
            return false;
        }
        x += direction.offsetX;
        y += direction.offsetY;
        z += direction.offsetZ;
        if (entityPlayer.canPlayerEdit(x, y, z, side, itemStack)) {
            if (world.isAirBlock(x, y, z)) {
                world.spawnParticle("portal", entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, 0.0, 0.0, 0.0);
                world.playSoundEffect(
                    x + 0.5,
                    y + 0.5,
                    z + 0.5,
                    "mob.endermen.portal",
                    1.0f,
                    world.rand.nextFloat() * 0.4f + 0.8f);
                if (!world.isRemote) {
                    world.setBlock(x, y, z, Blocks.cobblestone);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (entityPlayer.isSneaking()) {
            entityPlayer.openGui(Tags.MODID, ModGui.OFANIX_GUI_ID, world, 0, 0, 0);
        }
        return itemStack;
    }
}
