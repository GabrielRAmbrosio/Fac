/* MINI-PROJETO 1 ATAD 2018
* Identificacao dos Alunos:
*
*      Numero: 160221013 | Nome: Gabriel Ambrósio
*      Numero: 160221039 | Nome: Hugo Ferreira
*	   Turma: Lab 6ª 16H30
*	   Docente: Prof. Aníbal Ponte 
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "player.h"
#include "statistics.h"


//OTHERS
int equalsStringIgnoreCase(char str1[], char str2[]);
void printCommandsMenu();
char** split(char *str, int nFields, const char *delim);
void swap(Player *x, Player *y);
void sortPlayersAZ(Player players[]);
void sortPlayersZA(Player players[]);
void printArray(Player players[]);
void countPlayers(Player players[], int *sub14M, int *sub16M, int *sub18M, int *senioresM, int *sub14F, int *sub16F, int *sub18F, int *senioresF);
void sumOfPointsOfGame(PlayerGameStatistics pgs[], int idGameToSum, int *sum);
void sumOfPlayersInAGame(PlayerGameStatistics pgs[], int idGameToSum, int *sum);
void sumOfBlocksInAGame(PlayerGameStatistics pgs[], int idGameToSum, int *sum);
int calculateMvpValue(PlayerGameStatistics pgs);
int calculateMPV(PlayerGameStatistics pgs[], int idGame);
int calculateMPVId(PlayerGameStatistics pgs[], int idGame);
void printPlayerById(int idPlayer, Player p[]);
double calculateAverageFoulsByPlayerByGame(PlayerGameStatistics pgs[], Player p);
int numberOfGamesOfPlayer(PlayerGameStatistics pgs[], Player p);
double calculateAverageFoulsByTeamByGame(Player players[], char team[], PlayerGameStatistics pgs[]);
int numberOfGames(PlayerGameStatistics pgs[]);
double calculateAverageFoulsByGameByPlayerById(PlayerGameStatistics pgs[], int id);
int numberOfPlayersByAgeAndGender(Player players[], int min, int max, char gender);
void listOfPlayersByAgeAndGender(Player players[], int min, int max, char gender, Player *player[], int *arrSize);
int mostPoints(PlayerGameStatistics pgs[], Player players[]);
int mostAssists(PlayerGameStatistics pgs[], Player players[]);
int mostBlocks(PlayerGameStatistics pgs[], Player players[]);
//JOGADORES
void loadP(Player players[]);
void showP(Player players[]);
void table(Player players[]);
void search(Player players[]);
//JOGO
void loadG(PlayerGameStatistics pgs[]);
void searchG(PlayerGameStatistics pgs[]);
void mvp(PlayerGameStatistics pgs[], Player p[]);
//JOGOS E JOGADORES
void mFoulP(PlayerGameStatistics pgs[], Player p[]);
void mFoulG(PlayerGameStatistics pgs[]);
void fairP(Player player[], PlayerGameStatistics pgs[]);
//INDICADOR AVANÇADO
void idealTeam(Player player[], PlayerGameStatistics pgs[]);

int main(int argc, char** argv) {
	
	/* declaracao de variaveis */
	Player players[300];//calloc??
	PlayerGameStatistics pgs[517];//calloc??

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
		else if (equalsStringIgnoreCase(command, "LOADG")) {
			loadG(pgs);
		}
		else if (equalsStringIgnoreCase(command, "LOADP")) {
			loadP(players);
		}
		else if (equalsStringIgnoreCase(command, "SHOWP")) {
			showP(players);
		}
		else if (equalsStringIgnoreCase(command, "TABLE")) {
			table(players);
		}
		else if (equalsStringIgnoreCase(command, "SEARCH")) {
			search(players);
		}
		else if (equalsStringIgnoreCase(command, "STATG")) {
			//?nao e pedido este metodo?
		}
		else if (equalsStringIgnoreCase(command, "SEARCHG")) {
			searchG(pgs);
		}
		else if (equalsStringIgnoreCase(command, "MVP")) {
			mvp(pgs, players);
		}
		else if (equalsStringIgnoreCase(command, "MFOULG")) {
			mFoulG(pgs);
		}
		else if (equalsStringIgnoreCase(command, "MFOULP")) {
			mFoulP(pgs, players);
		}
		else if (equalsStringIgnoreCase(command, "FAIRPLAY")) {
			fairP(players, pgs);
			printf("Nao funciona na totalidade");
		}
		else if (equalsStringIgnoreCase(command, "IDEALTEAM")) {
			idealTeam(players, pgs);
			printf("Nao funciona na totalidade");
		}
		else {
			printf("Comando não encontrado.\n");
		}
	}

	/* libertar memória e apresentar mensagem de saída. */

	return 0;
}

