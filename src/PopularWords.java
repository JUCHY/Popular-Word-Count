import java.io.File;
import java.util.regex.*;
import java.io.FileNotFoundException;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.datastructures.*;
import java.util.ArrayList;

public class PopularWords {
	static String parameter1;
	static String parameter2;
	static String parameter3;
	static int k;
	
	public static void main(String[] args) throws FileNotFoundException {
		MyHashTable wordtable = new MyHashTable();
		
		//gets recordfile values separated by newline
		Scanner fs = new Scanner(System.in);
		System.out.println("Enter Parameter file location: ");
		String recfile = fs.next();
		fs.close();
		Scanner ss = new Scanner(new File(recfile));
		String recdata = "";
		while(ss.hasNextLine()) {
			recdata= recdata+ss.nextLine()+'\n';
		}
		ss.close();
		
		String[] recfilearray = recdata.split("\n");
		//parameter one
			parameter1 = recfilearray[0];
		//paremeter two
		//determines if parameter two is a valid parameter, if not, sets parameter to default: name
		try {
			parameter2 = recfilearray[1];
			
			if(parameter2.equals("name") == false && parameter2.equals("scarcity") == false && parameter2.equals("frequency") == false) {
				throw new InvalidParameterException("Invalid Parameter entered. Parameter set to default: name");
			}
		}
		//if fails, sets default to name
		catch(Exception e){
			e.printStackTrace();
			parameter2 = "name";
			
		}
		//parameter three
		//determines if parameter two is a valid parameter, if not, sets parameter to default: 0
		//if k = 0, program prints out unique words
		try {
			parameter3 = recfilearray[2];
			k = Integer.parseInt(parameter3);
		}
		catch(Exception e){
			k = 0;			
		}
		//prints out given parameters
		System.out.println(parameter1);
		System.out.println(parameter2);
		System.out.println(k);
		System.out.println();
		System.out.println("------------------------------");
		System.out.println();
		//opens file containing text
		Scanner df = new Scanner(new File(parameter1));
		//stores filedata in filedata
		String filedata = "";
		while(df.hasNextLine()) {
			filedata = filedata+df.nextLine()+'\n';
		}
		df.close();
		//creates array frome filedata. Splits when it sees something that is not a word or -_'
		String[] filedataarray = filedata.split("\\W|-_'");
		//iterates to word array, and places words in hash table
		for(int i = 0; i < filedataarray.length; i++) {
			//stores word
			String word = filedataarray[i];
			//converts to lowercase
			word = word.toLowerCase();
			//removes potentialwhitespaces and empty words
			if(word.indexOf(" ")!=-1 || word.length()<=0) {
				continue;
			}
			//divides word in two if it contains --
			if(word.indexOf("--")!=-1) {
				int num = word.indexOf("--");
				String word1 = word.substring(0, num);
				String word2 = word.substring(num+2, word.length());
				wordtable.add(word1);
				wordtable.add(word2);
				continue;
			}
			//places word in hashtable
			wordtable.add(word);
		}
		//stores data from hashtable into an arraylist
		ArrayList<Entry<String,Integer>> somearray =  wordtable.createarraylist();
		//Sorts arraylist depending on parameter2
		if(parameter2.equals("name")) {
			wordtable.mergeSort(somearray, new Sort1());
		}
		if(parameter2.equals("frequency")) {
			wordtable.mergeSort(somearray, new Sort1());
			wordtable.mergeSort(somearray, new Sort2());			
		}
		if(parameter2.equals("scarcity")) {
			wordtable.mergeSort(somearray, new Sort1());
			wordtable.mergeSort(somearray, new Sort3());
		}
		//if k == 0, prints out full arraylist else prints out words from 0 to k
		if(k == 0 || k > somearray.size()) {
			printarray(somearray);
		}
		else {
			printarray(somearray, k);
		}
	
	}
	//print arraylist function
	public static void printarray(ArrayList<Entry<String,Integer>> x){
		for(int i = 0; i < x.size(); i++) {
			System.out.println(x.get(i).getKey() + "  " + x.get(i).getValue());
		}
	}
	//print arraylist function till certain value
	public static void printarray(ArrayList<Entry<String,Integer>> x, int y){
		for(int i = 0; i < y; i++) {
			System.out.println(x.get(i).getKey() + "  " + x.get(i).getValue());
		}
	}
}
	