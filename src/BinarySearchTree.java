import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BinarySearchTree {
	private Leaf root;	
	private StringBuffer outputStrings = new StringBuffer();
	private final String inFileName = "testwords.txt";
	private final String outFileName = "testwordsOut.txt";
	
	public BinarySearchTree(){
		root = new Leaf();
		
	}
	
	public BinarySearchTree(String startString){
		root = new Leaf(startString);
		
	}
	
	public Leaf getRoot(){
		
		return this.root;
	}
	
	public ArrayList<String> getInOrder(Leaf start){
		ArrayList<String> wordList = new ArrayList<String>();
		if (start == null){
			
			return new ArrayList<String>();
		}
		wordList.addAll(getInOrder(start.getLeft()));
		wordList.add(start.getLeafData());
		wordList.addAll(getInOrder(start.getRight()));
		
		return wordList;
	}
	
	public ArrayList<String> getPreOrder(Leaf start){
		ArrayList<String> wordList = new ArrayList<String>();
		if (start == null){
			
			return new ArrayList<String>();
		}
		wordList.add(start.getLeafData());
		wordList.addAll(getPreOrder(start.getLeft()));
		wordList.addAll(getPreOrder(start.getRight()));
		
		return wordList;
	}
	
	public ArrayList<String> getPostOrder(Leaf start){
		ArrayList<String> wordList = new ArrayList<String>();
		if (start == null){
			
			return new ArrayList<String>();
		}
		
		wordList.addAll(getPostOrder(start.getLeft()));
		wordList.addAll(getPostOrder(start.getRight()));
		wordList.add(start.getLeafData());
		
		return wordList;
	}
	
	public ArrayList<Integer> getCountInOrder(Leaf start){
		ArrayList<Integer> countList = new ArrayList<Integer>();
		
		if (start == null){
			
			return new ArrayList<Integer>();
		}
		countList.addAll(getCountInOrder(start.getLeft()));
		countList.add(start.getWordCount());
		countList.addAll(getCountInOrder(start.getRight()));
		
		return countList;
	}
	
	public ArrayList<Integer> getCountPreOrder(Leaf start){
		ArrayList<Integer> countList = new ArrayList<Integer>();
		if (start == null){
			
			return new ArrayList<Integer>();
		}
		countList.add(start.getWordCount());
		countList.addAll(getCountPreOrder(start.getLeft()));
		countList.addAll(getCountPreOrder(start.getRight()));
		
		
		return countList;
	}
	
	public ArrayList<Integer> getCountPostOrder(Leaf start){
		ArrayList<Integer> countList = new ArrayList<Integer>();
		if (start == null){
			
			return new ArrayList<Integer>();
		}
		countList.addAll(getCountPostOrder(start.getLeft()));
		countList.addAll(getCountPostOrder(start.getRight()));
		countList.add(start.getWordCount());
		
		return countList;
	}
	
	public Leaf insertWord(Leaf current, String insertString){
		if (current == null){
			Leaf insertLeaf = new Leaf(insertString);
			current = insertLeaf;
			return current;
		}
		
		if (insertString.compareTo(current.data) <= 0){ //Insert string is less than data at root
			if (insertString.equals(current.data)){
				current.wordCount += 1;
				
			}
			else {
				current.left = insertWord(current.left, insertString);
				
			}	
		}
		else if (insertString.compareTo(current.data) > 0){
			if (insertString.equals(current.data)){
				current.wordCount += 1;
				
			}
			else {
				current.right = insertWord(current.right, insertString);
				
			}
			
		}
		
		return current;
	}
	
	public void readFileBuildTree(){ //Read file and build tree
		String line;
		String [] words;
		
		try {
			BufferedReader parseFile = new BufferedReader(new FileReader(inFileName));
			StringBuilder buildString = new StringBuilder();
			line = parseFile.readLine();
			
			while (line != null){
				buildString.append(line);
				buildString.append(System.lineSeparator());
				line = parseFile.readLine();
				
			}
			words = buildString.toString().split("\\s+");
			for (int i = 0; i < words.length; i++){
				this.insertWord(this.getRoot(), words[i]);
				
			}
			
			parseFile.close();
		}
		catch (FileNotFoundException noFile) {
			System.out.println("File not found.");
			
			
		}
		catch (IOException IOError){
			System.out.println("IOError");
			IOError.printStackTrace();
			
		}
		
	}
	
	public void appendInOrder(ArrayList<String> stringsInOrder, ArrayList<Integer> countsInOrder){
		outputStrings.append("In Order:");
		outputStrings.append(System.lineSeparator());
		for (int i = 1; i < stringsInOrder.size(); i++){ //i = 1 to skip blank space word
			outputStrings.append("word: " + stringsInOrder.get(i) + ", count: " + countsInOrder.get(i) + " ");
			outputStrings.append(System.lineSeparator());
		}
		outputStrings.append(System.lineSeparator());
		
	}
	
	public void appendPreOrder(ArrayList<String> stringsPreOrder, ArrayList<Integer> countsPreOrder){
		outputStrings.append("Pre-Order:");
		outputStrings.append(System.lineSeparator());
		for (int i = 1; i < stringsPreOrder.size(); i++){
			outputStrings.append("word: " + stringsPreOrder.get(i) + ", count: " + countsPreOrder.get(i) + " ");
			outputStrings.append(System.lineSeparator());
		}
		outputStrings.append(System.lineSeparator());
	}
	
	public void appendPostOrder(ArrayList<String> stringsPostOrder, ArrayList<Integer> countsPostOrder){
		outputStrings.append("Post-Order:");
		outputStrings.append(System.lineSeparator());
		for (int i = 0; i < stringsPostOrder.size() - 1; i++){
			outputStrings.append("word: " + stringsPostOrder.get(i) + ", count: " + countsPostOrder.get(i) + " ");
			outputStrings.append(System.lineSeparator());
			
		}
		outputStrings.append(System.lineSeparator());
		
	}
	
	public void appendHeader(){
		outputStrings.append("Kenji Morizono, CS 2050, Fall 2017, Binary Search Tree");
		outputStrings.append(System.lineSeparator());
		
	}
	
	public void printTreeToFile(){
		try {
			BufferedWriter reportFileOut =
			new BufferedWriter(new FileWriter(new File(outFileName)));
			reportFileOut.write(outputStrings.toString());
			reportFileOut.flush();
			reportFileOut.close();
			
		}
		catch (IOException IOError){
			IOError.printStackTrace();
			
		}
		
	}
	
	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		tree.appendHeader();
		tree.readFileBuildTree();
		tree.appendInOrder(tree.getInOrder(tree.getRoot()), tree.getCountInOrder(tree.getRoot()));
		tree.appendPreOrder(tree.getPreOrder(tree.getRoot()), tree.getCountPreOrder(tree.getRoot()));
		tree.appendPostOrder(tree.getPostOrder(tree.getRoot()), tree.getCountPostOrder(tree.getRoot()));
		tree.printTreeToFile();
		
	}
	
	private class Leaf {
		Leaf left;
		Leaf right;
		String data;
		int wordCount;
		
		public Leaf(){
			left = null;
			right = null;
			data = "";
			wordCount = 0;
			
		}
		
		public Leaf(String setString){
			left = null;
			right = null;
			data = setString;
			wordCount = 1;
			
		}
		
		public void setLeft(Leaf leftLeaf){
			this.left = leftLeaf;
			
		}
		
		public void setRight(Leaf rightLeaf){
			this.right = rightLeaf;
			
		}
		
		public Leaf getLeft(){
			
			return this.left;
		}
		
		public Leaf getRight(){
			
			return this.right;
		}
		
		public void setLeafData(String setString){
			this.data = setString;
			
		}
		
		public String getLeafData(){
			
			return this.data;
		}
		
		public void incrementWordCount(){
			this.wordCount += 1;
			
		}
		
		public int getWordCount(){
			
			return this.wordCount;
		}
		
	}
	
}