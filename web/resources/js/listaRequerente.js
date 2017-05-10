$(document).ready(function () {
    onload = function () {
        $('#liRequerente').addClass('active');
    };
    
    $('#btnIncluirRequerente').on("click", function () {
        $('#frm').attr('action', 'cadastro');
        $('#frm').submit();
    });

//  ----------------------------------------------------------------	

    $('.data-table').on("click", ".btn-visualizar", function () {
        var id = $(this).attr('data-id-requerente');
        $('#frm').attr('action', 'carregar?visualiza=true&id=' + id);
        $('#frm').submit();
    });

//  ----------------------------------------------------------------	

    $('.data-table').on("click", ".btn-alterar", function () {
        var id = $(this).attr('data-id-requerente');
        $('#frm').attr('action', 'carregar?visualiza=false&id=' + id);
        $('#frm').submit();
    });

//  ----------------------------------------------------------------	

    $('.data-table').on("click", ".btn-excluir", function () {
        var id = $(this).attr('data-id-requerente');
        $('.btn-realiza-exclusao-requerente').attr('data-id-requerente', id);
        $('#modal-excluir-requerente').modal('show');
    });

//  ----------------------------------------------------------------

    $('.btn-realiza-exclusao-requerente').on("click", function () {
        var id = $(this).attr('data-id-requerente');

        $('#frm').attr('action', 'remover?id=' + id);
        $('#frm').submit();
    });
});