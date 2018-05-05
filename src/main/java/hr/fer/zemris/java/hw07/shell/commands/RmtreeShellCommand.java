package hr.fer.zemris.java.hw07.shell.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;

/**
 * Metoda koja implementira naredbu za brisanje direkorija
 * 
 * @author Mihael
 *
 */
public class RmtreeShellCommand implements ShellCommand {

	/**
	 * Ime naredbe
	 */
	private final static String name = "rmtree";
	/**
	 * Opis naredbe
	 */
	private final static List<String> description = new ArrayList<>();

	/**
	 * Zadani program
	 */
	public RmtreeShellCommand() {
		description.add("Command erases directory with all files");
	}

	/**
	 * Metoda koja izvodi naredbu za brisanje direktorija sa svim sadržajem
	 * 
	 * @return {@link ShellStatus} za nastavak programa
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		Path path = Paths.get(Functions.split(arguments, 1)[0]);

		path.resolve(env.getCurrentDirectory());
		if (Files.isDirectory(path)) {
			try {
				delete(path);
			} catch (IOException e) {
				System.err.println("Problem with path: " + e.getMessage());
			}
		}

		return ShellStatus.CONTINUE;
	}

	/**
	 * Metoda koja rekurzivno prolazi kroz cijelo stablo direktorija i briše svaku
	 * datoteku
	 * 
	 * @param path
	 *            - put do trenutne datoteke
	 * @throws IOException
	 *             - u slučaju problema sa brisanjem
	 */
	private void delete(Path path) throws IOException {

		if (Files.isDirectory(path)) {
			File[] files = path.toFile().listFiles();

			for (File pomFile : files) {
				delete(pomFile.toPath());
			}
		}

		Files.delete(path);

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
