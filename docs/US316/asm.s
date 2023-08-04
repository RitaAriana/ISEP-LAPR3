.section .data
    .global maxX
    .global maxY
    .global maxZ
    .global containerLocation
    .global indexes

.section .text
    .global verifiesList

verifiesList:
    movl $0, %r8d # contador de iteracoes
    movl $0, %r9d # contador de contentores
    movq containerLocation(%rip), %rcx
    movq indexes(%rip), %rax
    jmp loop
    
loop:
    cmpl %r8d, %edx
    je end
    
    pushq %r8
    pushq %rdi
    pushq %rdx 
    
    movl (%rax), %edi
    addq $4, %rax
    movl (%rax), %esi
    addq $4, %rax
    movl (%rax), %edx

    pushq %rax
    
    call isContainerThere
    addl %eax, %r9d

    popq %rax
    popq %rdx
    popq %rdi
    popq %r8

    addq $4, %rax
    incl %r8d

    jmp loop

end:
    movl %r9d, %eax
    ret

isContainerThere:
    # x em %edi
    # y em %esi
    # z em %edx

    # Verifica Coordenadas

    movl maxX(%rip), %eax
    cmpl %eax, %edi
    jg noContainer

    movl maxY(%rip), %eax
    cmpl %eax, %esi
    jg noContainer

    movl maxZ(%rip), %r8d
    cmpl %r8d, %edx
    jg noContainer


    # Memory Address
    imull %eax, %edi # x*maxY
    imull %r8d, %edi # x*maxY*maxZ

    imull %r8d, %esi # y*maxZ

    addl %edx, %esi # y*maxZ + z

    addl %esi, %edi # soma expressoes

    imull $4, %edi # bytes a adicionar
    
    movq %rcx, %rax
    addq %rdi, %rax

    # Comparacao

    cmpl $0, (%rax)
    je noContainer
    jne hasContainer

hasContainer:
    movl $0, %eax
    movb $1, %al
    ret

noContainer:
    movl $0, %eax
    ret
