# Binary-Search-Tree
This program does not have an interface and runs from the command line.

Specifications:
1.	The exercise will be started in class, collaboratively, then finished as a small project on your own.
2.	The words are sorted in the BST by keying off every node’s word: words that come alphabetically before the node’s word will be placed in the left subtree, while words that are alphabetically later will be placed in the right subtree.  This algorithm is applied recursively.
3.	In addition, a node will keep track of how many times that particular word appeared by having an additional node field, for a total of four fields: left subtree, right subtree, the word for that node (a String), and a count for that word (an int).
4.	The tree will insert words, but will not delete any.  
5.	Insertion always starts at the root, and new words always become a new leaf.  Words already in the tree will have their count incremented rather than a new node created and inserted.  This gives two base cases for the insertion traversal: 1) the word is found; increment the count, return, 2) the traversal reaches the point where it would step down again but can’t because the alphabetically-correct subtree is empty; create a new node with the word and a count of one, return.
6.	Write three methods to print the tree: pre-order, in-order, and post-order, where printing the node is the “visit.”  Note that in-order should print the words in alphabetical order – check  your output.  Output goes to a text file.
7.	The program will have a tree class containing main with a private, embedded tree node class.  Write a node constructor that receives its word as a parameter.  The node class will also have a print method for the data in that one node.  The tree class will have the recursive print method for the whole tree.
8.	The user will input the name of the file containing the words.  The file will not have any non-letter words, and they will all be lowercase.  They are not, however, all on separate lines.
9.	Output will be to a text file, starting with a header containing your name, the course (CS 2), the semester (fall 2017), and a title.  It will then print the name of the input data file, followed by the output from the three traversals, labeled.  Sample traversal output:

In-order Traversal Output

word: cat, count: 5
word: dog, count: 2
(etc.)

10.	Incremental development.  Start with simpler versions of the program.
  a.	Create a tree without the counter.
  b.	Input a few words from the user before using a file.
  c.	Output words to the screen prior to using an output file.
11.	I will post several data files for you to use, along with one of the traversals and its expected results.
