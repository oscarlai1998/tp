# iGraduate User Guide
By: `W09-2` Latest update: `25 March 2021`

* [Introduction](#introduction)
* [Quick Start](#quick-start)
* [Features](#features)
    * [Add todo : `todo`](#add-todo--todo)
    * [Add event : `event`](#add-event--event)
    * [Add deadline : `deadline`](#add-deadline--deadline)
    * [List all tasks : `list`](#list-all-tasks--list)
    * [Mark task as done : `done`](#mark-task-as-done--done)
    * [Delete task : `delete`](#delete-task--delete)
    * [Find task : `find`](#find-task--find)
    * [Exit Program : `bye`](#exit-program--bye)
* [Storage](#storage)
* [Command Summary](#command-summary)

## Introduction
iGraduate is a command line application that will help NUS students majoring in information security check his/her 
graduation progress and modules taken in a coherent manner based on the programme requirements. It also contains tools 
to help make informed decisions about future modules. iGraduate data is saved in the hard disk automatically after any 
command that changes the data. There is no need to save manually.

## Quick Start

1. Make sure you have Java version 11 installed on your computer.
2. Download 'Duke.jar' from latest github release by clicking [here](https://github.com/kewenlok/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your Duke program.
4. Run the program in command prompt using `java -jar Duke.jar` command.
5. Type your command in the command prompt to interact with Duke.

## Features

### Add todo : `todo`

Adds a new todo task to task list.

Format:

`todo <task name>`

Example of Usage:

`todo exercise`

Expected Outcome:

    Got it. I've added this task:
      [T][✘] exercise
    Now you have 1 tasks in the list.

### Add event : `event`

Adds a new event task with date/time to task list.

Format:

`event <task name> /at <event date/time>`

Example of Usage:

`event AWS Summit 2020 /at 25 April 2020, 9AM`

Expected Outcome:

    Got it. I've added this task:
      [E][✘] AWS Summit 2020 (at: 25 April 2020, 9AM)
    Now you have 2 tasks in the list.

### Add deadline : `deadline`

Adds a new deadline task with deadline to task list.

Format:

`deadline <task name> /by <deadline>`

Example of Usage:

`deadline CS2113T Assignment /by 2021-03-05 23:59`

**Note**: The format of deadline should be `YYYY-MM-DD HH:MM`.

Expected Outcome:

    Got it. I've added this task:
      [D][✘] CS2113T Assignment (by: 5 Mar 2021 11:59pm)
    Now you have 3 tasks in the list.

### List all tasks : `list`

List all the tasks in the task list.

Format:

`list`

Example of Usage:

`list`

Expected Outcome:

    Here are the tasks in your list:
    1. [T][✘] practice coding
    2. [D][✓] CS2105 Assignment (by: 31 Jan 2021 11:59pm)
    3. [T][✘] Assignment
    4. [T][✘] exercise
    5. [E][✘] AWS Summit 2020 (at: 25 April 2020, 9AM)
    6. [D][✘] CS2113T Assignment (by: 5 Mar 2021 11:59pm)

### Mark task as done : `done`

Mark the specified task as done.

Format:

`done <index number>`

Example of Usage:

`done 5`

Expected Outcome:

    Nice! I've marked this task as done:
      [E][✓] AWS Summit 2020 (at: 25 April 2020, 9AM)

### Delete task : `delete`

Delete the specified task.

Format:

`delete <index number>`

Example of Usage:

`delete 4`

Expected Outcome:

    Noted. I've removed this task:
      [T][✘] exercise
    Now you have 5 tasks in the list.

### Find task : `find`

Find tasks that contains a specified keyword.

Format:

`find <keyword>`

Example of Usage:

`find assignment`

Expected Outcome:

    Here are the matching tasks in your list:
    1. [D][✓] CS2105 Assignment (by: 31 Jan 2021 11:59pm)
    2. [T][✘] Assignment
    3. [D][✘] CS2113T Assignment (by: 5 Mar 2021 11:59pm)

### Exit Program : `bye`

Saves data and exits the Duke program.

Format:

`bye`

Example of Usage:

`bye`

Expected Outcome:

    Bye. Hope to see you again soon!

## Storage

All data are stored automatically by Duke everytime a change is being made and when exiting the program. The data will be loaded in the next time of usage.

## Command Summary

Command | Format
--------|--------
todo    |`todo <task name>`
event   |`event <task name> /at <event date/time>`
deadline|`deadline <task name> /by <deadline>`<br>The format of deadline should be `YYYY-MM-DD HH:MM`.
list    |`list`
done    |`done <index number>`
delete  |`delete <index number>`
find    |`find <keyword>`
bye     |`bye`