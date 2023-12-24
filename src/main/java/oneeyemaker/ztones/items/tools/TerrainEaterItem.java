package oneeyemaker.ztones.items.tools;

import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.common.util.ForgeDirection;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import oneeyemaker.ztones.Tags;
import oneeyemaker.ztones.Ztones;

public class TerrainEaterItem extends ItemTool {

    public static final ToolMaterial WORLD_SPIRIT = EnumHelper
        .addToolMaterial("worldSpirit", 3, 15000, 25.0f, 2.0f, 32);
    private static final Set<Block> effectiveAgainstBlocks = Sets.newHashSet(
        Blocks.wooden_door,
        Blocks.trapdoor,
        Blocks.crafting_table,
        Blocks.grass,
        Blocks.dirt,
        Blocks.sand,
        Blocks.gravel,
        Blocks.snow_layer,
        Blocks.snow,
        Blocks.clay,
        Blocks.farmland,
        Blocks.soul_sand,
        Blocks.mycelium,
        Blocks.cobblestone,
        Blocks.double_stone_slab,
        Blocks.stone_slab,
        Blocks.stone,
        Blocks.sandstone,
        Blocks.mossy_cobblestone,
        Blocks.iron_ore,
        Blocks.iron_block,
        Blocks.coal_ore,
        Blocks.gold_block,
        Blocks.gold_ore,
        Blocks.diamond_ore,
        Blocks.diamond_block,
        Blocks.ice,
        Blocks.netherrack,
        Blocks.lapis_ore,
        Blocks.lapis_block,
        Blocks.redstone_ore,
        Blocks.lit_redstone_ore,
        Blocks.rail,
        Blocks.detector_rail,
        Blocks.golden_rail,
        Blocks.activator_rail,
        Blocks.planks,
        Blocks.bookshelf,
        Blocks.log,
        Blocks.log2,
        Blocks.chest,
        Blocks.pumpkin,
        Blocks.lit_pumpkin,
        Blocks.wool,
        Blocks.leaves,
        Blocks.leaves2,
        Blocks.fence,
        Blocks.web);

    public TerrainEaterItem() {
        super(2.0f, WORLD_SPIRIT, effectiveAgainstBlocks);
        this.setMaxStackSize(1);
        this.setCreativeTab(Ztones.creativeTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(String.format("%s:terrain_eater", Tags.MODID));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean flag) {
        list.add(StatCollector.translateToLocal(String.format("%s.terrainEater.tooltip", Tags.MODID)));
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("%s.terrainEater", Tags.MODID);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return this.getUnlocalizedName();
    }

    @Override
    public boolean canHarvestBlock(Block block, ItemStack itemStack) {
        return Items.diamond_pickaxe.canHarvestBlock(block, itemStack);
    }

    @Override
    public float func_150893_a(ItemStack itemStack, Block block) {
        Material material = block.getMaterial();
        return material == Material.rock || material == Material.iron || material == Material.anvil
            ? this.efficiencyOnProperMaterial
            : super.func_150893_a(itemStack, block);
    }

    @Override
    public Set<String> getToolClasses(ItemStack itemStack) {
        return ImmutableSet.of("pickaxe", "shovel", "axe", "shear");
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
            if (world.isAirBlock(x, y, z) && Blocks.torch.canPlaceBlockAt(world, x, y, z)) {
                world.playSoundEffect(
                    x + 0.5,
                    y + 0.5,
                    z + 0.5,
                    "random.burp",
                    1.0f,
                    world.rand.nextFloat() * 0.4f + 0.8f);
                if (!world.isRemote) {
                    world.setBlock(x, y, z, Blocks.torch);
                    itemStack.damageItem(1, entityPlayer);
                }
                return true;
            }
        }
        return false;
    }
}
