package merchants.guide.parsing;

public class UnitConverter {

	private final Unit fromUnit;

	private final double factor;

	public UnitConverter(final Unit fromUnit, final double factor) {
		this.fromUnit = fromUnit;
		this.factor = factor;
	}

	public double convert(final double input) {
		return input * factor;
	}

	public Unit getFromUnit() {
		return fromUnit;
	}
}
