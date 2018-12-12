Select *
From TaskStatus
Where IsResolved = 1;

Select *
From Task
Where TaskStatusId BETWEEN 5 AND 8;

SELECT *
FROM Task
INNER JOIN TaskStatus ON TaskStatus.TaskStatusId = Task.TaskStatusId
WHERE TaskStatus.IsResolved = 1;

SELECT task.title,
task.dueDate
FROM Task
INNER JOIN TaskStatus ON TaskStatus.TaskStatusId = Task.TaskStatusId
WHERE TaskStatus.IsResolved = 1;

SELECT task.title,
task.dueDate,
task.TaskTypeId
FROM Task
INNER JOIN TaskType ON TaskType.TaskTypeId = Task.TaskTypeId
INNER JOIN TaskStatus ON TaskStatus.TaskStatusId = Task.TaskStatusId
WHERE TaskType.TaskTypeId = 3 AND TaskStatus.IsResolved = 0;

SELECT COUNT(*)
FROM Task;

-- 165 Worker Ids
SELECT COUNT(WorkerId)
FROM ProjectWorker;

-- Sum of workerIds 8311
SELECT SUM(WorkerId)
FROM ProjectWorker;

-- The Average WorderIds SUM/COUNT = 50.3697
SELECT AVG(WorkerId)
FROM ProjectWorker;

-- Minimum WorkerId value = 1
SELECT MIN(WorkerId)
FROM ProjectWorker;

-- Maximum WorkerId value = 100
SELECT MAX(WorkerId)
FROM ProjectWorker;

-- That the max is 100 means we should have repeat Id numbers if count is 165 - Let's check that
-- As it should - It is the Project Worker bridge tand this has some workers working multiple projects
SELECT *
FROM ProjectWorker
ORDER BY WorkerId DESC;
-- Yes 66 of the Id are duplicate Ids