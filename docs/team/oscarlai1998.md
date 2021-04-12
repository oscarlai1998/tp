# Oscar Lai - Project Portfolio Page

## Overview

`iGraduate` is a command line application that acts as a centralised hub for NUS students
majoring in Information Security to plan their academic journey. 

___
## Summary of Contributions

* **Code contributed:** [RepoSense link](https://nus-cs2113-ay2021s2.github.io/tp-dashboard/?search=oscarlai1998&sort=groupTitle&sortWithin=title&since=2021-03-05&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)

___
### Enhancements implemented
Given below are details of my contributions to the project.

#### Implemented methods in `Ui` class of iGraduate
The Ui class is responsible for all operations that interact with the user, including scanning user input and printing
information to the screen. 

*Considerations*

The initial concern was to think of all the possible features that may require interaction
with the user. [Fuxi](https://github.com/fupernova) and [Ke Wen](https://github.com/kewenlok/) helped to implement
skeleton methods to help me implement the specific methods based on the different command classes I implemented
e.g. For ProgressCommand, I need to print the progress bar based on the number of
MCs completed out of 160.

___
#### Implemented `DeleteCommand` class of iGraduate
The DeleteCommand class is responsible for executing the `delete` command, and takes in an additional parameter of 
the module code. The module is then deleted from the Module List.

___
#### Implemented `ProgressCommand` class of iGraduate
The ProgressCommand class is responsible for executing the `progress` command, which allows users to see their 
graduation progress based on two criteria: a progress bar and the number of MCs completed out of 160MCs.

*Considerations*

The Progress Command is important to iGraduate because in essence, our app's main focus is to help users know their
progress towards graduation. Hence, we decided the best visual way to tell our users what their progress is like is 
through the progress bar which prints a black bar after every increment of 10% and the number of MCs completed out of 
160MCs. 

___
#### Implemented `ListCommand` class of iGraduate with [Ke Wen]((https://github.com/kewenlok/))
The ListCommand class is responsible for executing the `list` command, and allows an additional input parameter for
users to choose which list they want to view. `all` lists all modules on your list, `complete` lists all modules that 
have been completed (i.e. marked as done), `incomplete` lists all modules that have been added into the system but has 
not been completed(i.e. modules that you are currently taking or yet to take), `available` lists all modules that can be
taken (with prerequisites fulfilled), but have not been taken, based on the completed modules. The list shows module
details including the module `type`, `code`, `completetion status`, `name`, `grade` and
`credits`.

*Considerations*

Due to large amount of modules where modules have a specific type e.g. (core, elec, ge) and they are either completed
or incomplete, we decided to implement a list function to view the specific modules based on the list. On my part,
I implemented list all, incomplete and completed.

___
#### Implemented Exceptions 

* Implemented **InvalidModularCreditException** - This exception
  is implemented in the AddCommand and UpdateCommand Class to check if the modular credit input is negative.
* Implemented **InvalidModuleGradeException** - This exception is implemented in the UpdateCommand and DoneCommand class
  to check if the grade input is valid. 
* Implemented **PrerequisiteNotFoundException** - This exception is implemented in the AddCommand class is thrown if the
  pre-requisite module cannot be matched.
  
___
### Contributions to documentation

#### User Guide

For the user guide, I made some minor edits to improve grammar, spelling and improve the phrasing of the guide to 
audience-centric to our target audience.
___
### Contributions to DG

For the developer guide, my main contributions are the sequence and class diagrams in which I used PlantUML to
create the diagrams. Some Diagrams I contributed to is the **CapCommandSequenceDiagram** , **UiClassDiagram** and
**ProgressSequenceDiagram**. I also contributed to Cap and Progress Command and the Ui
component. In addition, I added information about Exceptions under Implementation including the summary of table of
exceptions in iGraduate.

___
### Contributions to team-based tasks

* Maintaining Issue Tracker 
    - Assigning myself and others issues
    - Creating User Stories
    
* Setting up Github
* Helping with Bug fixes 
* Supplying ideas for features and possible exceptions to be caught

___
### Reviews and PRs

I helped the team with repository maintenance, mostly about git (Pull Requests, Branches, Merge Conflicts etc).
Here is a list of pull requests I helped to review and merge:

[#52](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/52), [#56](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/56)
, [#59](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/59)
, [#60](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/60)
, [#71](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/71)
, [#83](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/83)
, [#110](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/110)
, [#111](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/111)
, [#113](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/113)
, [#120](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/120)
, [#143](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/143)
, [#149](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/149)
, [#155](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/155)
, [#162](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/162)
, [#165](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/165)
, [#170](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/170)

___
### Contributions beyond the project team

- Performed bug hunting on such as [All-in-OneNUS](https://github.com/AY2021S2-CS2113-T09-3/tp).
- Recommended possible fixes for bug found on other products.
- Reviewed User Guide and Developer Guide for other teams and provided constructive suggestions for improvement.
