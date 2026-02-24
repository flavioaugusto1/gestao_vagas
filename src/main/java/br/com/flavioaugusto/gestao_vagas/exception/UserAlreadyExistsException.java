package br.com.flavioaugusto.gestao_vagas.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException() {
        super("Usuário já existe");
    }
}
