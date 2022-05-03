#pragma once

#include "list.h"

typedef struct knapsack{
    PtList products;
    int maxWeight;
    int bestValue;
} Knapsack;

Knapsack* createKnapsack(int maxWeight, int bestValue);
void setBestValue(Knapsack *knap, int bestValue);
int addProduct(Knapsack* k,Product p, int rank);
int removeProduct(Knapsack* k,int rank, Product *p);
int calculateWeight(Knapsack *k);
int calculateValue(Knapsack *k);