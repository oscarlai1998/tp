# iGraduate User Guide
By: `W09-2` Latest update: `25 March 2021`

* [Introduction](#introduction)
* [Quick Start](#quick-start)
* [Features](#features)
    * [Add new module: `add`](#add-new-module-add)
    * [Delete existing module: `delete`](#delete-existing-module-delete)
    * [Update module information: `update`](#update-module-information-update)
    * [Mark a module as complete: `done`](#mark-a-module-as-complete-done)
    * [List modules: `list`](#list-modules-list)
    * [Show academic progression: `progress`](#show-academic-progression-progress)
    * [Calculate CAP: `cap`](#calculate-cap-cap)
    * [Exit the program: `exit`](#exit-the-program-exit)
* [Storage](#storage)
* [Manual Modification of Data](#manual-modification-of-data)
* [Command Summary](#command-summary)

## Introduction

iGraduate is a command line application that will help NUS students majoring in Information Security check his/her 
graduation progress and modules taken in a coherent manner based on the programme requirements. It also contains tools 
to help make informed decisions about future modules. iGraduate data is saved in the hard disk automatically after any 
command that changes the data. There is no need to save manually.

## Quick Start

1. Make sure you have Java version 11 installed on your computer.
2. Download `iGraduate.jar` from latest github release by clicking [here](https://github.com/AY2021S2-CS2113T-W09-2/tp/releases).
3. Copy the file to the folder you want to use as the home folder for your iGraduate program.
4. Run the program in command prompt using `java -jar iGraduate.jar` command.
5. Type your command in the command prompt to interact with iGraduate.

Note that the following symbols and formatting are used in this guide:

Symbols/Formatting | Description
-------------------|------------------------------------------
ℹ️ **Note:**        | Information to take note of.
`Grey highlight`   | Code or terms related to the application.
`[]`               | Optional parameter.
`<>`               | Include only one compulsory value from choices.

## Features

### Add new module: `add`

Adds a new module to the list. 

> ℹ️ **Note:** The prerequisite field is optional. 

> ℹ️ **Note:** If you decided to add prerequisite modules, please ensure that all the prerequisite modules are added
> to the application before proceed.

Format:

`add <name> -c <module code> -t <core|math|ue|ge> -mc <number of credits> [-p <prerequisite1,prerequisite2,...>]`

Example of Usage(s) and Expected Outcome(s):

`add Journey of the Innovator -c CP2201 -t ue -mc 2`

```
--------------------------------------------------------------------------------------
Added CP2201 Journey of the Innovator to the list. (2.0MCs)
[E][✘] CP2201   Journey of the Innovator                                NIL   2 MC
--------------------------------------------------------------------------------------
```

### Delete existing module: `delete`

Deletes an existing module from the list. 

> ℹ️ **Note:** The module must not be a prerequisite of another module. 

Format:

`delete <module code>`

Example of Usage(s) and Expected Outcome(s):

`delete CS2100`

```
--------------------------------------------------------------------------------------
"Core" module CS2100 has been deleted.
--------------------------------------------------------------------------------------
```

`delete CP2201`

```
--------------------------------------------------------------------------------------
"Elective" module CP2201 has been deleted.
--------------------------------------------------------------------------------------
```

### Update module information: `update`

Updates relevant information (name, credit and grade) for the selected module. The information can be arranged 
in any order and multiple information can be entered in one command. 

> ℹ️ **Note:** Updating of grades is only permitted if the module has been completed (see [done command](#mark-a-module-as-complete-done)).
> If a grade is entered for an incomplete module, no grades will be added but the rest of the 
information (if any) will be updated. 

Format:

`update <module code> [-n <name>] [-mc <credit>] [-g <grade>] [-p <prerequisite1,prerequisite2,...>]`

Example of Usage(s) and Expected Outcome(s):

`update CS2106 -n Introduction to Operating Systems -mc 4 -g A -p CS1010,CS2100`

```
--------------------------------------------------------------------------------------
Nice! I've updated this module:
  [C][✓] CS2106   Introduction to Operating Systems                         A   4 MC
--------------------------------------------------------------------------------------
```

### Mark a module as complete: `done`

Mark a module as done with grade. To modify the grade of taken module,
simply use the `done` command again.

> ℹ️ **Note:** Only the following letter grades (A+, A, A-, B+, B, B-, C+, C, D+, D, F, S, U, CS, CU) are valid.

Format:

`done <module code> -g <grade>`

Example of Usage(s) and Expected Outcome(s):

`done CS2106 -g A`

```
--------------------------------------------------------------------------------------
Nice! I've updated this module:
  [C][✓] CS2106   Introduction to Operating Systems                         A   4 MC
--------------------------------------------------------------------------------------
```

`done GES1041 -g S`

```
--------------------------------------------------------------------------------------
Nice! I've marked this module as done:
  [G][✓] GES1041  Everyday Ethics in Singapore                              S   4 MC
--------------------------------------------------------------------------------------
```

### List modules: `list`

Lists all modules added on your list and all modules you can take. 

Format:

`list all|incomplete|complete`

Example of Usage(s) and Expected Outcome(s):

`list all`

```
--------------------------------------------------------------------------------------
Module List:
1: [C][✓] CS1010   Introduction to Programming                              B+   4 MC
2: [G][✓] GES1041  Everyday Ethics in Singapore                              S   4 MC
3: [C][✘] CS2100   Computer Organisation                                   NIL   4 MC
4: [C][✘] CS2106   Introduction to Operating Systems                       NIL   4 MC
--------------------------------------------------------------------------------------
```

`list incomplete`

```
--------------------------------------------------------------------------------------
Modules you have yet to complete:
1: [C][✘] CS2100   Computer Organisation                                   NIL   4 MC
2: [C][✘] CS2106   Introduction to Operating Systems                       NIL   4 MC
--------------------------------------------------------------------------------------
```

`list complete`

```
--------------------------------------------------------------------------------------
Modules you have have completed:
1: [C][✓] CS1010   Introduction to Programming                              B+   4 MC
2: [G][✓] GES1041  Everyday Ethics in Singapore                              S   4 MC
--------------------------------------------------------------------------------------
```

### Show academic progression: `progress`

Shows a progress bar for the percentage of modules completed with respect to total modules needed for 
graduation requirement (default value is 160). 

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

### Calculate CAP: `cap`

Calculates the current CAP and shows the degree classification based on the grades from the modules that have 
been marked as completed. 

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

## Storage

All data are stored automatically by IGraduate everytime a module is modified (i.e. added, deleted or updated) 
and when exiting the program. The data will be loaded in the next time of usage.

## Manual Modification of Data

> ℹ️ **Note:** Please ensure that you modify only the value of module attributes if you are unsure of how the `json`
> structure works.

> ℹ️ **Note:** The application might not behave as the way it is expected to if you modify the application data in an 
> incorrect manner. For example, data corruption might occur.

The IGraduate application data file is stored under the same folder where the IGraduate application resides. You may 
notice a folder named `data` is created and there is a `modules.json` file inside the folder. In `modules.json`, you 
will find your data created in the application here. To modify the data of existing module, simply change the value
of each attribute in the `json` file and save it. You are advised to add or remove new module data manually only if you
understand the application's logic and `json` format.

## Command Summary

Command | Format
--------|--------
add | <code>add <name> -c <module code> -t <core&#124;math&#124;ue&#124;ge> -mc <number of credits> [-p <prerequisite1,prerequisite2,...>]</code>
delete | `delete <module code>`
update | `update <module code> [-n <name>] [-mc <credit>] [-g <grade>] [-p <prerequisite1,prerequisite2,...>]`
done | `done <module code> -g <grade>`
list | <code>list all&#124;incomplete&#124;complete</code>
progress | `progress`
cap    | `cap`
exit     | `exit`