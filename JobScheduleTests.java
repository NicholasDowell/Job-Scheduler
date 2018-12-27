import org.junit.Test;
public class JobScheduleTests {

	
	@Test
	public void testTopologicalCycleDetection()
	{
		JobSchedule js = new JobSchedule();
		js.addJob(0);
		js.addJob(1);
		js.addJob(2);
		js.addJob(3);
		js.addJob(4);
		
		js.getJob(1).requires(js.getJob(0));
		js.getJob(3).requires(js.getJob(1));
		js.getJob(2).requires(js.getJob(3));
		js.getJob(4).requires(js.getJob(0));
		js.getJob(1).requires(js.getJob(4));
		js.getJob(4).requires(js.getJob(2));
		
		assert(js.minCompletionTime() == -1);
	}
	
	@Test
	public void testTotalCompletionTimes()
	{
		JobSchedule js = new JobSchedule();
		js.addJob(3);
		js.addJob(2);
		js.addJob(1);
		js.addJob(0);
		
		js.getJob(1).requires(js.getJob(0));
		js.getJob(2).requires(js.getJob(1));
		js.getJob(3).requires(js.getJob(2));
		
		assert(js.getJob(0).getStartTime() == 0);
		assert(js.getJob(1).getStartTime() == 3);
		assert(js.getJob(2).getStartTime() == 5);
		assert(js.getJob(3).getStartTime() == 6);
	}
	
	@Test
	public void testGetStartTime()
	{
		JobSchedule js = new JobSchedule();
		js.addJob(3);
		js.addJob(2);
		js.addJob(1);
		js.addJob(0);
		
		js.getJob(1).requires(js.getJob(0));
		js.getJob(2).requires(js.getJob(1));
		js.getJob(3).requires(js.getJob(2));
		
		assert(js.getJob(0).getStartTime() == 0);
		assert(js.getJob(1).getStartTime() == 3);
		assert(js.getJob(2).getStartTime() == 5);
		assert(js.getJob(3).getStartTime() == 6);
	}
	
	@Test
	public void testMinCompletionTime()
	{
		JobSchedule js = new JobSchedule();
		js.addJob(3);
		js.addJob(2);
		js.addJob(1);
		js.addJob(0);
		
		js.getJob(1).requires(js.getJob(0));
		js.getJob(2).requires(js.getJob(1));
		js.getJob(3).requires(js.getJob(2));
		
		assert(js.minCompletionTime() == 6);
		
	}
	
