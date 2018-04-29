package hr.fer.zemris.java.hw07.shell.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Stack;

import hr.fer.zemris.java.hw07.shell.Environment;
import hr.fer.zemris.java.hw07.shell.ShellCommand;
import hr.fer.zemris.java.hw07.shell.ShellStatus;

public class PopdShellCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		try {
			@SuppressWarnings("unchecked")
			Stack<Path> stack = (Stack<Path>) env.getSharedData(Functions.CDSTACK);

			if (stack == null) {
				throw new IllegalArgumentException("Stack is null!");
			}

			if (stack.size() == 0) {
				throw new IllegalArgumentException("Stack is empty!");
			}

			Path path = stack.pop();

			if (Files.isDirectory(path)) {
				env.setCurrentDirectory(path);
				env.setSharedData(Functions.CDSTACK, stack);
			} else {
				throw new IllegalArgumentException("Path doesn't exist or it's not directory!");
			}

		} catch (ClassCastException e) {
			System.err.println("Object cannot be casted to Stack ");
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}

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
