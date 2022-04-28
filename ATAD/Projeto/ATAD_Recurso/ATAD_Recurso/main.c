/* PROJETO ATAD 2018
* Identificacao dos Alunos:
*
*      Numero: 160221013 | Nome: Gabriel Rodrigues Ambrósio
*
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "cache.h"
#include "list.h"
#include "map.h"
#include "math.h"

int equalsStringIgnoreCase(char str1[], char str2[]);
void printCommandsMenu();
void load(PtList list, PtMap map);
void clear(PtList list, PtMap map);
void foundr(PtList list);
void center(PtList list);
void sort(PtList list);
void dates(PtList list);
void sizes(PtList list);
void m81p(PtList list);
void search(PtMap map);
char** split(char *str, int nFields, const char *delim);
void latitudeLongitudeMean(PtList list, double *latitude, double *longitude);
void dpMean(PtList list, double latitude, double longitude, double *latitudeDp, double *longitudeDp);
void swap(PtList list, int i, int f);
void sortOwner(PtList orderedList);
void sortAltitude(PtList orderedList);
void sortDistancia(PtList orderedList, double latitude, double longitude);
int checkSize(char **sizes, char *size, int arraySize);

int main(int argc, char** argv) {

	/* declaracao de variaveis */

	PtList list = listCreate();
	PtMap map = mapCreate();
	
	/* interpretador de comandos */
	char command[21];
	int quit = 0;
	while (!quit) {

		printCommandsMenu();
		fgets(command, sizeof(command), stdin);
		command[strlen(command) - 1] = '\0';


		if (equalsStringIgnoreCase(command, "QUIT")) {
			quit = 1; /* vai provocar a saída do interpretador */
		}
		else if (equalsStringIgnoreCase(command, "LOAD")) {
			load(list, map);
		}
		else if (equalsStringIgnoreCase(command, "CLEAR")) {
			clear(list, map);
		}
		else if (equalsStringIgnoreCase(command, "FOUNDR")) {
			foundr(list);
		}
		else if (equalsStringIgnoreCase(command, "CENTER")) {
			center(list);
		}
		else if (equalsStringIgnoreCase(command, "SORT")) {
			sort(list);
		}
		else if (equalsStringIgnoreCase(command, "DATES")) {
			dates(list);
		}
		else if (equalsStringIgnoreCase(command, "SIZES")) {
			sizes(list);
		}
		else if (equalsStringIgnoreCase(command, "M81P")) {
			m81p(list);
		}
		else if (equalsStringIgnoreCase(command, "SEARCH")) {
			search(map);
		} else {
			printf("Comando não encontrado.\n");
		}
	}
	
	/* libertar memória e apresentar mensagem de saída. */

	listDestroy(&list);
	//mapDestroy(&map); //erro 

	return (EXIT_SUCCESS);
}

int equalsStringIgnoreCase(char str1[], char str2[]) {
	/* Apenas faz uma comparacao utilizando o strcmp.
	* Necessita de modificacao para obter uma comparacao
	* 'case insensitive' */
	return (_stricmp(str1, str2) == 0);
}

void printCommandsMenu() {
	printf("\n===================================================================================");
	printf("\n                               PROJECT: GeoCaching                                 ");
	printf("\n===================================================================================");
	printf("\nCOMMANDS AVAILABLE");
	printf("\nLOAD, CLEAR, FOUNDR, CENTER, SORT, DATES, SIZES, M81P AND SEARCH");
	printf("\nE. Exit (QUIT)\n\n");
	printf("COMMAND> ");
}

//OUTROS

char** split(char *str, int nFields, const char *delim) {
	char **tokens = malloc(sizeof(char*) * nFields);

	int index = 0;
	char *next_token = NULL;

	char *token = strtok_s(str, delim, &next_token);
	while (token) {
		tokens[index++] = token;
		token = strtok_s(NULL, delim, &next_token);
	}
	return	tokens;
}

void latitudeLongitudeMean(PtList list, double *latitude, double *longitude) {
	int size;
	listSize(list, &size);

	double latitudeMean = 0, longitudeMean = 0;

	for (int i = 0; i < size; i++) {
		Cache cache;
		listGet(list, i, &cache);
		latitudeMean += cache.latitude;
		longitudeMean += cache.longitude;
	}

	*latitude = latitudeMean / size;
	*longitude = longitudeMean / size;
}

