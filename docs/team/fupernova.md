# Xia Fuxi - Project Portfolio Page

## Overview

`iGraduate` is a command line application that acts as a centralised hub for NUS students majoring in Information 
Security to plan their academic journey. It is equipped features to help students make informed decisions about their academic plans.

### Summary of Contributions
<b>Code contributed</b>

[Link to my code contribution.](https://nus-cs2113-ay2021s2.github.io/tp-dashboard/?search=fupernova&sort=groupTitle&sortWithin=title&since=2021-03-05&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)

### Enhancements implemented

Given below are details of my contributions to the project.

#### Implemented `Ui` class of iGraduate

The Ui class is responsible for all operations that interact with the user, including scanning user input and printing 
information to the screen.

*Considerations*

Ui was challenging to implement as many methods depended on the implementations of other classes. I laid out skeleton 
methods of the possible printing methods required in the `Ui` class so that my teammates would be able to access or edit
the methods they required easily.

#### Implemented `Parser` class of iGraduate together with [Xin Ru](https://github.com/xseh/)

The Parser class takes in user input, determines the type of command and extracts the information required to 
execute the command before handing control back to the main program for the execution of the command.

*Considerations*

Coding the parser was challenging as all commands available in our program have a unique input format. We used a 
two-tiered parsing system. Firstly, the user command is parsed. Secondly, parameters would be extracted based on command
name.

#### Implemented `HelpCommand` class of iGraduate
The  `help` command provides users with a quick reference guide regarding the proper usage of iGraduate.

*Considerations*

We decided to have separate help pages for each command. This methodology helps make the quick guide less cluttered and 
more targeted towards specific commands that users intend to look up.

#### Implemented test code for multiple methods

Unit testing and integration testing was important to our project as it helped us to verify the correctness of the program
as well as help us identify bugs that we missed out on. We focused on testing for `Parser` and `Command`.

*Considerations*

Unit and Integration testing for `Parser` and `Command` classes was priority as they deal with user input.  Unit tests 
were implemented with possible inputs that would break the program. Integration tests were introduced to ensure that the
core functionality was working.

### Contributions to documentation

#### User Guide

I added the segments for components I implemented to the UG. I also added the segment for Add command.
I also helped to update the FAQ, design the logo and rephrase the introduction. Apart from that, I made many edits 
improve the phrasing of the guide to be more audience-centric and beginner-friendly.

### Contributions to DG

My main contribution to DG is creating sequence diagrams. I also contributed to the standardisation of sequence 
diagrams. I also contributed explanations for the components implemented by me. Apart from that, I also added the 
details for `ModuleList` class, `Command` class and the `Add` command.

### Contributions to team-based tasks

I edited the introduction and the content pages for UG and DG. I also reviewed my teammates' code, in particular look 
out for the reasons for failed gradle builds. I also hunted for bugs by writing unit tests for `Parser` and `Command`. 
I also tagged issues found and assign issues to myself and teammates.

### Reviews and PRs

I helped the team with repository maintenance, mostly about git (Pull Requests, Branches, Merge Conflicts etc). Here is a list of some non-trivial pull requests I helped to review and merge:

[#44](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/44), [#58](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/58), 
[#77](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/77), [#82](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/82),
[#92](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/92), [#98](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/98),
[#132](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/132), [#167](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/167),
[#223](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/223)