//OTHERS

int equalsStringIgnoreCase(char str1[], char str2[]) {
	
	return (strcmp(str1, str2) == 0);

	/*
	if (strlen(str1) != strlen(str2)) return 0;
    char primeira[strlen(str1)];<-----------------ERRO
    char segunda[strlen(str2)];<------------------ERRO
    strcpy(primeira, str1);
    strcpy(segunda, str2);
    for (int i = 0; i < strlen(primeira); i++) {
        primeira[i] = tolower(primeira[i]);
        segunda[i] = tolower(segunda[i]);
    }
    return ( strcmp(primeira, segunda) == 0);
	*/
	//return (strcasecmp(str1, str2) == 0);<------------------------ERRO
	//return (strcmpi(str1, str2) == 0);<---------------------------ERRO
	//return (strcmp(strlwr(str1), strlwr(str2)) == 0);<------------ERRO

	//nenhum destes 4 metodos funcionou...
}



void printCommandsMenu() {
	printf("\n===================================================================================");
	printf("\n                          MINI-PROJECT: Basket Scores                              ");
	printf("\n===================================================================================");
	printf("\nA. Info about players (LOADP, SHOWP, TABLE, SEARCH).");
	printf("\nB. Specific info about players' performance during the games (LOADG, SEARCHG, MVP).");
	printf("\nC. Aggregated info about games and players (MFOULP, MFOULG, FAIRPLAY).");
	printf("\nD. Advanced indicator (IDEALTEAM)");
	printf("\nE. Exit (QUIT)\n\n");
	printf("COMMAND> ");
}

char** split(char *str, int nFields, const char *delim) {//funcao split dada no lab de apoio
	char **tokens = malloc(sizeof(char*) * nFields);

	int index = 0;
	char *next_token = NULL;

	char *token = strtok_s(str, delim, &next_token);
	while(token) {
		puts(token);
		tokens[index++] = token;
		token = strtok_s(NULL, delim, &next_token);
	}
	return	tokens;
}

void swap(Player *x, Player *y) {//swaps two elements in array
	Player aux = *x;
	*x = *y;
	*y = aux;
}

void sortPlayersAZ(Player players[]) {//selectionS
	for (int i = 0; i < 300; i++) {
		Player minP = players[i];
		int minPindex = i;
		for (int j = i; j < 300; j++) {
			if (strcmp(players[j].name, minP.name) < 0) {
				minP = players[j];
				minPindex = j;
			}
		}
		swap(&players[i], &players[minPindex]);
	}
}

void sortPlayersZA(Player players[]) {//selectionS
	for (int i = 0; i < 300; i++) {	
		Player minP = players[i];
		int minPindex = i;
		for (int j = i; j < 300; j++) {
			if (strcmp(players[j].name, minP.name) > 0) {
				minP = players[j];
				minPindex = j;
			}
		}
		swap(&players[i], &players[minPindex]);
	}
}


void printArray(Player players[]) {//printa o array passado por argumento
	for (int i = 0; i < 300; i++){
		printPlayer(&players[i]);
	}
}

void countPlayers(Player players[], int *sub14M, int *sub16M, int *sub18M, int *senioresM, int *sub14F, int *sub16F, int *sub18F, int *senioresF) {
	for (int i = 0; i < 300; i++) {//conta os players pelas idades e divide-os por genero
		if (players[i].birthDate.year >= 2004) {
			if (players[i].gender == 'M') {
				(*sub14M)++;
			}
			else {
				(*sub14F)++;
			}
		}
		else if (players[i].birthDate.year >= 2002) {
			if (players[i].gender == 'M') {
				(*sub16M)++;
			}
			else {
				(*sub16F)++;
			}
		}
		else if (players[i].birthDate.year >= 2000) {
			if (players[i].gender == 'M') {
				(*sub18M)++;
			}
			else {
				(*sub18F)++;
			}
		}
		else {
			if (players[i].gender == 'M') {
				(*senioresM)++;
			}
			else {
				(*senioresF)++;
			}
		}
	}
}

