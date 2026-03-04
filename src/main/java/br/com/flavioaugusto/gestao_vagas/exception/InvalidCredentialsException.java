package br.com.flavioaugusto.gestao_vagas.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Username ou senha inválidos.");
    }
}
