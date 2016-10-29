A java program to implement banker’s algorithm to avoid deadlocks.

programs run in a multithreaded environment
	There is a banker thread that does the following 
		maintain two lists: threads and requests
		wait when there is no request or there is no safe request
		wake up and evaluate when it receives a new request or a user thread completes and hence returns all its resources.
		when evaluating a request, no new request can be made
		when evaluating request(s), it only considers currently running thread(s) 
		There are a number of user threads each of which will
			randomly create a need vector, meaning how much resource it will need throughout its life cycle,
			randomly create and submit a request vector to the banker every 0 to 1 second if it has not got all it needed,
			ask no more than it claimed it would need,
			wait until the request is granted,
			signal the banker when it is about to complete after getting all it needed. 

			tested using m=5 and n=5, where m is the number resource types and n is the number of threads.
			Suppose the banker initially has 10 instances for each type of resource. 
			The user threads shall not claim to request more than what the banker has initially.