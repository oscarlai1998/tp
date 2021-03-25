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
* [Command Summary](#command-summary)

## Introduction
iGraduate is a command line application that will help NUS students majoring in information security check his/her 
graduation progress and modules taken in a coherent manner based on the programme requirements. It also contains tools 
to help make informed decisions about future modules. iGraduate data is saved in the hard disk automatically after any 
command that changes the data. There is no need to save manually.

## Quick Start

1. Make sure you have Java version 11 installed on your computer.
2. Download 'iGraduate.jar' from latest github release by clicking [here](https://github.com/AY2021S2-CS2113T-W09-2/tp/releases).
3. Copy the file to the folder you want to use as the home folder for your iGraduate program.
4. Run the program in command prompt using `java -jar iGraduate.jar` command.
5. Type your command in the command prompt to interact with iGraduate.

## Features

### Add new module: `add`

Adds a new module to the list. 

<b>Note:</b>
- The prerequisite field is optional. 

Format:

`add <name> -c <code> -t core|math|ue|ge -mc <number of credits> [-p <prerequisite1,prerequisite2,...>]`

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

<b>Note:</b>
- The module must not be a prerequisite of another module. 

Format:

`delete <code>`

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

<b>Note:</b>
- Updating of grades is only permitted if the module has been completed (see done command).
- If a grade is entered for an incomplete module, no grades will be added but the rest of the 
information (if any) will be updated. 

Format:

`update <module code> [-n <name>] [-mc <credit>] [-g <grade>] [-p <prerequisite1,prerequisite2,...>]`

Example of Usage(s) and Expected Outcome(s):

`update CS1010 -n Introduction to Programming -mc 4 -g A -p CS1010,CS2100`

```
--------------------------------------------------------------------------------------
Nice! I've updated this module:
  [C][✓] CS2106   Introduction to Operating Systems                         A   4 MC
--------------------------------------------------------------------------------------
```

### Mark a module as complete: `done`

Mark a module as done with grade. 

Format:

`done <code> -g <grade>`

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

## Command Summary

Command | Format
--------|--------
add | `add <name> -c <code> -t core\|math\|ue\|ge -mc <number of credits> [-p <prerequisite1,prerequisite2,...>]`
delete | `delete <code>`
update | `update <module code> [-n <name>] [-mc <credit>] [-g <grade>] [-p <prerequisite1,prerequisite2,...>]`
done | `done <code> -g <grade>`
list | `list all\|incomplete\|complete`
progress | `progress`
cap    | `cap`
exit     | `exit`