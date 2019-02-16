package stream.sample;

import java.util.Optional;

class Sample {
	
	/**
	 * 文字列を数値に変換して返す。
	 * @param str 変換元の文字列。
	 * @return 変換後の数値。nullや数値に変換できない引数を渡した場合は{@code null}
	 */
	static Integer stringToInteger(String str){
		try {
			return Integer.valueOf(str);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	/**
	 * 文字列を数値に変換して返す。
	 * @param str 変換元の文字列。
	 * @return 変換後の数値。nullや数値に変換できない引数を渡した場合は空の{@code Optional}
	 */
	static Optional<Integer> stringToOptionalInteger(String str){
		try {
			return Optional.of(Integer.valueOf(str));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}

}
