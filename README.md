# Job-Scheduler
Finds the minimum time needed to complete a series of tasks

## Structure 
The program models jobs and their prerequisites using a graph structure.
  Each job(Vertex) takes a certain amount of time to complete, 
  Each job can only be started after its prerequisites (Edges) are completed.
  
The scheduler can find the minimum amount of time required to complete all tasks stored.
  This algorithm uses Kahn's algorithm to sort the graph topologically, 
  then calculates each job's total completion time in topological order by considering its prerequisite's completion times.
  
## Runtime
Kahn's algorithm runs in linear time O(V+E), where V is the number of jobs and E is the number of prerequisites.
  This is because Kahn's algorithm considers each vertex and each edge once.
  
The calculation of completion times takes linear time as well, because once the jobs are topologically sorted, each job is then considered once, looking at its prerequisites once each.

