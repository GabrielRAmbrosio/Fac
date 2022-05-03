#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "knapsack.h"


Knapsack* createKnapsack(int maxWeight, int bestValue){
    
    Knapsack *knap = (Knapsack *) calloc(1,sizeof(int));
    
    knap->products = listCreate();
    knap->maxWeight = maxWeight;
    knap->bestValue = bestValue;
    
    return knap;
}

void setBestValue(Knapsack *knap, int bestValue){
    knap->bestValue = bestValue;
}

int addProduct(Knapsack* k,Product p, int rank){
    if((p.weight + calculateWeight(k)) <= k->maxWeight){
        return listAdd(k->products, rank, p);
    }else{
        return -1;
    }
}

int removeProduct(Knapsack* k,int rank, Product *p){
    int size=0;
    listSize(k->products, &size);
    Product pp;
    if(size > 0){
        int res = listRemove(k->products,rank, &pp);
        *p = pp;
        return res;
    }else{
        return -1;
    }
}

int calculateWeight(Knapsack *k){
    int size;
    listSize(k->products, &size);
    int weight = 0;
    
    for(int i = 0; i < size; i++){
        Product p;
        listGet(k->products, i, &p);
        weight += p.weight;
    }
    return weight;
}

int calculateValue(Knapsack *k){
    int size;
    listSize(k->products, &size);
    int value = 0;
    
    for(int i = 0; i < size; i++){
        Product p;
        listGet(k->products, i, &p);
        value += p.value;
    }
    return value;
}