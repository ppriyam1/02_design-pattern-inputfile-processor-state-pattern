# CSX42: Assignment 2
## Name: Preeti Priyam

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [channelpopularity/src](./channelpopularity/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile channelpopularity/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile channelpopularity/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile channelpopularity/src/build.xml run -Dinput="input.txt" -Doutput="output.txt"
```
Note: Arguments accept the absolute path of the files.

-----------------------------------------------------------------------
Justification for the Data Structures used in the assignment in terms of time and/or space complexity.

List:
I have used List implementation ArrayList because it is a dynamic size array which is better in terms of space and time complexity as we don't need to define a static size for the array. It's better to store objects in the ArrayList as it provides type checking through generics. Moreover, when compare to other data structure such as LinkedList, Stack, Queue, etc., in terms of time complexity for inserting, reading a value, ArrayList is better.

Time complexity for adding an element into an arbitrary indices of the List : O(n)
Time complexity for adding last element into the List : O(1)
Time complexity for reading an element from List : O(1)
Time complexity for searching an element from List : O(n)

Map:
1. HashMap
I have used Map implementation HashMap. HashMap access element faster when compared to other data structure due to its hashing technique. HashMap provides constant time complexity for basic operations such as get and put if the hash function is properly implemented.

2. ConcurrentHashMap
I have used Map implementation ConcurrentHashMap for storing the videos for the context. ConcurrentHashMap provides concurrent readability to multiple threads in constant time.

Time complexity for adding an element into Map : O(1)
Time complexity for reading an element from Map : O(1)
Time complexity for searching an element from Map : O(n)

## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [06/24/2020]
