package ci.kamsa.banque.command.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditAccountRequestDTO {
	private String accountId;
	private BigDecimal montant;
	private String devise;

}
