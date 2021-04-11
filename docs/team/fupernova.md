# Xia Fuxi - Project Portfolio Page

## Overview

`iGraduate` is a command line application that acts as a centralised hub for NUS students majoring in Information 
Security to plan their academic journey. It is equipped with a module storage system where students
can mark modules as completed and store prerequisites for each module. This allows students to check modules available 
to take and help them make informed decisions about their academic plan. iGraduate also comes with a cap calculator and a 
graduation progress tracker.
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

Ui depended on the implementations of other classes, as each feature might have different considerations when printing
information to the screen. At the start of v1.0, we endeavoured to think of all the possible features that may require interaction
with the user and laid out skeleton methods of them in the `Ui` class so that my teammates would be able to access or edit 
the methods they required easily.
___
#### Implemented `Parser` class of iGraduate together with [Xin Ru](https://github.com/xseh/)
The Parser class is responsible for making sense of user input. The class determines the type of command from user input
and subsequently extracts the information required to execute the command before handing control back to the main program
for the execution of the command.

*Considerations*

The parser class was particularly challenging to tackle due to all 8 commands available in our program having a unique
input format and parameters required. Many ways of extracting parameters were considered, and we eventually settled on a
two-tiered parsing system. Firstly, a general parse was done to the user command to split the command name and parameters.
Secondly, depending on the command, the parameters would be extracted using other methods.

Xin Ru worked on the second level of parsing while I worked on the first. For my part, I implemented methods to split the user input 
into parts. This was done by splitting user input at the first instance of a dash(-), as the dash would signify the start 
of the different 'flags' required by the command. This way of splitting makes the second round of parsing easier as the flags
would already by separated from the rest of the user input.
___
#### Implemented `HelpCommand` class of iGraduate
The HelpCommand class is responsible for executing the `help` command, which provides users with a quick reference guide
regarding the proper usage of iGraduate, including the different commands and their input formats.

*Considerations*

Due to the large number of commands and features available in iGraduate, we decided to have separate help pages for each command,
 along with one help page listing down the commands available in iGraduate in case users forget. This methodology helps
make the quick guide less cluttered and more targeted towards specific commands that users intend to look up.

___
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

I helped to edit non-feature specific portions of the UG and DG such as the introduction and the content pages for both.

I also helped to review my teammates' code, in particular look out for the reasons for failed gradle builds.

When we started with logging, I configured `.gitignore` to ignore all local logging files.

After the PE dry run, I also helped to tag issues found and assign issues to myself and teammates.

In general, I opened myself to help teammates solve problems and took on additional tasks when they arised.
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