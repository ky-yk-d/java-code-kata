package stream.sample;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class SampleTest {

	@Test
	void test() {
		Stream<Object> st = Stream.of((Object)null);
		System.out.println(st);
		st.forEach(System.out::println); // nullが出力される
 		Stream<Object> nst = Stream.ofNullable((Object)null);
		System.out.println(nst);
		nst.forEach(System.out::println);  // 要素がないので出力されない
	}

}
