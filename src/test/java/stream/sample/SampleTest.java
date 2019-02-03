package stream.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
}