void sumOfPointsOfGame(PlayerGameStatistics pgs[], int idGameToSum, int *sum) {//soma dos pontos de um determinado jogo
	for (int i = 0; i < 517; i++) {
		if (pgs[i].idGame == idGameToSum) {
			(*sum) += (pgs[i].statistics.twoPoints * 2);
			(*sum) += (pgs[i].statistics.threePoints * 3);
		}
	}
}
void sumOfPlayersInAGame(PlayerGameStatistics pgs[], int idGameToSum, int *sum) {//soma de jogadores num determinado jogo
	for (int i = 0; i < 517; i++) {
		if (pgs[i].idGame == idGameToSum) {
			(*sum)++;
		}
	}
}
void sumOfBlocksInAGame(PlayerGameStatistics pgs[], int idGameToSum, int *sum) {//soma de blocos num determinado jogo
	for (int i = 0; i < 517; i++) {
		if (pgs[i].idGame == idGameToSum) {
			(*sum) += pgs[i].statistics.blocks;
		}
	}
}

int calculateMvpValue(PlayerGameStatistics pgs) {//calcular o valor individual mvp de um jogador num jogo
	return (3 * pgs.statistics.threePoints) + (2 * pgs.statistics.twoPoints) + pgs.statistics.assists + (2 * pgs.statistics.blocks) - (3 * pgs.statistics.fouls);
}

int calculateMPV(PlayerGameStatistics pgs[], int idGame) {//calcula o mvp de um jogo e retorna o valor mvp

	int classification = 0;
	for (int i = 0; i < 517; i++) {
		if (pgs[i].idGame == idGame) {
			if (calculateMvpValue(pgs[i]) > classification) {
				classification = calculateMvpValue(pgs[i]);
			}
		}
	}
	return classification;
}

int calculateMPVId(PlayerGameStatistics pgs[], int idGame) {//calcula o mvp e retorna o seu id

	int classification = 0;
	int mvp = 0;
	for (int i = 0; i < 517; i++) {
		if (pgs[i].idGame == idGame) {
			if (calculateMvpValue(pgs[i]) > classification) {
				classification = calculateMvpValue(pgs[i]);
				mvp = pgs[i].idPlayer;
			}
		}
	}

	return mvp;
}

void printPlayerById(int idPlayer, Player p[]) {//printa jogador pelo id
	for (int i = 0; i < 300; i++) {
		if (p[i].id == idPlayer) {
			printPlayer(&p[i]);
		}
	}
}
 
double calculateAverageFoulsByPlayerByGame(PlayerGameStatistics pgs[], Player p) {//media de faltas de um jogador por jogo
	int sum = 0;
	int j = 0;
	for (int i = 0; i < 517; i++) {
		if (pgs[i].idPlayer == p.id) {
			sum += pgs[i].statistics.fouls;
			j++;
		}
	}
	return sum / (double)j;
}

int numberOfGamesOfPlayer(PlayerGameStatistics pgs[], Player p) {//numero de jogos de um jogador
	int j = 0;
	for (int i = 0; i < 517; i++) {
		if (pgs[i].idPlayer == p.id) {
			j++;
		}
	}
	return j;
}

double calculateAverageFoulsByTeamByGame(Player players[], char team[], PlayerGameStatistics pgs[]) {//media de faltas de uma equipa por jogo
	int sum = 0;

	int teamPlayers[100];
	int arrSize = 0;


	for (int i = 0; i < 300; i++) {//calcula o numero de jogadores numa equipa e coloca os seus id's no array
		if (strcmp(players[i].team, team) == 0) {
			teamPlayers[arrSize++] = players[i].id;
		}
	}

	for (int a = 0; a < arrSize; a++) {//soma o numero de faltas dadas por cada jogador da equipa
		for (int z = 0; z < 517; z++) {
			if (teamPlayers[a] == pgs[z].idPlayer) {
				sum += pgs[z].statistics.fouls;
			}
		}
	}

	int mostPlayed = 0;
	int max = 0;
	
	for (int i = 0; i < arrSize; i++) {//jogador que jogou mais vezes, para saber quantos jogos a equipa tem
		int aux = 0;
		int j = 0;
		for (int z = 0; z < 517; z++) {
			if (teamPlayers[i] == pgs[z].idPlayer) {
				aux++;
				j = i;
			}
		}
		if (aux > max) {
				max = aux;
				mostPlayed = pgs[j].idPlayer;
			}
	}

	int numOfGames = 0;
	for (int i = 0; i < 517; i++) {//numero de jogos de uma equipa
		if (pgs[i].idPlayer == teamPlayers[mostPlayed]) {
			numOfGames++;
		}
	}

	return (double)sum / numOfGames;

}

