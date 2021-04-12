# Xia Fuxi - Project Portfolio Page

## Overview

`iGraduate` is a command line application that acts as a centralised hub for NUS students majoring in Information 
Security to plan their academic journey. It is equipped with a module storage system where students
can mark modules as completed and store prerequisites for each module. This allows students to check modules available 
to take and help them make informed decisions about their academic plan.
___
### Summary of Contributions
<b>Code contributed</b>

[Link to my code contribution.](https://nus-cs2113-ay2021s2.github.io/tp-dashboard/?search=fupernova&sort=groupTitle&sortWithin=title&since=2021-03-05&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)
___
### Enhancements implemented

Given below are details of my contributions to the project.

#### Implemented `Ui` class of iGraduate

The Ui class is responsible for all operations that interact with the user, including scanning user input and printing 
information to the screen.

*Considerations*

Ui depends on the implementations of other classes, as each feature might have different considerations when printing
information. I laid out skeleton methods of the possible printing methods required in the `Ui` class so that my 
teammates would be able to access or edit the methods they required easily.
___

#### Implemented `Parser` class of iGraduate together with [Xin Ru](https://github.com/xseh/)

The Parser class is responsible for making sense of user input. The class determines the type of command from user input
and subsequently extracts the information required to execute the command before handing control back to the main program
for the execution of the command.

*Considerations*

Implementing the parser class was challenging due to all commands available in our program having a unique
input format and parameters. Many ways of extracting parameters were considered, and we eventually settled on a
two-tiered parsing system. Firstly, a general parse was done to the user command to split the command name and parameters.
Secondly, depending on the command, the parameters would be extracted using other methods. Xin Ru worked on the second level
while I worked on the first.

___
#### Implemented `HelpCommand` class of iGraduate
The HelpCommand class is responsible for executing the `help` command, which provides users with a quick reference guide
regarding the proper usage of iGraduate, including the different commands and their input formats.

*Considerations*

Due to the large number of commands and features available in iGraduate, we decided to have separate help pages for each command,
 along with one help page listing down the commands available in iGraduate in case users forget. This methodology helps
make the quick guide less cluttered and more targeted towards specific commands that users intend to look up.
___
#### Implemented test code for multiple methods

Unit testing and integration testing was important to our project as it helped us to verify the correctness of the program
as well as help us identify bugs that we missed out on. In particular, special attention was given to tests for `Parser`
and `Command` classes as these 2 classes primarily dealt with user input and hence are the most prone to bugs introduced
by developers.

*Considerations*

Testing for `Parser` and `Command` classes were priority as they deal with user input, 
which is where many logic bugs can be introduced. Integration testing between `Parser` and `Command` was also important
as these two classes worked together directly to extract parameters and execute commands. Unit tests for these 
two classes were implemented first with possible inputs that would break the program. Once the 
two classes were amply tested separately, integration tests were introduced to ensure that the core functionality of 
parsing user inputs and creating the appropriate `Command` instance was working.

____

### Contributions to documentation

#### User Guide

For the user guide, I added the segments for components I implemented, such as the Help command. I also added the segment for
 Add command.
I also helped to update the FAQ, design the logo and rephrase the introduction.

Apart from that, I made many edits to improve grammar, correct spelling and improve the phrasing of the guide to be more
 audience-centric and beginner-friendly.
___
### Contributions to DG

My main contributions to DG comes in the form of many of the sequence diagrams. I used PlantUML to create sequence
diagrams as well as help my teammates check and correct their sequence diagrams. I also contributed to the standardisation
of sequence diagrams.

For the <b>Design</b> and <b>Implementation</b> sections of the DG, I contributed for the components implemented by me,
such as Parser and Help command. Apart from that, I also added the details for `ModuleList` class, `Command` class and the `Add` command.
___
### Contributions to team-based tasks

I edited non-feature specific portions of the UG and DG such as the introduction and the content pages for both. I also 
reviewed my teammates' code, in particular look out for the reasons for failed gradle builds. I also hunted for bugs by
writing unit tests for `Parser` and `Command`. I also tagged issues found and assign issues to myself and teammates.

___
### Reviews and PRs

I helped the team with repository maintenance, mostly about git (Pull Requests, Branches, Merge Conflicts etc).

Here is a list of some non-trivial pull requests I helped to review and merge:
- [#44](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/44)
- [#58](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/58)
- [#77](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/77)
- [#82](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/82)
- [#92](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/92)
- [#98](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/98)
- [#132](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/132)
- [#167](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/167)
- [#223](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/223)