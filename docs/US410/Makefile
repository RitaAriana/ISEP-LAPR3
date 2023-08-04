mainCompiled: main.o printResult.o readStructs.o lineNumber.o asm.o calculateEnergy.o
	gcc -g -Wall  main.o printResult.o readStructs.o lineNumber.o asm.o calculateEnergy.o -o main

calculateEnergy.o: calculateEnergy.c
	gcc -g -c -Wall calculateEnergy.c -o calculateEnergy.o

asm.o: asm.s
	gcc -g -c -Wall asm.s -o asm.o

lineNumber.o: lineNumber.c
	gcc -g -c -Wall lineNumber.c -o lineNumber.o

readStructs.o: readStructs.c
	gcc -g -c -Wall readStructs.c -o readStructs.o

printResult.o: printResult.c
	gcc -g -c -Wall printResult.c -o printResult.o

main.o: main.c
	gcc -g -Wall -c main.c -o main.o

clean:
	rm -f *.o main US410.csv

run: mainCompiled
	./main 20 5

debug: mainCompiled
	gdb -tui main

vg: run
	valgrind ./main 20 5