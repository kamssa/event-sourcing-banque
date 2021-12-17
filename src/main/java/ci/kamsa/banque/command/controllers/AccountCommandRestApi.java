package ci.kamsa.banque.command.controllers;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ci.kamsa.banque.command.dto.CreateAccountRequestDTO;
import ci.kamsa.banque.command.dto.CreditAccountRequestDTO;
import ci.kamsa.banque.command.dto.DebitAccountRequestDTO;
import ci.kamsa.banque.coreapi.commands.CreateAccountCommand;
import ci.kamsa.banque.coreapi.commands.CreditAccountCommand;
import ci.kamsa.banque.coreapi.commands.DebitAccountCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/commands/accounts/")
@Slf4j
@AllArgsConstructor
public class AccountCommandRestApi {
	
private CommandGateway commandGateway;
private EventStore eventStore;

	@PostMapping("create")
	public CompletableFuture<String> newAccount(@RequestBody CreateAccountRequestDTO request) {
		log.info("CreateAccountRequestDTO => "+request.getInitialBalance().toString());
	 CompletableFuture<String> response =	commandGateway.send(new CreateAccountCommand(
		UUID.randomUUID().toString(),
		request.getInitialBalance(),
		request.getDevise()));
	 return response;
	}
	@PutMapping("credit")
	public CompletableFuture<String> credit(@RequestBody CreditAccountRequestDTO request) {
		log.info("CreditAccountRequestDTO => ");
	 CompletableFuture<String> response =	commandGateway.send(new CreditAccountCommand(
		request.getAccountId(),
		request.getMontant(),
		request.getDevise()));
	 return response;
	}
	@PutMapping("debit")
	public CompletableFuture<String> debit(@RequestBody DebitAccountRequestDTO request) {
		log.info("DebitAccountRequestDTO => ");
	 CompletableFuture<String> response =	commandGateway.send(new DebitAccountCommand(
		request.getAccountId(),
		request.getMontant(),
		request.getDevise()));
	 return response;
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exceptionHandler(Exception ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@GetMapping(path = "/events/{accountId}")
	public  Stream accountEvent(@PathVariable String accountId) {
		return eventStore.readEvents(accountId).asStream();
	}
}
