package com.tp.beans;

public class Article {
    private String nom;
    private String description="";
    private float prix=0;
    private int idArticle;
	private boolean todelete;

	public int getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
  
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public boolean isTodelete() {
		return todelete;
	}

	public void setTodelete(boolean todelete) {
		this.todelete = todelete;
	}
}