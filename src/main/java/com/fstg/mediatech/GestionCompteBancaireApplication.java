package com.fstg.mediatech;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fstg.mediatech.entities.Compte;

@SpringBootApplication
public class GestionCompteBancaireApplication {
	
	public static List<Compte> comptes = new ArrayList<>();
	
	public static void main(String[] args) {
		SpringApplication.run(GestionCompteBancaireApplication.class, args);
		Compte c1=new Compte(1,"Rahma",15000);
		Compte c2=new Compte(2,"Ahmed",50000);
		comptes.add(c1);
		comptes.add(c2);
		
	}

}
