package com.fstg.mediatech.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity
public class Compte {
	@Id
	@GeneratedValue
	private long id;
    private String titulaire;
    private double solde;
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	


}
