package hr.fer.zemris.java.hw08.shell.commands.nameGenerating;

import java.util.Objects;
import java.util.regex.Matcher;

/**
 * 
 * Razred koji implementira strukturu za spremanje informacija o izvedenoj
 * naredbi
 * 
 * @author Mihael
 *
 */
public class RegexInfo implements NameBuilderInfo {

	/**
	 * Patternov matcher
	 */
	private Matcher matcher;
	/**
	 * Builder u kojeg spremamo sve za ispis
	 */
	private StringBuilder builder;

	/**
	 * Konsturktor koji inicijalizira dijelove izraza
	 * 
	 * @param matcher
	 *            - patternov matcher
	 * @throws NullPointerException
	 *             - ako je argument null
	 */
	public RegexInfo(Matcher matcher) {
		this.matcher = Objects.requireNonNull(matcher);
		this.builder = new StringBuilder();
	}

	/**
	 * Metoda vraća StringBuilder u kojeg su zapisane sve stvari za ispis
	 * 
	 * @param StringBuilder
	 *            - builder sa svim dijelovima izraza
	 */
	@Override
	public StringBuilder getStringBuilder() {
		return builder;
	}

	/**
	 * Metoda vraća Strign koji odgovara matcheru određenog indexa
	 * 
	 * @param index
	 *            - pozicija unutar matchera
	 */
	@Override
	public String getGroup(int index) {
		return matcher.group(index);
	}

}
