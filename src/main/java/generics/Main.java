package generics;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello, Java!");
		SimpleList<Number> list = new Cons<Number>(1, new Cons<Number>(2, new Cons<Number>(3, new Empty<Number>())));
		System.out.println(list);
		SimpleList<Number> filtered = list.filter(el -> el.intValue() % 2 == 1);
		System.out.println(filtered);
		SimpleList<String> mapped = list.map(el -> "el: " + el);
		System.out.println(mapped);
	}
}
