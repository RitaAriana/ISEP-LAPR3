.section .data
    .global maxX
    .global maxY
    .global maxZ

.section .text
    .global isContainerThere

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
