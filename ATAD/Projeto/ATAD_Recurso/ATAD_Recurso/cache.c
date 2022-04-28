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

Cache createCache(char *code, char *name, char *state, char *owner, double latitude, double longitude, char *kind, char *size, double difficulty, double terrain, char *status, Date date, int founds, int notFounds, int favourites, int altitude) {

	Cache newCache;

	strcpy_s(newCache.code, sizeof(newCache.code), code);
	strcpy_s(newCache.name, sizeof(newCache.name), name);
	strcpy_s(newCache.state, sizeof(newCache.state), state);
	strcpy_s(newCache.owner, sizeof(newCache.owner), owner);
	newCache.latitude = latitude;
	newCache.longitude = longitude;
	strcpy_s(newCache.kind, sizeof(newCache.kind), kind);
	strcpy_s(newCache.size, sizeof(newCache.size), size);
	newCache.difficulty = difficulty;
	newCache.terrain = terrain;
	strcpy_s(newCache.status, sizeof(newCache.status), status);
	newCache.hidden_date = date;
	newCache.founds = founds;
	newCache.not_founds = notFounds;
	newCache.favourites = favourites;
	newCache.altitude = altitude;

	return newCache;
}

Date createDate(unsigned int day, unsigned int month, unsigned int year) {
	Date newDate;

	newDate.day = day;
	newDate.month = month;
	newDate.year = year;

	return newDate;
}

void printCache(PtCache _this) {
	printf("%s\n%s\n%s\n%s\n%f\n%f\n%s\n%s\n%.2f\n%.2f\n%s\n%d/%d/%d\n%d\n%d\n%d\n%d\n", _this->code, _this->name, _this->state,
		_this->owner, _this->latitude, _this->longitude, _this->kind, _this->size, _this->difficulty,
		_this->terrain, _this->status, _this->hidden_date.day, _this->hidden_date.month, _this->hidden_date.year,
		_this->founds, _this->not_founds, _this->favourites, _this->altitude);
}

int equalCache(Cache c1, Cache c2) {//comparar duas caches
	if ((strcmp(c1.code, c2.code) == 0) && (strcmp(c1.name, c2.name) == 0) && (strcmp(c1.state, c2.state) == 0) && (strcmp(c1.owner, c2.owner) == 0) && (c1.latitude == c2.latitude) && (c1.longitude == c2.longitude) && (strcmp(c1.kind, c2.kind) == 0) && (strcmp(c1.size, c2.size) == 0) && (c1.difficulty == c2.difficulty) && (c1.terrain == c2.terrain) && (strcmp(c1.status, c2.status) == 0) && (c1.hidden_date.day == c2.hidden_date.day) && (c1.hidden_date.month == c2.hidden_date.month) && (c1.hidden_date.year == c2.hidden_date.year) && (c1.founds == c2.founds) && (c1.not_founds == c2.not_founds) && (c1.favourites == c2.favourites) && (c1.altitude == c2.altitude)) {
		return 1;
	}
	return -1;
}