package com.fstg.mediatech.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Compte {
	@Id
	@GeneratedValue
	private long id;

    private String titulaire;

    private double solde;

	@ManyToOne
	private User user;

}
