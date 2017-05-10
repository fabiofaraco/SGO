$(document).ready(function () {
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
});


