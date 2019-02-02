package stream.discount;

import java.util.Optional;

public class Customer {

	private MemberCard memberCard;

	public Customer() {
	}

	public Customer(MemberCard memberCard) {
		this.memberCard = memberCard;
	}

	public Optional<MemberCard> getMemberCard() {
		return Optional.ofNullable(memberCard);
	}

}
