mainCompiled: main.o zeroArray.o asm.o printResult.o
	gcc -g -Wall  main.o zeroArray.o asm.o printResult.o -o main

asm.o: asm.s
	gcc -g -c -Wall asm.s -o asm.o

printResult.o: printResult.c
	gcc -g -c -Wall printResult.c -o printResult.o

zeroArray.o: zeroArray.c
	gcc -g -c -Wall zeroArray.c -o zeroArray.o

main.o: main.c
	gcc -g -Wall -c main.c -o main.o

clean:
	rm -f *.o main US315.csv

run: mainCompiled
	./main

debug: mainCompiled
	gdb -tui main
