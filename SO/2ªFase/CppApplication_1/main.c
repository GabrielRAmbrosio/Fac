#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h> 

#include <sys/types.h>
#include <sys/wait.h>
#include <sys/mman.h>

#include "product.h"
#include "knapsack.h"
#include "list.h"

/*
 * 
 */
int main(int argc, char** argv) {
    
    int tests = 0;
    char *filename = (char*)calloc(20, sizeof(char));
    int forks = 0;
    double timer = 0;
    
    int bestValue = 0;
    int maxWeight = 0;
    
    start(&tests, filename, &forks, &timer);
    PtList allProducts = listCreate();
    int done = loadFile(filename, allProducts, &bestValue, &maxWeight);
    
    while(done == -1){
        start(&tests, filename, &forks, &timer);
        done = loadFile(filename, allProducts, &bestValue, &maxWeight);
    }
    
    PtList best = listCreate();
    
    double runTime = 0;
    int values;
    
    int protection = PROT_READ | PROT_WRITE;
    int visibility = MAP_SHARED;
    void *shmem = mmap(NULL, sizeof(int), protection, visibility, 0, 0);
    
    clock_t begin;
    double time_spent;
    begin = clock();
    
    int k=0;
    time_spent = (double)(clock() - begin) / CLOCKS_PER_SEC;
    
    int cpid = fork();
    
    while(time_spent < timer){
        

        if (cpid == 0){

            int z = ajkpa(allProducts, best, bestValue, maxWeight);
            
            if(z > (int)shmem){
                shmem = z;
            }
            
            if(shmem == 23){
                printf("SHMEM:   %d", shmem);
            }
            
        }else {
            int x = ajkpa(allProducts, best, bestValue, maxWeight);
            
            if(x > (int)shmem){
                shmem = x;
            }
            
            if(shmem == 23){
                printf("SHMEM:   %d", shmem);
            }
        }
        
        time_spent = (double)(clock() - begin) / CLOCKS_PER_SEC;
        
        k++;
        
    }
    
    time_spent = (double)(clock() - begin) / CLOCKS_PER_SEC;
    
    
    printf("Melhor valor encontrado: %d\n\n" , (int*)shmem);
    
    printf("Solução:\n");
    
    listPrint(best);
    
    results();
    
    listDestroy(&allProducts);
    
    return (EXIT_SUCCESS);
}

char** split(char *str, int nFields, const char *delim){
	char **tokens = malloc(sizeof(char*) * nFields);

	int index = 0;
	char *token = NULL;

	token = strtok(str, delim);
	while (token){
		tokens[index++] = token;
		token = strtok(NULL, delim);
	}
	return	tokens;
}

void start(int *test, char *file, int *forks, double *timer){
    printf("Inserir comando: (exemplo: kp 1 ex23.txt 10 60)\n");
    char command[50];
    fgets(command, sizeof(char) * 50, stdin);
    
    char** tokens = split(command, 5, " ");
    *test = atoi(tokens[1]);
    strcpy(file, tokens[2]);
    *forks = atoi(tokens[3]);
    *timer = atof(tokens[4]);
}

void results(){
    printf("i\t\tn\t\tproblem\t\tm\t\ttime\t\teval\t\twight\t\titerations\t\ttime\t\tobjects");
}


int loadFile(char *filename, PtList *a, int *best, int *maxW){
    FILE *fp;
        
    fp = fopen(filename, "r");

    if (fp == NULL){
        printf("FICHEIRO (%s) INACESSIVEL\n", filename);
        return -1;
    }

    printf("FICHEIRO %s ABERTO\n", filename);

    char nextLine[1024];

    int numberOfItems = -1;
    int maxWeight = -1;
    int bestValue = -1;

    int countItems = 0;

    if(numberOfItems == -1){
                fgets(nextLine, sizeof(nextLine), fp);
                numberOfItems = atoi(nextLine);
                fgets(nextLine, sizeof(nextLine), fp);
                maxWeight = atoi(nextLine);
                *maxW = maxWeight;
    }

    for(int i = 0; i < numberOfItems; i++){
        fgets(nextLine, sizeof(nextLine), fp);
            if (strlen(nextLine) < 1)
                continue;

        char** tokens = split(nextLine, 2, " ");

        Product p = createProduct(atoi(tokens[0]), atoi(tokens[1]));
        listAdd(a, countItems, p);

        countItems++;
    }

    bestValue = atoi(fgets(nextLine, sizeof(nextLine), fp));
    *best = bestValue;

    printf("\n%d ITEMS TOTAIS\n", countItems);
    countItems = 0;

    fclose(fp);
    
    return 1;
}
    
