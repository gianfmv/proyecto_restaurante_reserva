/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.*;
import modelo.Ingrediente;
import modelo.Novedad;
import modelo.Plato;
import modelo.Promocion;

public class BDLocal {

    private List<Plato> entradas;
    private List<Plato> fondos;
    private List<Plato> postres;
    private List<Plato> bebidas;
    private List<Promocion> promociones;
    private List<Novedad> novedades;

    public BDLocal() {
        this.entradas = new ArrayList<>();
        this.fondos = new ArrayList<>();
        this.postres = new ArrayList<>();
        this.bebidas = new ArrayList<>();
        this.promociones = new ArrayList<>();
        this.novedades = new ArrayList<>();
        cargarDatos();
    }
    
    public Plato getFeatured() {
        return fondos.get(0);
    }

    private void cargarDatos() {
        Ingrediente limon = new Ingrediente(1, "Limón", false);
        Ingrediente cebolla = new Ingrediente(2, "Cebolla", false);
        Ingrediente cordero = new Ingrediente(2, "Cordero", false);
        Ingrediente frejoles = new Ingrediente(2, "Frejoles", false);
        Ingrediente pescado = new Ingrediente(3, "Pescado", true);
        Ingrediente choclo = new Ingrediente(4, "Choclo", false);
        Ingrediente arroz = new Ingrediente(5, "Arroz", false);
        Ingrediente pollo = new Ingrediente(6, "Pollo", false);
        Ingrediente papa = new Ingrediente(7, "Papa", false);
        Ingrediente leche = new Ingrediente(8, "Leche", true);
        Ingrediente azucar = new Ingrediente(9, "Azúcar", false);
        Ingrediente canela = new Ingrediente(10, "Canela", false);
        Ingrediente hielo = new Ingrediente(11, "Hielo", false);
        Ingrediente camote = new Ingrediente(12, "Camote", false);
        Ingrediente mariscos = new Ingrediente(13, "Mariscos", true);
        Ingrediente quinoa = new Ingrediente(14, "Quinua", false);
        Ingrediente palta = new Ingrediente(15, "Palta", false);
        Ingrediente aji = new Ingrediente(15, "Ají Amarillo", false);
        Ingrediente mate = new Ingrediente(15, "Mate de Coca", false);
        // ENTRADAS
//        entradas.add(new Plato(1, "Ceviche", "Pescado marinado en limón con cebolla y ají", 25.0,
//                              "https://cdn0.recetasgratis.net/es/posts/7/4/1/ceviche_peruano_18147_600_square.jpg"));
//        entradas.add(new Plato(2, "Causa Limeña", "Puré de papa amarilla relleno", 20.0,
//                              "https://acomer.pe/wp-content/uploads/2018/02/causalimeniathumb-1.jpg"));
//        entradas.add(new Plato(3, "Tiradito", "Láminas de pescado con salsa de ají amarillo", 27.0,
//                              "https://cdn0.recetasgratis.net/es/posts/9/3/6/tiradito_de_pejerrey_77639_orig.jpg"));
//        entradas.add(new Plato(4, "Papa a la Huancaína", "Papa sancochada con salsa cremosa de ají", 18.0,
//                              "https://i.ytimg.com/vi/S5Ac-eaWfUU/maxresdefault.jpg"));
//        // FONDOS
//        fondos.add(new Plato(5, "Lomo Saltado", "Carne salteada con verduras y papas", 30.0, "https://www.recetasnestle.com.pe/sites/default/files/srh_recipes/d0bd5fbfcbb5231c896b8d33ff171a29.png"));
//        fondos.add(new Plato(6, "Ají de Gallina", "Guiso cremoso de pollo con ají", 28.0, "https://cdn.apartmenttherapy.info/image/upload/f_jpg,q_auto:eco,c_fill,g_auto,w_1500,ar_4:3/k%2FPhoto%2FRecipes%2F2024-04-aji-de-gallina%2Faji-de-gallina-254"));
//        fondos.add(new Plato(7, "Arroz con Mariscos", "Arroz salteado con una mezcla de mariscos", 35.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSVFi5GZzm4u56E3Cefr0K9YXJf7FisZK21Mw&s"));
//        fondos.add(new Plato(8, "Seco de Cordero", "Cordero guisado con cilantro y acompañado de arroz y frijoles", 32.0, "https://img-global.cpcdn.com/recipes/a902c5a2d5b7146b/680x482f0.501388_0.5_1.0q90/seco-de-res-cordero-criollo-foto-principal.jpg"));
//
//        // POSTRES
//        postres.add(new Plato(9, "Mazamorra Morada", "Postre de maíz morado y frutas", 10.0, "https://perudelights.com/wp-content/uploads/2012/07/DSC05814.jpg"));
//        postres.add(new Plato(10, "Arroz con Leche", "Arroz cocido en leche con canela", 10.0, "https://healthysimpleyum.com/wp-content/uploads/2022/12/Vegan-Arroz-con-Leche5-758x1024-1.jpg"));
//        postres.add(new Plato(11, "Suspiro a la Limeña", "Crema dulce de leche con merengue", 12.0, "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ed/Suspiro_lime%C3%B1o.jpg/1200px-Suspiro_lime%C3%B1o.jpg"));
//        postres.add(new Plato(12, "Picarones", "Buñuelos dulces de camote y zapallo con miel", 11.0, "https://www.perudelights.com/wp-content/uploads/2013/01/Picarones.jpg"));
//
//        // BEBIDAS
//        bebidas.add(new Plato(13, "Chicha Morada", "Bebida de maíz morado con piña y canela", 8.0, "https://www.lovferments.com/wp-content/uploads/2021/04/beb_chicha.jpg"));
//        bebidas.add(new Plato(14, "Limonada", "Bebida refrescante de limón", 7.0, "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/Lynchburg_Lemonade%2C_Grindhouse_Killer_Burgers%2C_Atlanta_GA.jpg/1200px-Lynchburg_Lemonade%2C_Grindhouse_Killer_Burgers%2C_Atlanta_GA.jpg"));
//        bebidas.add(new Plato(15, "Inka Kola", "Refresco gaseoso sabor a hierba luisa", 6.0, "https://preview.redd.it/productos-inca-kola-v0-jm7yocmetfae1.jpg?width=1000&format=pjpg&auto=webp&s=abc9b5517824e9ae1eba1b1fac5d6dc12af55cda"));
//        bebidas.add(new Plato(16, "Mate de Coca", "Infusión tradicional de hojas de coca", 5.0, "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c4/Mate_de_coca_boliviano.jpg/1200px-Mate_de_coca_boliviano.jpg"));
//        // Asignar ingredientes a cada menú
//        for (Plato m : entradas) {
//            switch (m.getIdMenu()) {
//                case 1 -> m.getIngredientes().addAll(Arrays.asList(pescado, limon, cebolla));
//                case 2 -> m.getIngredientes().addAll(Arrays.asList(papa, pollo, limon));
//                case 3 -> m.getIngredientes().addAll(Arrays.asList(pescado, aji)); 
//                case 4 -> m.getIngredientes().addAll(Arrays.asList(papa, leche, canela));
//            }
//        }
//        for (Plato m : fondos) {
//            switch (m.getIdMenu()) {
//                case 5 -> m.getIngredientes().addAll(Arrays.asList(papa, cebolla, arroz));
//                case 6 -> m.getIngredientes().addAll(Arrays.asList(pollo, leche, arroz));
//                case 7 -> m.getIngredientes().addAll(Arrays.asList(arroz, mariscos, cebolla));
//                case 8 -> m.getIngredientes().addAll(Arrays.asList(cordero, frejoles, arroz)); // define cordero, frijoles
//            }
//        }
//        for (Plato m : postres) {
//            switch (m.getIdMenu()) {
//                case 9, 10 -> m.getIngredientes().addAll(Arrays.asList(azucar, canela));
//                case 11 -> m.getIngredientes().addAll(Arrays.asList(leche, azucar));
//                case 12 -> m.getIngredientes().addAll(Arrays.asList(camote, azucar, canela));
//            }
//        }
//        for (Plato m : bebidas) {
//            switch (m.getIdMenu()) {
//                case 13, 14 -> m.getIngredientes().addAll(Arrays.asList(azucar, canela, limon));
//                case 15 -> m.getIngredientes().addAll(Arrays.asList(leche, azucar));
//                case 16 -> m.getIngredientes().addAll(Arrays.asList(mate)); // define variable leaves
//            }
//        }
        
                Calendar cal = Calendar.getInstance();

        // Promoción 1: Ceviche Fest
        cal.set(2025, Calendar.JULY, 1);
        Date inicio1 = cal.getTime();
        cal.set(2025, Calendar.JULY, 7);
        Date fin1 = cal.getTime();
        promociones.add(new Promocion(
            1,
            "Ceviche Fest",
            "¡Disfruta de 20% de descuento en todos los ceviches!",
            inicio1,
            fin1,
            20.0,
            "https://example.com/images/ceviche.jpg"
        ));

        // Promoción 2: Semana Criolla
        cal.set(2025, Calendar.JULY, 15);
        Date inicio2 = cal.getTime();
        cal.set(2025, Calendar.JULY, 21);
        Date fin2 = cal.getTime();
        promociones.add(new Promocion(
            2,
            "Semana Criolla",
            "Platos criollos con hasta 25% de descuento toda la semana.",
            inicio2,
            fin2,
            25.0,
            "https://example.com/images/lomo-saltado.jpg"
        ));

        // Promoción 3: Día del Anticucho
        cal.set(2025, Calendar.AUGUST, 1);
        Date inicio3 = cal.getTime();
        cal.set(2025, Calendar.AUGUST, 1);
        Date fin3 = cal.getTime();
        promociones.add(new Promocion(
            3,
            "Día del Anticucho",
            "¡Solo por hoy! Compra un anticucho y llévate otro gratis.",
            inicio3,
            fin3,
            50.0,
            "https://example.com/images/anticucho.jpg"
        ));

        // Promoción 4: Bebida Gratis
        cal.set(2025, Calendar.SEPTEMBER, 10);
        Date inicio4 = cal.getTime();
        cal.set(2025, Calendar.SEPTEMBER, 15);
        Date fin4 = cal.getTime();
        promociones.add(new Promocion(
            4,
            "Bebida Gratis",
            "Por la compra de cualquier plato de fondo, llévate una bebida gratis.",
            inicio4,
            fin4,
            15.0,
            "https://example.com/images/chicha-morada.jpg"
        ));
        
        novedades.add(new Novedad(
            1,"¡Nuevo plato: Ají de Gallina Vegano!",
            "Ahora puedes disfrutar del clásico Ají de Gallina en su versión 100% vegetal, con el mismo sabor criollo.",
            "https://example.com/images/aji-vegano.jpg", new Date(),new Date()
        ));

        novedades.add(new Novedad(
            2,"Ampliamos nuestro horario",
            "Desde este mes, abrimos una hora más por las noches. ¡Te esperamos hasta las 11:00 p.m. de lunes a sábado!",
            "https://example.com/images/horario-nuevo.jpg", new Date(),new Date()
        ));

        novedades.add(new Novedad(
            3,"Reapertura de nuestro local en Miraflores",
            "¡Ya abrimos! Ven a conocer el renovado ambiente del restaurante en Av. Larco con una vista increíble.",
            "https://example.com/images/local-miraflores.jpg", new Date(),new Date()
        ));

        novedades.add(new Novedad(
            4,"Participamos en Mistura 2025",
            "Estaremos presentes con nuestros platos estrella en la feria gastronómica más grande del país. ¡No faltes!",
            "https://example.com/images/mistura.jpg", new Date(),new Date()
        ));

        novedades.add(new Novedad(
            5,"Nueva carta de postres peruanos",
            "Descubre nuestra nueva selección de postres: suspiro a la limeña, mazamorra morada, arroz con leche y más.",
            "https://example.com/images/postres.jpg", new Date(),new Date()
        ));
    }

    public List<Plato> getEntradas() { return entradas; }
    public List<Plato> getFondos() { return fondos; }
    public List<Plato> getPostres() { return postres; }
    public List<Plato> getBebidas() { return bebidas; }
    public List<Promocion> getPromociones() { return promociones; }
}

