package br.com.flavioaugusto.gestao_vagas.exception;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
        super("A empresa informada não foi encontrada");
    }
}
