#pragma once

typedef struct date {
	unsigned int day, month, year;
}Date;


typedef struct cache {
	char code[11];
	char name[101];
	char state[61];
	char owner[101];
	double latitude, longitude;
	char kind[12];
	char size[11];
	double difficulty, terrain;
	char status[11];
	Date hidden_date;
	int founds, not_founds, favourites, altitude;
}Cache;

typedef Cache *PtCache;

Cache createCache(char *code, char *name, char *state, char *owner, double latitude, double longitude, char *kind, char *size, double difficulty, double terrain, char *status, Date date, int founds, int notFounds, int favourites, int altitude);
Date createDate(unsigned int day, unsigned int month, unsigned int year);
void printCache(PtCache _this);
int equalCache(Cache c1, Cache c2);