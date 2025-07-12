package com.mycompany.todo_list.exceptions;

/**
 *
 * @author Vinicius Corbellini
 */
public class EntityNotFoundException extends Exception {

    public EntityNotFoundException() {
        super("Entidade não encontrada.");
    }

    public EntityNotFoundException(String message) {
        super("Entidade não encontrada: " + message);
    }
}
