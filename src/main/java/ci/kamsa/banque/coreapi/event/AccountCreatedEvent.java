package ci.kamsa.banque.coreapi.event;

import java.math.BigDecimal;

import lombok.Getter;

public class AccountCreatedEvent extends BaseEvent<String> {
	
	@Getter BigDecimal initialBalance;
	@Getter String devise;

	public AccountCreatedEvent(String id, BigDecimal initialBalance, String devise) {
		super(id);
		this.initialBalance = initialBalance;
		this.devise = devise;
	}

}
