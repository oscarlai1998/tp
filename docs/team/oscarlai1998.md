# Oscar Lai - Project Portfolio Page

## Overview

`iGraduate` is a command line application that acts as a centralised hub for NUS students
majoring in Information Security to plan their academic journey. 

## Summary of Contributions

* **Code contributed:** [RepoSense link](https://nus-cs2113-ay2021s2.github.io/tp-dashboard/?search=oscarlai1998&sort=groupTitle&sortWithin=title&since=2021-03-05&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)

### Enhancements implemented
Given below are details of my contributions to the project.

#### Implemented methods in `Ui` class of iGraduate
*Considerations*

The initial concern was to think of all the possible features that may require interaction
with the user. [Fuxi](https://github.com/fupernova) and [Ke Wen](https://github.com/kewenlok/) helped to implement
skeleton methods to help me implement the specific methods based on the different command classes I implemented
e.g. For ProgressCommand, I need to print the progress bar based on the number of
MCs completed out of 160.

#### Implemented `DeleteCommand` class of iGraduate
The DeleteCommand class is responsible for executing the `delete` command, and takes in an additional parameter of 
the module code. The module is then deleted from the Module List.

#### Implemented `ProgressCommand` class of iGraduate
*Considerations*

The Progress Command is important to iGraduate because in essence, our app's main focus is to help users know their
progress towards graduation. Hence, the progress command was important so that we can display visually the graduation
progress through a progress bar and ratio of MCs completed out of 160.

#### Implemented `ListCommand` class of iGraduate with [Ke Wen]((https://github.com/kewenlok/))
The ListCommand class is responsible for executing the `list` command, and allows an additional input parameter for
users to choose which list they want to view. `all`, `complete`, `incomplete` , `available` . The list shows module
details including the module `type`, `code`, `completetion status`, `name`, `grade` and
`credits`.

*Considerations*

List command was very challenging to implement because it required us to isolate the specific detail that 
the user wants and list them out.

### Contributions to documentation

#### User Guide

For the user guide, I made some minor edits to improve grammar, spelling and improve the phrasing of the guide to 
audience-centric to our target audience.

### Contributions to DG

For the developer guide, my main contributions are the sequence and class diagrams in which I used PlantUML to
create the diagrams. e.g. **CapCommandSequenceDiagram** , **UiClassDiagram** and
**ProgressSequenceDiagram**. In addition, I wrote about Ui and Exception under Design

### Contributions to team-based tasks

* Maintaining Issue Tracker 
    - Assigning myself and others issues
    - Creating User Stories
* Setting up Github
* Helping with Bug fixes 
* Supplying ideas for features and possible exceptions to be caught
* Implemented Exceptions: **PrerequisiteNotFoundException**, **InvalidModuleGradeException**, **InvalidModularCreditException**.

### Reviews and PRs

I helped the team with repository maintenance. 
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

### Contributions beyond the project team

- Performed bug hunting on such as [All-in-OneNUS](https://github.com/AY2021S2-CS2113-T09-3/tp) and recommended possible 
  fixes for bugs found.
- Reviewed User Guide and Developer Guide for other teams and provided constructive suggestions for improvement.
