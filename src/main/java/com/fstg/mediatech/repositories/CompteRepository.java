package com.fstg.mediatech.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.fstg.mediatech.entities.Compte;

public interface CompteRepository extends  JpaRepository<Compte,Long> {

}
