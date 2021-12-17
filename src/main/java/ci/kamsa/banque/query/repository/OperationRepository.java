package ci.kamsa.banque.query.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.kamsa.banque.query.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long>{
List<Operation> findByCompteId(String compteId);
}
