#include "Buffer.h"
#include <iostream>

using namespace std;

buffer_item buffer[BUFFER_SIZE];

void initializeBuffer() {
	//initiallizing buffer
	for (int i = 0; i < BUFFER_SIZE; i++) {
		buffer[i] = NULL;
	}
}

int insert_item(buffer_item item) {
		/* insert item into buffer
		return 0 if successfull, otherwise
		return -1 indicating an error condition
		*/
	for (int i = 0; i < BUFFER_SIZE; i++) {
		if (buffer[i] == NULL) {
			buffer[i] = item;
			return 0;
			}
		}
	return -1;
}

int remove_item(buffer_item *item) {
		/*remove an object from buffer
		placing it in item
		return 0 if successfull, otherwise
		return -1 indicating an error condition
		*/
	for (int i = 0; i < BUFFER_SIZE; i++) {
		if (buffer[i] != NULL) {
			*item = buffer[i];
			buffer[i] = NULL;
			return 0;
		}
	}
	return -1;
}