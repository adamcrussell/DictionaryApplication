import java.util.HashMap;

public class WordInformation {
	private HashMap<String, String> h;

	public WordInformation() {
		h = new HashMap<String, String>();
		h.put("cat", "small furry animal. eats mice.");
		h.put("rabbit",  "small furry animal. eats carrots.");
	}

	public String lookup(String s) {
		String definition = h.get(s);
		if(definition == null) {
			return "Word not Found";
		}
		return definition;
	}

}
