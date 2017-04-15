$(document).ready(function() {
	$('#btnIncluirRequerente').on("click", function(){
		$('#frm').attr('action', 'cadastro');
		$('#frm').submit();
	});
	
//	----------------------------------------------------------------
	
	$('.btn-visualizar-requerente').on("click", function(){
		var id = $(this).attr('data-id-requerente');
		$('#frm').attr('action', 'requerente/carregar?visualiza=true&id='+id);
		$('#frm').submit();
	});
	
//	----------------------------------------------------------------
	
	$('.btn-alterar-requerente').on("click", function(){
		var id = $(this).attr('data-id-requerente');
		$('#frm').attr('action', 'requerente/carregar?visualiza=false&id='+id);
		$('#frm').submit();
	});
	
//	----------------------------------------------------------------
	
	$('.btn-excluir-requerente').on("click", function(){
		var id = $(this).attr('data-id-requerente');
		
		$('.btn-realiza-exclusao-requerente').attr('data-id-requerente', id);
		$('#modal-excluir-requerente').modal('show');
	});

//	----------------------------------------------------------------
	
	$('.btn-realiza-exclusao-requerente').on("click", function(){
		var id = $(this).attr('data-id-requerente');
		
		$('#frm').attr('action', 'requerente/remover?id='+id);
		$('#frm').submit();
	});
});