import java.util.ArrayList;
/**
 * JobSchedule is a data structure that holds a graph representing a series of tasks or "jobs" and the jobs that need to be done before them.
 * @author Nick
 *
 */
public class JobSchedule {

	
	public class Job{
		private ArrayList<Job> postrequisites = new ArrayList<Job>();
		private int totalCompletionTime = -1;
		private int time;
		private int inDegree = 0;
		private int maximumInDegree = 0;
		/**
		 * Constructs a new Job with time set to the passed integer
		 */
		private Job(int newTime)
		{
			time = newTime;
			timesAreUpdated = false;
		}
		/**
		 * sets the passed job j as a prerequisite for this
		 * @param j
		 */
		public void requires(Job j)
		{
			j.postrequisites.add(this);
			maximumInDegree ++;
			timesAreUpdated = false;
		}
		/**
		 * calculates the completion times of every job in the Schedule
		 * returns the earliest time This Job can be started
		 * If the job cannot be completed, returns -1 (This would be because a cycle in its prerequisites
		 */
		public int getStartTime()
		{
			if (!timesAreUpdated) calculateTotalCompletionTimes();
			
			if (inDegree != 0) return -1;
			return totalCompletionTime - time;
		}	
	}
	
	
	private ArrayList<Job> jobs = new ArrayList<Job>();
	private boolean timesAreUpdated = false;
	private boolean canBeCompleted = false;
	int greatestTime = -1;
	/**
	 * Constructs an empty JobSchedule
	 */
	public JobSchedule(){}

	/**
	 * 
	 * @param jobIndex
	 * @return returns the job located at the passed index
	 */
	public Job getJob(int index)
	{
		return jobs.get(index);
	}
	
	/**
	 * Adds a new job to the list with its time set to the parameter time
	 */
	public Job addJob(int time)
	{
		Job newJob = new Job(time);
		jobs.add(newJob);
		timesAreUpdated = false;
		return newJob;
	}
	
	/**
	 * returns the smallest amount of time that it will take to complete the
	 * entire job schedule
	 */
	public int minCompletionTime() {
		if (!timesAreUpdated) {
			calculateTotalCompletionTimes();
		}
		if (canBeCompleted) return greatestTime;
		return -1;
	}

	/**
	 * uses Khan's algorithm for topological sort. the jobs in the returned
	 * arrayList will be completable in the order given. Only jobs that CAN BE
	 * COMPLETED will be in the array. Any jobs that do not appear in the sorted
	 * array cannot be completed.
	 */
	private ArrayList<Job> topologicalSort() 
	{
		ArrayList<Job> sortedJobs = new ArrayList<Job>();
		for (int i = 0; i < jobs.size(); i ++) //set every inDegree to be the correct inDegree
		{
			getJob(i).inDegree = getJob(i).maximumInDegree;
		}
		
		for (int i = 0; i < jobs.size(); i ++)  //find every zero in degree job
		{
			if (getJob(i).inDegree == 0)
			{
				sortedJobs.add(getJob(i));
			}
		}
		for (int i = 0; i < sortedJobs.size(); i ++) //job adding loop. Goes through each already sorted job
		{
			for (int e = 0; e < sortedJobs.get(i).postrequisites.size(); e ++) //decriment each outogoing edges' inDegree
			{
				Job currentJob = sortedJobs.get(i);
				Job nextJob = currentJob.postrequisites.get(e);
				nextJob.inDegree --;
				if (nextJob.inDegree == 0)
				{
					
					sortedJobs.add(nextJob); //if the job's indegree gets to zero, add it to the sorted list.
				}
			}
		}
		canBeCompleted = (sortedJobs.size() == jobs.size());
		return sortedJobs;
		
	}
	
	
	/**
	 * calculates and sets the total completion time for every job in the passed arrayList
	 * returns true if every job can be completed
	 */
	private void calculateTotalCompletionTimes()
	{
		ArrayList<Job> sortedJobs = topologicalSort(); //get a list of completable jobs
		//for each Job in the order
		for(int i = 0; i < sortedJobs.size(); i ++) 
		{	
			Job tempJob = sortedJobs.get(i);
			if (tempJob.totalCompletionTime == -1) //if it hasn't been found yet, set its completionTime to its own time
			{
				tempJob.totalCompletionTime = tempJob.time;
//				if (tempJob.totalCompletionTime > greatestTime)
//					greatestTime = tempJob.totalCompletionTime;
			}
			//for each outgoing edge
			for(int e = 0; e < tempJob.postrequisites.size(); e ++)
			{
				// if the new weight of postreq is higher, update it
				Job postreq = tempJob.postrequisites.get(e);

				if (postreq.totalCompletionTime < tempJob.totalCompletionTime + postreq.time) {
					postreq.totalCompletionTime = tempJob.totalCompletionTime + postreq.time;
//					if (postreq.totalCompletionTime > greatestTime)
//						greatestTime = tempJob.totalCompletionTime;
				}
				

			}
			if (sortedJobs.get(i).totalCompletionTime > greatestTime)
				greatestTime = tempJob.totalCompletionTime;
		}
		timesAreUpdated = true;
	}
}
