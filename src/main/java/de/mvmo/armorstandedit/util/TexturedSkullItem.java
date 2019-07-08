package de.mvmo.armorstandedit.util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TexturedSkullItem {

    public static ItemStack create(String value, String signature) {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);

        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
        gameProfile.getProperties().put("textures", new Property("textures", value, signature));


        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, gameProfile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException exception) {
            exception.printStackTrace();
        }

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public static ItemStack create(String value) {
        return create(value, "Vx9t+dbc7Ok4nrXIiXtLTFYYTxUqyCobbrxauq/jUZ2cKuSkedF0BQ+S+SJ5GnBfO8qoAT5U1D62eVjVXFoNi0MU2LEeucm8bxAmX+Ozy6kggWyMSy+nFeiyNTQL6cztDQNsKPoJOwC3dGuHNXlmfiHVPOISvG/xddQhALWbQRrQhXYfGK2RxHzF+QD1on5a02VZr/uoAw9oEKkRqCTFlqcwmtVhsooNNjYpUdJxHcVUbAaCrMuTDCvvJswiezdvDf9cDBHiKPwrh34yZSUqUTGAbrYvn3MDnUnANb6e6cFkLGgAySZy/Sn+mXwMiEO0fedY/cMDD2/YGZeWzNr7CH00+W5REewYbdRKHQfHc9nQ42ggfL5Cu+sF7MIfpbDC05jJpkAWHg86+4FXfhjLEnbSAqznmHypY4LiRrFPFRNeWhOogrgmMHzOc3k9cQ1yZ8McDeC3j09q/j/f/HMekbaT5R6ik3ejHrhJ5ByVYLnKOl0Yx+G7sPeTvFdC2hDCvENOms79mX+ENKkmfANtyc4SQ4uWmb8Ex1t9Eex5+sNrXKLAu7B5v6yV1d50CxlR/hrjDSf30oWYP48IFC0MIX6tfNYWdg4TQwQmOoZOrzNH/PtGwJAed/PQYKHRxyx2Ek9k1Td9C6H/iYyGvBzmji5l/OQZIo9RP/3ZukKHEBE=");
    }

}
