package ci.kamsa.banque.query.service;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import ci.kamsa.banque.query.dto.CompteDTO;
import ci.kamsa.banque.query.dto.CompteHistoriqueDTO;
import ci.kamsa.banque.query.dto.OperationDTO;
import ci.kamsa.banque.query.entities.Compte;
import ci.kamsa.banque.query.entities.Operation;
import ci.kamsa.banque.query.mappers.CompteMapper;
import ci.kamsa.banque.query.queries.GetCompteByIdQuery;
import ci.kamsa.banque.query.queries.GetCompteHistoryQuery;
import ci.kamsa.banque.query.queries.GetCompteOperationsQuery;
import ci.kamsa.banque.query.repository.CompteRepository;
import ci.kamsa.banque.query.repository.OperationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class QueryHandlerService {
 private CompteRepository compteRepository;
 private OperationRepository operationRepository;
 private CompteMapper compteMapper;
 
 @QueryHandler
 public CompteDTO handle(GetCompteByIdQuery query) {
	 Compte compte = compteRepository.findById(query.getCompteId()).get();
	 CompteDTO compteDTO = compteMapper.fromCompte(compte);
	 return compteDTO;
 }
 @QueryHandler
 public List<OperationDTO> handle(GetCompteOperationsQuery query) {
	 List<Operation> operations = operationRepository.findByCompteId(query.getCompteId());
	 List<OperationDTO> operationDTOs = operations
			 .stream()
			 .map(op -> compteMapper.fromOperation(op))
			 .collect(Collectors.toList());
	 return operationDTOs;
 }
 @QueryHandler
 public CompteHistoriqueDTO handle(GetCompteHistoryQuery query) {
	 Compte compte = compteRepository.findById(query.getCompteId()).get();
	 CompteDTO compteDTO = compteMapper.fromCompte(compte);
	 List<Operation> operations = operationRepository.findByCompteId(query.getCompteId());
	 List<OperationDTO> operationDTOs = operations
			 .stream()
			 .map(op -> compteMapper.fromOperation(op))
			 .collect(Collectors.toList());
	 return new CompteHistoriqueDTO(compteDTO,operationDTOs);
 }
}
