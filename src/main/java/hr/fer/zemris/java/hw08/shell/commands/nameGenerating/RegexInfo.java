package hr.fer.zemris.java.hw08.shell.commands.nameGenerating;

import java.util.Objects;
import java.util.regex.Matcher;

public class RegexInfo implements NameBuilderInfo {

	private Matcher matcher;
	private StringBuilder builder;

	public RegexInfo(Matcher matcher) {
		this.matcher = Objects.requireNonNull(matcher);
		this.builder = new StringBuilder();
	}

	@Override
	public StringBuilder getStringBuilder() {
		return builder;
	}

	@Override
	public String getGroup(int index) {
		return matcher.group(index);
	}

}
