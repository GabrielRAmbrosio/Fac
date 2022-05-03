#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"

struct node;
typedef struct node* PtNode;

typedef struct node {
	ListElem elem;
	PtNode next;
	PtNode prev;
} Node;

typedef struct listImpl {
	PtNode header;
	PtNode trailer;
	unsigned int size;
} ListImpl;

PtNode nodeAtRank(PtList list, int rank) {
	
	PtNode atual;
	if (rank < (list->size / 2)) {
		atual = list->header->next;
		for (int i = 0; i<rank; i++) {
			atual = atual->next;
		}
		return atual;
	}
	else {
		atual = list->trailer;
		int count = list->size - rank;
		for (int i = 0; i<count; i++) {
			atual = atual->prev;
		}
		return atual;
	}
}

PtList listCreate(){

	PtList newList = (PtList)malloc(sizeof(ListImpl));
	if (newList == NULL) return NULL;
	
	newList->header = (PtNode)malloc(sizeof(Node));
	if (newList->header == NULL) {
		free(newList);
		return NULL;
	}
	newList->trailer = (PtNode)malloc(sizeof(Node));
	if (newList->trailer == NULL) {
		free(newList->header);
		free(newList);
		return NULL;
	}

	newList->header->prev = NULL;
	newList->header->next = newList->trailer;
	newList->trailer->prev = newList->header;
	newList->trailer->next = NULL;

	newList->size = 0;

	return newList;
}

int listDestroy(PtList *ptList) {

	PtList list = *ptList;
	if (list == NULL) return LIST_NULL;

	PtNode atual = list->header->next;
	PtNode aux = NULL;
	while (atual != list->trailer) {
		aux = atual;
		atual = atual->next;
		free(aux);
	}

	free(list->header);
	free(list->trailer);
	free(list);

	*ptList = NULL;

	return LIST_OK;
}

int listAdd(PtList list, int rank, ListElem elem) {
	if (list == NULL) return LIST_NULL;
	if (rank < 0 || rank > list->size) return LIST_INVALID_RANK;

	PtNode nodeAt = nodeAtRank(list, rank);
	

	PtNode newNode = (PtNode)malloc(sizeof(Node));
	if (newNode == NULL) {
		return LIST_NO_MEMORY;
	}

	newNode->elem = elem;
	newNode->next = nodeAt;
	newNode->prev = nodeAt->prev;

	nodeAt->prev->next = newNode;
	nodeAt->prev = newNode;

	list->size++;

	return LIST_OK;
}

int listRemove(PtList list, int rank, ListElem *ptElem) {
	if (list == NULL) return LIST_NULL;
	if (rank < 0 || rank >= list->size) return LIST_INVALID_RANK;

	PtNode nodeAt = nodeAtRank(list, rank);

	*ptElem = nodeAt->elem;

	nodeAt->prev->next = nodeAt->next;
	nodeAt->next->prev = nodeAt->prev;

	free(nodeAt);

	list->size--;

	return LIST_OK;
}

int listGet(PtList list, int rank, ListElem *ptElem) {
	if (list == NULL) return LIST_NULL;
	if (rank < 0 || rank >= list->size) return LIST_INVALID_RANK;

	PtNode nodeAt = nodeAtRank(list, rank);

	*ptElem = nodeAt->elem;

	return LIST_OK;
}

int listSet(PtList list, int rank, ListElem elem, ListElem *ptOldElem) {
	if (list == NULL) return LIST_NULL;
	if (rank < 0 || rank >= list->size) return LIST_INVALID_RANK;

	PtNode nodeAt = nodeAtRank(list, rank);

	*ptOldElem = nodeAt->elem;
	nodeAt->elem = elem;

	return LIST_OK;
}

int listSize(PtList list, int *ptSize) {
	if (list == NULL) return LIST_NULL;

	*ptSize = list->size;

	return LIST_OK;
}

int listIsEmpty(PtList list) {
	if (list == NULL ) return 1;
	if (list->size == 0) return 1;
	return 0;
}

int listClear(PtList list) {
	if (list == NULL) return LIST_NULL;

	PtNode atual = list->header->next;
	PtNode aux = NULL;
	while (atual != list->trailer) {
		aux = atual;
		atual = atual->next;
		free(aux);
	}

	//IMPORTANTE!!!
	list->header->next = list->trailer;
	list->trailer->prev = list->header;

	list->size = 0;

	return LIST_OK;
}

void listPrint(PtList list) {

	if (list == NULL)
		printf("LIST NULL \n");
	else if ( /*listIsEmpty(list)*/ list->size == 0)
		printf("LIST EMPTY \n");
	else {
		PtNode atual = list->header->next;
		for (int rank = 0; rank < list->size; rank++) {
			printf("Rank %d\n", rank);
			listElemPrint(atual->elem);
			atual = atual->next;
		}
	}
}