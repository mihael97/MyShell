package hr.fer.zemris.java.hw07.shell.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;
import hr.fer.zemris.java.hw08.shell.commands.nameGenerating.ExecutorNameBuilder;
import hr.fer.zemris.java.hw08.shell.commands.nameGenerating.NameBuilder;
import hr.fer.zemris.java.hw08.shell.commands.nameGenerating.NameBuilderInfo;
import hr.fer.zemris.java.hw08.shell.commands.nameGenerating.NameBuilderParser;
import hr.fer.zemris.java.hw08.shell.commands.nameGenerating.RegexInfo;

/**
 * Razred koji implementira metode za preimneovanje i premještanje direktorija i
 * datoteka
 * 
 * @author Mihael
 *
 */
public class MassrenameShellCommand implements ShellCommand {

	/**
	 * Ime naredbe
	 */
	private final static String name = "massrename";
	/**
	 * Opis naredbe
	 */
	private final static List<String> description = new ArrayList<>();

	/**
	 * Zadani program
	 */
	public MassrenameShellCommand() {
		description.add(
				"Command contains methods for renaming and moving directories and grouping and filtering by regex");
	}

	/**
	 * Metoda ovisno o argumentima poziva metodu filtriranje,grupiranje ili
	 * premještanje(preimneovanje)
	 * 
	 * @param env
	 *            - ljuska
	 * @param arguments
	 *            - argumenti
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		try {
			String[] argumentArray = Functions.split(arguments, 5);

			if (argumentArray.length < 5) {
				throw new IllegalArgumentException(
						"Not enough arguments. Number should be 5 but is " + argumentArray.length);
			}

			Path path1 = Paths.get(argumentArray[0]);
			path1.resolve(env.getCurrentDirectory());
			Path path2 = Paths.get(argumentArray[1]);
			path2.resolve(env.getCurrentDirectory());

			try {
				switch (argumentArray[2]) {
				case "filter":
					print(env, filter(path1, argumentArray[3]));
					break;
				case "groups":
					print(env, group(path1, argumentArray[3]));
					break;
				case "show":
					print(env, show(path1, path2, argumentArray[3], argumentArray[4], false));
					break;
				case "execute":
					print(env, show(path1, path2, argumentArray[3], argumentArray[4], true));
					break;
				}
			} catch (Exception e) {
				System.err.println("Unexpected exception: " + e.getMessage());
			}
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}

		return ShellStatus.CONTINUE;

	}

	/**
	 * @param source
	 *            - izvor
	 * @param destination
	 *            - odredište
	 * @param regex
	 *            - regularni izraz za datoteke
	 * @param nameRegex
	 *            - regularni izraz za naziv datoteke pri preimenovanju
	 * @param flag
	 *            - zastavica <code>true</code> ako se radi i premještanje inače
	 *            <code>false</code>
	 * @return lista imena datoteka
	 */
	private List<String> show(Path source, Path destination, String regex, String nameRegex, boolean flag) {
		NameBuilderParser parser = new NameBuilderParser(nameRegex);
		NameBuilder builder = parser.getNameBuilder();
		Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
		List<String> list = new ArrayList<>();

		for (File file : source.toFile().listFiles()) {
			Matcher matcher = pattern.matcher(file.getName().toString());

			if (matcher.matches()) {
				NameBuilderInfo info = new RegexInfo(matcher);
				builder.execute(info);

				String newName = (flag == true ? destination.toString() + "\\" : "")
						+ ExecutorNameBuilder.getStringBuilder().toString();
				String fileName = (flag == true ? source.toString() + "\\" : "") + file.getName().toString();
				list.add(fileName + " => " + newName);

				if (flag) {
					copyFiles(file.toPath(), Paths.get(newName));
				}

			}
		}

		return list;
	}

	/**
	 * Metoda premješta konkretan file na drugu lokaciju
	 * 
	 * @param source
	 *            - izvor
	 * @param target
	 *            - odredište
	 */
	private void copyFiles(Path source, Path target) {
		try {
			Files.move(source, target);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metoda koja ispisuje liste
	 * 
	 * @param env
	 *            - ljuska
	 * @param list
	 *            - lista
	 */
	private void print(Environment env, List<String> list) {
		for (String string : list) {
			env.writeln(string);
		}
	}

	/**
	 * Metoda koja grupira u odnosu na dani regex
	 * 
	 * @param source
	 *            - izvor
	 * @param regex
	 *            - regularni izraz
	 * @return lista imena datoteka
	 */
	private List<String> group(Path source, String regex) {
		Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
		Matcher matcher;
		List<String> list = new ArrayList<>();

		for (String string : filter(source, regex)) {
			matcher = pattern.matcher(string);
			StringBuilder builder = new StringBuilder();
			while (matcher.find()) {
				builder.append(string);
				builder.append(" 0: " + matcher.group(0));
				builder.append(" 1: " + matcher.group(1));
				builder.append(" 2: " + matcher.group(2));
				builder.append("\n");

				list.add(builder.toString());
			}
		}

		return list;
	}

	/**
	 * Metoda filtira datoteke u odnosu na njihove nazive i podudaranosti sa regexom
	 * 
	 * @param source
	 *            - izvor
	 * @param regex
	 *            - regularni izraz
	 * @return listu imena datoteka
	 */
	private List<String> filter(Path source, String regex) {
		File[] files = source.toFile().listFiles();
		List<String> forReturn = new ArrayList<>();
		Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

		for (File file : files) {
			if (file.isFile() && pattern.matcher(file.getName()).matches()) {
				forReturn.add(file.getName());
			}
		}

		return forReturn;
	}

	/**
	 * Metoda vreća ime naredbe
	 * 
	 * @return String - ime naredbe
	 */
	@Override
	public String getCommandName() {
		return name;
	}

	/**
	 * Metoda vraća opis naredbe
	 * 
	 * @return {@link List} - opis naredbe
	 */
	@Override
	public List<String> getCommandDescription() {
		return description;
	}

}
