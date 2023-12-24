package oneeyemaker.ztones.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.StatCollector;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import oneeyemaker.ztones.Tags;
import oneeyemaker.ztones.Ztones;

public class HungerPillItem extends ItemFood {

    public HungerPillItem() {
        super(0, 0.0f, true);
        this.setAlwaysEdible();
        this.setPotionEffect(Potion.hunger.id, 7, 40, 1.0f);
        this.setCreativeTab(Ztones.creativeTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(String.format("%s:hunger", Tags.MODID));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean flag) {
        list.add(StatCollector.translateToLocal(String.format("%s.hungerPill.tooltip", Tags.MODID)));
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("%s.hungerPill", Tags.MODID);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return this.getUnlocalizedName();
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack) {
        return 16;
    }
}
