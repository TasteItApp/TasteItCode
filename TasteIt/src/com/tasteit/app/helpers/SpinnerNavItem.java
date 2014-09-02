package com.tasteit.app.helpers;

public class SpinnerNavItem {
	
	private String titulo;
    private int icono;
     
    public SpinnerNavItem(String titulo, int icono){
        this.titulo = titulo;
        this.icono = icono;
    }
     
    public String getTitle(){
        return this.titulo;      
    }
     
    public int getIcon(){
        return this.icono;
    }


}
