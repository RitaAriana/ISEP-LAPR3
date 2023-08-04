mainCompiled: main.o printArray.o readStructs.o lineNumber.o
	gcc -g -Wall  main.o printArray.o readStructs.o lineNumber.o -o main

lineNumber.o: lineNumber.c
	gcc -g -c -Wall lineNumber.c -o lineNumber.o

readStructs.o: readStructs.c
	gcc -g -c -Wall readStructs.c -o readStructs.o

printArray.o: printArray.c
	gcc -g -c -Wall printArray.c -o printArray.o

main.o: main.c
	gcc -g -Wall -c main.c -o main.o

clean:
	rm -f *.o main US409.csv

run: mainCompiled
	./main

debug: mainCompiled
	gdb -tui main

vg: run
	valgrind ./main 