package merchants.guide.parsing;

import java.util.regex.Pattern;

public class AliasMapper {

	private final String from;
	private final String to;

	public AliasMapper(final String from, final String to) {
		this.from = Pattern.quote(from);
		this.to = to;
	}

	public String map(final String input) {
		return input.replaceAll(from, to);
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}
}
