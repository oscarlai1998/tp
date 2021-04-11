# Lok Ke Wen - Project Portfolio Page

## Overview

`iGraduate` is a command line application that acts as a centralised hub for NUS students 
majoring in Information Security to plan their academic journey. With tools like the 
storing of modules, listing of modules that can be taken, an academic progression indicator 
and a CAP calculator, iGraduate will help students check his/her graduation progress and make 
informed decisions about future modules.

## Summary of Contributions

### Code contributed

[Click here to View my code contribution on RepoSense.](https://nus-cs2113-ay2021s2.github.io/tp-dashboard/?search=kewenlok&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-03-05&tabOpen=true&tabType=authorship&tabAuthor=kewenlok&tabRepo=AY2021S2-CS2113T-W09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other) 

### Enhancements implemented

1. Implemented `AddCommand` class with [Le Jun](#https://github.com/LJ-37).
    * Le Jun and I was tasked to implement the `AddCommand` for adding new modules to the application, which is a critical 
      command for our application. I created methods that will be used by the command in `Ui` and `ModuleList` while Le Jun 
      writes the logic code in `AddCommand`. I added prerequisites for the `AddCommand` at a later time when we decided to 
      include prerequisites feature.
      
1. Implemented `InfoCommand` class to show module information.
    * To allow user to view their module information in the list, I implemented `InfoCommand` that executes `info` command for 
      users to view all the specified module information. I also created methods in `Ui`, `Parser` and `ModuleList` classes to aid 
      the processing of user input, module data fetching and display the results to the user.
      
1. Implemented `DoneCommand` class for user to mark module as taken.
    * The `DoneCommand` class is responsible for executing the operations related to `done` feature. The `done` feature allows 
      the user to update the module they have taken with a grade so that the application can update the prerequisite list of related
      modules accordingly to show an accurate results on the module status and whether it can be taken next.
      
1. Implemented `ListCommand` class with [Oscar](https://github.com/oscarlai1998).
    * The `ListCommand` class is implemented to execute the operations related to `list` feature. It allows the user to view 
      the modules available in the current application based on provided options. I programmed the `available` and list by 
      `module type` options' logic. For the `available` option, I check through the untaken prerequisites of all modules and 
      display only the modules where all their prerequisites are satisfied. As for the `module type` option, I filter out 
      modules which matches the specified module type and display them to the user.
      
1. Designed and implemented underlying logic for prerequisites processing.
    * I added an `ArrayList` for the respective prerequisite information to track to each module for efficient processing. Each 
      module can have multiple prerequisites as long the prerequisite module exists in the current list, not the module itself 
      and not each other's prerequisite (which is not possible in reality). Once a module is marked as taken, it will be removed 
      from the untaken prerequisites list of the modules in its required by list. Only modules with empty untaken prerequisites 
      list are shown as available. Lastly, for module deletion, the module requiring it as a prerequisite must be deleted first 
      before it can be deleted.
      
1. Added enhancement to the `Storage` component by incorporating the ability to differentiate child module classes.
    * The default `Gson` method does not differentiate the parent class `Module` object with its child class object. As such, 
      I included `RuntimeTypeAdapterFactory` and register the child module classes as the subtype of `Module` class's 
      `RuntimeTypeAdapterFactory` object. The modified `Module` class `RuntimeTypeAdapterFactory` is then registered to 
      the GsonBuilder for it to generate the `Json` data with labels.

### Contributions to the UG

1. Added [Show module information: `info`](https://ay2021s2-cs2113t-w09-2.github.io/tp/UserGuide.html#show-module-information-info) 
   and [Manual Modification of Data](https://ay2021s2-cs2113t-w09-2.github.io/tp/UserGuide.html#manual-modification-of-data) section.
1. Added and updated [List modules: `list`](https://ay2021s2-cs2113t-w09-2.github.io/tp/UserGuide.html#list-modules-list), 
   [Frequently Asked Questions](https://ay2021s2-cs2113t-w09-2.github.io/tp/UserGuide.html#frequently-asked-questions) 
   and [Command Summary](https://ay2021s2-cs2113t-w09-2.github.io/tp/UserGuide.html#command-summary) section.
1. Restructuring, reformatting and maintenance of UG.

### Contributions to the DG

1. Added [introduction](https://ay2021s2-cs2113t-w09-2.github.io/tp/DeveloperGuide.html#introduction), 
   [setting up, getting started](https://ay2021s2-cs2113t-w09-2.github.io/tp/DeveloperGuide.html#setting-up-getting-started), 
   [logging](https://ay2021s2-cs2113t-w09-2.github.io/tp/DeveloperGuide.html#logging) 
   and [documentation](https://ay2021s2-cs2113t-w09-2.github.io/tp/DeveloperGuide.html#documentation) section.
1. Added [`module` package](https://ay2021s2-cs2113t-w09-2.github.io/tp/DeveloperGuide.html#module-package) and its 
   class diagram under the design of model component.
1. Added [`AddCommand`](https://ay2021s2-cs2113t-w09-2.github.io/tp/DeveloperGuide.html#command), 
   [`InfoCommand`](https://ay2021s2-cs2113t-w09-2.github.io/tp/DeveloperGuide.html#command) and added their sequence 
   diagrams under implementation.
1. Added implementation for [`ModuleList`](https://ay2021s2-cs2113t-w09-2.github.io/tp/DeveloperGuide.html#modulelist).
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
  [#217](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/217))
1. Researched on third party libraries which could be used for the project and obtained permission from the lecturer 
  on behalf of the team. (Issues: [#31](https://github.com/nus-cs2113-AY2021S2/forum/issues/31)
  [#40](https://github.com/nus-cs2113-AY2021S2/forum/issues/40))

### Review/mentoring contributions

I helped the team with giving implementation advise of some features and reviewing code of the team in general
to ensure everything is in place and enhance the project's code quality.

Here are some example of the pull requests where I reviewed and refactored the project code:
* [#47](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/47), [#55](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/55), 
  [#62](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/62), [#66](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/66),
  [#74](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/74), [#213](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/213),
  [#217](https://github.com/AY2021S2-CS2113T-W09-2/tp/pull/217)

### Contributions beyond the project team

1. Advised other teams on implementation of certain features such as data storage.
1. Performed bug hunting on other products such as [MojoHr](https://github.com/AY2021S2-CS2113-W10-2/tp).
1. Recommended possible fixes for bug found on other products.
1. Reviewed User Guide and Developer Guide for other teams and provided constructive suggestions for improvement.