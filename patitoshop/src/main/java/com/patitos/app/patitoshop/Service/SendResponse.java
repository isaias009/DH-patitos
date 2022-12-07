package com.patitos.app.patitoshop.Service;

public class SendResponse {
    private String getEmpaque(String size){
        String res = null;

        size = (size.equals("Large") ? "XLarge" : size);
        size = (size.equals("Small") ? "XSmall" : size); 

        switch (size){
            case "XLarge": res = "madera";
            break;

            case "Medium": res = "carton";
            break;

            case "XSmall": res = "plastico";
            break;
        }
        return res;
    }

    private String getModes(String paquete, String mode){
        String res = null;
        int pos = -1;

        pos = mode.equals("Aire") && (paquete.equals("madera") || paquete.equals("carton")) ? 1 : pos;
        pos = mode.equals("Aire") && (paquete.equals("plastico")) ? 2 : pos;
        pos = mode.equals("Tierra") ? 3 : pos;
        pos = mode.equals("Mar") ? 4 : pos;

        switch (pos){
            case 1: res = "bolitas de plastoformo dentro del paquete";
            break;

            case 2: res = "relleno con bolsas de burbujas";
            break;

            case 3: res = "relleno con bolitas de plastoformo";
            break;

            case 4: res = "relleno con bolitas absorbentes de humedad y bolsas con burbujas";
            break;
        }

        return res;
        // if(mode.equals("Aire") && (paquete.equals("madera") || paquete.equals("carton"))){
        //     res = "bolitas de plastoformo dentro del paquete";
        // }

        // if(mode.equals("Aire") && (paquete.equals("plastico"))){
        //     res = "relleno con bolsas de burbujas";
        // }
        
        // if(mode.equals("Tierra")){
        //     res = "relleno con bolitas de plastoformo";
        // }

        // if(mode.equals("Mar")){
        //     res = "relleno con bolitas absorbentes de humedad y bolsas con burbujas";
        // }
    }

    public static String proteccion(String size, String mode){
        String paquete = new SendResponse().getEmpaque(size);
        return new SendResponse().getModes(paquete, mode);
    }

    public static String empaquetado(String size){
        return new SendResponse().getEmpaque(size);
    }
}