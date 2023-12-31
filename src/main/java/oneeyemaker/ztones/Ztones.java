package oneeyemaker.ztones;

import net.minecraft.entity.player.EntityPlayerMP;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import oneeyemaker.ztones.network.ModNetwork;
import oneeyemaker.ztones.proxy.CommonProxy;

@Mod(
    modid = Tags.MODID,
    version = Tags.VERSION,
    name = Tags.MODNAME,
    acceptedMinecraftVersions = "[1.7.10]",
    dependencies = "after:chisel")
public class Ztones {

    public static final Logger LOG = LogManager.getLogger(Tags.MODID);

    @SidedProxy(
        clientSide = "oneeyemaker.ztones.proxy.ClientProxy",
        serverSide = "oneeyemaker.ztones.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static ModCreativeTab creativeTab = new ModCreativeTab();

    @Mod.EventHandler
    public void preInitialize(FMLPreInitializationEvent event) {
        FMLCommonHandler.instance()
            .bus()
            .register(this);
        proxy.preInitialize(event);
    }

    @Mod.EventHandler
    public void initialize(FMLInitializationEvent event) {
        proxy.initialize(event);
    }

    @Mod.EventHandler
    public void postInitialize(FMLPostInitializationEvent event) {
        proxy.postInitialize(event);
    }

    @SubscribeEvent
    public void updateConfiguration(PlayerEvent.PlayerLoggedInEvent event) {
        ModNetwork.updateConfiguration((EntityPlayerMP) event.player);
    }
}
