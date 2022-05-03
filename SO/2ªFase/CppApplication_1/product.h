#pragma once

typedef struct product{
    int value;
    int weight;
} Product;

typedef Product *PtProduct;

Product createProduct(int value, int weight);
void printProduct(PtProduct elem);