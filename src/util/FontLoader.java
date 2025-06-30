/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.Font;
import java.io.InputStream;

/**
 *
 * @author salaz
 */
public class FontLoader {
    private final static Map<String, Font> fuentes = new LinkedHashMap<>();
    
    public static Font load(String name, int style, int size) {
        try {
            if(fuentes.containsKey(name)) {
                return fuentes.get(name).deriveFont(style, size);
            }
            InputStream inputStream = FontLoader.class.getResourceAsStream("/resources/fonts/" + name);
            fuentes.put(name, Font.createFont(Font.PLAIN, inputStream));
            return fuentes.get(name).deriveFont(style, size);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }
    
    public static Font load(String name, int size) {
        try {
            if(fuentes.containsKey(name)) {
                return fuentes.get(name).deriveFont(Font.PLAIN, size);
            }
            InputStream inputStream = FontLoader.class.getResourceAsStream("/resources/fonts/" + name);
            fuentes.put(name, Font.createFont(Font.PLAIN, inputStream));
            return fuentes.get(name).deriveFont(Font.PLAIN, size);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }
    
    public static Font load(String name) {
        try {
            if(fuentes.containsKey(name)) {
                return fuentes.get(name).deriveFont(Font.PLAIN, 14);
            }
            InputStream inputStream = FontLoader.class.getResourceAsStream("/resources/fonts/" + name);
            fuentes.put(name, Font.createFont(Font.PLAIN, inputStream));
            return fuentes.get(name).deriveFont(Font.PLAIN, 14);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }  
}
