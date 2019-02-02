package stream.discount;

import java.util.Optional;

public class DiscountService {
	public String getDiscountLine(Customer customer) {
		return customer.getMemberCard()
				.flatMap(card -> getDiscountPercentage(card))
				.map(d -> "Discount%: " + d)
				.orElse("");
	}

	private Optional<Integer> getDiscountPercentage(MemberCard card) {
		if (card.getFidelityPoints() >= 100) {
			return Optional.of(5);
		} 
		if (card.getFidelityPoints() >= 50) {
			return Optional.of(3);
		}
		return Optional.empty();
	}

	public static void main(String[] args) {
		DiscountService service = new DiscountService();
		System.out.println(service.getDiscountLine(new Customer(new MemberCard(60))));
		System.out.println(service.getDiscountLine(new Customer(new MemberCard(10))));
		System.out.println(service.getDiscountLine(new Customer()));
	}
}
