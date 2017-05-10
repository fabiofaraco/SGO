$(document).ready(function ()
{
    var contextPath = $("#contextPath").val();
    
    limparMenuAtivo = function()
    {
        $('#liUsuario').removeClass('active');
        $('#liRequerente').removeClass('active');
        $('#liOcorrencia').removeClass('active');
    };
    
    $("#aUsuario").on("click", function () {
        limparMenuAtivo();
        
        $('#liUsuario').addClass('active');

        $("#conteudo").load(contextPath + "/usuario/lista");
    });

    $("#aRequerente").on("click", function () {
        limparMenuAtivo();
        
        $('#liRequerente').addClass('active');

        $("#conteudo").load(contextPath + "/requerente/lista");
    });

    $("#aOcorrencia").on("click", function () {
        limparMenuAtivo();
        
        $('#liOcorrencia').addClass('active');

        $("#conteudo").load(contextPath + "/ocorrencia/lista");
    });
    
});