int numberOfGames(PlayerGameStatistics pgs[]) {//numero total de jogos
	int id[517];
	int arrSize = 0;
	int aux = 1;
	for (int i = 0; i < 517; i++) {
		for (int a = 0; a < arrSize; a++) {
			if (pgs[i].idGame == id[a]) {
				aux = 0;
			}
		}
		if (aux == 1) {
			id[arrSize++] = pgs[i].idGame;
		}
		aux = 1;
	}
	return arrSize;
}

double calculateAverageFoulsByGameByPlayerById(PlayerGameStatistics pgs[], int id) {//media de faltas por jogo por jogador
	int sum = 0;
	int j = 0;

	for (int i = 0; i < 517; i++) {
		if (pgs[i].idGame == id) {
			sum += pgs[i].statistics.fouls;
			j++;
		}
	}
	return (double)sum / j;
}

int numberOfPlayersByAgeAndGender(Player players[], int min, int max, char gender) {//numero de jogadores que estao entre um determinado escalao e genero
	int sum = 0;
	for (int i = 0; i < 300; i++) {
		if (players[i].birthDate.year >= min && players[i].birthDate.year <= max && players[i].gender == gender) {
			sum++;
		}
	}
	return sum;
}

void listOfPlayersByAgeAndGender(Player players[], int min, int max, char gender, Player *player[], int *arrSize) {//lista de jogadores que estao entre um determinado escalao e genero
	for (int i = 0; i < 300; i++) {
		if (players[i].birthDate.year >= min && players[i].birthDate.year <= max && players[i].gender == gender) {
			*player[*arrSize] = players[i];
			(*arrSize)++;
		}
	}
}

int mostPoints(PlayerGameStatistics pgs[], Player players[]) {//jogador com mais pontos de todos
	int most = 0;
	int mostId = 0;
	int j = 0;
	for (int i = 0; i < 300; i++) {
		int aux = 0;
		for (int a = 0; a < 517; a++) {
			if (players[i].id == pgs[a].idPlayer) {
				aux += pgs[a].statistics.twoPoints;
				aux += pgs[a].statistics.threePoints;
				j = i;
			}
		}
		if (aux > most) {
			most = aux;
			mostId = j;
		}
	}
	return mostId;
}
int mostAssists(PlayerGameStatistics pgs[], Player players[]) {//jogador com mais assistencias de todos 
	int most = 0;
	int mostId = 0;
	int j = 0;
	for (int i = 0; i < 300; i++) {
		int aux = 0;
		for (int a = 0; a < 517; a++) {
			if (players[i].id == pgs[a].idPlayer) {
				aux += pgs[a].statistics.assists;
				j = i;
			}
		}
		if (aux > most) {
			most = aux;
			mostId = j;
		}
	}
	return mostId;
}
int mostBlocks(PlayerGameStatistics pgs[], Player players[]) {//jogador com mais blocos de todos
	int most = 0;
	int mostId = 0;
	int j = 0;
	for (int i = 0; i < 300; i++) {
		int aux = 0;
		for (int a = 0; a < 517; a++) {
			if (players[i].id == pgs[a].idPlayer) {
				aux += pgs[a].statistics.blocks;
				j = i;
			}
		}
		if (aux > most) {
			most = aux;
			mostId = j;
		}
	}
	return mostId;
}

//JOGADORES

