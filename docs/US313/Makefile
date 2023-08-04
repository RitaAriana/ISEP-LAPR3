mainCompiled: main.o zeroArray.o printArray.o
	gcc -g -Wall  main.o zeroArray.o printArray.o -o main

zeroArray.o: zeroArray.c
	gcc -g -c -Wall zeroArray.c -o zeroArray.o

printArray.o: printArray.c
	gcc -g -c -Wall printArray.c -o printArray.o

main.o: main.c
	gcc -g -Wall -c main.c -o main.o

clean:
	rm -f *.o main US313.csv

run: mainCompiled
	./main

debug: mainCompiled
	gdb -tui main
