package merchants.guide.rules;

public interface IRule {

	boolean check(final String input);

	String getName();

}
