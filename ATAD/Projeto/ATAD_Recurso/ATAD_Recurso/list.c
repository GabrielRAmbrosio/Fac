/* PROJETO ATAD 2018
* Identificacao dos Alunos:
*
*      Numero: 160221013 | Nome: Gabriel Rodrigues Ambrósio
*
*/

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

/* Função auxiliar que devolve o endereço do nó que ocupa
 um determinado rank na instancia.

 Assume-se que 'list' e 'rank' estão validados!!
 */
PtNode nodeAtRank(PtList list, int rank) {
	
	PtNode atual;
	if (rank < (list->size / 2)) { //metade 'inferior'
		atual = list->header->next;
		for (int i = 0; i<rank; i++) {
			atual = atual->next;
		}
		return atual;
	}
	else { //metade 'superior'
		atual = list->trailer;
		int count = list->size - rank;
		for (int i = 0; i<count; i++) {
			atual = atual->prev;
		}
		return atual;
	}
}

/*
Cria uma nova instância da List.
Recebe:
initialCapacity - capacidade inicial (se aplicável)
Retorno:
referência da instância ou;
NULL no caso de inexistência de memória.
*/
PtList listCreate() {

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

/*
Destroi uma instância, libertando a memória associada.
Argumentos:
ptList - endereço da referência da instância;
Retorno:
LIST_NULL se a referência recebida for NULL, ou;
LIST_OK em caso de sucesso;
*/
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

/*
Adiciona um elemento numa dada instância.
Argumentos:
list - referência da instância;
rank - o rank/índice para a operação (0 >= r <= size);
elem - elemento a emlistar;
Retorno:
LIST_NULL se a referência recebida for NULL, ou;
LIST_FULL caso a lista esteja cheia, ou;
LIST_NO_MEMORY caso nao haja memória para guardar o elemento, ou;
LIST_INVALID_RANK caso o rank seja inválido para a operação, ou;
LIST_OK em caso de sucesso;
*/
int listAdd(PtList list, int rank, ListElem elem) {
	if (list == NULL) return LIST_NULL;
	if (rank < 0 || rank > list->size) return LIST_INVALID_RANK;

	//Descobrir que NO ocupa o RANK e inserir o NOVO no ants de NO.
	PtNode nodeAt = nodeAtRank(list, rank);

	for (int i = 0; i < list->size; i++) {
		Cache aux;
		listGet(list, i, &aux);
		if (equalCache(aux, elem) == 1) {
			return -1;
		}
	}

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

	//Descobrir que NO ocupa o RANK e inserir o NOVO no ants de NO.
	PtNode nodeAt = nodeAtRank(list, rank);

	*ptElem = nodeAt->elem;

	//Remover NODEAT da estrutura da lista ligada

	nodeAt->prev->next = nodeAt->next;
	nodeAt->next->prev = nodeAt->prev;

	//Libertar memória
	free(nodeAt);

	list->size--;

	return LIST_OK;
}
int listGet(PtList list, int rank, ListElem *ptElem) {
	if (list == NULL) return LIST_NULL;
	if (rank < 0 || rank >= list->size) return LIST_INVALID_RANK;

	//Descobrir que NO ocupa o RANK e inserir o NOVO no ants de NO.
	PtNode nodeAt = nodeAtRank(list, rank);

	*ptElem = nodeAt->elem;

	return LIST_OK;
}

int listSet(PtList list, int rank, ListElem elem, ListElem *ptOldElem) {
	if (list == NULL) return LIST_NULL;
	if (rank < 0 || rank >= list->size) return LIST_INVALID_RANK;

	//Descobrir que NO ocupa o RANK e inserir o NOVO no ants de NO.
	PtNode nodeAt = nodeAtRank(list, rank);

	*ptOldElem = nodeAt->elem;
	nodeAt->elem = elem;

	return LIST_OK;
}

/*
Quantos elementos estão armazenados numa instância.
Argumentos:
list - referência da instância;
ptSize - número de elementos (retorno por referência);
Retorno:
LIST_NULL se a referência recebida for NULL, ou;
LIST_OK em caso de sucesso;
*/
int listSize(PtList list, int *ptSize) {
	if (list == NULL) return LIST_NULL;

	*ptSize = list->size;

	return LIST_OK;
}

/*
Verifica se a instância está vazia (não contém elementos)
Argumentos:
list - referência da instância;
Retorno:
1 caso esteja vazia ou a referência seja NULL, ou;
0 caso não esteja vazia;
*/
int listIsEmpty(PtList list) {
	if (list == NULL ) return 1;
	if (list->size == 0) return 1;
	return 0;
}

/*
Limpa uma instância (remove todos os elementos)
Argumentos:
list - referência da instância;
Retorno:
LIST_NULL se a referência recebida for NULL, ou;
LIST_OK em caso de sucesso;
*/
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

/*
Mostra informação sobre uma instância
Argumentos:
list - referência da instância;
*/
void listPrint(PtList list) {

	if (list == NULL)
		printf("LIST NULL \n");
	else if ( /*listIsEmpty(list)*/ list->size == 0)
		printf("LIST EMPTY \n");
	else {
		PtNode atual = list->header->next;
		for (int rank = 0; rank < list->size; rank++) {
			printf("Rank %d : ", rank);
			listElemPrint(atual->elem);
			atual = atual->next;
		}
		
	}
}

