/* PROJETO ATAD 2018
* Identificacao dos Alunos:
*
*      Numero: 160221013 | Nome: Gabriel Rodrigues Ambr�sio
*
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "map.h"
/*
Definicao de um registo auxiliar (tuplo) para guardar
um mapeamento chave/valor
*/

#define MULTIPLIER 7
#define HASHTABLE_SIZE 1951	

typedef struct keyValue {
	MapKey key;
	MapValue value;
	int inUse; //para saber se o bucket que a key aponta estaa  ser usado (se sim avan�a para o prox) (1 - usado, 0 - livre)
} KeyValue;


typedef struct mapImpl {
	KeyValue *elements;
	unsigned int size;
	unsigned int capacity;
} MapImpl;


/*
Cria uma nova inst�ncia da Map.
Recebe:
initialCapacity - capacidade inicial (se aplic�vel)
Retorno:
refer�ncia da inst�ncia ou;
NULL no caso de inexist�ncia de mem�ria.
*/
PtMap mapCreate() {
	PtMap newMap = (PtMap)malloc(sizeof(MapImpl));
	if (newMap == NULL) return NULL;

	newMap->elements = (KeyValue*)calloc(HASHTABLE_SIZE, sizeof(KeyValue));

	if (newMap->elements == NULL) {
		free(newMap);
		return NULL;
	}
	
	newMap->size = 0;
	newMap->capacity = HASHTABLE_SIZE;

	return newMap;
}

/*
Destroi uma inst�ncia, libertando a mem�ria associada.
Argumentos:
ptMap - endere�o da refer�ncia da inst�ncia;
Retorno:
MAP_NULL se a refer�ncia recebida for NULL, ou;
MAP_OK em caso de sucesso;
*/
int mapDestroy(PtMap *ptMap) {
	PtMap map = (*ptMap);

	if (map == NULL) return MAP_NULL;

	free(map->elements);
	free(map);

	*ptMap = NULL;

	return MAP_OK;
}

/*
Adiciona um mapeamento numa dada inst�ncia. Se a chave
j� existir, atualiza o valor mapeado.
Argumentos:
map - refer�ncia da inst�ncia;
key - chave do mapeamento;
value - valor mapeado;
Retorno:
MAP_NULL se a refer�ncia recebida for NULL, ou;
MAP_FULL caso o mapa esteja cheio, ou;
MAP_NO_MEMORY caso nao haja mem�ria para guardar o elemento, ou;
MAP_OK em caso de sucesso;
*/
int mapPut(PtMap map, MapKey key, MapValue value) {
	if (map == NULL) return MAP_NULL;
	if (map->size == map->capacity) return MAP_FULL;

	
	int bucket = hashFunction(map, key);

	while (map->elements[bucket].inUse == 1) {
		bucket++;
	}

	KeyValue entry;
	entry.inUse = 1;
	strcpy_s(entry.key, sizeof(entry.key), key);
	entry.value = value;

	map->elements[bucket] = entry;

	map->size++;

	return MAP_OK;
}

/*
Remove um mapeamento de uma dada inst�ncia.
Argumentos:
map - refer�ncia da inst�ncia;
key - chave do mapeamento;
ptValue - valor removido (retorno por refer�ncia);
Retorno:
MAP_NULL se a refer�ncia recebida for NULL, ou;
MAP_EMPTY caso o mapa esteja vazio, ou;
MAP_UNKNOWN_KEY caso a chave n�o exista no mapa, ou;
MAP_OK em caso de sucesso;
*/
int mapRemove(PtMap map, MapKey key, MapValue *ptValue) {
	if (map == NULL) return MAP_NULL;
	if (map->size == 0) return MAP_EMPTY;

	int index = hashFunction(map, key);

	for (unsigned int i = index; i < map->capacity; i++) {
		if (strcmp(map->elements[index].key, key) == 0) {
			*ptValue = map->elements[index].value;
			map->elements[index].inUse = 0;

			map->size--;
			return MAP_OK;
		}
		index++;
	}

	return MAP_UNKNOWN_KEY;
}

