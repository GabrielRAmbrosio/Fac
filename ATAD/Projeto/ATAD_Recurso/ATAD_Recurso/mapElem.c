#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "mapElem.h"

void mapKeyPrint(MapKey key) {
	printf("%s\n", key);
}

void mapValuePrint(MapValue value) {
	printCache(&value);
}

/* funcao de comparacao de chaves */
int mapKeyEquals(MapKey key1, MapKey key2) {
	//no caso de MapKey == int. Alterar de acordo
	//com o tipo efetivo
	return (strcmp(key1, key2));
}