void loadP(Player players[]) {
	printf("Please enter the name of the file:");

	char name[100];
	fgets(name, sizeof(name), stdin);
	FILE *fp;
	int err = fopen_s(&fp, "players_1.csv", "r");//nao funciona com o input do utilizador

	if (err != 0) {
		printf("FICHEIRO (%s) INACESSIVEL\n", name);
		return;
	}
	printf("The file %s was opened", name);
	char nextLine[1024];
	int countPlayers = 0;

	while (fgets(nextLine, sizeof(nextLine), fp)) {
		if (strlen(nextLine) < 1) {
			continue;
		}

		char **tokens = split(nextLine, 5, ";");

		int playerId = atoi(tokens[0]);
		int day, month, year;
		sscanf_s(tokens[3], "%d/%d/%d", &day, &month, &year);
		char playerGender = tokens[4][0];

		Date dt = createDate(day, month, year);
		Player p = createPlayer(playerId, tokens[1], tokens[2], dt, playerGender);
		players[countPlayers] = p;

		free(tokens);

		countPlayers++;
	}
	printf("\n\n%d players were imported", countPlayers);
	countPlayers = 0;
	fclose(fp);
}

void showP(Player players[]) {
	printf("Chose order:\n1 - Sort [A-Z]\n2 - Sort [Z-A]\n");
	int order;
	printf("Option> ");
	scanf_s("%d", &order);
	//fgets(order, sizeof(order), stdin);

	if (order == 1) {
		sortPlayersAZ(players);
	} else if (order == 2) {
		sortPlayersZA(players);
	} else {
		printf("Invalid Input...");
		return;
	}
	printf("Player\t| Name\t\t\t| Team\t\t\t| BirthDate\t| Gender |\n");
	printf("==================================================================================\n");
	printArray(players);
}

void table(Player players[]){
	int matrix[4][2];
	int sub14M=0, sub16M=0, sub18M=0, senioresM=0, sub14F=0, sub16F=0, sub18F=0, senioresF=0;

	countPlayers(players, &sub14M, &sub16M, &sub18M, &senioresM, &sub14F, &sub16F, &sub18F, &senioresF);

	matrix[0][0] = sub14F;
	matrix[0][1] = sub14M;
	matrix[1][0] = sub16F;
	matrix[1][1] = sub16M;
	matrix[2][0] = sub18F;
	matrix[2][1] = sub18M;
	matrix[3][0] = senioresF;
	matrix[3][1] = senioresM;

	char *escalao[4] = {"Sub 14","Sub 16","Sub18","Seniores"};
	printf("Level/Gender|\tFemale\tMale\n");
	printf("===============================\n");
	for (int i = 0; i < 4; i++) {
		printf("%-11s|\t", escalao[i]);
		for (int j = 0; j < 2; j++) {
			printf("%d\t", matrix[i][j]);
		}
		printf("\n-------------------------------\n");
	}
}

void search(Player players[]) {
	printf("Name of the team?:");
	char team[50];
	fgets(team, sizeof(team), stdin);
	int aux = 0;
	for (int i = 0; i < 300; i++) {
		if (strcmp("Imortal", players[i].team) == 0) {//nao funciona com o input do utilizador
			aux = 1;
			printPlayer(&players[i]);
		}
	}
	if (aux == 0) {
		printf("NONEXISTENG TEAM\n");
	}
}

//JOGO

void loadG(PlayerGameStatistics pgsA[]) {
	printf("Please enter the name of the file:");

	char name[100];
	fgets(name, sizeof(name), stdin);
	FILE *fp;
	int err = fopen_s(&fp, "games_1.csv", "r");//nao funciona com o input do utilizador

	if (err != 0) {
		printf("FICHEIRO (%s) INACESSIVEL\n\n", name);
		return;
	}
	printf("The file %s was opened", name);

	char nextLine[1024];
	int countGames = 0;

	while (fgets(nextLine, sizeof(nextLine), fp)) {
		if (strlen(nextLine) < 1) {
			continue;
		}

		char **tokens = split(nextLine, 7, ";");

		int playerId = atoi(tokens[0]);
		int gameId = atoi(tokens[1]);
		int twoPoints = atoi(tokens[2]);
		int threePoints = atoi(tokens[3]);
		int assists = atoi(tokens[4]);
		int fouls = atoi(tokens[5]);
		int blocks = atoi(tokens[6]);

		Statistics s = createStatistics(twoPoints, threePoints, assists, fouls, blocks);
		PlayerGameStatistics pgs = createPlayerGameStatistics(playerId, gameId, s);
		pgsA[countGames] = pgs;

		free(tokens);
		countGames++;
	}
	printf("\n\n%d games were imported", countGames);
	countGames = 0;
	fclose(fp);
}