	@Test
	public void testMinCompletioTimeWithCycle()
	{
		JobSchedule js = new JobSchedule();
		js.addJob(3);
		js.addJob(2);
		js.addJob(1);
		js.addJob(0);
		
		js.getJob(1).requires(js.getJob(0));
		js.getJob(2).requires(js.getJob(1));
		js.getJob(3).requires(js.getJob(2));
		js.getJob(1).requires(js.getJob(3));
		
		assert(js.minCompletionTime() == -1);
		
	}
	@Test
	public void testLargerJobNumberOfJobs()
	{
		JobSchedule js = new JobSchedule();
		js.addJob(7);  //0
		js.addJob(3);  //1
		js.addJob(1);  //2
		js.addJob(12); //3
		js.addJob(7); //4
		js.addJob(4); //5
		js.addJob(5); //6
		js.addJob(11); //7
		js.addJob(2); //8
		js.addJob(3); //9
		js.addJob(8);  //10
		js.addJob(6);	//11
		js.addJob(12);  //12
		js.addJob(10);  //13
		js.addJob(5);  //14
		js.addJob(1);	//15
		js.addJob(14);  //16
		js.addJob(9);	//17
		js.addJob(3);	//18
		js.addJob(13);	//19
		js.addJob(1);	//20
		
		
		
		js.getJob(0).requires(js.getJob(3)); //
		js.getJob(1).requires(js.getJob(4)); //
		js.getJob(5).requires(js.getJob(1)); //
		js.getJob(2).requires(js.getJob(9)); //
		js.getJob(4).requires(js.getJob(3)); //
		js.getJob(9).requires(js.getJob(4)); //
		js.getJob(6).requires(js.getJob(5)); //
		js.getJob(8).requires(js.getJob(3)); //
		js.getJob(7).requires(js.getJob(3)); //
		js.getJob(5).requires(js.getJob(8)); //
		js.getJob(7).requires(js.getJob(10)); //
		js.getJob(11).requires(js.getJob(10)); //
		//js.getJob(0).requires(js.getJob(3)); //?????????????????  This line seems to be exxtra
		js.getJob(9).requires(js.getJob(8)); //
		js.getJob(12).requires(js.getJob(8)); //
		js.getJob(13).requires(js.getJob(11)); //
		js.getJob(14).requires(js.getJob(11)); //
		js.getJob(8).requires(js.getJob(14)); //
		js.getJob(15).requires(js.getJob(14)); //
		js.getJob(19).requires(js.getJob(13)); //
		js.getJob(17).requires(js.getJob(13)); //
		js.getJob(17).requires(js.getJob(16)); //
		js.getJob(17).requires(js.getJob(19)); //
		js.getJob(20).requires(js.getJob(17)); //
		js.getJob(15).requires(js.getJob(19)); //
		js.getJob(18).requires(js.getJob(20)); //
		
		assert(js.minCompletionTime() == 50);
		assert(js.getJob(0).getStartTime() == 12);
		assert(js.getJob(1).getStartTime() == 19);
		assert(js.getJob(2).getStartTime() == 24);
		assert(js.getJob(3).getStartTime() == 0);
		assert(js.getJob(4).getStartTime() == 12);
		assert(js.getJob(5).getStartTime() == 22);
		assert(js.getJob(6).getStartTime() == 26);
		assert(js.getJob(7).getStartTime() == 12);
		assert(js.getJob(8).getStartTime() == 19);
		assert(js.getJob(9).getStartTime() == 21);
		assert(js.getJob(10).getStartTime() == 0);//
		assert(js.getJob(11).getStartTime() == 8);
		assert(js.getJob(12).getStartTime() == 21);
		assert(js.getJob(13).getStartTime() == 14);
		assert(js.getJob(14).getStartTime() == 14);
		assert(js.getJob(15).getStartTime() == 37);//
		assert(js.getJob(16).getStartTime() == 0);
		assert(js.getJob(17).getStartTime() == 37);
		assert(js.getJob(18).getStartTime() == 47);
		assert(js.getJob(19).getStartTime() == 24);
		assert(js.getJob(20).getStartTime() == 46);
	}
	
