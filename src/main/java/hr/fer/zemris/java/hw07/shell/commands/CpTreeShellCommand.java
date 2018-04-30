package hr.fer.zemris.java.hw07.shell.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
	private final static String name = "cptree";
	/**
	 * Opis naredbe
	 */
	private final static List<String> description = new ArrayList<>();

	/**
	 * Zadani program
	 */
	public CpTreeShellCommand() {
		description.add("Command copies context from one directory to other");
	}

	/**
	 * Metoda koja izvodi naredbu za kopiranje cijelog stabla direktorija
	 * 
	 * @param env
	 *            - ljuska
	 * 
	 * @param arguments
	 *            - argumenti
	 * 
	 * @return {@link ShellStatus} za nastavak programa
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		String[] array = Functions.split(arguments, 2);

		Path path1 = Paths.get(array[0]);//.resolve(env.getCurrentDirectory());
		Path path2 = Paths.get(array[1]);//.resolve(env.getCurrentDirectory());

		if (!Files.isDirectory(path1)) {
			System.err.println("Source file must be directory!");
		} else if (Files.isDirectory(path2)) {
			copy(path1.toFile(), Paths.get(path2.toString() + "\\" + path1.getFileName().toString()).toFile());
		} else if (Files.isDirectory(path2.getParent())) {
			copy(path1.toFile(),
					Paths.get(path2.toString()).toFile());
		} else {
			System.err.println("Incorrect path. Can't be copied! Destination path \'"+path2.toString()+"\' doesn't exist!");
		}

		return ShellStatus.CONTINUE;
	}

	/**
	 * Metoda kopira strukturu jednog direktorija u drugi
	 * 
	 * @param file
	 *            - izvorni direktorij
	 * @param file2
	 *            - odredišni direktorij
	 */
	private void copy(File file, File file2) {

		if (file.isDirectory()) {
			// if destination folder doesn't exist,we need to make new one
			if (!file2.exists()) {
				file2.mkdir();
			}

			String[] files = file.list();

			for (String pomFile : files) {
				File srcFile = new File(file, pomFile);
				File destFile = new File(file2, pomFile);

				// Recursive function call
				copy(srcFile, destFile);
			}
		} else {
			try {
				Files.copy(file.toPath(), file2.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

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
