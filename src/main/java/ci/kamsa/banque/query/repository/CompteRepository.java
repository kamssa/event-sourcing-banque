package ci.kamsa.banque.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.kamsa.banque.query.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, String>{

}
