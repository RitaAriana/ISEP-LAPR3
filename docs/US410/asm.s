.section .text
    .global isRefrigerated

isRefrigerated:
    movl $0, %r9d
    jmp loop

loop:   
    cmpw %r9w, %r8w
    je end

    cmpw %si, 4(%rdi)
    jne incorrectCoordinates

    cmpw %dx, 8(%rdi)
    jne incorrectCoordinates

    cmpw %cx, 12(%rdi)
    jne incorrectCoordinates

    jmp containerFound
incorrectCoordinates:
    incw %r9w
    addq $68, %rdi
    jmp loop

containerFound:
    cmpb $1, 16(%rdi)
    jne end

    movl $1, %eax
    ret

end:
    movl $0, %eax
    ret