	@Test
	public void testLargerJobNumberOfJobsWithCycle()
	{
		JobSchedule js = new JobSchedule();
		js.addJob(15);  //0
		js.addJob(3);  //1
		js.addJob(1);  //2
		js.addJob(12); //3
		js.addJob(7); //4
		js.addJob(4); //5
		js.addJob(5); //6
		js.addJob(11); //7
		js.addJob(2); //8
		js.addJob(3); //9
		js.addJob(8);  //10
		js.addJob(6);	//11
		js.addJob(12);  //12
		js.addJob(10);  //13
		js.addJob(5);  //14
		js.addJob(1);	//15
		js.addJob(14);  //16
		js.addJob(9);	//17
		js.addJob(3);	//18
		js.addJob(13);	//19
		js.addJob(1);	//20
		
		js.getJob(0).requires(js.getJob(3));
		js.getJob(1).requires(js.getJob(4));
		js.getJob(5).requires(js.getJob(1));
		js.getJob(2).requires(js.getJob(9));
		js.getJob(4).requires(js.getJob(3));
		js.getJob(9).requires(js.getJob(4));
		js.getJob(6).requires(js.getJob(5));
		js.getJob(8).requires(js.getJob(3));
		js.getJob(7).requires(js.getJob(3));
		js.getJob(5).requires(js.getJob(8));
		js.getJob(7).requires(js.getJob(10));
		js.getJob(11).requires(js.getJob(10));
		js.getJob(0).requires(js.getJob(3));
		js.getJob(9).requires(js.getJob(8));
		js.getJob(12).requires(js.getJob(8));
		js.getJob(13).requires(js.getJob(11));
		js.getJob(14).requires(js.getJob(11));
		js.getJob(8).requires(js.getJob(14));
		js.getJob(15).requires(js.getJob(14));
		js.getJob(19).requires(js.getJob(13));
		js.getJob(17).requires(js.getJob(13));
		js.getJob(17).requires(js.getJob(16));
		js.getJob(17).requires(js.getJob(19));
		js.getJob(20).requires(js.getJob(17));
		js.getJob(15).requires(js.getJob(19));
		js.getJob(18).requires(js.getJob(20));
		
		//CYCLE EDGE
		js.getJob(4).requires(js.getJob(6));
		
		assert(js.minCompletionTime() == -1);
		assert(js.getJob(0).getStartTime() == 12);
		assert(js.getJob(1).getStartTime() == -1);
		assert(js.getJob(2).getStartTime() == -1);
		assert(js.getJob(3).getStartTime() == 0);
		assert(js.getJob(4).getStartTime() == -1);
		assert(js.getJob(5).getStartTime() == -1);
		assert(js.getJob(6).getStartTime() == -1);
		assert(js.getJob(7).getStartTime() == 12);
		assert(js.getJob(8).getStartTime() == 19);
		assert(js.getJob(9).getStartTime() == -1);
		assert(js.getJob(10).getStartTime() == 0);
		assert(js.getJob(11).getStartTime() == 8);
		assert(js.getJob(12).getStartTime() == 21);
		assert(js.getJob(13).getStartTime() == 14);
		assert(js.getJob(14).getStartTime() == 14);
		assert(js.getJob(15).getStartTime() == 37);
		assert(js.getJob(16).getStartTime() == 0);
		assert(js.getJob(17).getStartTime() == 37);
		assert(js.getJob(18).getStartTime() == 47);
		assert(js.getJob(19).getStartTime() == 24);
		assert(js.getJob(20).getStartTime() == 46);
	}
	
