$(document).ready(function () {
    var contextPath = $("#contextPath").val();

//  ----------------------------------------------------------------------------

    $("#conteudo").on("click", "#btnSalvar", function () {
        if (validaCampos())
        {
            $.ajax({
                type: "POST",
                url: contextPath + "/usuario/salvar",
                data: $("form").serialize(),
                dataType: "html",
                beforeSend: function () {
                    $(".load-img").fadeIn();
                },
                success: function (data) {
                    $("#conteudo").load(contextPath + "/usuario/lista", function () {
                        carregarScriptPagina();
                        exibirMensagemSucesso(data);
                    });
                }
            });
        }
    });

//  ----------------------------------------------------------------------------

    $("#conteudo").on("click", ".btn-close-msg", function () {
        $("#success").fadeOut(1000);
        $("#danger").fadeOut(1000);
    });

//  ----------------------------------------------------------------------------

    $("#conteudo").on("focusout", "#cpf", function () {
        $.ajax({
            type: "POST",
            url: contextPath + "/usuario/validaCpf",
            data: {
                cpf: $("#cpf").val(),
                id: $("#id").val()
            },
            dataType: "html",
            success: function (data) {
                if (data !== "")
                {
                    $("#cpf").val("");
                    exibirMensagemErro(data);
                }
            }
        });
    });

//  ----------------------------------------------------------------------------

    var validaCampos = function ()
    {
        if (!criticar({valor: $("#nome").val(), mensagem: "Campo Obrigatório: Nome"}))
        {
            return false;
        }

        if (!criticar({valor: $("#sobrenome").val(), mensagem: "Campo Obrigatório: Sobrenome"}))
        {
            return false;
        }

        if (!criticar({valor: $("#cpf").val(), mensagem: "Campo Obrigatório: CPF"}))
        {
            return false;
        }

        if (!validarCPF($("#cpf").val()))
        {
            exibirMensagem("O CPF digitado não é válido.");
            return false;
        }

        if (!criticar({valor: $("#email").val(), mensagem: "Campo Obrigatório: E-mail"}))
        {
            return false;
        }

        if (!criticar({valor: $("#perfil").val(), mensagem: "Campo Obrigatório: Perfil"}))
        {
            return false;
        }

        if (!criticar({valor: $("#senha").val(), mensagem: "Campo Obrigatório: Senha"}))
        {
            return false;
        }

        if (!criticar({valor: $("#confirmaSenha").val(), mensagem: "Campo Obrigatório: Confirmar Senha"}))
        {
            return false;
        }

        if (!criticarSenha({valor: $("#senha").val(), valorConfirma: $("#confirmaSenha").val()}))
        {
            return false;
        }

        return true;
    };

//  ----------------------------------------------------------------------------

    var criticar = function (options)
    {
        if (options.valor === undefined
                || options.valor === null
                || options.valor === "".trim())
        {
            exibirMensagemErro(options.mensagem);
            return false;
        }

        return true;
    };

//  ----------------------------------------------------------------------------

    var criticarSenha = function (options)
    {
        if (options.valor.trim().length < 6 || options.valor.trim().length > 10)
        {
            exibirMensagemErro("A senha deve possuir no mínimo 6 e no máximo 10 digitos.");
            return false;
        }

        if (options.valor.trim() !== options.valorConfirma.trim())
        {
            exibirMensagemErro("As senhas digitadas não conferem.");
            return false;
        }

        return true;
    };

});	