package com.emransac.rviewbutton;

public class Producto {
    String id,nombre,stock,img;
    public Producto(){
    }

    public Producto(String id,String nombre,String stock,String img){
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.img = img;
    }

    public String getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getStock(){
        return stock;
    }

    public String getImg(){
        return img;
    }
    public String setId(){
        return this.id;
    }

    public String setNombre(){
        return this.nombre;
    }

    public String setStock(){
        return this.stock;
    }
    public String setImg(){
        return this.img;
    }
}