/*
Obtem um valor mapeado de uma dada inst�ncia.
Argumentos:
map - refer�ncia da inst�ncia;
key - chave do mapeamento;
ptValue - valor mapeado (retorno por refer�ncia);
Retorno:
MAP_NULL se a refer�ncia recebida for NULL, ou;
MAP_EMPTY caso o mapa esteja vazio, ou;
MAP_UNKNOWN_KEY caso a chave n�o exista no mapa, ou;
MAP_OK em caso de sucesso;
*/
int mapGet(PtMap map, MapKey key, MapValue *ptValue) {
	if (map == NULL) return MAP_NULL;
	if (map->size == 0) return MAP_EMPTY;

	int bucket = hashFunction(map, key);

	for (unsigned int i = bucket; i < map->capacity; i++) {
		if (map->elements[bucket].inUse == 1) {
			if (strcmp(map->elements[bucket].key, key) == 0) {
				*ptValue = map->elements[bucket].value;
				return MAP_OK;
			}
			bucket++;
		}
		else {
			return MAP_UNKNOWN_KEY;
		}
	}

	return MAP_UNKNOWN_KEY;
}

/*
Verifica se uma chave faz parte do mapa.
Argumentos:
map - refer�ncia da inst�ncia;
key - chave do mapeamento;
Retorno:
1 se existir a chave;
0 se n�o existir ou o mapa for NULL;
*/
int mapContains(PtMap map, MapKey key) {
	if (map == NULL) return 0;

	int bucket = hashFunction(map, key);
	for (unsigned int i = bucket; i < map->capacity; i++) {
		if (strcmp(map->elements[bucket].key, key) == 0) {//se for a key pretendida devolve logo
			return 1;
		}
		bucket++;//se nao avan�a bucket ate encontrar a key
	}

	return 0;
}

/*
Quantos elementos est�o armazenados numa inst�ncia.
Argumentos:
map - refer�ncia da inst�ncia;
ptSize - n�mero de elementos (retorno por refer�ncia);
Retorno:
MAP_NULL se a refer�ncia recebida for NULL, ou;
MAP_OK em caso de sucesso;
*/
int mapSize(PtMap map, int *ptSize) {
	if (map == NULL) return MAP_NULL;

	*ptSize = map->size;

	return MAP_OK;
}

/*
Verifica se a inst�ncia est� vazia (n�o cont�m elementos)
Argumentos:
map - refer�ncia da inst�ncia;
Retorno:
1 caso esteja vazia ou a refer�ncia seja NULL, ou;
0 caso n�o esteja vazia;
*/
int mapIsEmpty(PtMap map) {
	if (map == NULL) return 1;

	return (map->size == 0) ? 1 : 0;
}

/*
Limpa uma inst�ncia (remove todos os elementos)
Argumentos:
map - refer�ncia da inst�ncia;
Retorno:
MAP_NULL se a refer�ncia recebida for NULL, ou;
MAP_OK em caso de sucesso;
*/
int mapClear(PtMap map) {
	if (map == NULL) return MAP_NULL;

	for (unsigned int i = 0; i < map->capacity; i++) {
		Cache c;
		mapRemove(map, map->elements[i].key, &c);
	}
	return MAP_OK;
}

/*
Mostra informa��o sobre uma inst�ncia
Argumentos:
map - refer�ncia da inst�ncia;
*/
void mapPrint(PtMap map) {
	if (map == NULL) {
		printf("MAP NULL \n");
	}
	else if (mapIsEmpty(map)) {
		printf("MAP EMPTY \n");
	}
	else {
		printf("Map key/values: \n");

		for (unsigned int i = 0; i < map->capacity; i++) {
			KeyValue entry = map->elements[i];
			if(entry.inUse == 1) {
				printf("\tBucket: %s -> Key: ", map->elements[i].key);
				mapValuePrint(entry.value);
			}
			
		}

		printf("--------------- \n");
	}
}

int hashFunction(PtMap map, MapKey key) {

	int length = strlen(key);
	int A = 31415;
	int B = 27183;
	int total = 0;
	for (int i = 0; i < length; i++) {
		total = (total * A + key[i]) % map->capacity;
		A = A * B % (map->capacity - 1);
	}

	return total;
}