void dpMean(PtList list, double latitude, double longitude, double *latitudeDp, double *longitudeDp) {
	int size;
	listSize(list, &size);

	double latitudeDP = 0, longitudeDP = 0;

	for (int i = 0; i < size; i++) {
		Cache cache;
		listGet(list, i, &cache);
		latitudeDP += pow((cache.latitude - latitude), 2);
		longitudeDP += pow((cache.longitude - longitude), 2);
	}
	*latitudeDp = sqrt((latitudeDP / size));
	*longitudeDp = sqrt((longitudeDP / size));
}

void swap(PtList list, int i, int f) {//trocar posições de duas caches
	Cache cacheI, cacheF;
	listGet(list, i, &cacheI);
	listGet(list, f, &cacheF);

	Cache aux, trsh;

	listGet(list, i, &aux);

	listSet(list, i, cacheF, &trsh);
	listSet(list, f, aux, &trsh);

}

void sortOwner(PtList orderedList) {//ordenar por dono
	int size;
	listSize(orderedList, &size);
	for (int i = 0; i < size ; i++) {

		int indexMin = i;
		
		for (int j = i; j < size; j++) {

			Cache a, indexMinC;
			listGet(orderedList, j, &a);
			listGet(orderedList, indexMin, &indexMinC);

			if (strcmp(a.owner, indexMinC.owner) == 0) {//desempate por favoritos
				if (a.favourites <  indexMinC.favourites) {
					indexMin = j;
				}
			}else if (strcmp(a.owner, indexMinC.owner) < 0) {
				indexMin = j;
			}
		}
		swap(orderedList, i, indexMin);
	}
}

void sortAltitude(PtList orderedList) {//ordenar por altitude
	int size;
	listSize(orderedList, &size);
	for (int i = 0; i < size; i++) {

		int indexMin = i;

		for (int j = i; j < size; j++) {
			Cache a, indexMinC;
			listGet(orderedList, j, &a);
			listGet(orderedList, indexMin, &indexMinC);

			if (a.altitude > indexMinC.altitude) {
				indexMin = j;
			}
		}

		swap(orderedList, i, indexMin);
	}
}

void sortDistancia(PtList orderedList, double latitude, double longitude) {//ordenar por distancia enclidiana da media de lat e long
	int size;
	listSize(orderedList, &size);
	for (int i = 0; i < size; i++) {

		int indexMin = i;

		for (int j = i; j < size; j++) {
			Cache a, indexMinC;
			listGet(orderedList, j, &a);
			listGet(orderedList, indexMin, &indexMinC);

			double powXA = pow((a.latitude - latitude), 2);
			double powYA = pow((a.longitude - longitude), 2);

			double powXIndexMinC = pow((indexMinC.latitude - latitude), 2);
			double powYIndexMinC = pow((indexMinC.longitude - longitude), 2);

			double distanciaA = sqrt(powXA + powYA);//distância euclidiana
			double distanciaIndexMinC = sqrt(powXIndexMinC + powYIndexMinC);//distância euclidiana

			if (distanciaA > distanciaIndexMinC) {
				indexMin = j;
			}
		}

		swap(orderedList, i, indexMin);
	}
}

int checkSize(char **sizes, char *size, int arraySize) {//verifica se a string size esta no array sizes
	if (arraySize == 0) return -1;
	for (int i = 0; i < arraySize; i++) {
		if (strcmp(sizes[i], size) == 0) {
			return 1;
		}
	}
	return -1;
}

//COMANDOS

