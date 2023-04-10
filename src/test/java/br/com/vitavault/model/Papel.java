package br.com.vitavault.model;

import br.com.vitavault.domain.EnumNivelAcesso;

import java.util.UUID;

public class Papel {

    private UUID id;
    private EnumNivelAcesso nivelAcesso;
    private String descricao;

    public Papel(UUID id, EnumNivelAcesso nivelAcesso, String descricao) {
        this.id = id;
        this.nivelAcesso = nivelAcesso;
        this.descricao = descricao;
    }
}
