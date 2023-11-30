package com.sekwah.advancedportals.bukkit;

import com.sekwah.advancedportals.bukkit.config.ConfigAccessor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class PluginMessages {
    private static String WARP_MESSAGE;
    public boolean useCustomPrefix = false;
    public static String customPrefix = "\u00A7a[\u00A7eAdvancedPortals\u00A7a]";
    public static String customPrefixFail = "\u00A7c[\u00A77AdvancedPortals\u00A7c]";

    public PluginMessages (AdvancedPortalsPlugin plugin) {
        ConfigAccessor config = new ConfigAccessor(plugin, "config.yml");
        this.useCustomPrefix = config.getConfig().getBoolean("UseCustomPrefix");

        MiniMessage mm = MiniMessage.miniMessage();

        if (useCustomPrefix) {

            PluginMessages.customPrefix = ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacySection().serialize(mm.deserialize(config.getConfig().getString("CustomPrefix"))));

            PluginMessages.customPrefixFail = ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacySection().serialize(mm.deserialize(config.getConfig().getString("CustomPrefixFail"))));
        }

        WARP_MESSAGE = ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacySection().serialize(mm.deserialize(config.getConfig().getString("WarpMessage", "<green>You have warped to <yellow><warp>"))));
        System.out.println(WARP_MESSAGE);
    }

    // This class is so then the common messages in commands or just messages over the commands are the same and can be
    //  easily changed.

    public static String getWarpMessage(String warp) {
        String cleanedWarp = warp.replace("_", " ");
        return WARP_MESSAGE.replace("<warp>", cleanedWarp);
    }

    public static void UnknownCommand(CommandSender sender, String command) {
        sender.sendMessage(customPrefixFail + " You need to type something after /" + command + "\n");
        sender.sendMessage("\u00A7cIf you do not know what you can put or would like some help with the commands please type \u00A7e" + '"' + "\u00A7e/" + command + " help" + '"' + "\u00A7c\n");
    }

    public static void NoPermission(CommandSender sender, String command) {
        sender.sendMessage(customPrefixFail + " You do not have permission to perform that command!");
    }
}
