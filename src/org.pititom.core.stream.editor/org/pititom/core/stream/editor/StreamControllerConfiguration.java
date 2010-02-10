package org.pititom.core.stream.editor;

import java.io.InputStream;
import java.io.OutputStream;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.Option;
import org.pititom.core.ConfigurationException;
import org.pititom.core.Factory;
import org.pititom.core.args4j.CommandLineParser;

/**
 *
 * @author Thomas Pérennou
 */
public class StreamControllerConfiguration {

	@Option(name = "-n", aliases = "--name", required = true)
	private String name;
	@Option(name = "-c", aliases = "--auto-connect", required = false)
	private boolean isAutoConnect = true;
	@Option(name = "-is", aliases = "--input-stream", required = false)
	private InputStream inputStream = null;
	@Option(name = "-os", aliases = "--output-stream", required = false)
	private OutputStream outputStream = null;
	@Option(name = "-se", aliases = "--stream-editor", required = false)
	private StreamEditorStack editorStack;

	public StreamControllerConfiguration(String... arguments)
			throws CmdLineException {
		CommandLineParser.registerHandler(StreamEditorStack.class,
				StreamEditorStackOptionHandler.class);
		CommandLineParser commandLineParser = new CommandLineParser(this);
		commandLineParser.parseArgument(arguments);
	}

	public StreamControllerConfiguration(String name, boolean isAutoConnect,
			Class<? extends InputStream> inputStreamClass,
			String inputStreamConfiguration,
			Class<? extends OutputStream> outputStreamClass,
			String outputStreamConfiguration, StreamEditorStack editorStack) throws ConfigurationException {
		super();
		this.name = name;
		this.isAutoConnect = isAutoConnect;
		this.inputStream = new Factory<InputStream>(inputStreamClass, inputStreamConfiguration).getInstance();
		this.outputStream = new Factory<OutputStream>(outputStreamClass, outputStreamConfiguration).getInstance();
		this.editorStack = editorStack;
	}

	public StreamControllerConfiguration(String configuration)
			throws CmdLineException {
		this(CommandLineParser.splitArguments(configuration));
	}

	public String getName() {
		return name;
	}

	public boolean isAutoConnect() {
		return isAutoConnect;
	}

	public StreamEditorStack getEditorStack() {
		return editorStack;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}
}
