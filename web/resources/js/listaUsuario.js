$(document).ready(function() {
	
        $('#btnIncluirUsuario').on("click", function(){
		$('#frm').attr('action', 'cadastro');
		$('#frm').submit();
	});
	
//	----------------------------------------------------------------	
	
	$('.btn-visualizar-usuario').on("click", function(){
		var id = $(this).attr('data-id-usuario');
		$('#frm').attr('action', 'usuario/carregar?visualiza=true&id='+id);
		$('#frm').submit();
	});

//	----------------------------------------------------------------	
	
	$('.btn-alterar-usuario').on("click", function(){
		var id = $(this).attr('data-id-usuario');
		$('#frm').attr('action', 'usuario/carregar?visualiza=false&id='+id);
		$('#frm').submit();
	});
	
//	----------------------------------------------------------------	
	
	$('.btn-excluir-usuario').on("click", function(){
		var id = $(this).attr('data-id-usuario');
		$('.btn-realiza-exclusao-usuario').attr('data-id-usuario', id);
		$('#modal-excluir-usuario').modal('show');
	});
	
	
//	----------------------------------------------------------------
	
	$('.btn-realiza-exclusao-usuario').on("click", function(){
		var id = $(this).attr('data-id-usuario');
		$('#frm').attr('action', 'usuario/remover?id='+id);
		$('#frm').submit();
	});
	
//	----------------------------------------------------------------
});