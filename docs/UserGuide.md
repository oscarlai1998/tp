# iGraduate User Guide
By: `W09-2` Latest update: `30 March 2021`

* [Introduction](#introduction)
* [User Guide Usage](#user-guide-usage)
* [Quick Start](#quick-start)
* [Features](#features)
    * [Add new module: `add`](#add-a-new-module-add)
    * [Delete existing module: `delete`](#delete-existing-module-delete)
    * [Update module information: `update`](#update-module-information-update)
    * [Mark a module as complete: `done`](#mark-a-module-as-complete-done)
    * [List modules: `list`](#list-modules-list)
    * [Show academic progression: `progress`](#show-academic-progression-progress)
    * [Calculate CAP: `cap`](#calculate-cap-cap)
    * [Exit the program: `exit`](#exit-the-program-exit)
* [Storage of Data](#storage-of-data)
* [Manual Modification of Data](#manual-modification-of-data)
* [Frequently Asked Questions](#frequently-asked-questions)
* [Command Summary](#command-summary)

## Introduction

iGraduate is a command line application that acts as a centralised hub for <b>NUS students majoring in Information 
Security</b> to plan their academic journey. With tools like the storing of modules, listing of modules that can be 
taken, an academic progression indicator and a CAP calculator, iGraduate will help students check his/her graduation 
progress, view modules taken based on programme requirements and make informed decisions about future modules.

## User Guide Usage
This user guide serves as a quick introduction to the application as well as provide instructions on when to utilise 
each feature, its proper formats and expected behaviours from different sample inputs. The User Guide also explains
how modules are stored in the computer and how it can be access and modified. There is also a list of 
frequently asked questions to answer any common queries. Finally, the user guide provides a command summary of all
possible commands and the expected input for each command.

The following symbols are used in this user guide: 

Symbols/Formatting | Description
-------------------|------------------------------------------
ℹ️ **Note:**        | Information to take note of.
`Grey highlight`   | Code or terms related to the application.
`[]`               | Optional parameter.
`<>`               | Include only one compulsory value from choices.

## Quick Start
> ℹ️ **Note:** The recommended operating system for iGraduate is Windows 10

1. Make sure you have Java version 11 installed on your computer. If you do not have Java 11 installed on your computer,
   you can install it from [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html).
2. Download `iGraduate.jar` from latest github release by clicking [here](https://github.com/AY2021S2-CS2113T-W09-2/tp/releases).
3. Copy the file to the folder you want to use as the home folder for your iGraduate program.
4. Run the program in command prompt using `java -jar iGraduate.jar` command.
5. If successful, the programme would look like this:
```
 _  ____               _             _       
(_)/ ___|_ __ __ _  __| |_   _  __ _| |_ ___ 
| | |  _| '__/ _` |/ _` | | | |/ _` | __/ _ \
| | |_| | | | (_| | (_| | |_| | (_| | ||  __/
|_|\____|_|  \__,_|\__,_|\__,_|\__,_|\__\___|
iGraduate starting up...
Welcome to iGraduate, your one stop study planning service!
What would you like to do today?
--------------------------------------------------------------------------------------
```

## Features
The following section outlines the various commands supported by the application, its proper usages and expected behaviours. 

### Add a new module: `add`

Adds a new module to the list of modules you wish to track. The list serves to keep track of the modules that 
you have taken, are currently taking or intend to take in the future. Other functions would interact with these added 
modules to keep track, calculate CAP and check the progress of your academic career. 

> ℹ️ **Note:** iGraduate does not restrict the number of modules you can add. You can add modules even after fulfilling
> minimum graduation requirements.


Format:

`add <name> -c <module code> -t <core|math|ue|ge> -mc <number of credits> [-p <prerequisite1,prerequisite2,...>]`

> ℹ️ **Note:** If you have to add prerequisite modules, please ensure that all the prerequisite modules are already added before proceeding.

> ℹ️ **Note:** The maximum MCs allowed for a single module in iGraduate is <b>32</b>.

> ℹ️ **Note:** iGraduate only supports module codes that conform to NUS style module codes.
> Examples of valid codes include `CS1010`, `MA1101R` and `UTC1102B`.

> ℹ️ **Note:** Module codes are <b>case-insensitive</b>. This means that module codes like `CS2102` and `cs2102` are considered the same. 

Example of Usage(s) and Expected Outcome(s):

`add Journey of the Innovator -c CP2201 -t ue -mc 2`
```
--------------------------------------------------------------------------------------
Added CP2201 Journey of the Innovator to the list. (2.0MCs)
[E][X] CP2201   Journey of the Innovator                                NIL   2 MC
--------------------------------------------------------------------------------------
```

<sup>***Figure 1.1.1** Expected results from adding the `unrestricted elective` module `Journey of the 
Innovator`, a `2` credit module with the code `CP2201`, without any prerequisites.*</sup>


`add Introduction to Operating Systems -c CS2106 -t core -mc 4 -p CS1010,CS2100`

```
--------------------------------------------------------------------------------------
Added CS2106 Introduction to Operating Systems to the list. (4.0MCs)
List of pre-requisites needed to take CS2106: CS1010, CS2100
[C][✘] CS2106   Introduction to Operating Systems                       NIL   4 MC
--------------------------------------------------------------------------------------
```

<sup>***Figure 1.1.2** Expected results from adding the `core` module `Introduction to Operating Systems`, a 
`4` credit module with the code `CS2106`, with the prerequisites `CS1010` and `CS2100`.*</sup>

### Delete existing module: `delete`

Deletes an existing module from the list of modules added via the module code. The delete function serves to 
amend any changes to the planning of modules. 

> ℹ️ **Note:** The module must not be a prerequisite of another module. 

Format:

`delete <module code>`

> ℹ️ **Note:** Module codes are <b>case-insensitive</b>. This means that module codes like `CS2102` and `cs2102` are considered the same.

Example of Usage(s) and Expected Outcome(s):

`delete CS2100`

```
--------------------------------------------------------------------------------------
"Core" module CS2100 has been deleted.
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.2.1** Expected results from deleting the `core` module with the code `CS2100`.*</sup>

`delete CP2201`

```
--------------------------------------------------------------------------------------
"Elective" module CP2201 has been deleted.
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.2.2** Expected results from deleting the `elective` module with the code `CP2201`.*</sup>

### Update module information: `update`

Updates relevant information (name, credit and grade) for the selected module. The information can be arranged 
in any order and multiple information can be entered in one command. The command is used to make any changes 
to the module information in case of mistakes or unexpected changes to the module. 

> ℹ️ **Note:**  You cannot change the module type and code as iGraduate uses them to identify the modules. If you want to remove 
the modules, use the [`delete` command](#delete-existing-module-delete) instead. 

> ℹ️ **Note:** Updating of grades is only permitted if the module has been completed (see 
[`done` command](#mark-a-module-as-complete-done)). If a grade is entered for an incomplete module, no grades 
will be added but the rest of the information (if any) will be updated. 


Format:

`update <module code> [-n <name>] [-mc <credit>] [-g <grade>] [-p <prerequisite1,prerequisite2,...>]`

> ℹ️ **Note:** Although all parameters are optional, the update command requires at least 1 parameter to be used. 

> ℹ️ **Note:** Module codes are <b>case-insensitive</b>. This means that module codes like `CS2102` and `cs2102` are considered the same.

Example of Usage(s) and Expected Outcome(s):

`update CS2106 -n Introduction to Operating Systems -mc 4 -g A -p CS1010,CS2100`

```
--------------------------------------------------------------------------------------
Nice! I've updated this module:
  [C][O] CS2106   Introduction to Operating Systems                         A   4 MC
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.3.1** Expected results from updating the `credit`, `grade` and `prerequisites` of the module 
with the code `CS2106`. The `credit` was updated to `4`, grade to `A` and prerequisites to `CS1010` and 
`CS2100`.*</sup>

### Mark a module as complete: `done`

The done command marks a modules as completed. In addition, you must include the grade obtained to facilitate 
the calculation of CAP. If you want to modify the grades, use the [`update` command](#update-module-information-update). 

> ℹ️ **Note:** Only the following letter grades (A+, A, A-, B+, B, B-, C+, C, D+, D, F, S, U, CS, CU) are valid.

Format:

`done <module code> -g <grade>`

> ℹ️ **Note:** Module codes are <b>case-insensitive</b>. This means that module codes like `CS2102` and `cs2102` are considered the same.

Example of Usage(s) and Expected Outcome(s):

`done CS2106 -g A`

```
--------------------------------------------------------------------------------------
Nice! I've updated this module:
  [C][O] CS2106   Introduction to Operating Systems                         A   4 MC
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.4.1** Expected results from marking the module with the code `CS2106` with the grade `A` as 
done.*</sup>

`done GES1041 -g S`

```
--------------------------------------------------------------------------------------
Nice! I've marked this module as done:
  [G][O] GES1041  Everyday Ethics in Singapore                              S   4 MC
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.1.6** Expected results from marking the module with the code `GES1041` with the grade `S` as 
done.*</sup>

### List modules: `list`

The list command lists modules added to your list according to the filter. List all modules or select only completed, 
incomplete or available modules to take. `all` lists all modules on your list, `complete` lists all modules that have 
been completed (i.e. marked as done), `incomplete` lists all modules that have been added into the system but has not
been completed(i.e. modules that you are currently taking or yet to take), `available` lists all modules that can be 
taken (with prerequisites fulfilled), but have not been taken, based on the completed modules. The list shows module 
details including the module `type`, `code`, `completetion status`, `name`, `grade` and `credits`. 

> ℹ️ **Note:** If a module is completed, the grade obtained is displayed. Otherwise, a `NIL` is displayed instead. 

Format: 

`list all|incomplete|complete|available`

Example of Usage(s) and Expected Outcome(s):

`list all`

```
--------------------------------------------------------------------------------------
Module List:
1: [C][O] CS1010   Introduction to Programming                              B+   4 MC
2: [G][O] GES1041  Everyday Ethics in Singapore                              S   4 MC
3: [C][X] CS2100   Computer Organisation                                   NIL   4 MC
4: [C][X] CS2106   Introduction to Operating Systems                       NIL   4 MC
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.5.1** Expected results from listing `all` modules. The list includes the module information that has 
been added, including those that are completed (indicated with a tick) and incompleted (indicated with a cross).*</sup>

`list incomplete`

```
--------------------------------------------------------------------------------------
Modules you have yet to complete:
1: [C][X] CS2100   Computer Organisation                                   NIL   4 MC
2: [C][X] CS2106   Introduction to Operating Systems                       NIL   4 MC
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.5.2** Expected results from listing `incomplete` modules. The list includes the module information that has been added but has not been completed, as indicated with a cross.*</sup>

`list complete`

```
--------------------------------------------------------------------------------------
Modules you have have completed:
1: [C][O] CS1010   Introduction to Programming                              B+   4 MC
2: [G][O] GES1041  Everyday Ethics in Singapore                              S   4 MC
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.5.3** Expected results from listing `completed` modules. The list includes the module information that has been completed, as indicated with with a tick.*</sup>

`list available`

```
--------------------------------------------------------------------------------------
Modules can be taken:
1: [C][X] CS2106   Intro to OS                                             NIL   4 MC 
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.5.4** Expected results from listing `available` modules. The list includes the module information that has been added and can be taken (with all prerequisites fulfiled).*</sup>

### Show academic progression: `progress`

Displays a bar that represents the current progress of your academic career. The progress bar shows the percentage of your total completed module credits against the total number of credits needed for graduation requirements. The bar will fill up as more modules are completed. 

> ℹ️ **Note:** The default number of credits used to calculate the progress bar is `160`, the amount of an <b>NUS single-degree Information Security undergraduate</b> student

> ℹ️ **Note:**  If total MCs exceeds 160, the progress bar will still display `100%`. 
> (See notes under [Add Command](#add-a-new-module-add))


Format:

`progress`

Example of Usage(s) and Expected Outcome(s):

`progress`

```
--------------------------------------------------------------------------------------
Progress:
░░░░░░░░░░░ 5.00%
8MCs/160MCs Completed
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.6.1** Expected results when 8 out of the 160 MCs has been completed (5% completion)*</sup>

### Calculate CAP: `cap`

Calculates your current Cumulative Average Point (CAP) according to the modules that have been completed with a graded score (i.e. A+ to F). The `cap` command also displays the current degree classification based on the CAP calculated. 

> ℹ️ **Note:** Modules with Satisfactory/Unsatisfactory (S/U) grades are not calculated in the CAP

Format:

`cap`

Example of Usage(s) and Expected Outcome(s):

`cap`

```
--------------------------------------------------------------------------------------
Current CAP: 4.00
Current Degree Classification: Honours (Distinction)
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.7.1** Expected results from running the cap command based on the modules in the previous sections (see [`list complete`](#list-modules-list) for the list of modules used to calculate this cap)*</sup>

### Exit the program: `exit`
Exits the program. 

Format:

`exit`

Example of Usage(s) and Expected Outcome(s):

`exit`

```
--------------------------------------------------------------------------------------
See you soon! Happy studying!
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.8.1** Expected results from exiting the program*</sup>

## Storage of Data

All data are stored automatically by iGraduate everytime a module is modified (i.e. added, deleted or 
updated) and when exiting the program. The data will be automatically loaded in the next time of usage. No user
intervention is required for storage of data.

## Manual Modification of Data

> ℹ️ **Note:** Please ensure that you modify only the value of module attributes if you are unsure of how the 
`json` structure works.

> ℹ️ **Note:** The application might not behave as the way it is expected to if you modify the application 
data in an incorrect manner. For example, data corruption might occur.

The IGraduate application data file is stored under the same folder where the IGraduate application resides. 
You may notice a folder named `data` is created and there is a `modules.json` file inside the folder. In 
`modules.json`, you will find your data created in the application here. To modify the data of existing 
module, simply change the value of each attribute in the `json` file and save it. You are advised to add or 
remove new module data manually only if you understand the application's logic and `json` format.

## Frequently Asked Questions

**Q1**: Can I use the application on my MacBook?
> Yes! iGraduate is a cross-platform application that can be run on Windows, Linux or Mac.  

**Q2**: Can I share my module list with my friends?
> Yes! Just go into `/data` of the application folder and pass the `modules.json` file over to your friends. Make sure
> your friends save the `modules.json` file under their `/data` folder as well!

**Q3**: Do I need internet connection when using iGraduate?
> No. No internet is required as the application stores and retrieves modules information locally. 

**Q4**: I am a NUS student who is not majoring in Information Security. Can I use the application for module planning?
> Yes, of course! Most of the features provided by the application are implemented in a generic way. However, features
> such as progress is more targeted towards Information Security students. Support for other majors in NUS will be
> implemented in the future. Stay tuned!

**Q5**: Is the module list only in JSON format?
> Yes! Currently, module list is only in JSON format. However, there are various platforms available online to convert 
> the module list into a different format.

**Q6**: I noticed a `iGraduate-0.log` file is created after running the application. What is it for?
> The `iGraduate-0.log` is a log file for recording the crashes, errors and anomalies occurred when running the 
> application. You may submit the file [here](https://github.com/AY2021S2-CS2113T-W09-2/tp/issues) if you experienced
> any error during your use of the application in order for the developers to look into the problem.

## Command Summary

Command | Format
--------|--------
add | <code>add <name> -c <module code> -t <core&#124;math&#124;ue&#124;ge> -mc <number of credits> [-p <prerequisite1,prerequisite2,...>]
delete | `delete <module code>`
update | `update <module code> [-n <name>] [-mc <credit>] [-g <grade>] [-p <prerequisite1,prerequisite2,...>]`
done | `done <module code> -g <grade>`
list | <code>list <all&#124;incomplete&#124;complete></code>
progress | `progress`
cap    | `cap`
exit     | `exit`