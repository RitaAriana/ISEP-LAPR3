mainCompiled: main.o printResult.o readStructs.o lineNumber.o calculateEnergy.o
	gcc -g -Wall  main.o printResult.o readStructs.o lineNumber.o calculateEnergy.o -o main

calculateEnergy.o: calculateEnergy.c
	gcc -g -c -Wall calculateEnergy.c -o calculateEnergy.o

lineNumber.o: lineNumber.c
	gcc -g -c -Wall lineNumber.c -o lineNumber.o

readStructs.o: readStructs.c
	gcc -g -c -Wall readStructs.c -o readStructs.o

printResult.o: printResult.c
	gcc -g -c -Wall printResult.c -o printResult.o

main.o: main.c
	gcc -g -Wall -c main.c -o main.o

clean:
	rm -f *.o main US411.csv

run: mainCompiled
	./main 20 5 50 500000

debug: mainCompiled
	gdb -tui main

vg: run
	valgrind ./main 20 5 50 500000