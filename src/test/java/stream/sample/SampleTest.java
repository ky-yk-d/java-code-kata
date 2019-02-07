package stream.sample;

import static org.junit.jupiter.api.Assertions.*;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
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
		void Optionalのof() {
			Optional<Object> opt = null;
			try {
				opt = Optional.of(null);
			} catch (Exception e) {
				assertEquals(NullPointerException.class, e.getClass());
			}
			assertNull(opt);
		}

		@Test
		void OptionalのofNullable() {
			Optional<Object> opt = Optional.ofNullable(null);
			assertTrue(opt.isEmpty());
			opt = Optional.ofNullable(42);
			assertEquals(42, opt.get());
		}

		@Test
		void Streamのof() {
			List<Object> list = Stream.of((Object)null)
									.collect(Collectors.toList());
			assertEquals(1, list.size());
		}

		@Test
		void StreamのofNullable() {
			List<Object> list = Stream.ofNullable((Object)null)
									.collect(Collectors.toList());
			assertTrue(list.isEmpty());
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
