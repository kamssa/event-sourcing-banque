package ci.kamsa.banque.command.aggregates;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import ci.kamsa.banque.command.exception.SoldeInsuffisantException;
import ci.kamsa.banque.coreapi.commands.CreateAccountCommand;
import ci.kamsa.banque.coreapi.commands.CreditAccountCommand;
import ci.kamsa.banque.coreapi.commands.DebitAccountCommand;
import ci.kamsa.banque.coreapi.enumeted.AccountStatus;
import ci.kamsa.banque.coreapi.event.AccountActivatedEvent;
import ci.kamsa.banque.coreapi.event.AccountCreatedEvent;
import ci.kamsa.banque.coreapi.event.AccountCreditedEvent;
import ci.kamsa.banque.coreapi.event.AccountDebitedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aggregate
@NoArgsConstructor
@Slf4j
public class AccountAggregate {

	@AggregateIdentifier
	private String accountId;
	
	private BigDecimal solde;
	private String devise;
	private AccountStatus status;
	
	@CommandHandler
	public AccountAggregate(CreateAccountCommand command) {
		log.info("CreateAccountCommand bien reçu...");    
		/** Business logic**/
		AggregateLifecycle.apply(new AccountCreatedEvent(
				command.getId(),
				command.getInitialBalance(),
				command.getDevise()));
	}
	@EventSourcingHandler
	public void on(AccountCreatedEvent event) {
		log.info("AccountCreatedEvent occured...");
		this.accountId = event.getId();
		this.solde = event.getInitialBalance();
		this.devise= event.getDevise();
		this.status = AccountStatus.CREATED;
		AggregateLifecycle.apply(new AccountActivatedEvent(
				event.getId(),
				AccountStatus.ACTIVETED));
		
	}
	
	@EventSourcingHandler
	public void on(AccountActivatedEvent event) {
		log.info("AccountActivatedEvent occured...");
		this.status= event.getStatus();
		
	}
	@CommandHandler
	public void handle(CreditAccountCommand command) {
		log.info("CreditAccountCommand bien reçu...");    
		/** Business logic**/
		AggregateLifecycle.apply(new AccountCreditedEvent(
				command.getId(),
				command.getMontant(),
				command.getDevise()));
	}
	@EventSourcingHandler
	public void on(AccountCreditedEvent event) {
		log.info("AccountCreditedEvent occured...");
		this.solde= this.solde.add(event.getMontant());
		
	}
	@CommandHandler
	public void handle(DebitAccountCommand command) {
		log.info("DebitAccountCommand bien reçu...");    
		/** Business logic**/
		if(this.solde.subtract(command.getMontant()).doubleValue()<0) {
			throw new SoldeInsuffisantException("Solde Insuffisant Exception");

		}
		AggregateLifecycle.apply(new AccountDebitedEvent(
				command.getId(),
				command.getMontant(),
				command.getDevise()));
	}
	@EventSourcingHandler
	public void on(AccountDebitedEvent event) {
		log.info("AccountDebitedEvent occured...");
		this.solde= this.solde.subtract(event.getMontant());
		
	}
}
