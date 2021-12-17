package ci.kamsa.banque.coreapi.commands;

import java.math.BigDecimal;

import lombok.Getter;

public class CreditAccountCommand extends BaseCommand<String> {

	@Getter
	BigDecimal montant;
	@Getter
	String devise;

	public CreditAccountCommand(String id, BigDecimal montant, String devise) {
		super(id);
		this.montant = montant;
		this.devise = devise;
	}

}
