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
                    $("#btnSalvar #btn-nome").html("Salvando...");
                    $("#btnSalvar #load").addClass("glyphicon glyphicon-refresh glyphicon-refresh-animate");
                },
                success: function (data) {
                    $("#btnSalvar #btn-nome").html("Salvar");
                    $("#btnSalvar #load").removeClass("glyphicon glyphicon-refresh glyphicon-refresh-animate");
                    $(".msg-success").html(data);
                    $("#success").fadeIn(1000);
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

    var validaCampos = function ()
    {
        if (!criticar({valor: $("nome").val(), mensagem: "Campo Obrigatório: Nome"}))
        {
            return false;
        }

        if (!criticar({valor: $("sobrenome").val(), mensagem: "Campo Obrigatório: Sobrenome"}))
        {
            return false;
        }

        if (!criticar({valor: $("cpf").val(), mensagem: "Campo Obrigatório: CPF"}))
        {
            return false;
        }

        if (!criticar({valor: $("email").val(), mensagem: "Campo Obrigatório: E-mail"}))
        {
            return false;
        }

        if (!criticar({valor: $("perfil").val(), mensagem: "Campo Obrigatório: Perfil"}))
        {
            return false;
        }
        
        if (!criticar({valor: $("confirmaSenha").val(), mensagem: "Campo Obrigatório: Confirmar Senha"}))
        {
            return false;
        }

        if (!criticarSenha({valor: $("senha").val(), valorConfirma: $("confirmaSenha").val()}))
        {
            return false;
        }

        return true;
    };

//  ----------------------------------------------------------------------------

    var criticar = function (options)
    {
        if (options.valor.trim() === undefined 
                || options.valor.trim() === null 
                || options.valor.trim() === "")
        {
            $('html, body').animate({scrollTop: 0}, 'slow');
            $(".msg-danger").html(options.mensagem);
            $("#danger").fadeIn(1000);
            
            return false;
        }
        
        return true;
    };

//  ----------------------------------------------------------------------------

    var criticarSenha = function (options)
    {
        if (options.valor.trim().length < 6 || options.valor.trim().length > 10)
        {
            $('html, body').animate({scrollTop: 0}, 'slow');
            $(".msg-danger").html("A senha deve possuir no mínimo 6 e no máximo 10 digitos");
            $("#danger").fadeIn(1000);
        
            return false;
        }
        
        if (options.valor.trim() !== options.valorConfirma.trim())
        {
            $('html, body').animate({scrollTop: 0}, 'slow');
            $(".msg-danger").html("Senhas não conferem! Digite novamente.");
            $("#danger").fadeIn(1000);
        
            return false;
        }

        return true;
    };

    /* FUNÇÕES */
    function validaCampoeees() {


        $("#divNome").limparCritica();
        $("#divSobrenome").limparCritica();
        $("#divCpf").limparCritica();
        $("#divEmail").limparCritica();
        $("#divPerfil").limparCritica();
        $("#divSenha").limparCritica();
        $("#divConfirmaSenha").limparCritica();

        if (!validaCampo("nome", "divNome", "Nome")) {
            return false;
        }

        if (!validaCampo("sobrenome", "divSobrenome", "Sobrenome")) {
            return false;
        }

        if (!validaCampo("cpf", "divCpf", "CPF")) {
            return false;
        }

        if (!validaCampo("email", "divEmail", "Email")) {
            return false;
        }

        if (!validaCampo("perfil", "divPerfil", "Perfil")) {
            return false;
        }

        if (!validaCampo("senha", "divSenha", "Senha")) {
            return false;
        }

        if (!validaCampo("confirmaSenha", "divConfirmaSenha", "Confirmar Senha")) {
            return false;
        }

        if ($("#senha").val().trim().length < 6 || $("#senha").val().trim().length > 10) {
            $('.msg-warning').html('A senha deve possuir no mínimo 6 e no máximo 10 digitos');
            $('#warning').css("display", "block");
            $("#divSenha").addClass("has-error has-feedback");

            return false;
        }

        if ($("#confirmaSenha").val().trim() === "") {
            $('.msg-warning').html('Por favor Confirme a Senha');
            $('#warning').css("display", "block");
            $("#divConfirmaSenha").addClass("has-error has-feedback");

            return false;
        }

        if ($("#senha").val() !== $("#confirmaSenha").val()) {
            $('.msg-warning').html('Senhas não conferem! Digite novamente.');
            $('#warning').css("display", "block");
            $("#senha").val("");
            $("#confirmaSenha").val("");
            $("#divSenha").addClass("has-error has-feedback");

            return false;
        }

        return true;
    }
});	