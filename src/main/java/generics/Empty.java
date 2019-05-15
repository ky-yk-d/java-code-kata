package generics;

import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

public class Empty<A> extends SimpleList<A>{

	@Override
	A head() {
		throw new NoSuchElementException();
	}

	@Override
	SimpleList<A> tail() {
		throw new NoSuchElementException();
	}

	@Override
	boolean isEmpty() {
		return true;
	}

	@Override
	<B> SimpleList<B> map(Function<A, B> mapper) {
		return new Empty<B>();
	}

	@Override
	SimpleList<A> filter(Predicate<A> predicate) {
		return new Empty<A>();
	}
	
	@Override
	public String toString() {
		return "";
	}
	
	@Override
	public boolean equals(Object other) {
		if (other != null && other instanceof Empty<?>) {
			return true;
		}
		return false;
	}
}
