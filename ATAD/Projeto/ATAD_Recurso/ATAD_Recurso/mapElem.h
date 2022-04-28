#pragma once
#include "cache.h"

/* definicao do tipo da chave */
typedef char MapKey[11];
/* definicao do tipo do valor*/
typedef Cache MapValue;

void mapKeyPrint(MapKey key);
void mapValuePrint(MapValue value);

/* funcao de comparacao de chaves */
int mapKeyEquals(MapKey key1, MapKey key2);