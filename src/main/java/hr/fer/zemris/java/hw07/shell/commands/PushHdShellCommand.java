package hr.fer.zemris.java.hw07.shell.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;

/**
 * Razred koji implementira metodu za spremanja trenutnog radnod direktorija na
 * stog,a za novog stavlja argument
 * 
 * @author Mihael
 *
 */
public class PushHdShellCommand implements ShellCommand {

	/**
	 * Ime naredbe
	 */
	private static String name = "pushhd";
	/**
	 * Opis naredbe
	 */
	private static List<String> description = new ArrayList<>();

	/**
	 * Zadani konstruktor
	 */
	public PushHdShellCommand() {
		description.add("Command pushes current workspace on stack,and sets for new one path from argument");
	}

	/**
	 * Metoda trenutni radni direktorij stavlja na stol,a za sljedeći postavlja
	 * argumentF
	 * 
	 * @param env
	 *            - ljuska
	 * @param arguments
	 *            - argumenti(ne koristi se)
	 * 
	 * @return {@link ShellStatus} za prekid programa
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		try {
			Path path = Paths.get(Functions.split(arguments, 1)[0]);
			path.resolve(env.getCurrentDirectory());
			Stack<Path> stack;
			if (Files.isDirectory(path)) {
				if ((env.getSharedData("cdstack")) != null) {
					stack = (Stack<Path>) env.getSharedData(Functions.CDSTACK);
				} else {
					stack = new Stack<>();
				}
				stack.add(env.getCurrentDirectory());
				env.setSharedData(Functions.CDSTACK, stack);
				env.setCurrentDirectory(path);

				return ShellStatus.CONTINUE;
			}

			System.err.println("Path \'" + path.toString() + "\' is not directory!");

		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}

		return ShellStatus.CONTINUE;
	}

	/**
	 * Metoda vrća ime naredbe
	 * 
	 * @return String ime naredbe
	 */
	@Override
	public String getCommandName() {
		return name;
	}

	/**
	 * Metoda vraća opis naredbe
	 * 
	 * @return opis naredbe
	 */
	@Override
	public List<String> getCommandDescription() {
		return description;
	}

}
