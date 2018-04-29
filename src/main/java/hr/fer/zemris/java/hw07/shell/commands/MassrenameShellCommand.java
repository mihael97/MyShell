package hr.fer.zemris.java.hw07.shell.commands;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;

public class MassrenameShellCommand implements ShellCommand {

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
	public MassrenameShellCommand() {
		description.add("Command contains methods for renaming and moving directories");
	}
	
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		String[] argumentArray = Functions.split(arguments, 5);
		Path path1 = Paths.get(argumentArray[0]).resolve(env.getCurrentDirectory());
		Path path2 = Paths.get(argumentArray[1]).resolve(env.getCurrentDirectory());

		switch (argumentArray[2]) {
		case "filter":
			print(env, filter(path1, env, argumentArray[3]));
			break;
		case "groups":
			print(env, group(path1, env, argumentArray[3]));
			break;
		case "show":
			print(env, show(path1, argumentArray[3], argumentArray[4]));
			break;
		case "execute":
			print(env, execute(path1, path2, argumentArray[3], argumentArray[4]));
		}

		return ShellStatus.CONTINUE;

	}

	private List<String> show(Path path1, String regex, String nameRegex) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<String> execute(Path path1, Path path2, String regex, String nameRegex) {
		List<String> list = show(path1, regex, nameRegex);

		return null;
	}

	private void print(Environment env, List<String> list) {
		for (String string : list) {
			env.writeln(string);
		}
	}

	private List<String> group(Path path1, Environment env, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher;
		List<String> list = new ArrayList<>();

		for (String string : filter(path1, env, regex)) {
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

	private List<String> filter(Path path1, Environment env, String regex) {
		File[] files = path1.toFile().listFiles();
		List<String> forReturn = new ArrayList<>();
		Pattern pattern = Pattern.compile(regex);

		for (File file : files) {
			if (file.isFile() && pattern.matcher(file.getName()).matches()) {
				forReturn.add(file.getName());
			}
		}

		return forReturn;
	}

	@Override
	public String getCommandName() {
		return name;
	}

	@Override
	public List<String> getCommandDescription() {
		return description;
	}

}
