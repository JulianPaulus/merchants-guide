package merchants.guide;

public enum RomanNumeral {
	I(1),
	V(5),
	X(10),
	L(50),
	C(100),
	D(500),
	M(1000);

	private final int value;

	RomanNumeral(final int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public boolean smallerThan(RomanNumeral other) {
		return this.value < other.value;
	}

	public static RomanNumeral fromChar(final char input) {
		return valueOf(String.valueOf(input).toUpperCase());
	}
}
