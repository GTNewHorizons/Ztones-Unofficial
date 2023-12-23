package oneeyemaker.ztones.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import oneeyemaker.ztones.Tags;
import oneeyemaker.ztones.Ztones;

public class MiniFuelItem extends Item {

    private final String name;

    public MiniFuelItem(String name) {
        super();
        this.name = name;
        this.setMaxDamage(0);
        this.setHasSubtypes(false);
        this.setCreativeTab(Ztones.creativeTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(String.format("%s:mini_%s", Tags.MODID, this.name.toLowerCase()));
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("%s.mini%s", Tags.MODID, this.name);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return this.getUnlocalizedName();
    }
}
