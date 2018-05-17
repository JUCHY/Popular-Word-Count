import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map.Entry;
public class Sort1<K,V extends Comparable<V>> implements Comparator<Entry<String,Integer>>{
	//sorts alphabetically

	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		// TODO Auto-generated method stub
		return o1.getKey().compareTo(o2.getKey());
	}
}
