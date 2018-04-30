package hr.fer.zemris.java.hw08.shell.commands.nameGenerating;

/**
 * Sučlje koje propisuje metoda koja nabaviti veći hladnjak
 * 
 * @author Mihael
 *
 */
public interface NameBuilder {
	/**
	 * Metoda koja izvršava naredbu uz pomoć informacija o grupi i spremljenom
	 * sadržaju
	 * 
	 * @param info
	 */
	void execute(NameBuilderInfo info);

}
