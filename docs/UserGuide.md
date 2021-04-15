# **iGraduate User Guide** #
By: `W09-2` Latest update: `12 April 2021`

![logo](./images/logo.jpg)

<div style="page-break-after: always;"></div>

## **Table of Contents** ##
1. [Introduction](#introduction)
1. [User Guide Usage](#user-guide-usage)
1. [Quick Start](#quick-start)
1. [Features](#features)
    - [Add new module: `add`](#add-a-new-module-add)
    - [Delete existing module: `delete`](#delete-existing-module-delete)
    - [Update module information: `update`](#update-module-information-update)
    - [Mark a module as complete: `done`](#mark-a-module-as-complete-done)
    - [Show module information: `info`](#show-module-information-info)
    - [List modules: `list`](#list-modules-list)
    - [Show academic progression: `progress`](#show-academic-progression-progress)
    - [Calculate CAP: `cap`](#calculate-cap-cap)
    - [Exit the program: `exit`](#exit-the-program-exit)
    - [Program manual: `help`](#program-manual-help)
1. [Storage of Data](#storage-of-data)
1. [Manual Modification of Data](#manual-modification-of-data)
1. [Frequently Asked Questions](#frequently-asked-questions)
1. [Command Summary](#command-summary)

----

<div style="page-break-after: always;"></div>

## **Introduction** ##

iGraduate is a command line application that acts as a centralised hub for **NUS students Majoring in Information Security** to plan their academic journey. With tools like the storing of modules, listing of modules that can be taken, an academic progression indicator and a CAP calculator, iGraduate will help students check his/her graduation progress, view modules taken based on programme requirements and make informed decisions about future modules.

[Back to Top](#table-of-contents)

----

## **User Guide Usage** ##

This user guide serves as a quick introduction to the application as well as provide instructions on when to utilise each feature, the format for each command, and the expected behaviour based on different sample inputs. The User Guide also explains when modules are stored in the computer and how it can be accessed and modified. There is also a list of frequently asked questions to answer any common queries. Finally, the user guide provides a summary of all commands, and the expected input for each command. 

<br>The following symbols are used in this user guide: 

Symbols/Formatting | Description
-------------------|------------------------------------------
ℹ️ **Note:**        | Information to take note of.
`Grey highlight`   | Code or terms related to the application.
`[]`               | Optional parameter.
`<>`               | Include only one compulsory value from choices.

<br> 

[Back to Top](#table-of-contents)

----

<div style="page-break-after: always;"></div>

## **Quick Start** ##

> ℹ️ **Note:** The recommended operating system for iGraduate is Windows 10

1. Make sure you have Java version 11 installed on your computer. If you do not have Java 11 installed on your computer,
   you can install it from [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html).
   > ℹ️ **Note:** You can check your version of Java by entering the command `java -version`
   on a command line interface like Command Prompt. 
1. Download `iGraduate.jar` from the latest GitHub release [here](https://github.com/AY2021S2-CS2113T-W09-2/tp/releases).
1. Copy the file to the folder you want to use as the home folder for your iGraduate program.
1. Run the program in command prompt using `java -jar iGraduate.jar` command.
1. If the set-up is successful, the programme would look like this:
```
Starting without existing module data...
Initializing new module list...
--------------------------------------------------------------------------------------
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

[Back to Top](#table-of-contents)

----

<div style="page-break-after: always;"></div>

<!--@@author fupernova-->

## **Features** ##

iGraduate works by tracking the modules you have taken, is taking or intend to take and collating them into a list. iGraduate comes with various features that help you track your academic journey with the use of different commands. The following section outlines the various commands supported by iGraduate, its proper usages and expected behaviours. 

### **Add a new module: `add`** ###

Adds a new module to the list of modules. This module can be one that you have taken, is taking or intend to take. You need the module's **name, module code, module type and number of MCs** to add the module. You can also add any **prerequisites** to the module. 

**Format:**

`add <name> -c <module code> -t <core|math|ue|ge> -mc <number of credits> [-p <prerequisite1,prerequisite2,...>]`

> ℹ️ **Note:** 
> - If you have to add prerequisite modules, please ensure that all the prerequisite modules are already added before proceeding.
> - The *maximum* MCs allowed for a single module in iGraduate is **32**.
> - The *minimum* MCs allowed for a single module in iGraduate is **1**. 
> - iGraduate only supports module codes that conform to NUS style module codes.
>   - Examples of valid codes include `CS1010`, `MA1101R`, `UTC1102B` and `UIS3952CS`.
> - Module codes are **case-insensitive**. This means that module codes like `CS2102` and `cs2102` are considered the same. 

**Example of Usage(s) and Expected Outcome(s):**

The following section outlines some usages of the `add` command and its expected output(s). 

`add Journey of the Innovator -c CP2201 -t ue -mc 2`
```
--------------------------------------------------------------------------------------
Added CP2201 Journey of the Innovator to the list. (2.0MCs)
  [E][X] CP2201   Journey of the Innovator                                NIL   2 MC
--------------------------------------------------------------------------------------
```

<sup>***Figure 1.1.1** Expected results from adding the `unrestricted elective` module `Journey of the 
Innovator`, a `2` credit module with the code `CP2201`, without any prerequisites.*</sup>

`add Introduction to Operating Systems -c CS2106 -t core -mc 4 -p CS2100`

```
--------------------------------------------------------------------------------------
Added CS2106 Introduction to Operating Systems to the list. (4.0MCs)
List of pre-requisites needed to take CS2106: CS2100
  [C][X] CS2106   Introduction to Operating Systems                       NIL   4 MC
--------------------------------------------------------------------------------------
```

<sup>***Figure 1.1.2** Expected results from adding the `core` module `Introduction to Operating Systems`, a 
`4` credit module with the code `CS2106`, with the prerequisite `CS2100`.*</sup>

[Back to Top](#table-of-contents)

----

<div style="page-break-after: always;"></div>

### **Delete existing module: `delete`** ###

Deletes an *existing* module from the list of modules via the module `code`. The delete command allows you to make any changes to the planning of modules throughout the semester. 

**Format:**

`delete <module code>`

> ℹ️ **Note:** 
> - The module must not be a prerequisite of another module.
> - Module codes are **case-insensitive**. This means that module codes like `CS2102` and `cs2102` are considered the same.

**Example of Usage(s) and Expected Outcome(s):**

The following section outlines some usages of the `delete` command and its expected output(s). 

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

[Back to Top](#table-of-contents)

----

<div style="page-break-after: always;"></div>

<!--@@author LJ-37-->

### **Update module information: `update`** ###

Updates relevant information (module `name`, `MCs`, `prerequisites` and/or `grade`) for the selected module on the list. You can arrange the information in any order or change multiple information in one command. The update command allows you to make any changes to the module information in the event of mistakes or unexpected changes to the module. 

**Format:**

`update <module code> [-n <name>] [-mc <credit>] [-g <grade>] [-p <prerequisite1,prerequisite2,...>]`

> ℹ️ **Note:**  
> - You cannot change the module type and code as iGraduate uses them to identify the modules. If you would like to remove the modules, use the [`delete` command](#delete-existing-module-delete) instead. 
> - You can only update a module's grade if the module has been completed (see
[`done` command](#mark-a-module-as-complete-done)). 
> - You cannot update the grade of an incomplete module. 
> - Although all parameters are optional, the update command requires at least 1 parameter to be used. 
> - Module codes are **case-insensitive**. This means that module codes like `CS2102` and `cs2102` are considered the same. 

**Example of Usage(s) and Expected Outcome(s):**

The following section outlines some usages of the `update` command and its expected output(s). 

`update CS2106 -n Introduction to Operating Systems -mc 4 -g A -p CS1010,CS2100`

```
--------------------------------------------------------------------------------------
Nice! I've updated this module:
  [C][O] CS2106   Introduction to Operating Systems                         A   4 MC
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.3.1** Expected results from updating the `credit`, `grade` and `prerequisites` of the module with the code `CS2106`. The `credit` was updated to `4`, grade to `A` and prerequisites to `CS1010` and `CS2100`.*</sup>

[Back to Top](#table-of-contents)

----

<div style="page-break-after: always;"></div>

### **Mark a module as complete: `done`** ###

The done command marks a module on the list as completed. In addition, you must include the `grade` obtained to facilitate the calculation of CAP. If you would like to modify the grades, use the [`update` command](#update-module-information-update). 

**Format:**

`done <module code> -g <grade>`

> ℹ️ **Note:** 
> - Only the following letter grades (A+, A, A-, B+, B, B-, C+, C, D+, D, F, S, U, CS, CU) are valid.
> - Module codes are **case-insensitive**. This means that module codes like `CS2102` and `cs2102` are considered the same.

**Example of Usage(s) and Expected Outcome(s):**

The following section outlines some usages of the `done` command and its expected output(s). 

`done CS2106 -g A`

```
--------------------------------------------------------------------------------------
Nice! I've updated this module:
  [C][O] CS2106   Introduction to Operating Systems                         A   4 MC
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.4.1** Expected results from marking the module with the code `CS2106` with the grade `A` as done.*</sup>

`done GES1041 -g S`

```
--------------------------------------------------------------------------------------
Nice! I've marked this module as done:
  [G][O] GES1041  Everyday Ethics in Singapore                              S   4 MC
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.4.2** Expected results from marking the module with the code `GES1041` with the grade `S` as done.*</sup>

[Back to Top](#table-of-contents)

----

<div style="page-break-after: always;"></div>

<!--@@author kewenlok-->

### **Show module information: `info`** ###

The info command shows the information of the specified module in a detailed manner. All information related to the specified module including its prerequisites will be shown to you. 

**Format:** 

`info <module code>`

> ℹ️ **Note:** Module codes are **case-insensitive**. This means that module codes like `CS2102` and `cs2102` are considered the same. 

**Example of Usage(s) and Expected Outcome(s):**

The following section outlines some usages of the `info` command and its expected output(s). 

`info CS2106`

```
--------------------------------------------------------------------------------------
Printing CS2106 module information...
Module Type                           : Core
Module Code                           : CS2106
Module Name                           : Introduction to Operating Systems
Modular Credits                       : 4.0 MC
Status                                : not taken
Grade                                 : NIL
Prerequisites                         : [CS2100]
Incomplete Prerequisites              : [CS2100]
Prerequisite for                      : []
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.5.1** Expected results from showing the detailed information of `CS2106`, a core module.*</sup>

`info ger1000`

```
--------------------------------------------------------------------------------------
Printing GER1000 module information...
Module Type                           : GE
Module Code                           : GER1000
Module Name                           : Quantitative Reasoning
Modular Credits                       : 4.0 MC
Status                                : taken
Grade                                 : A
Prerequisites                         : []
Incomplete Prerequisites              : []
Prerequisite for                      : []
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.5.2** Expected results from showing the detailed information of `GER1000`, a GE module.*</sup>

[Back to Top](#table-of-contents)

----

<div style="page-break-after: always;"></div>

<!--@@author LJ-37-->

### **List modules: `list`** ###

The list command lists modules based on the option you provided. There are four main options, `all`, `incomplete`, `complete` and `available`. 
1. `all` 
    - lists all modules on your list
1. `complete` 
    - lists all modules that have been taken
1. `incomplete` 
    - lists all added modules that has not been completed (i.e. modules that you are currently taking or yet to take)
1. `available`
    - lists all incomplete modules that can be taken based on prerequisites fulfilled.

You could also list specific type of modules by specifying a valid module type, `core`, `elec`, `ge` or `math` as an option. The list shows all module details
**excluding** the module prerequisites. 

**Format:**

`list <all|incomplete|complete|available|core|elec|ge|math>`

> ℹ️ **Note:** If a module is completed, the module `grade` attained is displayed. Otherwise, a `NIL` is displayed instead. 

**Example of Usage(s) and Expected Outcome(s):**

The following section outlines some usages of the `list` command and its expected output(s). 

`list all`

```
--------------------------------------------------------------------------------------
Module List:
1: [C][O] CS1010   Introduction to Programming                              B+   4 MC
2: [G][O] GES1041  Everyday Ethics in Singapore                              S   4 MC
3: [C][X] CS2100   Computer Organisation                                   NIL   4 MC
4: [C][X] CS2106   Introduction to Operating Systems                       NIL   4 MC
5: [G][X] GER1000  Quantitative Reasoning                                  NIL   4 MC
6: [M][O] MA1521   Calculus for Computing                                   A-   4 MC
7. [E][X] LAJ1201  Japanese 1                                              NIL   4 MC
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.6.1** Expected results from listing `all` modules. The list includes all module information that has been added.*</sup>

`list incomplete`

```
--------------------------------------------------------------------------------------
Modules you have yet to complete:
1: [C][X] CS2100   Computer Organisation                                   NIL   4 MC
2: [C][X] CS2106   Introduction to Operating Systems                       NIL   4 MC
3: [G][X] GER1000  Quantitative Reasoning                                  NIL   4 MC
4. [E][X] LAJ1201  Japanese 1                                              NIL   4 MC
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.6.2** Expected results from listing `incomplete` modules. The list includes the module information that has been added but has not been completed (indicated with an "X").*</sup>

`list complete`

```
--------------------------------------------------------------------------------------
Modules you have have completed:
1: [C][O] CS1010   Introduction to Programming                              B+   4 MC
2: [G][O] GES1041  Everyday Ethics in Singapore                              S   4 MC
3: [M][O] MA1521   Calculus for Computing                                   A-   4 MC
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.6.3** Expected results from listing `completed` modules. The list includes the module information that has been completed (indicated with an "O").*</sup>

`list available`

```
--------------------------------------------------------------------------------------
Modules can be taken:
1: [C][X] CS2100   Computer Organisation                                   NIL   4 MC
2: [G][X] GER1000  Quantitative Reasoning                                  NIL   4 MC
3. [E][X] LAJ1201  Japanese 1                                              NIL   4 MC
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.6.4** Expected results from listing `available` modules. The list includes the module information that has been added and can be taken (with all prerequisites fulfilled).*</sup>

<!--@@author kewenlok-->

`list core`

```
--------------------------------------------------------------------------------------
Core modules in the list:
1: [C][O] CS1010   Introduction to Programming                              B+   4 MC
2: [C][X] CS2100   Computer Organisation                                   NIL   4 MC
3: [C][X] CS2106   Introduction to Operating Systems                       NIL   4 MC
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.6.5** Expected results from listing `core` modules. This option will list out all `core` modules on the list.*</sup>

`list elec`

```
--------------------------------------------------------------------------------------
Elective modules in the list:
1. [E][X] LAJ1201  Japanese 1                                              NIL   4 MC
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.6.6** Expected results from listing `elec` modules. This option will list out all `unrestricted elective` modules on the list.*</sup>

`list ge`

```
--------------------------------------------------------------------------------------
GE modules in the list:
1: [G][O] GES1041  Everyday Ethics in Singapore                              S   4 MC
2: [G][X] GER1000  Quantitative Reasoning                                  NIL   4 MC
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.6.7** Expected results from listing `ge` modules. This option will list out all `general elective` modules on the list.*</sup>

`list math`

```
--------------------------------------------------------------------------------------
Math modules in the list:
1: [M][O] MA1521   Calculus for Computing                                   A-   4 MC
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.6.8** Expected results from listing `math` modules. This option will list out all `mathematics` modules on the list.*</sup>

[Back to Top](#table-of-contents)

----

<div style="page-break-after: always;"></div>

<!--@@author ??-->

### **Show academic progression: `progress`** ###

Displays a bar that represents the current progress of your academic career. The progress bar shows the percentage of your **total completed module `credits`** against the *total number of credits needed* for fulfilling the graduation requirements. The bar will fill up as more module credits are completed. 

**Format:**

`progress`

> ℹ️ **Note:** 
> - The number of modular credits used to calculate the progress bar is `160`, the graduation requirement of an **NUS single-degree Information Security undergraduate** student
> - If the total number of completed MCs exceeds 160, the progress bar will still display `100%`.
> (See Q7 of [FAQ](#frequently-asked-questions))

**Example of Usage(s) and Expected Outcome(s):**

The following section outlines some usages of the `progress` command and its expected output(s). 

`progress`

```
--------------------------------------------------------------------------------------
Progress:
░░░░░░░░░░░ 5.00%
8MCs/160MCs Completed
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.7.1** Expected results when 8 out of the 160 MCs has been completed (5% completion)*</sup>

[Back to Top](#table-of-contents)

----

<div style="page-break-after: always;"></div>

### **Calculate CAP: `cap`** ###

Calculates your current Cumulative Average Point (CAP) according to the modules you have completed with the grade obtained (i.e. A+ to F). The `cap` command also displays the current degree classification based on the CAP calculated. 

> ℹ️ **Note:** Modules with Satisfactory/Unsatisfactory (S/U) grades are not calculated in the CAP.

**Format:**

`cap`

**Example of Usage(s) and Expected Outcome(s):**

The following section outlines some usages of the `cap` command and its expected output(s). 

`cap`

```
--------------------------------------------------------------------------------------
Current CAP: 4.00
Current Degree Classification: Honours (Distinction)
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.8.1** Expected results from running the `cap` command based on the modules in the previous sections (see [`list complete`](#list-modules-list) for the list of modules used to calculate this cap)*</sup>

[Back to Top](#table-of-contents)

----

<div style="page-break-after: always;"></div>

### **Exit the program: `exit`** ###
Exits the program. 

**Format:**

`exit`

**Example of Usage(s) and Expected Outcome(s):**

The following section outlines some usages of the `exit` command and its expected output(s). 

`exit`

```
--------------------------------------------------------------------------------------
See you soon! Happy studying!
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.9.1** Expected results from exiting the program*</sup>

[Back to Top](#table-of-contents)

----

<div style="page-break-after: always;"></div>

<!--@@author fupernova-->

### **Program Manual: `help`** ###

The `help` command provides you a quick reference guide on the description and format of the different commands. The help command allows you to list the different commands available or their purpose and format. 

Format:
`help [add|delete|update|done|info|list|progress|cap|exit]`

> ℹ️ **Note:** If an optional parameter is provided, `help` will provide a short description of the command's functionality and format. Otherwise, `help` will provide the list of commands available. 

**Example of Usage(s) and Expected Outcome(s):**

The following section outlines some usages of the `help` command and its expected output(s). 

`help`

```
--------------------------------------------------------------------------------------
iGraduate is a command line application that acts as a centralised hub for NUS students majoring in Information Security to plan their academic journey.
The application comes with 9 features:
-add
-delete
-update
-done
-info
-list
-progress
-cap
-exit

Type help <command> to view further details on each command.
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.10.1** Expected results of `help` without any parameters.*</sup>

`help delete`

```
--------------------------------------------------------------------------------------
The Delete command deletes an existing module from the list of modules added via the module code.

Syntax: delete <module code>
--------------------------------------------------------------------------------------
```
<sup>***Figure 1.10.2** Expected results of `help` with `delete` as the parameter*</sup>

[Back to Top](#table-of-contents)

----

<div style="page-break-after: always;"></div>

<!--@@author xseh-->

## **Storage of Data** ##

All data are stored automatically by iGraduate every time a module is modified (i.e. added, deleted or updated). The data will automatically loaded in when you relaunch iGraduate. No intervention is required. 

[Back to Top](#table-of-contents)

----

<div style="page-break-after: always;"></div>

<!--@@author kewenlok-->

## **Manual Modification of Data** ##

The IGraduate application data file is stored under the same folder where the IGraduate application resides. 
- You may notice a folder named `data` is created and there is a `modules.json` file inside the folder. 
- In 
`modules.json`, you will find your data created in the application here. 
- To modify the data of existing module, simply change the value of each attribute in the `json` file and save it. 


> ℹ️ **Note:** 
> - You are advised to add or remove new module data manually **only** if you understand the iGraduate's logic and `json` format. 
> - Otherwise, please ensure that you modify only the value of module attributes if you are unsure of how the `json` structure works. 
> - The application might not behave the way it is expected to if you modify the application data incorrectly. For example, data corruption might occur. 

The sample `Json` format for the module data file:
<pre>
[
   {
     "type": "core",                      <em>--> Module type (core/math/elec/ge)</em>
     "code": "CS1010",                    <em>--> Module code</em>
     "name": "Programming Methodology",   <em>--> Module name</em>
     "credit": 4.0,                       <em>--> Modular credit</em>
     "status": "not taken",               <em>--> Module status</em>
     "grade": "NIL",                      <em>--> Module grade (NIL if not assigned)</em>
     "prerequisites": [],                 <em>--> Prerequisites of the module</em>
     "untakenPrerequisites": [],          <em>--> Unsatisfied prerequisites of the module</em>
     "requiredByModules": [               <em>--> Modules required the current module as prerequisite</em>
       "CS2100",
       "CS2040C"
     ]
   },
   {
      ...
   },
   ...
]
</pre>

<sup>***Figure 1.11.1** Sample JSON file structure in modules.json*</sup>

> ℹ️ **Note:**
> - All fields shown in the example `json` file format above are compulsory. Missing any fields above in the file will
result in an error.
> - Please take note that you should fill in the `prerequisites`, `untakenPrerequisites` and `requiredByModules` field
with caution. Any wrong information will result in processing errors when performing operations.
> - Setting two or more modules in each other's `requiredByModules` is strongly discouraged as it does not reflect the
module requirements logic realistically and might prevent iGraduate from giving accurate advice.

[Back to Top](#table-of-contents)

----

<div style="page-break-after: always;"></div>

<!--@@author xseh-->

## **Frequently Asked Questions** ##

**Q1**: Can I use the application on my MacBook?
> Yes! iGraduate is a cross-platform application that can be run on Windows, Linux or Mac.  

**Q2**: Can I share my module list with my friends?
> Yes! Just go into `/data` of the application folder and pass the `modules.json` file over to your friends. Make sure
> your friends save the `modules.json` file under their `/data` folder as well!

**Q3**: Do I need internet connection when using iGraduate?
> No. No internet is required as the application stores and retrieves modules information locally. 

<!--@@author kewenlok-->

**Q4**: I am a NUS student who is not majoring in Information Security. Can I still use the application for module planning?
> Yes, of course! Most of the features provided by the application are implemented in a generic way. However, features
> such as progress is more targeted towards Information Security students. Support for other majors in NUS will be
> implemented in the future. Stay tuned!

<!--@@author xseh-->

**Q5**: Is the module list only in JSON format?
> Yes! Currently, module list data file is only in JSON format. However, there are various platforms available online to convert 
> the module list into a different format.

<!--@@author kewenlok-->

**Q6**: I noticed a `iGraduate-0.log` file is created after running the application. What is it for?
> The `iGraduate-0.log` is a log file for recording the crashes, errors and anomalies occurred when running the 
> application. You may submit the file [here](https://github.com/AY2021S2-CS2113T-W09-2/tp/issues) if you experienced
> any error during your use of the application for the developers to fix the problem.

<!--@@author fupernova-->

**Q7**: I want to take more modules than my major's graduation requirements. Does iGraduate allow me to do so?
> Yes! iGraduate does not limit the number of modules you can track. This means that you can add more modules even after 
> reaching your graduation requirements. Do note that the progress bar under `progress` command will still display 100% even
> if you exceed your graduation requirements.

[Back to Top](#table-of-contents)

----

<div style="page-break-after: always;"></div>

<!--@@author kewenlok-->

## **Command Summary** ##

Command | Format
--------|--------
add | `add <name> -c <module code> -t <core\|math\|ue\|ge> -mc <number of credits> [-p <prerequisite1,prerequisite2,...>]`
delete | `delete <module code>`
update | `update <module code> [-n <name>] [-mc <credit>] [-g <grade>] [-p <prerequisite1,prerequisite2,...>]`
done | `done <module code> -g <grade>`
info | `info <module code>`
list | `list <all\|incomplete\|complete\|core\|elec\|ge\|math>`
progress | `progress`
cap    | `cap`
exit     | `exit`
help     | `help [add\|delete\|update\|done\|info\|list\|progress\|cap\|exit]`

[Back to Top](#table-of-contents)

----