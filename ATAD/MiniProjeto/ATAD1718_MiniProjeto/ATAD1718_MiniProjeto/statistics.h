typedef struct statistics {
	unsigned int twoPoints, threePoints, assists, fouls, blocks;
}Statistics;

typedef Statistics* PtStatistics;

typedef struct playerGameStatistics {
	unsigned int idPlayer, idGame;
	Statistics statistics;
}PlayerGameStatistics;

typedef PlayerGameStatistics* PtPlayerGameStatistics;

Statistics createStatistics(unsigned int twoPoints, unsigned int threePoints, unsigned int assists, unsigned int fouls, unsigned int blocks);
PlayerGameStatistics createPlayerGameStatistics(unsigned int idPlayer, unsigned int idGame, Statistics statistics);
void printStatistics(PtStatistics _this);