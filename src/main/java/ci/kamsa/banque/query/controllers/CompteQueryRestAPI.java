package ci.kamsa.banque.query.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ci.kamsa.banque.query.dto.CompteDTO;
import ci.kamsa.banque.query.dto.CompteHistoriqueDTO;
import ci.kamsa.banque.query.dto.OperationDTO;
import ci.kamsa.banque.query.entities.Operation;
import ci.kamsa.banque.query.queries.GetCompteByIdQuery;
import ci.kamsa.banque.query.queries.GetCompteHistoryQuery;
import ci.kamsa.banque.query.queries.GetCompteOperationsQuery;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/query/compte/")
@AllArgsConstructor
public class CompteQueryRestAPI {
 private QueryGateway gateway;
	@GetMapping("/{compteId}")
	public CompletableFuture<CompteDTO> getCompte(@PathVariable String compteId) {
	CompletableFuture<CompteDTO> query = gateway.query(new GetCompteByIdQuery(compteId), 
				CompteDTO.class);
	return query;
	}
	@GetMapping("/{compteId}/operations")
	public CompletableFuture<List<OperationDTO>> getOperations(@PathVariable String compteId) {
	CompletableFuture<List<OperationDTO>> query = gateway.query(
			new GetCompteOperationsQuery(compteId), 
				ResponseTypes.multipleInstancesOf(OperationDTO.class));
	return query;
	}
	@GetMapping("/{compteId}/history")
	public CompletableFuture<CompteHistoriqueDTO> getHistory(@PathVariable String compteId) {
	CompletableFuture<CompteHistoriqueDTO> query = gateway.query(
			new GetCompteHistoryQuery(compteId), 
				ResponseTypes.instanceOf(CompteHistoriqueDTO.class));
	return query;
	}
}