int ajkpa(PtList a, PtList best, int bestValue, int maxWeight){
    
    
    
    srand(time(NULL));
    
    Knapsack *knapsack = createKnapsack(maxWeight, bestValue);
    PtList aCopy = listCreate();
    
    int aSize = 0;
    listSize(a, &aSize);
    
    for(int b = 0; b < aSize; b++){
        Product aux;
        
        listGet(a,b, &aux);
        listAdd(aCopy,b,aux);
    }
    
    
    int size;
    listSize(aCopy, &size);
    
    int knapsackSize = 0;
    
    //remove produtos da lista total, e adiciona-os ao saco até este ficar cheio
    for(int i = 0; i < size; i++){
        
        listSize(knapsack->products, &knapsackSize);
        
        if(knapsackSize == 0){
            Product p;
            listGet(aCopy,i, &p);
            if(addProduct(knapsack,p,0) != -1){
                listRemove(aCopy,i,&p);
            }else{
                continue;
            }
        }else{
            int n = rand() % knapsackSize;
            Product p;
            listGet(aCopy,i, &p);
            if(addProduct(knapsack,p,n) != -1){
                listRemove(aCopy,i,&p);
            }else{
                continue;
            }
        }
    }
    
    int bestSoFar = calculateValue(knapsack);
    int auxSize = 0;
    listSize(knapsack->products, &auxSize);
    listClear(best);
    //duplica o saco, para criar um que só contem a melhor solução encontrada até ao momento
    for(int z = 0; z < auxSize; z++){
        Product aux;
        
        listGet(knapsack->products,z, &aux);
        listAdd(best,z,aux);
    }
    
    int aux;
    listSize(knapsack->products, &aux);

    //numero random entre 0 e o size do saco-1, para decidir quantos itens vamos tirar
    int nToRemove = rand() % aux;

    for(int o = 0; o < nToRemove; o++){
        int n = rand() % aux;
        removeFromKnap(knapsack,aCopy,n);
        listSize(knapsack->products, &aux);
    }

    int aux2;
    listSize(aCopy, &aux2);
    int nToAdd = rand() % aux2;

    for(int p = 0; p < nToAdd; p++){
        int n = rand() % aux2;
        addToKnap(knapsack,aCopy,p);
        listSize(aCopy, &aux2);
    }

    int res = calculateValue(knapsack);

    //se a solução atual é a melhor, atualizar o saco best e o melhor valor
    if(res > bestSoFar){
        listClear(best);

        bestSoFar = res;

        int aauxSize = 0;
        listSize(knapsack->products, &aauxSize);

        for(int i = 0; i < aauxSize; i++){
            Product aux;
            listGet(knapsack->products,i, &aux);
            listAdd(best,i,aux);
        }

        if(bestSoFar == bestValue){//se a solução encontrada é a melhor possivel, sai logo
            listDestroy(knapsack->products);
            return bestSoFar;
        }
    }
    

    
    
    listDestroy(&knapsack->products);
    return bestSoFar;
}

void addToKnap(Knapsack * k, PtList a, int rank){
    Product aux;
    int size = 0;
    listSize(k->products,&size);
    listRemove(a,rank,&aux);
    addProduct(k,aux,size);
}

void removeFromKnap(Knapsack * k, PtList a, int rank){
    Product aux;
    int size = 0;
    listSize(a,&size);
    removeProduct(k,rank,&aux);
    listAdd(a,size, aux);
}