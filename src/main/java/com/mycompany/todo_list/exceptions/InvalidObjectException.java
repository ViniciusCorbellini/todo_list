package com.mycompany.todo_list.exceptions;

/**
 *
 * @author Vinicius Corbellini
 */
public class InvalidObjectException extends Exception{
    public InvalidObjectException() {
        super("O Objeto possui campos inválidos.");
    }

    public InvalidObjectException(String message) {
        super("O Objeto possui campos inválidos: " + message);
    }
}
