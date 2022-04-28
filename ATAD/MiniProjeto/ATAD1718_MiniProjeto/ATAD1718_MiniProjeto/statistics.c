#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "statistics.h"


Statistics createStatistics(unsigned int twoPoints, unsigned int threePoints, unsigned int assists, unsigned int fouls, unsigned int blocks) {
	Statistics s;
	s.twoPoints = twoPoints;
	s.threePoints = threePoints;
	s.assists = assists;
	s.fouls = fouls;
	s.blocks = blocks;
	return s;
}

PlayerGameStatistics createPlayerGameStatistics(unsigned int idPlayer, unsigned int idGame, Statistics statistics) {
	PlayerGameStatistics pgs;
	pgs.idGame = idGame;
	pgs.idPlayer = idPlayer;
	pgs.statistics = statistics;
	return pgs;
}

void printStatistics(PtStatistics _this) {
	printf("2 Pt: %d\n3 Pt: %d\nAssists: %d\nFouls: %d\nBlocks: %d",
		_this->twoPoints, _this->threePoints,
		_this->assists,
		_this->fouls,
		_this->blocks);
}