package projecte;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class JsonVersion {
	public String Urlscan;
	public String CurrentVersion;
	public String line = null;
	public BufferedReader in;
	public String[] string1;
	public String[] string2;
	public String newVersion;

	public JsonVersion() throws IOException {
		try {
			URL url = new URL("http://shadowcity.net:8080/job/Project-E/api/xml");
			in = new BufferedReader(new InputStreamReader(url.openStream()));

			// Scanner s = new Scanner(url.openStream());
			// Urlscan = s.next();
			// CurrentVersion = Urlscan.toString();
			// read from your scanner
		} catch (IOException ex) {
			// there was some connection problem, or the file did not exist on
			// the server,
			// or your URL was not in the right format.
			// think about what to do now, and put it here.
			ex.printStackTrace(); // for now, simply output it.
		}
		System.out.println("Reaching");

		while ((line = in.readLine()) != null) {

			System.out.println("Reaching Stage 1");

			if (line.contains("<lastStableBuild>")) {
				System.out.println("Reaching Stage 2");

				string1 = line.split("<number>");
				string2 = string1[1].split("</number>");
				newVersion = string2[0];
			}
		}

	}
}
