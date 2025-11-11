import java.util.Dictionary;
import java.util.Hashtable;

public class Translation {
    private Dictionary<String, String> content = new Hashtable<>();

    public String getText(String text) {
        return content.get(text);
    }

    public void setText(String key, String value) {
        content.put(key, value);
    }
}
