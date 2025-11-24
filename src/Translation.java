import java.util.Dictionary;
import java.util.Hashtable;

public class Translation {
    //Dicionario para armazenar as frases
    private Dictionary<String, String> content = new Hashtable<>();

    public String getText(String text) {
        return content.get(text);
    }

    public void setText(String key, String value) {
        content.put(key, value);
    }
}
