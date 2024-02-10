package nl.alphaport.messagingapp.dto.enums;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author: Abolfazl Shahrad Shahri (abolfazl2112@gmail.com)
 * 2024-01-30   10:55 AM
 */
public enum ShopEngineName {
    WOOCOMMERCE("woocommerce"),
    PRESTASHOP("prestashop");

    private String name;

    ShopEngineName(String name){
        this.name = name;
    }

    public static ShopEngineName getByName(String name){
        return Objects.isNull(name)? null: Arrays.stream(values()).filter(a -> a.name.equals(name)).findFirst().get();
    }
}
