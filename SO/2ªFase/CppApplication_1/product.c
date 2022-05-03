#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "product.h"

Product createProduct(int value, int weight){
    Product p;
    
    p.value = value;
    p.weight = weight;
    
    return p;
}

void printProduct(PtProduct elem){
    printf("Value: %d\n", elem->value);
    printf("Weight: %d\n", elem->weight);
}