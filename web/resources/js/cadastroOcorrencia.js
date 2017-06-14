$(document).ready(function () {
    $("#conteudo").on("click", ".cadOcorrencia #btnSalvarOcorrencia", function () {
        if (validaCampos())
        {
            ajaxPostSubmit("/ocorrencia/salvar", $("form").serialize(),
                    function ()
                    {
                        beforeSendDefult();
                    },
                    function ()
                    {
                        errorDefault();
                    },
                    function (data)
                    {
                        successDefault("/ocorrencia/filtrar", data, {
                            nomeFiltro: $("#nome").val(),
                            cpfFiltro: $("#cpfRequerente").val()
                        });
                    }
            );
        }
    });

//  ----------------------------------------------------------------------------

    $('#conteudo').on('change', ".cadOcorrencia #estado", function (e)
    {
        e.preventDefault();

        var idEstado = $('#estado').val();
        var select = $('#cidade');

        select.find('option').remove();
        $('<option>').val("").text("Selecione...").appendTo(select);

        ajaxPost("/requerente/carregaCidade", {idEstado: idEstado}, function (data)
        {
            if (data !== "")
            {
                $.each(JSON.parse(data), function (index, value)
                {
                    $('<option>').val(value.id).text(value.nome).appendTo(select);
                });
            }
        });
    });

//  ----------------------------------------------------------------------------
    var validaCampos = function ()
    {
        if (!criticar({valor: $("#evento").val(), mensagem: "Campo Obrigatório: Natureza da Ocorrência"}))
        {
            return false;
        }

        if (!criticar({valor: $("#responsavel").val(), mensagem: "Campo Obrigatório: Responsável"}))
        {
            return false;
        }

        if (!criticar({valor: $("#logradouro").val(), mensagem: "Campo Obrigatório: Endereço"}))
        {
            return false;
        }

        if (!criticar({valor: $("#complemento").val(), mensagem: "Campo Obrigatório: Complemento"}))
        {
            return false;
        }

        if (!criticar({valor: $("#bairro").val(), mensagem: "Campo Obrigatório: Bairro"}))
        {
            return false;
        }

        if (!criticar({valor: $("#estado").val(), mensagem: "Campo Obrigatório: Estado"}))
        {
            return false;
        }

        if (!criticar({valor: $("#cidade").val(), mensagem: "Campo Obrigatório: Cidade"}))
        {
            return false;
        }
        
        if (!criticar({valor: $("#relato").val(), mensagem: "Campo Obrigatório: Relato dos Fatos"}))
        {
            return false;
        }

        return true;
    };

//  ----------------------------------------------------------------------------

    $("#conteudo").on("click", ".btn-close-msg", function ()
    {
        $("#success").fadeOut(1000);
        $("#danger").fadeOut(1000);
    });
});