void load(PtList list, PtMap map) {

	if (listIsEmpty(list) != 1 || mapIsEmpty(map) != 1) {
		printf("DADOS EXISTENTES NAS TADS!");
		return;
	}

	char filename[20];
	printf("File Name: ");
	scanf_s("%s", &filename, 20);

	FILE *fp;
	int err = fopen_s(&fp, filename, "r");

	if (err != 0) {
		printf("FICHEIRO (%s) INACESSIVEL\n", filename);
		return;
	}

	printf("FICHEIRO %s ABERTO\n", filename);

	char nextLine[1024];
	int importedCache = 0;
	int countCache = 0;

	while (fgets(nextLine, sizeof(nextLine), fp)) {
		if (strlen(nextLine) < 1) 
			continue;
		
		char** tokens = split(nextLine, 15, ";");
		char** tokens2 = split(tokens[11], 3, "/");
		
		Date data = createDate(atoi(tokens2[2]), atoi(tokens2[1]), atoi(tokens2[0]));

		Cache cache = createCache(tokens[0], tokens[1], tokens[2], tokens[3], atof(tokens[4]), atof(tokens[5]), tokens[6], tokens[7],
			atof(tokens[8]), atof(tokens[9]), tokens[10], data, atoi(tokens[12]), atoi(tokens[13]), atoi(tokens[14]), atoi(tokens[15]));

		if (listAdd(list, importedCache, cache) != -1) {
			importedCache++;
		}
		mapPut(map, cache.code, cache);
		countCache++;
	}
	printf("\n\n%d CACHES TOTAIS\n", countCache);
	printf("\n\n%d CACHES IMPORTADAS PARA A LISTA\n", importedCache);
	printf("\n\n%d CACHES REPETIDAS\n", (countCache - importedCache));

	importedCache = 0;
	countCache = 0;
	fclose(fp);
}

void clear(PtList list, PtMap map) {
	if (listIsEmpty(list) == 1 || mapIsEmpty(map) == 1) {
		printf("NAO EXISTEM CACHES PARA LIMPAR!");
		return;
	}
	listClear(list);
	mapClear(map);
	printf("CACHES IMPORTADAS LIMPAS");
}

void foundr(PtList list) {
	if (listIsEmpty(list) == 1) {
		printf("LIST VAZIA!");
		return;
	}

	int size;
	listSize(list, &size);
	int totalFounds = 0;

	for (int i = 0; i < size; i++){
		Cache cache;
		listGet(list, i, &cache);
		totalFounds += cache.founds;
	}

	for (int i = 0; i < size; i++) {
		Cache cache;
		listGet(list, i, &cache);

		printCache(&cache);
		double percentage = (cache.founds * 100.0) / totalFounds;
		printf("ENCONTRADA %d VEZES\nPERCENTAGEM: %.2f %%\n", cache.founds, percentage);
	}
}

void center(PtList list) {
	if (listIsEmpty(list) == 1) {
		printf("LISTA VAZIA!");
		return;
	}

	int size;
	listSize(list, &size);

	double latitude = 0, longitude = 0, latitudeDP = 0, longitudeDP = 0;

	latitudeLongitudeMean(list, &latitude, &longitude);

	dpMean(list, latitude, longitude, &latitudeDP, &longitudeDP);

	printf("Media/Desvio Latitude: %f / %.2f\nMedia/Desvio Longitude: %f / %.2f", latitude, latitudeDP, longitude, longitudeDP);
}

void sort(PtList list) {
	if (listIsEmpty(list) == 1) {
		printf("LISTA VAZIA!");
		return;
	}

	int order = -1;
	PtList ordered = listCreate();

	int size;
	listSize(list, &size);
	
	for (int i = 0; i < size; i ++) {
		Cache c;
		listGet(list, i, &c);
		listAdd(ordered, i, c);
	}

	while (order != 1 && order != 2 && order != 3) {
		printf("Ordem:\n1 - Dono\n2 - Altitude\n3 - Distancia\n");
		printf("Option> ");
		scanf_s("%d", &order);
	}
	if (order == 1) {
		sortOwner(ordered);

		listPrint(ordered);
	}
	else if (order == 2) {
		sortAltitude(ordered);

		int ordSize;
		listSize(ordered, &ordSize);

		for (int i = ordSize; i >= 0; i--) {
			Cache c;
			listGet(ordered, i, &c);

			if (c.altitude < 0) {
				listRemove(ordered, i, &c);
			}
		}

		listPrint(ordered);
	}
	else if(order == 3){
		double latitudeMean = 0, longitudeMean = 0;

		latitudeLongitudeMean(ordered, &latitudeMean, &longitudeMean);
		sortDistancia(ordered, latitudeMean, longitudeMean);

		listPrint(ordered);
	}
	else {
		printf("Invalid Input...");
		return;
	}

	listDestroy(&ordered);

}

