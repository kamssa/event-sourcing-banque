package ci.kamsa.banque.query.service;

import java.util.Date;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ci.kamsa.banque.coreapi.enumeted.AccountStatus;
import ci.kamsa.banque.coreapi.enumeted.OperationType;
import ci.kamsa.banque.coreapi.event.AccountActivatedEvent;
import ci.kamsa.banque.coreapi.event.AccountCreatedEvent;
import ci.kamsa.banque.coreapi.event.AccountCreditedEvent;
import ci.kamsa.banque.coreapi.event.AccountDebitedEvent;
import ci.kamsa.banque.query.dto.CompteDTO;
import ci.kamsa.banque.query.entities.Compte;
import ci.kamsa.banque.query.entities.Operation;
import ci.kamsa.banque.query.mappers.CompteMapper;
import ci.kamsa.banque.query.queries.GetCompteByIdQuery;
import ci.kamsa.banque.query.repository.CompteRepository;
import ci.kamsa.banque.query.repository.OperationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class EventHandlerService {
 private CompteRepository compteRepository;
 private OperationRepository operationRepository;
 private QueryUpdateEmitter emitter;
 private CompteMapper compteMapper;
 
 @ResetHandler
 public void restdatabase() {
	 log.info("reset database");
	 compteRepository.deleteAll();
	 operationRepository.deleteAll();
 }
 @EventHandler
 public void on(AccountCreatedEvent event) {
	 log.info("*************Query side*************");
	 Compte compte = new Compte();
	 compte.setId(event.getId());
	 compte.setSolde(event.getInitialBalance());
	 compte.setDevise(event.getDevise());
	 compte.setStatus(AccountStatus.CREATED);
	 compteRepository.save(compte);
	 
 }
 @EventHandler
 @Transactional
 public void on(AccountActivatedEvent event) {
	 log.info("*************Query side*************");
	 Compte compte = compteRepository.findById(event.getId()).get();
	 compte.setStatus(event.getStatus());
	 compteRepository.save(compte);
	 
 }
 @EventHandler
 @Transactional
 public void on(AccountCreditedEvent event) {
	 log.info("*************Query side*************");

	 Compte compte = compteRepository.findById(event.getId()).get();
	 Operation operation = new Operation();
	 operation.setDateOperation(new Date());
	 operation.setMontant(event.getMontant());
	 operation.setType(OperationType.CREDIT);
	 operation.setCompte(compte);
	 operationRepository.save(operation);
	 compte.setSolde(compte.getSolde().add(event.getMontant()));
	 compteRepository.save(compte);
	 CompteDTO compteDTO = compteMapper.fromCompte(compte);
	 emitter.emit(m ->
	 ((GetCompteByIdQuery)m.getPayload()).getCompteId().equals(event.getId()),
			 compteDTO);
	 
 }
 @EventHandler
 @Transactional
 public void on(AccountDebitedEvent event) {
	 log.info("*************Query side*************");

	 Compte compte = compteRepository.findById(event.getId()).get();
	 Operation operation = new Operation();
	 operation.setDateOperation(new Date());
	 operation.setMontant(event.getMontant());
	 operation.setType(OperationType.DEBIT);
	 operation.setCompte(compte);
	 operationRepository.save(operation);
	 compte.setSolde(compte.getSolde().subtract(event.getMontant()));
	 compteRepository.save(compte);
	 CompteDTO compteDTO = compteMapper.fromCompte(compte);
	 emitter.emit(m ->
	 ((GetCompteByIdQuery)m.getPayload()).getCompteId().equals(event.getId()),
			 compteDTO);
 }
}
