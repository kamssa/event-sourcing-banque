package ci.kamsa.banque.query.queries;

import lombok.Data;

@Data
public class GetCompteByIdQuery {
 private String compteId;

public GetCompteByIdQuery(String compteId) {
	super();
	this.compteId = compteId;
}
 
}
