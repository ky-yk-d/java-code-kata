package generics;

import java.util.function.Predicate;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello, Java!");
		SimpleList<Integer> list = new Cons<Integer>(1, new Cons<Integer>(2, new Cons<Integer>(3, new Empty<Integer>())));
		System.out.println(list);
		SimpleList<Integer> filtered = list.filter(
				new Predicate<Number>() {
					@Override
					public boolean test(Number t) {
						return t.intValue() % 2 == 1;
					}
				});
		System.out.println(filtered);
		SimpleList<String> mapped = list.map(el -> "el: " + el);
		System.out.println(mapped);
	}
}
