# cli-search-engine
Basic command line search engine that allows for efficient search of a set of documents. Utilizes the InvertedIndex data structure to index words across documents and return word locations at search time. Can be used both to create InvertedIndex file, and to load these files in order to search.

## CLI Text Editor Tool Suite
This tools is a part of a series of Java based text tools designed to work in command line. Other tools in this suite include:
- [CLI Spellcheck](https://github.com/marcusgabrielmartinez/cli-spellcheck)

## System Requirements
- Java 1.8.0_201+

## Running the System
The easiest method for running the system is to use the provided jar file ```SearchEngine.jar```. If you'd like to recompile the binaries and jar file, follow the steps described in **Compiling** below.

### Execute the Code
```
java -jar SearchEngine.jar [--index indexFile] [--out outputFile] [searchDirectory]
```
- ```--index``` is an optional flag that allows you to specify an ```indexFile```. Specifying an ```indexFile``` will trigger the system to load the  provided InvertedIndex file and run the search program. If you don't specify ```--index``` or ```--out```, the system will assume build mode and create an InvertedIndex file (named ```InvertedIndex.out```)
- ```--out``` is an optional flag that allows you to specify an ```outFile```. Specifying an ```outFile``` will trigger the system to build an InvertedIndex file and output to the provided output filepath. If you don't specify ```--index``` or ```--out```, the system will assume build mode and create an InvertedIndex file (named ```InvertedIndex.out```)
- ```searchDirectory``` is a required path to a directory that contains the docuements you want to index/search

### Output

#### InvertedIndex File Format
The InvertedIndex file documents the directory's words, indexed via their document, index (within document), and line number instances, as well as frequency. The format of the outputted file is detailed below.
```
word doc-loc-line;...doc-loc-line; (freq)
alpha 0-0-1;1-2-1; (2)
bravo 0-2-1; (1)
charlie 0-2-3;1-1-4;1-2-3; (3)
```

#### Search Engine Output
```
Running search...
Enter your search term. Multi-word searches must be space delimited.
Enter search term or .quit to exit: 
alpha bravo delta

alpha
Filename: /foo/bar/file.txt, Line Num: 1, Word Num: 0
Filename /foo/bar/file1.txt, Line Num: 1, Word Num: 2

bravo
Filename: /foo/bar/file.txt, Line Num: 1, Word Num: 2

delta
Search term not found.

Enter search term or .quit to exit:
|
```

## Compiling
Compiling is most easily done with the provided build script.
```
./build.sh
```

## Future Updates
- Implementing multi-word phrase searches
- Implementing use of a stemmer/stop word removal
- Implementing handling of HTML/XML documents

## References
- [GeeksforGeeks Inverted Index page](https://www.geeksforgeeks.org/inverted-index/)
- [Wikipedia's Inverted Index page](https://en.wikipedia.org/wiki/Inverted_index)
