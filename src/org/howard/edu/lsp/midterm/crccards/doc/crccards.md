
**TaskManager collaborates with Task, but Task does not collaborate with TaskManager.**

TaskManager's core responsibility is to manage a collection of Task objects — it stores them, retrieves them by ID, and filters them by status. To carry out these responsibilities, TaskManager must directly interact with Task objects, making Task a collaborator. Task, however, is only responsible for holding its own data (ID, description, and status) and has no need to know about or interact with the collection that manages it, so TaskManager does not appear as a collaborator on Task's CRC card.