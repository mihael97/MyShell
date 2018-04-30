package hr.fer.zemris.java.hw08.shell.commands.nameGenerating;

import java.util.List;
import java.util.Objects;

public class ExecutorNameBuilder implements NameBuilder {

	private List<NameBuilder> list;
	static StringBuilder stringBuilder;

	public ExecutorNameBuilder(List<NameBuilder> list) {
		this.list = Objects.requireNonNull(list);
		ExecutorNameBuilder.stringBuilder = new StringBuilder();
	}

	@Override
	public void execute(NameBuilderInfo info) {
		list.forEach(e -> e.execute(info));

		stringBuilder = new StringBuilder(info.getStringBuilder());
	}

	public static StringBuilder getStringBuilder() {
		return stringBuilder;
	}

}
