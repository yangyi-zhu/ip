# 🥖 Baguette

Baguette is a lightweight, command-line task management application designed to help users organize and track their tasks efficiently.

### 💻 Setup
1. Ensure that you have Java 17 installed. Open CMD and type the following:
    ```
    java -version
    ```
   If another version is detected or if you do not have Java installed, visit:
   > https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html
2. Download `Baguette.v0_3.jar` from the following link and move it to any directory you desire
   > https://github.com/yangyi-zhu/ip/releases/tag/v0.3
3. Click the `.jar` file to run. If no window pops up, right-click, copy file path, open CMD and enter the following:
   ```
   java -jar [path]
   ```

### 📌 Features

### ✅ Task Management
✍🏻 **Add Tasks:** Create different types of tasks, including:
- **To-dos:** Simple tasks without a deadline.
  > **Format:** todo [description] <br>
  > **Example:** todo Do the laundry
- **Deadlines:** Tasks with a specific due date.
  > **Format:** deadline [description] ddl: [DD-MM-YY HH:MM] <br>
  > **Example:** deadline CS2113 Increment L5 ddl: 14-02-25 16:00
- **Events:** Tasks with a start and end time.
  > **Format:** event [description] from: [DD-MM-YY HH:MM] to: [DD-MM-YY HH:MM] <br>
  > **Example:** event Josh's birthday party from: 03-03-25 15:00 to: 04-03-25 00:00
❌ **Mark & Unmark Tasks:** Keeps track of completed and pending tasks.
> **Format:** mark/unmark [index]
🗑️ **Delete Tasks:** Removes tasks that are no longer needed.
> **Format:** delete [index]
📋 **View Task List:** Displays all stored tasks in an organized format.
> **Format:** list
🔎 **Find Task:** Searches for tasks containing the given string (case-sensitive).
> **Format:** find [keyword]

### ❓ FAQs
**Q:** How do I transfer data to a friend or another device of my own? <br>
**A:** Locate the directory where you stored the `.jar` file. Open `../data` and your list should be saved in a file called `tasks.txt`. Place this file in the `../data` directory on the secondary device and run the program as normal.

### 🛠️ Known Issues
By default, completed tasks are marked with a checkmark `✓` symbol. This is not supported by all devices, and the user might see a question mark `?` in its place.
> If this is the case, the user can fix this by forking and cloning this repository to their local device, and replacing the checkmark with another character at Line 67 in `src/main/java/baguette/datatypes/Task.java`.
