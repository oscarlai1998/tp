# Seh Xin Ru - Project Portfolio Page

## **Overview** ##

iGraduate is a command line application for <b>NUS students majoring in Information Security</b> to plan their academic journey. iGraduate comes equipped with tools such as module storage, a CAP calculator and academic progression indicators. This allows you to check your graduation progress, check program requirements and make more informed decisions about future modules to take. 

## **Summary of Contributions** ##

### **Code Contributed** ###

Access my contribution on [RepoSense](https://nus-cs2113-ay2021s2.github.io/tp-dashboard/?search=xseh&sort=groupTitle&sortWithin=title&since=2021-03-05&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=xseh&tabRepo=AY2021S2-CS2113T-W09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other). 

### **Enhancements Implemented** ###

#### ***Storage*** ####

With [Ke Wen](/team/kewenlok.md)

I implemented the storage function of iGraduate, including the considerations, researching of third-party libraries and its development. 
The most difficult portion of implementing the storage function was the use of external libraries, specifically the JSON library.  We wanted a robust storage system that is resilient to bugs, especially arising from the use of custom delimiters. In the end, we decided that the use of Java libraries was the way to go, since it is more developed and comes fully equipped to handling parsing or modification of the storage files. From the various version of JSON, I ultimately decided on GSON since it is easier to understand and straightforward to use, and comes with the features flexible enough to accommodate the use of custom objects in a single function. The advantage applies to both the loading and saving of module list to and fro th storage file. I had to research on the limitations and different flavours of JSON, learn its usage and apply the library to the application.

#### ***Parser*** ####

With [Fuxi](/team/fupernova.md)

I implemented the Parser function. While he worked on the higher layers of parser, I coded the foundational code for parser including the logic behind the parsing structure, the extraction method and the syntax (i.e. flags) used in the commands. 

Ultimately, I decided that the command should first be split into the command type (with its parameters) and the flags. Initially, I was using arrays to utilise the efficient memory allocation and standard size. Ultimately, the type was changed to an array list instead, to make use of its class functions (like indexOf() and size()). 

After, I decided that the flags should be used to parse other parameters, using regex to identify and isolate them from the main command. Instead of keeping them as a string to search for the flags, I split them up according to their delimiters dashes and space ('-', ' ') and getting the value in the next index. This way, it would save the trouble of having a start and end index since the command flags (except -n) only allows one value (without spaces). 

### **Contributions to UG** ###

I documented the sections that was implemented by me, that is, the parser and storage. I also converted all the UG from a word document into GitHub markdown in the user guide file. 

Other contributions to the UG are:
1. [Introduction](https://ay2021s2-cs2113t-w09-2.github.io/tp/UserGuide.html#introduction)
1. [UG usage](https://ay2021s2-cs2113t-w09-2.github.io/tp/UserGuide.html#ug-guide-usage)
1. [Quick start](https://ay2021s2-cs2113t-w09-2.github.io/tp/UserGuide.html#quick-start) (only the sample output of a successful setup)
1. [Frequently Asked Questions (FAQ)](https://ay2021s2-cs2113t-w09-2.github.io/tp/UserGuide.html#frequently-asked-questions)
1. [Command Summary](https://ay2021s2-cs2113t-w09-2.github.io/tp/UserGuide.html#command-summary)
1. Markdown and formatting of DG

### **Contributions to the DG** ###

I also documented the sections that was implemented by me, that is, the parser and storage function. This includes the UML class, object and sequence diagrams.

Other contributions to the DG are: 
1. [Introduction](https://ay2021s2-cs2113t-w09-2.github.io/tp/DeveloperGuide.html#introduction)
1. [Value proposition](https://ay2021s2-cs2113t-w09-2.github.io/tp/DeveloperGuide.html#value-proposition)
1. [User stories (v1.0 and v2.0)](https://ay2021s2-cs2113t-w09-2.github.io/tp/DeveloperGuide.html#appendix-b-user-stories)
1. [Non-functional requirements](https://ay2021s2-cs2113t-w09-2.github.io/tp/DeveloperGuide.html#appendix-c-non-functional-requirements)
1. Adding some of the icons used in the DG (like the arrow, bulk and paper icons)
1. Markdown and formatting of DG


### **Contributions to Team-based Tasks** ###

1. Designing the overall code behaviour 
1. Setting up the github
1. Making necessary general code enhancements
1. Maintaining the issue tracker 
    - Assigning myself or others
    - Creating user stories
    - Creating issues for bugs found
    - Reviewing the issues from the others
1. Compilation of the java file for release in v2.0 (I did not officially release it)

### **Review/Mentoring Contributions** ###

1. Bug fixes or suggestions to bug fixes
1. Edited, added and restructured other UG and DG portions
1. Implemented helper functions that are used throughout the application
1. General formatting and readability of UG and DG, including navigation buttons and links

### **Contributions Beyond the Project Team** ###

1. Bug hunting on other's products
1. Testing other's products including EasyLog, Finux, etc. 
1. Suggestions to bug fixes
1. Provided guidance with a description of my high-level implementation logic (based on what I have coded)