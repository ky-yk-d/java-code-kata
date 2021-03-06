package generics;

import java.util.function.Function;
import java.util.function.Predicate;

public class Cons<A> extends SimpleList<A> {
	A head;
	SimpleList<A> tail;

	public Cons(A head, SimpleList<A> tail) {
		this.head = head;
		this.tail = tail;
	}

	@Override
	A head() {
		return this.head;
	}

	@Override
	SimpleList<A> tail() {
		return this.tail;
	}

	@Override
	boolean isEmpty() {
		return false;
	}

	@Override
	<B> SimpleList<B> map(Function<? super A, B> mapper) {
		B newHead = mapper.apply(this.head);
		SimpleList<B> newTail = tail.map(mapper);
		return new Cons<B>(newHead, newTail);
	}


	@Override
	SimpleList<A> filter(Predicate<? super A> predicate) {
		if (predicate.test(this.head)){
			return new Cons<A>(this.head, this.tail.filter(predicate));
		}
		return tail.filter(predicate);
	}
	
	@Override
	SimpleList<A> append(SimpleList<A> other){
		return new Cons<A>(this.head, this.tail.append(other));
	}

	@Override
	<B> SimpleList<B> flatMap(Function<? super A, SimpleList<B>> mapper) {
		return mapper.apply((this.head)).append(this.tail.flatMap(mapper));
	}

}
