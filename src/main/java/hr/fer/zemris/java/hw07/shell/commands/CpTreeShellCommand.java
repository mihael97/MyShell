package hr.fer.zemris.java.hw07.shell.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;

/**
 * Razred koji implementira metodu za kopiranje jednog direktorija u drugi
 * 
 * @author Mihael
 *
 */
public class CpTreeShellCommand implements ShellCommand {

	/**
	 * Ime naredbe
	 */
	private final static String name = "charsets";
	/**
	 * Opis naredbe
	 */
	private final static List<String> description = new ArrayList<>();

	/**
	 * Zadani program
	 */
	public CpTreeShellCommand() {
		description.add("Command copy context from one file to other");
	}

	/**
	 * Metoda koja izvodi naredbu za kopiranje cijelog stabla direktorija
	 * 
	 * @return {@link ShellStatus} za nastavak programa
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		String[] array = Functions.split(arguments, 2);

		Path path1 = Paths.get(array[0]).resolve(env.getCurrentDirectory());
		Path path2 = Paths.get(array[1]).resolve(env.getCurrentDirectory());

		if (Files.isDirectory(path2)) {
			copy(env, path1, path2);
		} else if (Files.isDirectory(path2.getParent())) {
			copy(env, path1, Paths.get(path2.getParent().toString()));
		} else {
			System.err.println("Incorrect path. Can't be coppied!");
		}

		return ShellStatus.CONTINUE;
	}

	/**
	 * Metoda kopira strukturu jednog file u drugi
	 * 
	 * @param env
	 *            - ljuska
	 * @param path1
	 *            - izvorni direktorij
	 * @param path2
	 *            - odredišni direktorij
	 */
	private void copy(Environment env, Path path1, Path path2) {
		path2 = Paths.get(path2.toString() + "\\" + path1.getFileName().toString());

	}

	/**
	 * Metoda koja vraća ime naredbe
	 * 
	 * @return ime naredbe
	 */
	@Override
	public String getCommandName() {
		return name;
	}

	/**
	 * Metoda koja vraća opis naredbe
	 * 
	 * @return opis naredbe
	 */
	@Override
	public List<String> getCommandDescription() {
		return description;
	}

}
