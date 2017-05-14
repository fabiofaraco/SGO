$(document).ready(function () {
    var contextPath = $("#contextPath").val();

    $('#conteudo').on("click", "#btnIncluirRequerente", function () {
        $(".load-img").fadeIn();
        $("#conteudo").load(contextPath + "/requerente/cadastro", carregarScriptPagina);
    });

//  ----------------------------------------------------------------	

    $("#conteudo").on("click", ".btn-alterar-requerente", function () {
        var id = $(this).attr('data-id-requerente');
        $(".load-img").fadeIn();
        $("#conteudo").load(contextPath + "/requerente/carregar?&id=" + id, carregarScriptPagina);
    });

//  ----------------------------------------------------------------	

    $("#conteudo").on("click", ".btn-excluir-requerente", function () {
        var id = $(this).attr('data-id-requerente');
        var nome = $(this).attr("data-nome-requerente");
        
        $("#msgConfirmacao").html("Deseja excluir o requerente " + nome + "?");
        $('.btn-realiza-exclusao-requerente').attr('data-id-requerente', id);
        $('#modal-excluir-requerente').modal('show');
    });


//  ----------------------------------------------------------------

    $("#conteudo").on("click", ".btn-realiza-exclusao-requerente", function () {
        var id = $(this).attr('data-id-requerente');

        $.ajax({
            url: contextPath + "/requerente/remover?id=" + id,
            data: $("form").serialize(),
            error: function() {
                $(".load-img").fadeOut();
                exibirMensagemErro("Ocorreu um erro inesperado. Operação não realizada.");
            },
            beforeSend: function () {
                $('#modal-excluir-requerente').modal('toggle');
                $(".load-img").fadeIn();
            },
            success: function (data) {
                $("#conteudo").load(contextPath + "/requerente/lista", function() {
                    carregarScriptPagina()();
                    exibirMensagemSucesso(data);
                });
            }
        });
    });
    
    carregarScriptPagina = function ()
    {
        $(".load-img").fadeOut();

        $('.mascara-cpf').mask('999.999.999-99');
        $('.mascara-data').mask('00/00/0000');
        $('.mascara-telefone').mask('(00) 0000-0000');
        $('.mascara-celular').mask('(00) 00000-0000');
    };
});