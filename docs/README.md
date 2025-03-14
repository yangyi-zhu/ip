# 🥖 Baguette

Baguette is a lightweight, command-line task management application designed to help users organize and track their tasks efficiently.

### 📌 Features

### ✅ Task Management
- **Add Tasks:** Create different types of tasks, including:
    - **To-dos:** Simple tasks without a deadline.
    - **Deadlines:** Tasks with a specific due date.
    - **Events:** Tasks with a start and end time.
- **Mark & Unmark Tasks:** Keep track of completed and pending tasks.
- **Delete Tasks:** Remove tasks that are no longer needed.
- **View Task List:** Display all stored tasks in an organized format.

### 🔎 Task Searching
- **Find Tasks by Keyword:** Search for tasks containing specific words.

### Command Formats
- todo [description]
- deadline [description] ddl: [DD-MM-YY HH:MM]
- event [description] from: [DD-MM-YY HH:MM] to: [DD-MM-YY HH:MM]
- mark [index]
- unmark [index]
- delete [index]
- list
- find [keyword]

### Examples for Task Addition
- todo Do the laundry
- deadline CS2113 Increment L5 ddl: 14-02-25 16:00
- event Josh's birthday party from: 03-03-25 15:00 to: 04-03-25 00:00