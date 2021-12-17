package ci.kamsa.banque.query.queries;

import lombok.Data;

@Data
public class GetCompteHistoryQuery {
	private String compteId;

	public GetCompteHistoryQuery(String compteId) {
		super();
		this.compteId = compteId;
	}
	
}
