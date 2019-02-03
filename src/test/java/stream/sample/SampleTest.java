package stream.sample;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class SampleTest {

	@Test
	void test() {
		Stream<Object> st = Stream.of((Object)null);
		System.out.println(st);
		st.forEach(System.out::println);
//		Stream<Object> nst = Stream.ofNullable((Object)null);
	}

}
