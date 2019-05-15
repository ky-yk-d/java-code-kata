package generics;

import java.util.function.Function;
import java.util.function.Predicate;

abstract class SimpleList<A> {
	abstract A head();
	abstract SimpleList<A> tail();
	abstract boolean isEmpty();
	abstract <B> SimpleList<B> map(Function<? super A, B> mapper);
	abstract SimpleList<A> filter(Predicate<? super A> predicate);
	abstract SimpleList<A> append(SimpleList<A> other);
	abstract <B> SimpleList<B> flatMap(Function<? super A, SimpleList<B>> mapper);
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.head())
		  .append(" ")
		  .append(this.tail().toString());
		return sb.toString();
	}
}
