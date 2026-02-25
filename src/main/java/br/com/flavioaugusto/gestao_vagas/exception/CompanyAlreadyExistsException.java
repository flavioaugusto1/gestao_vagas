package br.com.flavioaugusto.gestao_vagas.exception;

public class CompanyAlreadyExistsException extends RuntimeException {
    public CompanyAlreadyExistsException() {
        super("A empresa informada já existe.");
    }
}
