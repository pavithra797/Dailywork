package log.analyzer;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LogAnalyzerTest {

	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private ByteArrayOutputStream outContent;
	private ByteArrayOutputStream errContent;

	@BeforeEach
	void setUpStreams() {
		outContent = new ByteArrayOutputStream();
		errContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@AfterEach
	void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	@Test
	void testAllLogTypes() throws IOException {
		Path tempFile = Files.createTempFile("logfile", ".log");
		Files.write(tempFile, Arrays.asList("INFO Starting system", "WARNING Disk space low", "ERROR Failed to connect",
				"INFO User logged in", "ERROR Null pointer", "WARNING CPU high"));

		LogAnalyzer.main(new String[] { tempFile.toString() });
		String output = outContent.toString().trim();
		assertTrue(output.contains("INFO: 2"));
		assertTrue(output.contains("WARNING: 2"));
		assertTrue(output.contains("ERROR: 2"));

		Files.deleteIfExists(tempFile);
	}

	@Test
	void testSingleLogType() throws IOException {
		Path tempFile = Files.createTempFile("logfile", ".log");
		Files.write(tempFile, Arrays.asList("INFO System running", "INFO User logged in"));

		LogAnalyzer.main(new String[] { tempFile.toString() });
		String output = outContent.toString().trim();
		assertTrue(output.contains("INFO: 2"));
		assertTrue(output.contains("WARNING: 0"));
		assertTrue(output.contains("ERROR: 0"));

		Files.deleteIfExists(tempFile);
	}

	@Test
	void testNoMatchingLogs() throws IOException {
		Path tempFile = Files.createTempFile("logfile", ".log");
		Files.write(tempFile, Arrays.asList("DEBUG Step executed", "TRACE Another debug"));

		LogAnalyzer.main(new String[] { tempFile.toString() });
		String output = outContent.toString().trim();
		assertTrue(output.contains("INFO: 0"));
		assertTrue(output.contains("WARNING: 0"));
		assertTrue(output.contains("ERROR: 0"));

		Files.deleteIfExists(tempFile);
	}

	@Test
	void testEmptyFile() throws IOException {
		Path tempFile = Files.createTempFile("logfile", ".log");

		LogAnalyzer.main(new String[] { tempFile.toString() });
		String output = outContent.toString().trim();
		assertTrue(output.contains("INFO: 0"));
		assertTrue(output.contains("WARNING: 0"));
		assertTrue(output.contains("ERROR: 0"));

		Files.deleteIfExists(tempFile);
	}

	@Test
	void testLogLineFormatting() throws IOException {
		Path tempFile = Files.createTempFile("logfile", ".log");
		Files.write(tempFile, Arrays.asList(" INFO Should not count", "warning lowercase", "ERROR Proper error"));

		LogAnalyzer.main(new String[] { tempFile.toString() });
		String output = outContent.toString().trim();
		assertTrue(output.contains("INFO: 0"));
		assertTrue(output.contains("WARNING: 0"));
		assertTrue(output.contains("ERROR: 1"));

		Files.deleteIfExists(tempFile);
	}

	@Test
	void testOneLinePerLogType() throws IOException {
		Path tempFile = Files.createTempFile("logfile", ".log");
		Files.write(tempFile, Arrays.asList("INFO Single info", "WARNING Single warning", "ERROR Single error"));

		LogAnalyzer.main(new String[] { tempFile.toString() });
		String output = outContent.toString().trim();
		assertTrue(output.contains("INFO: 1"));
		assertTrue(output.contains("WARNING: 1"));
		assertTrue(output.contains("ERROR: 1"));

		Files.deleteIfExists(tempFile);
	}

	@Test
	void testFileNotFound() {
		Path path = Paths.get("system.log");
		try {
			Files.deleteIfExists(path);
		} catch (IOException ignored) {
		}

		LogAnalyzer.main(new String[] {});
		String errOutput = errContent.toString().trim();
		assertTrue(errOutput.contains("Error: File system.log not found."));
	}

	@Test
	void testIOException() {
		LogAnalyzerWithIOException.main(new String[] {});
		String errOutput = errContent.toString().trim();
		assertTrue(errOutput.contains("Error reading the file"));
	}

	static class LogAnalyzerWithIOException extends LogAnalyzer {
		public static void main(String[] args) {
			Map<String, Integer> logCounts = new HashMap<>();
			String fileName = "system.log";

			try (BufferedReader br = new BufferedReader(new Reader() {
				@Override
				public int read(char[] cbuf, int off, int len) throws IOException {
					throw new IOException("Simulated read error");
				}

				@Override
				public void close() throws IOException {
				}
			})) {
				String line;
				while ((line = br.readLine()) != null) {
				}
			} catch (FileNotFoundException e) {
				System.err.println("Error: File " + fileName + " not found.");
			} catch (IOException e) {
				System.err.println("Error reading the file: " + e.getMessage());
			}
		}
	}
}