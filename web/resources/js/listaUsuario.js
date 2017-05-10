$(document).ready(function () {
    var contextPath = $("#contextPath").val();

    $('#conteudo').on("click", "#btnIncluirUsuario", function () {
        $("#conteudo").load(contextPath + "/usuario/cadastro");
    });

//  ----------------------------------------------------------------	

    $("#conteudo").on("click", ".btn-visualizar", function () {
        var id = $(this).attr('data-id-usuario');
        $("#conteudo").load(contextPath + "/usuario/carregar?visualiza=true&id=" + id);
    });

//  ----------------------------------------------------------------	

    $("#conteudo").on("click", ".btn-alterar", function () {
        var id = $(this).attr('data-id-usuario');
        $("#conteudo").load(contextPath + "/usuario/carregar?visualiza=false&id=" + id);
    });

//  ----------------------------------------------------------------	

    $("#conteudo").on("click", ".btn-excluir", function () {
        var id = $(this).attr('data-id-usuario');
        $('.btn-realiza-exclusao-usuario').attr('data-id-usuario', id);
        $('#modal-excluir-usuario').modal('show');
    });


//  ----------------------------------------------------------------

    $("#conteudo").on("click", ".btn-realiza-exclusao-usuario", function () {
        var id = $(this).attr('data-id-usuario');

        $.ajax({
            url: contextPath + "/usuario/remover?id=" + id,
            data: $("form").serialize(),
            beforeSend: function() {
                $('#modal-excluir-usuario').modal('toggle');
            },
            success: function () {
                $("#conteudo").load(contextPath + "/usuario/lista");
            }
        });
    });
});