# Lok Ke Wen - Project Portfolio Page

## Overview

`iGraduate` is a command line application written in `Java` that acts as a centralised hub for NUS students 
majoring in Information Security to plan their academic journey.

## Summary of Contributions

### Code contributed

[Click here to view my code contribution on RepoSense.](https://nus-cs2113-ay2021s2.github.io/tp-dashboard/?search=kewenlok&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-03-05&tabOpen=true&tabType=authorship&tabAuthor=kewenlok&tabRepo=AY2021S2-CS2113T-W09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other) 

### Enhancements implemented

1. Implemented `AddCommand` class with [Le Jun](https://github.com/LJ-37).
    * The implementation of `AddCommand` is generally doable except for the checking and inserting the prerequisites 
      information to the module's prerequisites, untaken prerequisites and each of the prerequisites' requiredByModules
      as it is quite complex.
      
1. Implemented `InfoCommand` class to show module information.
    * The idea of implementing `InfoCommand` is to prevent convoluted view for the `ListCommand` by displaying all information
      of the specified module to the user.
      
1. Implemented `DoneCommand` class for user to mark module as taken.
    * The `DoneCommand` class is responsible for executing and providing the `done` feature to allow the user to update 
      the module they have taken with a grade so that the application can update the prerequisite list of related
      modules accordingly to show an accurate results on the module status and whether it can be taken next.
      
1. Implemented `ListCommand` class with [Oscar](https://github.com/oscarlai1998).
    * I programmed the `available` and list by `module type` options' logic. For the `available` option, I checked through 
      all the modules and display only the modules where all their prerequisites are satisfied. As for the `module type` 
      option, I filter out modules which matches the specified module type and display them to the user.
      
1. Designed and implemented underlying logic for prerequisites processing.
    * I added three `ArrayList` for the respective prerequisite information to track to each module for efficient processing. Each 
      module can have any prerequisites as long the prerequisite exists in the current list, not the module itself and not each 
      other's prerequisite. Once a module is marked as taken, it will be removed from the untaken prerequisites list of the modules 
      in its required by list. Only modules with empty untaken prerequisites list are shown as available. Lastly, for module 
      deletion, the module requiring it as a prerequisite must be deleted first before it can be deleted.

1. Incorporated the ability to differentiate child module classes to the `Storage` component.
    * The default `Gson` method does not differentiate the parent class `Module` object with its child class object. As such, 
      I researched on `RuntimeTypeAdapterFactory` and included it to generate the `Json` data with labels that distinguishes
      the child classes.

### Contributions to the UG

1. Added `Show module information: info` and `Manual Modification of Data` section.
1. Added and updated `List modules: list`, `FAQs` and `Command Summary` section.
1. Restructuring, reformatting and maintenance of UG.

### Contributions to the DG

1. Added `introduction`, `setting up, getting started`, `logging` and `documentation` section.
1. Added `module package` and its class diagram under the design of model component.
1. Added `AddCommand`, `InfoCommand` and their sequence diagrams under implementation.
1. Added implementation for `ModuleList`.
1. Restructuring, reformatting and maintenance of DG.

### Contributions to team-based tasks

1. Setup the GitHub team organization and repository.
1. Managed the milestones, issue tracker and releases for team repository.
1. Setup logging configuration file. (Pull Request: [#74](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/74))
1. Implemented skeleton code for the team project including project structure, command class and module class. 
  (Pull Requests: [#8](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/8)
  [#26](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/26) 
  [#32](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/32)
  [#39](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/39)
  [#41](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/41))
1. Enhanced overall code quality by refactoring and improving the code design from time to time. (Pull Requests: 
  [#47](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/47)
  [#55](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/55)
  [#62](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/62)
  [#66](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/66)
  [#74](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/74)
  [#213](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/213)
  [#217](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/217)
  [#241](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/241)
  [#253](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/253))
1. Researched on third party libraries which could be used for the project and obtained permission from the lecturer 
  on behalf of the team. (Issues: [#31](https://github.com/nus-cs2113-AY2021S2/forum/issues/31)
  [#40](https://github.com/nus-cs2113-AY2021S2/forum/issues/40))

### Review/mentoring contributions

I helped the team with giving implementation advise of some features and reviewing code of the team in general
to ensure everything is in place and enhance the project's code quality. Here are some example of the pull requests where 
I reviewed and refactored the project code:
* [#47](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/47), [#55](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/55), 
  [#62](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/62), [#66](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/66),
  [#74](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/74), [#213](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/213),
  [#217](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/217), [#241](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/241),
  [#253](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/253)

### Contributions beyond the project team

1. Performed bug hunting, reviewed UG and DG on other products such as [MojoHr](https://github.com/AY2021S2-CS2113-W10-2/tp).