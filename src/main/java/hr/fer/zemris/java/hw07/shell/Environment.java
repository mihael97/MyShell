package hr.fer.zemris.java.hw07.shell;

import java.nio.file.Path;
import java.util.SortedMap;

/**
 * Sučelje koje propisuje sve metode koje ljuska mora imati
 * 
 * @author Mihael
 *
 */
public interface Environment {
	/**
	 * Metoda za čitanje sa standardnog ulaza
	 * 
	 * @return pročitanu naredbu
	 * @throws ShellIOException
	 *             - u slučaju problema sa Scannerom
	 */
	String readLine() throws ShellIOException;

	/**
	 * Metoda ispisuje predani teskt
	 * 
	 * @param text
	 *            - tekst
	 * @throws ShellIOException
	 *             - u slučaju pogreška pri ispisu
	 */
	void write(String text) throws ShellIOException;

	/**
	 * Metoda ispisuje predani teskt
	 * 
	 * @param text
	 *            - tekst
	 * @throws ShellIOException
	 *             - u slučaju pogreška pri ispisu
	 */
	void writeln(String text) throws ShellIOException;

	/**
	 * Metoda vraća konačnu mapu podržnih naredbi
	 * 
	 * @return konačna mapa naredbi
	 */
	SortedMap<String, ShellCommand> commands();

	/**
	 * Metoda vraća trenutni znak koji predstavlja početak nastavka višelinijske
	 * naredbe
	 * 
	 * @return trenutni znak koji predstavlja početak nastavka višelinijske naredbe
	 */
	Character getMultilineSymbol();

	/**
	 * Metoda postavlja trenutni znak koji predstavlja početak nastavka višelinijske
	 * naredbe
	 * 
	 * @param symbol
	 *            - budući znak koji predstavlja početak nastavka višelinijske
	 *            naredbe
	 */
	void setMultilineSymbol(Character symbol);

	/**
	 * Metoda vraća trenutni simbol za početak naredbe
	 * 
	 * @return trenutni simbol za početak naredbe
	 */
	Character getPromptSymbol();

	/**
	 * Metoda koja postavlja simbol koji predstavlja početak upisa naredbe
	 * 
	 * @param symbol
	 *            - novi simbol
	 */
	void setPromptSymbol(Character symbol);

	/**
	 * Metoda vraća trenutni simbol za nastavak višelinijske naredbe
	 * 
	 * @return simbol za nastavak više linijske naredbe
	 */
	Character getMorelinesSymbol();

	/**
	 * Metoda koja postavlja znak za simboliziranje višelinijske naredbe
	 * 
	 * @param symbol
	 *            - novi simbol
	 */
	void setMorelinesSymbol(Character symbol);

	/**
	 * Metoda vraća trenutni direktorij
	 * 
	 * @return trenutni direktorij
	 */
	Path getCurrentDirectory();

	/**
	 * Metoda postavlja trenutni direktorij
	 * 
	 * @param path
	 *            - novi radni direktorij
	 */
	void setCurrentDirectory(Path path);

	/**
	 * Metoda vraća trenutnu tablicu zajedničkih vrijednosti
	 * 
	 * @param key
	 *            ključ
	 * @return vrijednost
	 */
	Object getSharedData(String key);

	/**
	 * Metoda postavlja zajedničke vrijednosti
	 * 
	 * @param key
	 *            - ključ
	 * @param value
	 *            - vrijednost
	 */
	void setSharedData(String key, Object value);
}
