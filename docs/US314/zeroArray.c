void zeroArray(int x, int y, int z, int (*shipAllocation)[y][z]){
    for(int i = 0; i < x; i++){
        for(int j = 0; j < y; j++){
            for(int k = 0; k < z; k++){
                shipAllocation[i][j][k] = 0;
            }
        }
    }
}