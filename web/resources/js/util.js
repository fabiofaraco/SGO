/* global carregaTabela */

$(document).ready(function () {

    exibirMensagemErro = function (mensagem)
    {
        limparMensagens();

        $('html, body').animate({scrollTop: 0}, 'slow');
        $(".msg-danger").html(mensagem);
        $("#danger").fadeIn(1000);
    };

//  ----------------------------------------------------------------------------

    exibirMensagemSucesso = function (mensagem)
    {
        limparMensagens();

        $('html, body').animate({scrollTop: 0}, 'slow');
        $(".msg-success").html(mensagem);
        $("#success").fadeIn(1000);
    };

//  ----------------------------------------------------------------------------

    limparMensagens = function ()
    {
        $("#success").fadeOut();
        $("#danger").fadeOut();
    };

//  ----------------------------------------------------------------------------

    validarCPF = function (cpf)
    {
        var strCPF = cpf;
        strCPF = strCPF.replace(/[^\d]+/g, '');

        var Soma;
        var Resto;
        var cboll = true;
        Soma = 0;

        if (strCPF.length !== 11
                || strCPF === "00000000000"
                || strCPF === "11111111111"
                || strCPF === "22222222222"
                || strCPF === "33333333333"
                || strCPF === "44444444444"
                || strCPF === "55555555555"
                || strCPF === "66666666666"
                || strCPF === "77777777777"
                || strCPF === "88888888888"
                || strCPF === "99999999999") {
            cboll = false;
        }

        for (i = 1; i <= 9; i++) {
            Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (11 - i);
        }

        Resto = (Soma * 10) % 11;

        if ((Resto === 10) || (Resto === 11)) {
            Resto = 0;
        }

        if (Resto !== parseInt(strCPF.substring(9, 10))) {
            cboll = false;
        }

        Soma = 0;
        for (i = 1; i <= 10; i++) {
            Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (12 - i);
        }

        Resto = (Soma * 10) % 11;

        if ((Resto === 10) || (Resto === 11)) {
            Resto = 0;
        }

        if (Resto !== parseInt(strCPF.substring(10, 11))) {
            cboll = false;
        }

        return cboll;
    };

//  ----------------------------------------------------------------------------

    validaData = function (campo)
    {
        var expReg = new RegExp("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/[12][0-9]{3}");
        var data = campo;
        var ardt = data.split("/");
        var erro = false;

        if (data.search(expReg) === -1) {
            erro = true;
        } else if (((ardt[1] === 4) || (ardt[1] === 6) || (ardt[1] === 9) || (ardt[1] === 11)) && (ardt[0] > 30)) {
            erro = true;
        } else if (ardt[1] === 2) {
            if ((ardt[0] > 28) && ((ardt[2] % 4) !== 0)) {
                erro = true;
            }

            if ((ardt[0] > 29) && ((ardt[2] % 4) === 0)) {
                erro = true;
            }
        }

        if (erro) {
            return false;
        }

        return true;
    };

//  ----------------------------------------------------------------------------

    criticar = function (options)
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

    isEmail = function (email)
    {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    };

//  ----------------------------------------------------------------------------

    isDoubleClicked = function (element)
    {
        if (element.data("isclicked"))
        {
            return true;
        }

        element.data("isclicked", true);
        
        setTimeout(function () {
            element.removeData("isclicked");
        }, 1500);

        return false;
    };

});