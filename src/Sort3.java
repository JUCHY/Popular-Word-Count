import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map.Entry;
public class Sort3<K,V extends Comparable<V>> implements Comparator<Entry<String,Integer>>{
	 // sorts based on frequency, ascending order, smallest to largest

	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		// TODO Auto-generated method stub
		int num1 = o1.getValue();
		int num2 = o2.getValue();
		return num1-num2;
	}
}
