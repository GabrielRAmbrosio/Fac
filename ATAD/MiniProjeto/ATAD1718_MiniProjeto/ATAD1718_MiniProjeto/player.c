#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "player.h"


Player createPlayer(unsigned int id, char name[], char team[], Date birthDate, char gender) {
	Player p;
	p.id = id;
	strcpy_s(p.name, sizeof(p.name),  name);
	strcpy_s(p.team, sizeof(p.team), team);
	p.birthDate = birthDate;
	p.gender = gender;
	return p;
}

Date createDate(unsigned int day, unsigned int month, unsigned int year) {
	Date d;
	d.day = day;
	d.month = month;
	d.year = year;
	return d;
}

void printPlayer(PtPlayer _this){
	printf("%d\t| %-20s\t| %-15s\t|  %d/%d/%d\t|    %c   |\n",
		_this->id, _this->name,
		_this->team,
		_this->birthDate.day,
		_this->birthDate.month,
		_this->birthDate.year,
		_this->gender);
}