void dates(PtList list) {
	if (listIsEmpty(list) == 1) {
		printf("LISTA VAZIA!");
		return;
	}

	int size;
	listSize(list, &size);

	Cache oldest, newest;
	listGet(list, 0, &oldest);
	listGet(list, 0, &newest);

	for (int i = 0; i < size; i++) {
		Cache aux;
		listGet(list, i, &aux);
		if (aux.hidden_date.year > newest.hidden_date.year) {//verifica o ano
			newest = aux;
		}
		else if (aux.hidden_date.year == newest.hidden_date.year) {//se for o mesmo
			if (aux.hidden_date.month > newest.hidden_date.month) {//verifica o mes
				newest = aux;
			}
		}
		if (aux.hidden_date.year < oldest.hidden_date.year) {//verifica o ano
			oldest = aux;
		}
		else if (aux.hidden_date.year == oldest.hidden_date.year) {//se for o mesmo
			if (aux.hidden_date.year < oldest.hidden_date.year) {//verifica o mes
				oldest = aux;
			}
		}
	}
	printf("Cache Mais Antiga: %d/%d/%d\n", oldest.hidden_date.day, oldest.hidden_date.month, oldest.hidden_date.year);
	printf("Cache Mais Nova: %d/%d/%d\n", newest.hidden_date.day, newest.hidden_date.month, newest.hidden_date.year);
	printf("Diferenca Em Meses: %d\n", ((newest.hidden_date.year - oldest.hidden_date.year) * 12) + newest.hidden_date.month - oldest.hidden_date.month);
}

void sizes(PtList list) {
	if (listIsEmpty(list) == 1) {
		printf("LISTA VAZIA!");
		return;
	}

	int size;
	listSize(list, &size);

	char **sizes;
	sizes = (char**)malloc(sizeof(char*) *	1000);
	int sizesSize = 0;

	for (int i = 0; i < size; i++) {

		int number = 0;
		Cache cache;
		listGet(list, i, &cache);

		if (checkSize(sizes, cache.size, sizesSize) == 1) {//verifica se o size que vamos contar, ja foi contado, se foi passa a prox
			continue;
		}

		for (int f = 0; f < size; f++) {
			Cache aux;
			listGet(list, f, &aux);
			if (strcmp(cache.size, aux.size) == 0) {
				number++;
			}
		}

		int strLen = strlen(cache.size);
		sizes[sizesSize] = (char*)malloc(sizeof(char) * (strLen + 1));
		strcpy_s(sizes[sizesSize], (strLen + 1), cache.size);

		sizesSize++;

		printf("Number Of %s Size Caches: %d\n", cache.size, number);
	}

}

