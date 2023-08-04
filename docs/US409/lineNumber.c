#include <stdlib.h>
#include <stdio.h>

short getNumberOfContainers(char *containerFileName){
    FILE *containerFile = fopen(containerFileName, "r");
    
    if (!containerFile){ // Verifica se o ficheiro existe
        printf("No file found\n"); 
        exit(-1); 
    } 

    short numLinhas = 0;

    char ch;
    while((ch=fgetc(containerFile)) != EOF) {
      if(ch=='\n')
         numLinhas++;
    }

    fclose(containerFile);

    return numLinhas;
}