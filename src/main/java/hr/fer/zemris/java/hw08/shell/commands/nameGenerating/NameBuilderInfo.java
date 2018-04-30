package hr.fer.zemris.java.hw08.shell.commands.nameGenerating;

/**
 * Sučelje koje obvezuje korisnika da ima metode za dobivanje grupe i
 * {@link StringBuilder}B
 * 
 * @author ime
 *
 */
public interface NameBuilderInfo {
	/**
	 * Metoda vraća StringBuilder spremljenim sadržajem
	 * 
	 * @return
	 */
	StringBuilder getStringBuilder();

	/**
	 * Metoda vrća vrijednost grupe iz argumenta
	 * 
	 * @param index
	 *            - pozicija grupe
	 * @return String kao vrijendost na index poziciji
	 */
	String getGroup(int index);
}
