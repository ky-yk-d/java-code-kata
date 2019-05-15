package generics;

import java.util.function.Function;
import java.util.function.Predicate;

abstract class SimpleList<A> {
	abstract A head();
	abstract SimpleList<A> tail();
	abstract boolean isEmpty();
	abstract <B> SimpleList<B> map(Function<? super A, B> mapper);
	abstract SimpleList<A> filter(Predicate<? super A> predicate);
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.head())
		  .append(" ")
		  .append(this.tail().toString());
		return sb.toString();
	}
}
