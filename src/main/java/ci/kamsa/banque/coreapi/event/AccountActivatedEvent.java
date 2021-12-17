package ci.kamsa.banque.coreapi.event;

import ci.kamsa.banque.coreapi.enumeted.AccountStatus;
import lombok.Getter;

public class AccountActivatedEvent extends BaseEvent<String>{
@Getter  AccountStatus status;

public AccountActivatedEvent(String id, AccountStatus status) {
	super(id);
	this.status = status;
}

}
