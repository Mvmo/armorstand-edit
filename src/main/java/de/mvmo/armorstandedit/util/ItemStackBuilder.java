package de.mvmo.armorstandedit.util;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;

/**
 * I just copied this class from a old repository of mine from github (i think it is a private repo) maybe redo this shit some day
 */
public class ItemStackBuilder {

    private ItemStack itemStack;

    public ItemStackBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStackBuilder(Material itemMaterial, Integer itemAmount, byte subId) {
        this.itemStack = new ItemStack(itemMaterial, itemAmount, subId);
    }

    public ItemStackBuilder(Material itemMaterial) {
        this.itemStack = new ItemStack(itemMaterial);
    }

    public ItemStackBuilder(Material itemMaterial, Integer itemAmount) {
        this.itemStack = new ItemStack(itemMaterial, itemAmount);
    }

    @Deprecated
    public ItemStackBuilder(Integer itemId, Integer itemAmount, byte subId) {
        this.itemStack = new ItemStack(itemId, itemAmount, subId);
    }

    public ItemStackBuilder clone() {
        return new ItemStackBuilder(itemStack);
    }

    public ItemStackBuilder setDisplayName(String displayName) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemStackBuilder setLore(List<String> itemLore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemStackBuilder setLore(String... itemLore) {
        setLore(Arrays.asList(itemLore));
        return this;
    }

    public ItemStackBuilder clearItemLore() {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getLore().clear();
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemStackBuilder removeLoreLine(String line) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.getLore();

        if (!lore.contains(line))
            return this;

        lore.remove(line);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemStackBuilder addUnsafeEnchantment(Enchantment enchantment, Integer level) {
        itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemStackBuilder removeEnchantment(Enchantment enchantment) {
        itemStack.removeEnchantment(enchantment);
        return this;
    }

    public ItemStackBuilder setSubId(byte subId) {
        itemStack.setDurability(subId);
        return this;
    }

    public ItemStackBuilder setDyeColor(DyeColor color) {
        itemStack.setDurability(color.getData());
        return this;
    }

    public ItemStackBuilder addItemFlags(ItemFlag... itemFlags) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(itemFlags);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemStackBuilder removeItemFlags(ItemFlag... itemFlags) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.removeItemFlags(itemFlags);
        itemStack.setItemMeta(itemMeta);
        return this;
    }


    @Deprecated
    public ItemStackBuilder setWoolColor(DyeColor color) {

        if (!itemStack.getType().equals(Material.WOOL))
            return this;

        itemStack.setDurability(color.getData());
        return this;
    }

    public ItemStackBuilder setLeatherArmorColor(Color color) {
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
        leatherArmorMeta.setColor(color);
        itemStack.setItemMeta(leatherArmorMeta);
        return this;
    }

    public ItemStackBuilder setSkullOwner(String skullOwner) {
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwner(skullOwner);
        itemStack.setItemMeta(skullMeta);
        return this;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public ItemStack toItemStack() {
        return itemStack;
    }

    public ItemStack build() {
        return itemStack;
    }

    public ItemStack buildItemStack() {
        return itemStack;
    }

}
