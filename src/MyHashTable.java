import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.ArrayList;
public class MyHashTable {
	//hashtable
	Hashtable<String, Integer> wordcount;
	public MyHashTable() {
		 wordcount = new Hashtable<String, Integer>();
	}
	//places words in hashtable
	public void add(String x) {
		if(wordcount.containsKey(x)) {
			int num = wordcount.get(x);
			wordcount.replace(x, num+1);
		}
		else{
			wordcount.put(x, 1);
		}
	}
	//string representation of hashtable
	public String toString() {
		return wordcount.toString();
	}
	//creates hashtable representation in an arraylist
	public ArrayList<Entry<String,Integer>> createarraylist(){
		ArrayList<Entry<String,Integer>> words = new ArrayList<Entry<String, Integer>>(wordcount.entrySet());
		return words;
	}
	//merges to arraylists into a bigger arraylists, while sorting them
	public void merge(ArrayList<Entry<String,Integer>> S1, ArrayList<Entry<String,Integer>> S2, ArrayList<Entry<String,Integer>> list, Comparator<Entry<String,Integer>> comp) {
			int i = 0;
			int j= 0;
			while(i+j<list.size()) {
				
				if(j == S2.size() || (i<S1.size() && comp.compare(S1.get(i),S2.get(j))<=0)) {
					list.set(i+j, S1.get(i));
					i++;
				}
				else {
					list.set(i+j, S2.get(j));
					j++;
				}
			}
			
		}
	//mergesort algorithm
		public void mergeSort(ArrayList<Entry<String,Integer>> unsortedwords, Comparator<Entry<String,Integer>> comp) {
		int n = unsortedwords.size();
		if(n<2)return;
		int mid = n/2;
		ArrayList<Entry<String,Integer>> somelist1 = copyArrayList(unsortedwords, 0, mid);
		ArrayList<Entry<String,Integer>> somelist2 = copyArrayList(unsortedwords, mid,n);
		
		mergeSort(somelist1, comp);
		mergeSort(somelist2, comp);
		
		merge(somelist1,somelist2, unsortedwords,comp);
			
		}
		//creates shallowcopy of Arraylist
		public static ArrayList<Entry<String,Integer>> copyArrayList(ArrayList<Entry<String,Integer>> original,int x, int y){
			ArrayList<Entry<String,Integer>> somelist = new ArrayList<Entry<String,Integer>>();
			for(int i = x; i < y; i++) {
				somelist.add(original.get(i));
			}
			return somelist;
		}
		

}