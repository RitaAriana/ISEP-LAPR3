.section .text
    .global checkArray

checkArray:
    movl $0, %eax # numIteracoes
    movl $0, %r8d # numero de lugares livres
    movl $0, %r9d # numero de lugares ocupados

    jmp loop

loop:
    cmpl %eax, %esi
    je end

    cmpl $0, (%rdi)
    je slotNotOccupied
    jne slotOccupied

nextIteration:
    addq $4, %rdi
    incl %eax
    jmp loop

slotOccupied:
    incl %r9d
    jmp nextIteration

slotNotOccupied:
    incl %r8d
    jmp nextIteration

end:
    movq %r8, %rax
    shlq $32, %rax
    addq %r9, %rax
    ret
