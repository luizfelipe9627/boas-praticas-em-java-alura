package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;

// Criado uma interface ValidacaoSolicitacaoAdocao para ser utilizada na validação de solicitação de adoção.
public interface ValidacaoSolicitacaoAdocao {

    void validar(SolicitacaoAdocaoDto solicitacaoAdocaoDto); // O método validar é responsável por validar uma solicitação de adoção, recebendo um objeto do tipo SolicitacaoAdocaoDto como parâmetro.

}
