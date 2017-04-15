$(document).ready(function() {
	$('#btnIncluirOcorrencia').on("click", function(){
		$('#frm').attr('action', 'cadastro');
		$('#frm').submit();
	});

//	----------------------------------------------------------------
	
	$('.btn-visualizar-ocorrencia').on("click", function(){
		var id = $(this).attr('data-id-ocorrencia');
		$('#frm').attr('action', 'ocorrencia/carregar?visualiza=true&id='+id);
		$('#frm').submit();
	});
	
//	----------------------------------------------------------------
	
	$('.btn-alterar-ocorrencia').on("click", function(){
		var id = $(this).attr('data-id-ocorrencia');
		$('#frm').attr('action', 'ocorrencia/carregar?visualiza=false&id='+id);
		$('#frm').submit();
	});
	
//	----------------------------------------------------------------
	
	$('.btn-excluir-ocorrencia').on("click", function(){
		var id = $(this).attr('data-id-ocorrencia');
		
		$('.btn-realiza-exclusao-ocorrencia').attr('data-id-ocorrencia', id);
		$('#modal-excluir-ocorrencia').modal('show');
	});

//	----------------------------------------------------------------
	
	$('.btn-realiza-exclusao-ocorrencia').on("click", function(){
		var id = $(this).attr('data-id-ocorrencia');
		
		$('#frm').attr('action', 'ocorrencia/remover?id='+id);
		$('#frm').submit();
	});
});