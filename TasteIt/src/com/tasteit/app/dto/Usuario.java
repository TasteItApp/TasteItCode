package com.tasteit.app.dto;

public class Usuario {

	private int Id;
	private String Nombre;
	private String ApellidoPaterno;
	private String ApellidoMaterno;
	private String NickName;
	private String CorreoElectronico;
	private String FechaAlta;
	private String UltimoInicioSesion;
	private String Password;
	
	public Usuario(){
		this.Id = 0;
		this.Nombre = "";
		this.ApellidoPaterno = "";
		this.ApellidoMaterno = "";
		this.NickName = "";
		this.CorreoElectronico = "";
		this.FechaAlta = "";
		this.UltimoInicioSesion = "";
		this.Password = "";
	}
	
	public Usuario(int Id, String Nombre, String ApellidoPaterno, String ApellidoMaterno, String NickName,
			String CorreoElectronico, String FechaAlta, String UltimoInicioSesion, String Password)
	{
		this.Id = Id;
		this.Nombre = Nombre;
		this.ApellidoPaterno = ApellidoPaterno;
		this.ApellidoMaterno = ApellidoMaterno;
		this.NickName = NickName;
		this.CorreoElectronico = CorreoElectronico;
		this.FechaAlta = FechaAlta;
		this.UltimoInicioSesion = UltimoInicioSesion;
		this.Password = Password;
	}
	public void setId(int Id){
		this.Id = Id;
	}
	
	public int getId(){
		return this.Id;
	}
	
	public void setNombre(String Nombre){
		this.Nombre = Nombre;
	}
	
	public String getNombre(){
		return this.Nombre;
	}
	
	public void setApellidoPaterno(String ApellidoPaterno){
		this.ApellidoPaterno = ApellidoPaterno;
	}
	
	public String getApellidoPaterno(){
		return this.ApellidoPaterno;
	}
	
	public void setApellidoMaterno(String ApellidoMaterno){
		this.ApellidoMaterno = ApellidoMaterno;
	}
	
	public String getApellidoMaterno(){
		return this.ApellidoMaterno;
	}
	
	public void setNickName(String NickName){
		this.NickName = NickName;
	}
	
	public String getNickName(){
		return this.NickName;
	}
	
	public void setCorreoElectronico(String CorreoElectronico){
		this.CorreoElectronico = CorreoElectronico;
	}
	
	public String getCorreoElectronico(){
		return this.CorreoElectronico;
	}
	
	public void setFechaAlta(String FechaAlta){
		this.FechaAlta = FechaAlta;
	}
	
	public String getFechaAlta(){
		return this.FechaAlta;
	}
	
	public void setUltimoInicioSesion(String UltimoInicioSesion){
		this.UltimoInicioSesion = UltimoInicioSesion;
	}
	
	public String getUltimoInicioSesion(){
		return this.UltimoInicioSesion;
	}
	
	public void setPassword(String Password){
		this.Password = Password;
	}
	
	public String getPassword(){
		return this.Password;
	}
}