void searchG(PlayerGameStatistics pgs[]) {
	int idGame;
	printf("ID of the game?>");
	scanf_s("%d", &idGame);

	int sumOfPoints=0, sumOfPlayers=0, sumOfBlocks=0;
	sumOfPointsOfGame(pgs, idGame, &sumOfPoints);
	sumOfPlayersInAGame(pgs, idGame, &sumOfPlayers);
	sumOfBlocksInAGame(pgs, idGame, &sumOfBlocks);
	if (sumOfBlocks > 0 && sumOfPlayers > 0 && sumOfPoints > 0) {
		printf("---------------------------------------------------\n");
		printf("Total number of points %d\n", sumOfPoints);
		printf("---------------------------------------------------\n");
		printf("Total number of blocks %d\n", sumOfBlocks);
		printf("---------------------------------------------------\n");
		printf("Total number of used players %d\n", sumOfPlayers);
		printf("---------------------------------------------------\n");
	}else{
		printf("Invalid Game");
	}
}

void mvp(PlayerGameStatistics pgs[], Player p[]) {
	int idGame;
	printf("ID of the game?:");
	scanf_s("%d", &idGame);
	printf("O melhor jogador em campo tem o ID %d e um MVP = %d", calculateMPVId(pgs, idGame), calculateMPV(pgs, idGame));
}

//JOGOS E JOGADORES

void mFoulP(PlayerGameStatistics pgs[], Player p[]) {
	printf("FOULS AVERAGE PER PLAYER\n\n");
	printf("Player Name\t    | #Played Games | Av. Fouls\n");
	printf("====================================================\n");
	
	for (int i = 0; i < 300; i++) {
		if (numberOfGamesOfPlayer(pgs, p[i]) > 0) {
			printf("%-20s|       %d\t    | %.2f\n", p[i].name, numberOfGamesOfPlayer(pgs, p[i]), calculateAverageFoulsByPlayerByGame(pgs, p[i]));
		}
	}
}

void mFoulG(PlayerGameStatistics pgs[]) {
	
	int games = numberOfGames(pgs);//so conta 41 jogos

	printf("FOULS AVERAGE PER GAME\n\n");
	printf("#Game | Av.Fouls\n");
	printf("================\n");

	for (int i = 0; i < games; i++) {
		if (calculateAverageFoulsByGameByPlayerById(pgs, i + 1) > 0) {
			printf("%-4d  |  %.2f\n", i + 1, calculateAverageFoulsByGameByPlayerById(pgs, i+1));
		}
		else {
			printf("%-4d  |  %.2f\n", i + 1, 0.0);
		}
		
	}

}

void fairP(Player players[], PlayerGameStatistics pgs[]) {
	char * teams[] = {"Maia Clube","Esgueira","FC Porto","Imortal"};
	for (int i = 0; i < 10; i++) {
		printf("%.2f", calculateAverageFoulsByTeamByGame(players, teams[i], pgs));
	}

	//printf("%.2f", calculateAverageFoulsByTeamByGame(players, "FC Porto", pgs));
}

//INDICADOR AVANÇADO

void idealTeam(Player player[], PlayerGameStatistics pgs[]) {
	printf("Chose Level:\n0 - sub14;1 - sub16; 2 - sub18; 3 - senior\n");
	int order;
	printf("Option> ");
	scanf_s("%d", &order);

	printf("Chose Gender:\nF-Feminino, M-Masculino\n");
	//char order2;
	printf("Option> ");
	//scanf_s(" %c", &order2);

	int max, min;
	if (order == 1) {
		max = 2005;
		min = 2004;
	}
	else if (order == 2) {
		max = 2003;
		min = 2002;
	}
	else if (order == 3) {
		max = 2001;
		min = 2000;
	}
	else {
		max = 3000;
		min = 2000;
	}
	//if (numberOfPlayersByAgeAndGender(player, min, max, order2) < 5) {
		//printf("NAO EXISTEM JOGADORES PARA A EQUIPA IDEAL");
		//return;
	//}

	Player playersInRange[50];
	int arrSize = 0;
	//listOfPlayersByAgeAndGender(player, min, max, order2, &playersInRange, &arrSize);
	
	
	printf("CENTER:\n");
	printPlayerById(mostAssists(pgs, playersInRange), player);
	printf("SHOOTY GUARD\n");
	printPlayerById(mostPoints(pgs, playersInRange), player);
	printf("POINT GUARD:\n");
	printPlayerById(mostBlocks(pgs, playersInRange), player);
}
