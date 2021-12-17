package ci.kamsa.banque.coreapi.commands;

import java.math.BigDecimal;

import lombok.Getter;

public class CreateAccountCommand extends BaseCommand<String> {
	
	@Getter BigDecimal initialBalance;
	@Getter String devise;

	public CreateAccountCommand(String id, BigDecimal initialBalance, String devise) {
		super(id);
		this.initialBalance = initialBalance;
		this.devise = devise;
	}

}
