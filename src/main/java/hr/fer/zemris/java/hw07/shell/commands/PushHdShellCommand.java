package hr.fer.zemris.java.hw07.shell.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Stack;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;

public class PushHdShellCommand implements ShellCommand {

	@SuppressWarnings("unchecked")
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		Path path = Paths.get(Functions.split(arguments, 1)[1]).resolve(env.getCurrentDirectory());
		Stack<Path> stack;
		if (!Files.isDirectory(path)) {
			if ((env.getSharedData("cdstack")) != null) {
				stack = (Stack<Path>) env.getSharedData(Functions.CDSTACK);
			} else {
				stack = new Stack<>();
			}
			stack.add(path);
			env.setSharedData(Functions.CDSTACK, stack);
			env.setCurrentDirectory(path);

			return ShellStatus.CONTINUE;
		}

		System.err.println("Path \'" + path.toString() + "\' is not directory!");
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCommandDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
