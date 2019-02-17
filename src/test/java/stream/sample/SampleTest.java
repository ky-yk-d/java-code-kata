package stream.sample;

import static org.junit.jupiter.api.Assertions.*;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SampleTest {

	@Nested
	class filterの仕様の確認{

		@Test
		void Optionalのfilterが空のOptionalを返す() {
			Optional<Integer> opt = Optional.of(3).filter(e -> e % 2 == 0);
			assertTrue(opt.isEmpty());
		}

		@Test
		void Streamのfilterが空のStreamを返す() {
			List<Integer> list = Stream.of(1,3)
					.filter(e -> e % 2 == 0)
					.collect(Collectors.toList());
				assertEquals(0, list.size());
		}
	}

	@Nested
	class ofとofNullableの仕様の確認{

		@Test
		void Optionalのofは例外をスローする() {
			final String helloWorld = "Hello World";
			Optional<Object> opt = Optional.of(helloWorld);
			Exception exception = null; // スローされたら格納しておく
			try {
				opt = Optional.of(null);
				fail("Optional.of()はnullを渡すと例外をスローするので到達しないはずのコード");
			} catch (Exception e) {
				exception = e;
				assertEquals(NullPointerException.class, e.getClass());
			}
			assertEquals(helloWorld, opt.get()); // 当初の値のままである
			assertNotNull(exception); // 例外がスローされていることを確認
		}

		@Test
		void OptionalのofNullable() {
			Optional<Object> opt = Optional.ofNullable(null);
			assertTrue(opt.isEmpty());
			opt = Optional.ofNullable(42);
			assertEquals(42, opt.get());
		}

		@Test
		void Streamのofはnullを含むStreamを返す() {
			List<Object> list = Stream.of((Object)null).collect(Collectors.toList());
			assertEquals(1, list.size());
			assertNull(list.get(0)); // null 1つが入ったListとなっている
			assertThrows(IndexOutOfBoundsException.class, ()->list.get(1)); //  
			list.forEach((o)->System.out.println("object:"+ o));
		}
		
		@Test
		void StreamのofNullableの利用() {
			List<String> list = Arrays.asList("Kent Beck", "Martin Fowler", "Robert C. Martin");
			Map<String, Integer> map = new HashMap<String, Integer>(){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				{
					put("Kent Beck", 1);
					put("Martin Fowler", 2);
					put("James O. Coplien", 3);
				}
			};
			// nullを返す可能性のある中間処理をStream.ofNullable()で囲むことで、そのままflatMapに流せる
			// 要素1つを受け取ってStreamを生成して返す関数を受け取るのがflatMap()
			// 要素1つからStreamを作るので、map()を使うと、Stream<Stream<E>>となる
			List<Integer> ofNullable = list.stream().flatMap(str -> Stream.ofNullable(map.get(str))).collect(Collectors.toList());
			assertEquals(2,ofNullable.size());
			List<Integer> simple = list.stream().map(str -> map.get(str)).collect(Collectors.toList());
			assertEquals(3, simple.size());
			ofNullable.stream().forEach(System.out::println);
			simple.stream().forEach(System.out::println);
		}

		@Test
		void StreamのofNullable() {
			List<Object> list = Stream.ofNullable((Object)null)
									.collect(Collectors.toList());
			assertTrue(list.isEmpty());
		}
	}
	
	@Nested
	class Sampleクラスのメソッドのテスト {
		@Test
		void stringToIntegerはnull安全() {
			Integer result = Sample.stringToInteger(null);
			assertNull(result);
		}
		@Test
		void stringToOptionalIntegerはnull安全() {
			Optional<Integer> result = Sample.stringToOptionalInteger(null);
			assertTrue(result.isEmpty());
		}
	}
	
	@Nested
	class mapとflatMap {
		@Test
		void Optionalのmap() {
			Optional<String> opt = Optional.of("123");
			Optional<Integer> result = opt.map(Sample::stringToInteger);
			System.out.println(result);
			opt = Optional.of("変換できない文字列");
			result = opt.map(Sample::stringToInteger);
			System.out.println(result);
		}
		
		@Test
		void StreamのofNullableの利用() {
			List<List<String>> src = List.of(List.of("123","456"), List.of(), List.of("789", "変換できない文字列"));
			List<Integer> list = src.stream()
					.flatMap(List::stream)
					// nullなら空のStream、非nullならStreamに入れて流す
					.flatMap(str -> Stream.ofNullable(Sample.stringToInteger(str)))
					.collect(Collectors.toList());
			list.forEach(System.out::print);
		}
		
		@Test
		void Optionalのstreamの利用() {
			List<List<String>> src = List.of(List.of("123","456"), List.of(), List.of("789", "変換できない文字列"));
			List<Integer> list = src.stream()
					.flatMap(List::stream)
					// Optionalが空なら空のStream、空ではないOptionalなら要素をStreamに入れて流す
					.flatMap(str -> Sample.stringToOptionalInteger(str).stream())
					.collect(Collectors.toList());
			list.forEach(System.out::print);
		}
		
		@Test
		void StreamのflatMap() {
			List<List<String>> src = List.of(List.of("C言語","Java"), List.of(), List.of("Python", "JavaScript", "Ruby"));
			List<String> dest = src.stream().flatMap(List::stream).collect(Collectors.toList());
			dest.forEach(System.out::print);
			System.out.println();
		}
		@Test
		void Streamのmap() {
			List<String> src = List.of("123","456","","変換できない文字列");
			// nullも出力する
			src.stream().map(Sample::stringToInteger)
				.forEach(System.out::println); 
			// nullは出力しない（※以下の2つのコードは同じ動きを示す）
			src.stream().flatMap(el -> Sample.stringToOptionalInteger(el).stream())
				.forEach(System.out::println);
			src.stream().flatMap(el -> Stream.ofNullable(Sample.stringToInteger(el)))
				.forEach(System.out::println);
		}
	}

	@Nested
	class JUnit5の練習{
		@ParameterizedTest
		@ValueSource(strings = {"201801","199912"})
		void 年月の表現として有効な文字列(String str) {
			DateTimeFormatter yyyymm = DateTimeFormatter.ofPattern("yyyyMM");
			YearMonth yearMonth = YearMonth.parse(str, yyyymm);
			assertNotNull(yearMonth);
		}

		@ParameterizedTest
		@ValueSource(strings = {"20181", "20180A", "-010012", "200012011"})
		void 年月の表現として無効な文字列(String str) {
			DateTimeFormatter yyyymm = DateTimeFormatter.ofPattern("yyyyMM");
			assertThrows(DateTimeParseException.class, ()->YearMonth.parse(str, yyyymm));
		}
	}

}