	@Test
	public void testLargerJobNumberOfJobsWithMoreCycle()
	{
		JobSchedule js = new JobSchedule();
		js.addJob(7);  //0
		js.addJob(3);  //1
		js.addJob(1);  //2
		js.addJob(12); //3
		js.addJob(7); //4
		js.addJob(4); //5
		js.addJob(5); //6
		js.addJob(11); //7
		js.addJob(2); //8
		js.addJob(3); //9
		js.addJob(8);  //10
		js.addJob(6);	//11
		js.addJob(12);  //12
		js.addJob(10);  //13
		js.addJob(5);  //14
		js.addJob(1);	//15
		js.addJob(14);  //16
		js.addJob(9);	//17
		js.addJob(3);	//18
		js.addJob(13);	//19
		js.addJob(1);	//20
		
		js.getJob(0).requires(js.getJob(3));
		js.getJob(1).requires(js.getJob(4));
		js.getJob(5).requires(js.getJob(1));
		js.getJob(2).requires(js.getJob(9));
		js.getJob(4).requires(js.getJob(3));
		js.getJob(9).requires(js.getJob(4));
		js.getJob(6).requires(js.getJob(5));
		js.getJob(8).requires(js.getJob(3));
		js.getJob(7).requires(js.getJob(3));
		js.getJob(5).requires(js.getJob(8));
		js.getJob(7).requires(js.getJob(10));
		js.getJob(11).requires(js.getJob(10));
		js.getJob(0).requires(js.getJob(3));
		js.getJob(9).requires(js.getJob(8));
		js.getJob(12).requires(js.getJob(8));
		js.getJob(13).requires(js.getJob(11));
		js.getJob(14).requires(js.getJob(11));
		js.getJob(8).requires(js.getJob(14));
		js.getJob(15).requires(js.getJob(14));
		js.getJob(19).requires(js.getJob(13));
		js.getJob(17).requires(js.getJob(13));
		js.getJob(17).requires(js.getJob(16));
		js.getJob(17).requires(js.getJob(19));
		js.getJob(20).requires(js.getJob(17));
		js.getJob(15).requires(js.getJob(19));
		js.getJob(18).requires(js.getJob(20));
		
		//CYCLE EDGE
		
		js.getJob(3).requires(js.getJob(6));
		js.getJob(16).requires(js.getJob(15));
		
		assert(js.minCompletionTime() == -1);
		assert(js.getJob(0).getStartTime() == -1);
		assert(js.getJob(1).getStartTime() == -1);
		assert(js.getJob(2).getStartTime() == -1);
		assert(js.getJob(3).getStartTime() == -1);
		assert(js.getJob(4).getStartTime() == -1);
		assert(js.getJob(5).getStartTime() == -1);
		assert(js.getJob(6).getStartTime() == -1);
		assert(js.getJob(7).getStartTime() == -1);
		assert(js.getJob(8).getStartTime() == -1);
		assert(js.getJob(9).getStartTime() == -1);
		assert(js.getJob(10).getStartTime() == 0);
		assert(js.getJob(11).getStartTime() == 8);
		assert(js.getJob(12).getStartTime() == -1);
		assert(js.getJob(13).getStartTime() == 14);//
		assert(js.getJob(14).getStartTime() == 14);
		assert(js.getJob(15).getStartTime() == 37);
		assert(js.getJob(16).getStartTime() == 38);
		assert(js.getJob(17).getStartTime() == 52);
		assert(js.getJob(18).getStartTime() == 62);
		assert(js.getJob(19).getStartTime() == 24);
		assert(js.getJob(20).getStartTime() == 61);
	}
	
	@Test
	public void testProfessorTaylorsTest()
	{
		JobSchedule schedule = new JobSchedule();
		schedule.addJob(8); //adds job 0 with time 8
		JobSchedule.Job j1 = schedule.addJob(3); //adds job 1 with time 3
		schedule.addJob(5); //adds job 2 with time 5
		assert(schedule.minCompletionTime()== 8); //should return 8, since job 0 takes time 8 to complete.
		/* Note it is not the min completion time of any job, but the earliest the entire set can complete. */
		schedule.getJob(0).requires(schedule.getJob(2)); //job 2 must precede job 0
		assert(schedule.minCompletionTime()== 13); //should return 13 (job 0 cannot start until time 5)
		schedule.getJob(0).requires(j1); //job 1 must precede job 0
		assert(schedule.minCompletionTime()==13); //should return 13
		assert(schedule.getJob(0).getStartTime() == 5); //should return 5
		assert(j1.getStartTime() == 0); //should return 0
		assert(schedule.getJob(2).getStartTime() == 0); //should return 0
		j1.requires(schedule.getJob(2)); //job 2 must precede job 1
		assert(schedule.minCompletionTime() == 16); //should return 16
		assert(schedule.getJob(0).getStartTime() == 8); //should return 8
		assert(schedule.getJob(1).getStartTime() == 5); //should return 5
		assert(schedule.getJob(2).getStartTime() == 0); //should return 0
		schedule.getJob(1).requires(schedule.getJob(0)); //job 0 must precede job 1 (creates loop)
		assert(schedule.minCompletionTime() == -1); //should return -1
		assert(schedule.getJob(0).getStartTime() == -1); //should return -1
		assert(schedule.getJob(1).getStartTime() == -1); //should return -1
		assert(schedule.getJob(2).getStartTime() == 0); //should return 0 (no loops in prerequisites)
	}
}
