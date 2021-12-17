package ci.kamsa.banque.query.dto;

import java.math.BigDecimal;
import java.util.Date;

import ci.kamsa.banque.coreapi.enumeted.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor @AllArgsConstructor
public class OperationDTO {
	
	
	private Long id;
	private Date dateOperation;
	private BigDecimal montant;
	private OperationType type;
	
}
