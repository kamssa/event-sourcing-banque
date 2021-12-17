package ci.kamsa.banque.coreapi.commands;

import java.math.BigDecimal;

import lombok.Getter;

public class DebitAccountCommand extends BaseCommand<String> {

	@Getter
	BigDecimal montant;
	@Getter
	String devise;

	public DebitAccountCommand(String id, BigDecimal montant, String devise) {
		super(id);
		this.montant = montant;
		this.devise = devise;
	}

}