void m81p(PtList list) {
	if (listIsEmpty(list) == 1) {
		printf("LISTA VAZIA!");
		return;
	}

	double matrix81[9][9] = { 0 };

	int size;
	listSize(list, &size);

	for (int i = 0; i < size; i++) {
		Cache cache;
		listGet(list, i, &cache);
		if (cache.terrain == 1 && cache.difficulty == 1) {
			matrix81[0][0]++;
		}
		else if (cache.terrain == 1.5 && cache.difficulty == 1) {
			matrix81[0][1]++;
		}
		else if (cache.terrain == 2 && cache.difficulty == 1) {
			matrix81[0][2]++;
		}
		else if (cache.terrain == 2.5 && cache.difficulty == 1) {
			matrix81[0][3]++;
		}
		else if (cache.terrain == 3 && cache.difficulty == 1) {
			matrix81[0][4]++;
		}
		else if (cache.terrain == 3.5 && cache.difficulty == 1) {
			matrix81[0][5]++;
		}
		else if (cache.terrain == 4 && cache.difficulty == 1) {
			matrix81[0][6]++;
		}
		else if (cache.terrain == 4.5 && cache.difficulty == 1) {
			matrix81[0][7]++;
		}
		else if (cache.terrain == 5 && cache.difficulty == 1) {
			matrix81[0][8]++;
		}
		else if (cache.terrain == 1 && cache.difficulty == 1.5) {
			matrix81[1][0]++;
		}
		else if (cache.terrain == 1.5 && cache.difficulty == 1.5) {
			matrix81[1][1]++;
		}
		else if (cache.terrain == 2 && cache.difficulty == 1.5) {
			matrix81[1][2]++;
		}
		else if (cache.terrain == 2.5 && cache.difficulty == 1.5) {
			matrix81[1][3]++;
		}
		else if (cache.terrain == 3 && cache.difficulty == 1.5) {
			matrix81[1][4]++;
		}
		else if (cache.terrain == 3.5 && cache.difficulty == 1.5) {
			matrix81[1][5]++;
		}
		else if (cache.terrain == 4 && cache.difficulty == 1.5) {
			matrix81[1][6]++;
		}
		else if (cache.terrain == 4.5 && cache.difficulty == 1.5) {
			matrix81[1][7]++;
		}
		else if (cache.terrain == 5 && cache.difficulty == 1.5) {
			matrix81[1][8]++;
		}
		else if (cache.terrain == 1 && cache.difficulty == 2) {
			matrix81[2][0]++;
		}
		else if (cache.terrain == 1.5 && cache.difficulty == 2) {
			matrix81[2][1]++;
		}
		else if (cache.terrain == 2 && cache.difficulty == 2) {
			matrix81[2][2]++;
		}
		else if (cache.terrain == 2.5 && cache.difficulty == 2) {
			matrix81[2][3]++;
		}
		else if (cache.terrain == 3 && cache.difficulty == 2) {
			matrix81[2][4]++;
		}
		else if (cache.terrain == 3.5 && cache.difficulty == 2) {
			matrix81[2][5]++;
		}
		else if (cache.terrain == 4 && cache.difficulty == 2) {
			matrix81[2][6]++;
		}
		else if (cache.terrain == 4.5 && cache.difficulty == 2) {
			matrix81[2][7]++;
		}
		else if (cache.terrain == 5 && cache.difficulty == 2) {
			matrix81[2][8]++;
		}
		else if (cache.terrain == 1 && cache.difficulty == 2.5) {
			matrix81[3][0]++;
		}
		else if (cache.terrain == 1.5 && cache.difficulty == 2.5) {
			matrix81[3][1]++;
		}
		else if (cache.terrain == 2 && cache.difficulty == 2.5) {
			matrix81[3][2]++;
		}
		else if (cache.terrain == 2.5 && cache.difficulty == 2.5) {
			matrix81[3][3]++;
		}
		else if (cache.terrain == 3 && cache.difficulty == 2.5) {
			matrix81[3][4]++;
		}
		else if (cache.terrain == 3.5 && cache.difficulty == 2.5) {
			matrix81[3][5]++;
		}
		else if (cache.terrain == 4 && cache.difficulty == 2.5) {
			matrix81[3][6]++;
		}
		else if (cache.terrain == 4.5 && cache.difficulty == 2.5) {
			matrix81[3][7]++;
		}
		else if (cache.terrain == 5 && cache.difficulty == 2.5) {
			matrix81[3][8]++;
		}
		else if (cache.terrain == 1 && cache.difficulty == 3) {
			matrix81[4][0]++;
		}
		else if (cache.terrain == 1.5 && cache.difficulty == 3) {
			matrix81[4][1]++;
		}
		else if (cache.terrain == 2 && cache.difficulty == 3) {
			matrix81[4][2]++;
		}
		else if (cache.terrain == 2.5 && cache.difficulty == 3) {
			matrix81[4][3]++;
		}
		else if (cache.terrain == 3 && cache.difficulty == 3) {
			matrix81[4][4]++;
		}
		else if (cache.terrain == 3.5 && cache.difficulty == 3) {
			matrix81[4][5]++;
		}
		else if (cache.terrain == 4 && cache.difficulty == 3) {
			matrix81[4][6]++;
		}
		else if (cache.terrain == 4.5 && cache.difficulty == 3) {
			matrix81[4][7]++;
		}
		else if (cache.terrain == 5 && cache.difficulty == 3) {
			matrix81[4][8]++;
		}
		else if (cache.terrain == 1 && cache.difficulty == 3.5) {
			matrix81[5][0]++;
		}
		else if (cache.terrain == 1.5 && cache.difficulty == 3.5) {
			matrix81[5][1]++;
		}
		else if (cache.terrain == 2 && cache.difficulty == 3.5) {
			matrix81[5][2]++;
		}
		else if (cache.terrain == 2.5 && cache.difficulty == 3.5) {
			matrix81[5][3]++;
		}
		else if (cache.terrain == 3 && cache.difficulty == 3.5) {
			matrix81[5][4]++;
		}
		else if (cache.terrain == 3.5 && cache.difficulty == 3.5) {
			matrix81[5][5]++;
		}
		else if (cache.terrain == 4 && cache.difficulty == 3.5) {
			matrix81[5][6]++;
		}
		else if (cache.terrain == 4.5 && cache.difficulty == 3.5) {
			matrix81[5][7]++;
		}
		else if (cache.terrain == 5 && cache.difficulty == 3.5) {
			matrix81[5][8]++;
		}
		else if (cache.terrain == 1 && cache.difficulty == 4) {
			matrix81[6][0]++;
		}
		else if (cache.terrain == 1.5 && cache.difficulty == 4) {
			matrix81[6][1]++;
		}
		else if (cache.terrain == 2 && cache.difficulty == 4) {
			matrix81[6][2]++;
		}
		else if (cache.terrain == 2.5 && cache.difficulty == 4) {
			matrix81[6][3]++;
		}
		else if (cache.terrain == 3 && cache.difficulty == 4) {
			matrix81[6][4]++;
		}
		else if (cache.terrain == 3.5 && cache.difficulty == 4) {
			matrix81[6][5]++;
		}
		else if (cache.terrain == 4 && cache.difficulty == 4) {
			matrix81[6][6]++;
		}
		else if (cache.terrain == 4.5 && cache.difficulty == 4) {
			matrix81[6][7]++;
		}
		else if (cache.terrain == 5 && cache.difficulty == 4) {
			matrix81[6][8]++;
		}
		else if (cache.terrain == 1 && cache.difficulty == 4.5) {
			matrix81[7][0]++;
		}
		else if (cache.terrain == 1.5 && cache.difficulty == 4.5) {
			matrix81[7][1]++;
		}
		else if (cache.terrain == 2 && cache.difficulty == 4.5) {
			matrix81[7][2]++;
		}
		else if (cache.terrain == 2.5 && cache.difficulty == 4.5) {
			matrix81[7][3]++;
		}
		else if (cache.terrain == 3 && cache.difficulty == 4.5) {
			matrix81[7][4]++;
		}
		else if (cache.terrain == 3.5 && cache.difficulty == 4.5) {
			matrix81[7][5]++;
		}
		else if (cache.terrain == 4 && cache.difficulty == 4.5) {
			matrix81[7][6]++;
		}
		else if (cache.terrain == 4.5 && cache.difficulty == 4.5) {
			matrix81[7][7]++;
		}
		else if (cache.terrain == 5 && cache.difficulty == 4.5) {
			matrix81[7][8]++;
		}
		else if (cache.terrain == 1 && cache.difficulty == 5) {
			matrix81[8][0]++;
		}
		else if (cache.terrain == 1.5 && cache.difficulty == 5) {
			matrix81[8][1]++;
		}
		else if (cache.terrain == 2 && cache.difficulty == 5) {
			matrix81[8][2]++;
		}
		else if (cache.terrain == 2.5 && cache.difficulty == 5) {
			matrix81[8][3]++;
		}
		else if (cache.terrain == 3 && cache.difficulty == 5) {
			matrix81[8][4]++;
		}
		else if (cache.terrain == 3.5 && cache.difficulty == 5) {
			matrix81[8][5]++;
		}
		else if (cache.terrain == 4 && cache.difficulty == 5) {
			matrix81[8][6]++;
		}
		else if (cache.terrain == 4.5 && cache.difficulty == 5) {
			matrix81[8][7]++;
		}
		else if (cache.terrain == 5 && cache.difficulty == 5) {
			matrix81[8][8]++;
		}
	}

	printf("\t1.0\t1.5\t2.0\t2.5\t3.0\t3.5\t4.0\t4.5\t5.0\n");
	double diff = 1;
	for (int row = 0; row < 9; row++){
		printf("%.1f    ", diff);
		diff += 0.5;
		for (int column = 0; column < 9; column++){
			
			matrix81[row][column] = ((matrix81[row][column] * 100.0) / size);
			printf("%.2f%%\t", matrix81[row][column]);
		}
		printf("\n");
	}
}

void search(PtMap map) {
	if (mapIsEmpty(map) == 1) {
		printf("MAP VAZIO!");
		return;
	}

	char key[11];
	printf("Code Of Cache:\n");
	printf("Option>");
	scanf_s("%[^\n]", key, 11);
	printf("\n");
	Cache c;
	if (mapGet(map, key, &c) == 5) {
		printf("Cache nao encontrada!");
	}
	else {
		printCache(&c);
	}
}