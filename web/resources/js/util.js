/* global carregaTabela */

$(document).ready(function () {

    carregarScriptPagina = function ()
    {
        $(".load-img").fadeOut();

        $('.mascara-cpf').mask('999.999.999-99');
        $('.mascara-data').mask('00/00/0000');
        $('.mascara-telefone').mask('(00) 0000-0000');
        $('.mascara-celular').mask('(00) 00000-0000');

        carregaTabela();
    };

//  ----------------------------------------------------------------------------

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

    validarCPF = function (cpf) {
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

    validaData = function (idCampo) {
        var expReg = new RegExp("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/[12][0-9]{3}");
        var data = $("#" + idCampo).val().trim();
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

    carregaTabela = function ()
    {
        $('.data-table').DataTable({
            responsive: {
                details: {
                    renderer: function (api, rowIdx, columns) {
                        var data = $.map(columns, function (col, i) {
                            return '<tr style="height:30px">' +
                                    '<td style="width:60px"><b>' + col.title + '</b></td>' +
                                    '<td>' + col.data + '</td>' +
                                    '</tr>';
                        }).join('');
                        return data ?
                                $('<table/>').append(data) :
                                false;
                    }
                }
            },
            "paging": true,
            "ordering": true,
            "lengthMenu": [[5], [5]],
            "language": {
                "sEmptyTable": "Nenhum registro encontrado",
                "sInfo": "",
                "sInfoEmpty": "",
                "sInfoFiltered": "(Filtrados de _MAX_ registros)",
                "sInfoPostFix": "",
                "sInfoThousands": ".",
                "sSearch": "Pesquisar",
                "sLengthMenu": "",
                "sLoadingRecords": "Carregando...",
                "sProcessing": "Processando...",
                "sZeroRecords": "Nenhum registro encontrado",
                "oPaginate": {
                    "sNext": "Próximo",
                    "sPrevious": "Anterior",
                    "sFirst": "Primeiro",
                    "sLast": "Último"
                },
                "oAria": {
                    "sSortAscending": ": Ordenar colunas de forma ascendente",
                    "sSortDescending": ": Ordenar colunas de forma descendente"
                }
            }
        });
    };
    
//  ----------------------------------------------------------------------------

});