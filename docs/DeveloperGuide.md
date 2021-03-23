# iGraduate
By: `W09-2` Latest update: `22 March 2021`

- [iGraduate Developer Guide](#igraduate-developer-guide)
    * [1. Introduction](#1-introduction)
    * [2. Setting up, getting started](#2-setting-up-getting-started)
    * [3. Design](#3-design)
        + [3.1 Architecture](#31-architecture)
        + [3.2 UI component](#32-ui-component)
        + [3.3 Logic component](#33-logic-component)
        + [3.4 Model component](#34-model-component)
        + [3.5 Storage component](#35-storage-component)
        + [3.6 Common classes](#36-common-classes)
    * [4. Implementation](#4-implementation)
        + [4.1]
    * [Appendix: Requirements](#appendix-requirements)
        + [Product Scope](#product-scope)
            + [Target User Profile](#target-user-profile)
            + [Value Proposition](#value-proposition)
        + [User Stories](#user-stories)

## 1. Introduction
iGraduate is a command line interface application written in Java. 
The iGraduate API allows students to add modules to take, edit, delete or 
mark these modules as done. Students can also calculate their CAP at any 
point of time and list modules added to the application.

## 2. Setting up, getting started

## 3. Design

### 3.1 Architecture

![archi](./images/ArchitectureDiagram.png)

The Architecture Diagram given above explains the high-level design of the App. Given below is a quick overview of each 
component.

iGraduate has one class called `iGraduate` which contains a main and run method and iGraduate constructor. 
It is responsible for:

- At app launch: Initialising the components in the correct sequence, and connects them up with each other.
- At shut down: Shuts down the components and invokes cleanup methods where necessary.

`Commons` represents a collection of classes used by multiple other components.

The rest of the App consists of four components:
- UI: The UI of the App.
- Logic: The command executor.
    - Parser - Understands and interprets the commands input by user, passes command to run to Command.
    - Command - Executes command based on what is parsed.
- Model: The user data held by the program
    - Module - Holds the information of individual modules.
    - ModuleList - Holds data of all modules of the app in memory.
- Storage: Reads data from, and writes data to, the hard disk.

Each of the four components,
- defines its API in an interface with the same name as the Component.
- exposes its functionality using a concrete {Component Name}Manager class (which implements the corresponding 
API interface mentioned in the previous point.

How the architecture interacts with each other [DIAGRAM]

### 3.2 UI Component
The UI is a public class that consists of **three components** that is made up `Scanner`, `Constants` 
and `Print Methods`. 

The `UI` component:
- Executes user command using the Logic Package which consist of `Command` and `Parser` classes.
- Listens for calls from `Model` package which consist of `ModuleList` and `Module` classes. Model packages will call
  the specific print method to print an output.
- Print method references `Constants` and prints them for user to see.

### 3.3 Logic Component
The logic component consists of  the class `Parser` and the package `command`. They work together to deal
with interpreting user input, identifying the right command to execute as well as the
actual execution of the command. `Parser` identifies the command to run and extracts the parameters and flags
required for the command from user input and passes these values to `command`, which then runs the command.

### 3.3.1 Parser
#### Description
The parser interprets user input and subsequently passes the properly parsed user input to `command` to execute the command.
#### Design
The parser feature contains one class, `Parser.java` and does its job through the `parseCommand()` method.

`parseCommand()`extracts the command phrase entered by the user by extracting the first word of the user input.
Based on the type of command from the user, `parseCommand()` then calls different methods to extract parameters and 
flags from the user command that are relevant to the command. The parser then creates 
the relevant `Command` object and dispatches the control of the program to the created object.

Given below is the Parser class diagram showing the important methods that returns a `Command` object.
![archi](./images/ParserClassDiagram.png)

### 3.3.2 Command
#### Description
The `command` component executes the correct command based on what the parser interprets.
#### Design
The `command` component consists of an abstract class `Command` and 8 subclasses that inherit from it.

The 8 subclasses are:
* AddCommand
* CapCommand
* DeleteCommand
* DoneCommand
* ExitCommand
* ListCommand
* ProgressCommand
* UpdateCommand

The correct command is executed once the `Command` object is created by the parser by executing the `execute()` method in the correct subclass.
The command execution can affect the `Model` (eg. adding a module).
At the end of each command execution, different methods in the `Ui` will be called to perform certain actions, such as displaying the list of modules to the user.

### 3.4 Model Component

The `model` component consists of two main packages that define and deal with data storing issues based on the information 
provided by the user input. The data storing issues are split into two main categories, what data should be included in for a 
module and a container managing the module objects. The `module` package holds the information which acts as a blueprint for 
creating and manipulating module objects while the `list` package consists of a class that defines the way the module objects 
should be managed and stored.

#### 3.4.1 `module` Package

##### Description

The `module` package consists of classes that are used to define the type of data to be stored in a module object and establish 
a framework to show how other components can make use of the features in module classes.

##### Design

The `module` package consists of classes related to module objects. An abstract class `Module` is created to hold attributes 
and methods applicable to all class objects. It is then inherited by all other child module classes. A class diagram illustrating 
the relationship between the interaction of classes under the module package is shown below.

![archi](./images/ModulePackageClassDiagram.png)
<sup>***Figure 3.4.1.1** UML class diagram for Module package*</sup>

The following child classes are created to handle different types of modules based on the generic module type available in 
the university:
- `CoreModule`
- `GeModule`
- `ElectiveModule`
- `MathModule`

Each of the module classes consists of:
- Attributes related to the module type it is representing
- Getter and setter methods for setting and retrieval of its attributes
- Methods that alter an instance of its own class

##### 3.4.1.1 `Module` Class

`Module` class is an abstract class in the module package. It holds the attributes and methods for manipulating the attributes 
applicable to all modules. The attributes found in the `Module` class are:

Scope   | Type              | Variable             | Description | 
--------|-------------------|----------------------|-------------|
private | String            | code                 | Module code of the module object.
private | String            | name                 | Module name of the module object.
private | double            | credit               | Modular credit of the module object.
private | String            | status               | Status of the module, whether it is “taken”, “not taken” or “taking”.
private | String            | grade                | The grade of taken modules.
private | ArrayList<String> | preRequisites        | A list of prerequisite modules.
private | ArrayList<String> | untakenPreRequisites | A list of unsatisfied prerequisite modules.
private | ArrayList<String> | requiredByModules    | A list of modules requiring the current module as a prerequisite.

The `Module` class also consists of methods that set and get the value of attributes shown in the table above. There are 
four additional methods in the class, namely `removeUntakenPreRequisite`, `removeRequiredByModule`, `getStatusIcon`and `toString`
. The `removeUntakenPreRequisite` and `removeRequiredByModule` methods are used to remove a single`untakenPreRequisites` module 
and `requiredByModules` module respectively, whereas `getStatusIcon` returns the status icon based on the module 
status. For customized formatting of module printing messages, `toString` method is overridden.

##### 3.4.1.2 `CoreModule` Class

The `CoreModule` class inherits from the `Module` class. It initializes the core module object with the information needed and 
contains a `toString` method that overrides the format of core module printing.

##### 3.4.1.3 `GeModule` Class

The `GeModule` class inherits from the `Module` class. It initializes the general education module object with the information needed 
and contains a `toString` method that overrides the format of general education module printing.

##### 3.4.1.4 `ElectiveModule` Class

The `ElectiveModule` class inherits from the `Module` class. It initializes the elective module object with the information needed 
and contains a `toString` method that overrides the format of elective module printing.

##### 3.4.1.5 `MathModule` Class

The `MathModule` class inherits from the `Module` class. It initializes the math module object with the information needed and 
contains a `toString` method that overrides the format of math module printing.

### 3.4.2 `list` Package

### 3.5 Storage Component
Class Diagram:

![archi](./images/storageClassDiagram.jpg)

<sup>***Figure 3.5.1** UML class diagram for Storage package*</sup>

The `Storage` Component, 
- Can save `module` objects in the `moduleList` in a JSON format and read them back

![archi](./images/storageObjectDiagram.jpg)

<sup>***Figure 3.5.2** UML object diagram for an instance of storage object*</sup>

### 3.6 Common classes
The common class used by multiple components in this app are in the `exception` package. The `exceptions` are thrown
when an error occurs. The method catches the exceptions and prints out the respective error message. 

Each `exception` is specified by the name and description.

## 4. Implementation
This section elaborates on some details about how certain features are implemented.

### 4.1 UI

### 4.2 Parser

### 4.3 Command

#### 4.3.1 Add Command

#### 4.3.2 Delete Command

#### 4.3.3 Update Command

#### 4.3.4 List Command

#### 4.3.5 CAP Command

#### 4.3.6 Done Command

#### 4.3.7 Progress Command

### 4.4 Module

### 4.5 ModuleList

### 4.6 Storage
The storage feature saves the state of the module list after every execution of commands that manipulates 
(i.e. update, add or delete) the modules in the list.

<b>Details</b>

The storage function is executed after every command that manipulates (i.e. adds, deletes or updates) the 
modules in the module list, saving the updated state into the storage file. 
The module list is stored in a storage file named `modules.json` in the `data` folder 
(`<program location>/data/modules.json`). 

![archi](./images/storageSequenceDiagram.jpg)

<sup>***Figure 3.5.2** UML sequence diagram showing the life of Storage when the Add command is invoked*</sup>

<b>Considerations</b>

The main reason for using a JSON file instead of designing one is to allow a more robust error and exception 
handling and management with regards to modified storage files. The parsing of JSON format is also more 
sophisticated and reliable. 

In addition, the JSON format can be read across multiple different types of applications, allowing flexibility
 in any future implementations regarding exporting of data. 

<b>Alternatives</b>

The alternative storage format considered is the use of delimiters. However, there are concerns regarding such
 usage; the most important being potential parsing failure from a valid module. With the use of common 
 delimiters such as commas (,) and dashes (-), the program is unable to differentiate between the various 
 module information and legitimate module names containing delimiters and may parse the portion of the module 
 to a wrong variable, resulting in corrupted results and a potential program crash. One example of such 
 occurrence would be a module named `Software Engineering and Object-Oriented Programming`, which contains 
 dashes when the delimiters are used for separating various module information is also a dash. 

Considerations were also given to use more unique delimiters (such as \\, |, etc.) to avoid accidental parsing
 fails but the problem still remains. Attempting to fuzz characters would lead to a corrupted storage file and
  render the application useless. Ultimately, the idea was scrapped in favour of the JSON format with a 
  third-party library, since the exception handling and parsing management lies in the library functions. 


### 4.7 Exception

## Appendix: Requirements

### Product scope

#### Target user profile:
* is a NUS Information Security student
* has a need to manage and plan modules
* has a need to track graduation progress
* has a need to track his/her CAP
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

#### Value proposition:
Allows users to manage modules faster than a typical mouse/GUI driven app.
Includes higher level features such as ability to add modules while ensuring user has cleared all prerequisites
and to list all modules taken, graduation progress and current CAP with degree classification.
This app will help NUS students majoring in information security check his/her graduation progress and modules taken in 
a coherent manner based on the programme requirements.
It also contains tools to help make informed decisions about future modules.

### User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|user|see the list of modules I can take now|decide on which modules to register|
|v1.0|user|clearly see which modules I have taken|to check my prerequisites|
|v1.0|user|delete mods that I have added|amend any typos|
|v1.0|user|add new modules that I have taken|track the modules I have taken|
|v1.0|user|utilise the CLI to input my information and execute functions||
|v1.0|user|have my data be persistent|access it again next time when I run the program|
|v1.0|impatient user|access information quickly||
|v1.0|lazy user|access information with as little effort as possible||
|v1.0|user|know my academic progress|estimate my pace and plan for the future|
|v2.0|careless user|know if the module I entered is valid||
|v2.0|user|calculate my current CAP||
|v2.0|user|know if the module I entered is valid|monitor my performance throughout the course of study|
|v2.0|user|see what modules I have already taken|plan what modules to take next semester|
|v2.0|forgetful user|know if I meet the prerequisites for computing modules I want to take||
|v2.0|user|edit the modules for the course|amend my mistakes or changes in module details|
|v2.0|user|be able to pick out any module as my UE||
|v2.0|user|know the pre-requisites of a module|plan my semesters better|
|v2.0|user|see all core modules I have to take||
|v2.0|user|be able to choose any electives of my course||


## Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed.
2. Should be able to hold up to 1000 modules without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. A user without online connection should still be able to use the application.
5. A beginner user without prior knowledge should be able to pick up the application comfortably.

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
