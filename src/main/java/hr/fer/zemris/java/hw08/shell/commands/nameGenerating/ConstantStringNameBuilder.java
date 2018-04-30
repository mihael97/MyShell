package hr.fer.zemris.java.hw08.shell.commands.nameGenerating;

import java.util.Objects;

/**
 * Razred koji predstavlja dio koda koji če se izvršiti pozivanjem metode
 * execute
 * 
 * @author Mihael
 *
 */
public class ConstantStringNameBuilder implements NameBuilder {

	/**
	 * String kojeg ćemo pohrantii u novom
	 */
	private String string;

	/**
	 * Konsturktor koji kao argument prima sadržaj čvora
	 * 
	 * @param string
	 *            - sadržaj čvora
	 */
	public ConstantStringNameBuilder(String string) {
		this.string = Objects.requireNonNull(string);
	}

	/**
	 * Metoda koja nadograđuje trenutni String Builder na veći stupanj
	 * 
	 * @param info
	 *            - informacija o načinu izrade
	 * 
	 * @return informacije o čvoru
	 */
	@Override
	public void execute(NameBuilderInfo info) {
		info.getStringBuilder().append(string);
	}

}
