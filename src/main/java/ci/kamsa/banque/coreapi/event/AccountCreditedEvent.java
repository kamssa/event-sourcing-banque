package ci.kamsa.banque.coreapi.event;

import java.math.BigDecimal;

import lombok.Getter;

public class AccountCreditedEvent extends BaseEvent<String> {

	@Getter
	BigDecimal montant;
	@Getter
	String devise;

	public AccountCreditedEvent(String id, BigDecimal montant, String devise) {
		super(id);
		this.montant = montant;
		this.devise = devise;
	}

}
