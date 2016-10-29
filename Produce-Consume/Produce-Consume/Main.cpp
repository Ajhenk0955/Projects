#include <iostream>
#include "Buffer.h"
#include <Windows.h>
#include <stdio.h>
#include <ctime>

using namespace std;

void *producer(HANDLE *buffer[]) {

	buffer_item item;

	// make numbers more random
	srand(time(NULL)*GetCurrentThreadId());

	while (true) {

		//sleep half to one second
		Sleep(int(rand() % 1000 - 500) + 500);

		item = rand();

		//wait for availability empty and lock
		WaitForSingleObject(buffer[1], INFINITE);
		WaitForSingleObject(buffer[2], INFINITE);

		// error checking and item insertion

		if (insert_item(item)) {

			printf("Producer Error\n");
		}
		else {

			printf("%d\n", item);
		}
		// release full and lock
		ReleaseSemaphore(buffer[0], 1, NULL);
		ReleaseMutex(buffer[2]);

	}
}

void *consumer(HANDLE *buffer[]) {

	buffer_item item;

	while (true) {

		//MORE RANDOM =D
		Sleep(int(rand() % (1000 - 500) + 500));

		// wait for lock and full
		WaitForSingleObject(buffer[0], INFINITE);
		WaitForSingleObject(buffer[2], INFINITE);

		// error checking and item receiving
		if (remove_item(&item)) {

			printf("Consumer Error");
		}
		else {

			printf("\t%d\n", item);
		}
		// release empty and lock
		ReleaseSemaphore(buffer[1], 1, NULL);
		ReleaseMutex(buffer[2]);

	}
}

int main(int argc, char *argv[]) {

	printf("In\tOut\n");
	// make buffer void
	initializeBuffer();

	int numOfProducers = 5;
	int numOfConsumers = 5;

	HANDLE *producers = new HANDLE[numOfProducers];
	HANDLE *consumers = new HANDLE[numOfConsumers];

	DWORD ThreadID;
	int i;

	// create semaphores for empty, full, and the mutex
	HANDLE full = CreateSemaphore(
		NULL,
		0,
		BUFFER_SIZE,
		NULL);

	HANDLE empty = CreateSemaphore(
		NULL,
		BUFFER_SIZE,
		BUFFER_SIZE,
		NULL);

	HANDLE lock = CreateMutex(
		NULL,
		FALSE,
		NULL);

	// Error checking
	if (full == NULL)
	{
		printf("CreateSemaphore error: %d\n", GetLastError());
		return 1;
	}
	if (empty == NULL)
	{
		printf("CreateSemaphore error: %d\n", GetLastError());
		return 1;
	}
	if (lock == NULL)
	{
		printf("CreateMutex error: %d\n", GetLastError());
	}

	// Create a handle array to pass variables to threads
	HANDLE temp[3] = { full, empty, lock };

	// create producer threads

	for (i = 0; i < numOfProducers; i++)
	{
		producers[i] = CreateThread(
			NULL,       // default security attributes
			0,          // default stack size
			(LPTHREAD_START_ROUTINE)producer,
			&temp,       // no thread function arguments
			0,          // default creation flags
			&ThreadID); // receive thread identifier

		if (&producers[i] == NULL)
		{
			printf("CreateThread error: %d\n", GetLastError());
			return 1;
		}
	}

	// Create consumer threads

	for (i = 0; i < numOfConsumers; i++)
	{
		consumers[i] = CreateThread(
			NULL,       // default security attributes
			0,          // default stack size
			(LPTHREAD_START_ROUTINE)consumer,
			&temp,       // no thread function arguments
			0,          // default creation flags
			&ThreadID); // receive thread identifier

		if (consumers[i] == NULL)
		{
			printf("CreateThread error: %d\n", GetLastError());
			return 1;
		}
	}

	// Wait for threads
	WaitForMultipleObjects(numOfConsumers, consumers, TRUE, INFINITE);
	WaitForMultipleObjects(numOfProducers, producers, TRUE, INFINITE);


	// Closing handles and threads

	for (int i = 0; i < numOfConsumers; i++) {
		CloseHandle(consumers[i]);
	}
	for (int i = 0; i < numOfProducers; i++) {
		CloseHandle(producers[i]);
	}


	CloseHandle(full);
	CloseHandle(empty);
	CloseHandle(lock);

	return 0;
}