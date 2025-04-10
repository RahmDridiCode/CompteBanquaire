package com.fstg.mediatech.entities;

public class Compte {
	private int id;
    private String titulaire;
    private double solde;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulaire() {
		return titulaire;
	}
	public void setTitulaire(String titulaire) {
		this.titulaire = titulaire;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public Compte(String titulaire, double solde) {
		super();
		this.titulaire = titulaire;
		this.solde = solde;
	}
	public Compte() {
		super();
	}
	public Compte(int id, String titulaire, double solde) {
		super();
		this.id = id;
		this.titulaire = titulaire;
		this.solde = solde;
	}


}
