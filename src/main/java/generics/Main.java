package generics;

import java.util.function.Function;
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
		SimpleList<Integer> other = new Cons<>(10, new Cons<>(20, new Cons<>(30, new Cons<>(40, new Empty<>()))));
		System.out.println(filtered.append(other));
		System.out.println(other.append(other));
		Function<Number, SimpleList<Number>> mapper = new Function<>() {
			@Override
			public SimpleList<Number> apply(Number t) {
				return new Cons<>(t.intValue() / 3, new Cons<>(t.intValue() % 3, new Empty<>()));
			}
		};
		System.out.println(other.flatMap(mapper));
	}
}
