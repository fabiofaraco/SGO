$(document).ready(function () {
    var contextPath = $("#contextPath").val();

    ajaxLoad = function (url, parametros)
    {
        $(".load-img").fadeIn();
        $("#conteudo").load(contextPath + url, parametros, carregarScriptPagina);
    };

//  ----------------------------------------------------------------------------

    ajaxPostSubmit = function (url, parametros, fnBeforeSend, fnError, fnSuccess)
    {
        $.ajax({
            type: "POST",
            url: contextPath + url,
            data: parametros,
            dataType: "html",
            beforeSend: fnBeforeSend,
            error: fnError,
            success: fnSuccess
        });
    };

//  ----------------------------------------------------------------------------

    ajaxPost = function (url, parametros, fnSuccess)
    {
        $.ajax({
            type: "POST",
            url: contextPath + url,
            data: parametros,
            dataType: "html",
            error: function () 
            {
                errorDefault();
            },
            success: fnSuccess
        });
    };

//  ----------------------------------------------------------------------------

    beforeSendDefult = function()
    {
        $(".load-img").fadeIn();
    };

//  ----------------------------------------------------------------------------

    successDefault = function(url, data , parametros)
    {
        $("#conteudo").load(contextPath + url, parametros, function () {
            carregarScriptPagina();
            exibirMensagemSucesso(data);
        });
    };

//  ----------------------------------------------------------------------------

    errorDefault = function ()
    {
        $(".load-img").fadeOut();
        exibirMensagemErro("Ocorreu um erro inesperado. Operação não realizada.");
    };

//  ----------------------------------------------------------------------------

    function carregarScriptPagina()
    {
        $(".load-img").fadeOut();

        $('.mascara-cpf').mask('999.999.999-99');
        $('.mascara-data').mask('00/00/0000');
        $('.mascara-telefone').mask('(00) 0000-0000');
        $('.mascara-celular').mask('(00) 00000-0000');
    }
    ;
});