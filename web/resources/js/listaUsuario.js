/* global carregarScriptPagina */

$(document).ready(function () {
    $('#conteudo').on("click", "#btnIncluirUsuario", function (e) {
        e.preventDefault();
        ajaxLoad("/usuario/cadastro", null);
    });

//  ----------------------------------------------------------------	

    $("#conteudo").on("click", ".btn-alterar", function (e) {
        e.preventDefault();
        var id = $(this).attr('data-id-usuario');
        ajaxLoad("/usuario/carregar",
                {
                    id: id,
                    nomeFiltro: $("#nomeFiltro").val(),
                    cpfFiltro: $("#cpfFiltro").val()
                });
    });

//  ----------------------------------------------------------------	

    $("#conteudo").on("click", ".btn-excluir", function (e) {
        e.preventDefault();
        var id = $(this).attr('data-id-usuario');
        var nome = $(this).attr("data-nome-usuario");

        $("#msgConfirmacao").html("Deseja excluir o usuário " + nome + "?");
        $('.btn-realiza-exclusao-usuario').attr('data-id-usuario', id);
        $('#modal-excluir-usuario').modal('show');
    });


//  ----------------------------------------------------------------

    $("#conteudo").on("click", ".btn-realiza-exclusao-usuario", function (e) {
        e.preventDefault();
        var id = $(this).attr('data-id-usuario');

        ajaxPostSubmit("/usuario/remover", {id: id},
                function ()
                {
                    $('#modal-excluir-usuario').modal('toggle');
                    beforeSendDefult();
                },
                function ()
                {
                    errorDefault();
                },
                function (data)
                {
                    successDefault("/usuario/filtrar", data,
                            {
                                nomeFiltro: $("#nomeFiltro").val(),
                                cpfFiltro: $("#cpfFiltro").val()
                            });
                }
        );
    });

//  ----------------------------------------------------------------	

    $("#conteudo").on("click", "#btnFiltro", function () {
        if (validaCampos())
        {
            ajaxLoad("/usuario/filtrar",
                    {
                        nomeFiltro: $("#nomeFiltro").val(),
                        cpfFiltro: $("#cpfFiltro").val()
                    });
        }
    });

//  ----------------------------------------------------------------	

    var validaCampos = function ()
    {
        if ($.trim($("#nomeFiltro").val()) === ""
                && $.trim($("#cpfFiltro").val()) === "")
        {
            exibirMensagemErro("Ao menos um dos campos do filtro deve ser preenchido");
            return false;
        }

        if ($.trim($("#nomeFiltro").val()).length < 3)
        {
            exibirMensagemErro("Digite ao menos três letras para realizar a consulta pelo nome.");
            return false;
        }

        if ($.trim($("#cpfFiltro").val()) !== "")
        {
            if (!validarCPF($("#cpfFiltro").val()))
            {
                exibirMensagemErro("O CPF digitado não é válido.");
                return false;
            }
        }

        return true;
    };
});