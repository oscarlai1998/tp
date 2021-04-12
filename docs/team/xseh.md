### Seh Xin Ru - Project Portfolio Page

### **Overview** ###

iGraduate is a command-line application for <b>NUS students majoring in Information Security</b> to plan their academic journey. iGraduate comes equipped with tools such as module storage, a CAP calculator and academic progression indicators. This allows you to check your graduation progress, check program requirements and make more informed decisions about future modules to take. 

### **Summary of Contributions** ###

### **Code Contributed** ###

Access my contribution on [RepoSense](https://nus-cs2113-ay2021s2.github.io/tp-dashboard/?search=xseh&sort=groupTitle&sortWithin=title&since=2021-03-05&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=xseh&tabRepo=AY2021S2-CS2113T-W09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other). 

### **Enhancements Implemented** ###

#### ***Storage*** with [Ke Wen](https://github.com/kewenlok) ####

Implemented the storage function of iGraduate, including the considerations, researching of third-party libraries and its development. 
The most difficult portion of implementing the storage function was the use of external libraries, specifically the JSON library. I had to research the limitations and different flavours of JSON (settled on GSON at the end), learn its usage and apply the library to iGraduate. 

#### ***Parser*** with [Fuxi](https://github.com/fupernova) ####

I implemented the Parser function. While he worked on the higher layers of the parser, I coded the foundational code for the parser including the logic behind the parsing structure, the extraction method and the syntax (i.e. flags) used in the commands. The challenging portion would be designing the behaviour of the Parser. For instance, deciding on how to identify and split the input. The command was first split into the command type (with its parameters) and the flags. The initial arrays used (which were memory efficient) were changed to ArrayList, making use of its class functions (like indexOf() and size()). Another challenge was the use of regex to identify and isolate flags from the main command parameters. 

### **Contributions to UG** ###

1. [Introduction](https://ay2021s2-cs2113t-w09-2.github.io/tp/UserGuide.html#introduction)
1. [User Guide usage](https://ay2021s2-cs2113t-w09-2.github.io/tp/UserGuide.html#ug-guide-usage)
1. [Quick start](https://ay2021s2-cs2113t-w09-2.github.io/tp/UserGuide.html#quick-start) (only the sample output of a successful setup)
1. [Update](https://ay2021s2-cs2113t-w09-2.github.io/tp/UserGuide.html#update-module-information-update)
1. [Storage](https://ay2021s2-cs2113t-w09-2.github.io/tp/UserGuide.html#storage-of-data)
1. [Frequently Asked Questions (FAQ)](https://ay2021s2-cs2113t-w09-2.github.io/tp/UserGuide.html#frequently-asked-questions)
1. [Command Summary](https://ay2021s2-cs2113t-w09-2.github.io/tp/UserGuide.html#command-summary)
1. Markdown and formatting of DG
1. Converted all the UG from a word document into GitHub markdown

### **Contributions to the DG** ###

1. [Introduction](https://ay2021s2-cs2113t-w09-2.github.io/tp/DeveloperGuide.html#introduction)
1. [Value proposition](https://ay2021s2-cs2113t-w09-2.github.io/tp/DeveloperGuide.html#value-proposition)
1. [Parser](https://ay2021s2-cs2113t-w09-2.github.io/tp/DeveloperGuide.html#parser)
1. [Storage](https://ay2021s2-cs2113t-w09-2.github.io/tp/DeveloperGuide.html#storage-component)
1. [Update](https://ay2021s2-cs2113t-w09-2.github.io/tp/DeveloperGuide.html#command)
1. [User stories (v1.0 and v2.0)](https://ay2021s2-cs2113t-w09-2.github.io/tp/DeveloperGuide.html#appendix-b-user-stories)
1. [Non-functional requirements](https://ay2021s2-cs2113t-w09-2.github.io/tp/DeveloperGuide.html#appendix-c-non-functional-requirements)
1. Adding some of the icons used in the DG (like the arrow, bulk and paper icons)
1. Markdown and formatting of DG

### **Contributions to Team-based Tasks** ###

1. Designing the overall code behaviour 
1. Setting up the GitHub
1. Making necessary general code enhancements
1. Maintaining the issue tracker 
    - Assigning myself or others
    - Creating user stories
    - Creating issues for bugs found
    - Reviewing the issues from the others

### **Review/Mentoring Contributions** ###

1. Bug fixes or suggestions to bug fixes
1. Implemented helper functions that are used throughout the application

### **Contributions Beyond the Project Team** ###

1. Testing other's products including EasyLog, Finux, etc. 
1. Suggestions for bug fixes
1. Guided with a description of my high-level implementation logic (based on what I have coded)