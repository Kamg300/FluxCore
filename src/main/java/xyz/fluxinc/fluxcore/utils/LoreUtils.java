package xyz.fluxinc.fluxcore.utils;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoreUtils {

    /**
     * Appends a string to the end of an items lore
     * @param item The item that you want to add the line to
     * @param lore The string you with to append
     * @return The item with the lore appended
     */
    public static ItemStack addLore(ItemStack item, String lore){
        ItemMeta iMeta = item.getItemMeta();
        List<String> currentLore = Objects.requireNonNull(iMeta).getLore();
        if (currentLore == null) { currentLore = new ArrayList<>(); }
        currentLore.add(lore);
        iMeta.setLore(currentLore);
        item.setItemMeta(iMeta);
        return item;
    }

    /**
     * Get a string that would be invisible when inside the lore
     * @param lore The string you with to be hidden
     * @return The resulting string
     */
    public static String generateInvisibleLore(String lore) {
        StringBuilder hidden = new StringBuilder();
        for (char c : lore.toCharArray()) { hidden.append(ChatColor.COLOR_CHAR + "").append(c); }
        return hidden.toString();
    }

    /**
     * Converts an invisible string to a normal string
     * @param lore the invisible string
     * @return The resulting string
     */
    public static String getInvisibleLore(String lore) { return lore.replaceAll(ChatColor.COLOR_CHAR + "", ""); }


    /**
     * Check to see if a set of lore has a specific invisible string
     * @param itemLore The lore to check for the string
     * @param requestedString The string you are looking for
     * @return Whether the lore contains the string or not
     */
    public static boolean hasInvisibleLore(List<String> itemLore, String requestedString) {
        for (String lore : itemLore) {
            lore = lore.replaceAll(ChatColor.COLOR_CHAR + "", "");
            if (lore == requestedString) { return true; }
        }
        return false;
    }

    /**
     * A convenient wrapper for hasInvisibleLore(List, String) to extract lore from an ItemStack
     * @param item The item you wish to get the lore from
     * @param requestedString The string you are looking for
     * @return Whether the lore contains the string or not
     */
    public static boolean hasInvisibleLore(ItemStack item, String requestedString) {
        ItemMeta iMeta = item.getItemMeta();
        if (iMeta == null) { return false; }
        List<String> lore = iMeta.getLore();
        return lore != null && hasInvisibleLore(lore, requestedString);
    }
}