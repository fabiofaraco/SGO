/* global carregarScriptPagina */

$(document).ready(function () {
    var contextPath = $("#contextPath").val();

    $('#conteudo').on("click", "#btnIncluirUsuario", function () {
        $(".load-img").fadeIn();
        $("#conteudo").load(contextPath + "/usuario/cadastro", carregarScriptPagina);
    });

//  ----------------------------------------------------------------	

    $("#conteudo").on("click", ".btn-visualizar", function () {
        var id = $(this).attr('data-id-usuario');
        $(".load-img").fadeIn();
        $("#conteudo").load(contextPath + "/usuario/carregar?visualiza=true&id=" + id, carregarScriptPagina);
    });

//  ----------------------------------------------------------------	

    $("#conteudo").on("click", ".btn-alterar", function () {
        var id = $(this).attr('data-id-usuario');
        $(".load-img").fadeIn();
        $("#conteudo").load(contextPath + "/usuario/carregar?visualiza=false&id=" + id, carregarScriptPagina);
    });

//  ----------------------------------------------------------------	

    $("#conteudo").on("click", ".btn-excluir", function () {
        var id = $(this).attr('data-id-usuario');
        var nome = $(this).attr("data-nome-usuario");
        
        $("#msgConfirmacao").html("Deseja excluir o usuário " + nome + "?");
        $('.btn-realiza-exclusao-usuario').attr('data-id-usuario', id);
        $('#modal-excluir-usuario').modal('show');
    });


//  ----------------------------------------------------------------

    $("#conteudo").on("click", ".btn-realiza-exclusao-usuario", function () {
        var id = $(this).attr('data-id-usuario');

        $.ajax({
            url: contextPath + "/usuario/remover?id=" + id,
            data: $("form").serialize(),
            beforeSend: function () {
                $('#modal-excluir-usuario').modal('toggle');
                $(".load-img").fadeIn();
            },
            success: function (data) {
                $("#conteudo").load(contextPath + "/usuario/lista", function() {
                    carregarScriptPagina();
                    exibirMensagemSucesso(data);
                });
            }
        });
    });

//  ----------------------------------------------------------------	

});