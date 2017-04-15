$(document).ready(function() {
	
	$('#btnSalvar').click(function(){
		if(validaCampos()) {
			$('#frm').attr('action', 'salvar');
			$('#frm').submit();
		}
	});

	$('#btnVoltar').click(function(){
		$('#frm').attr('action', 'retornar');
		$('#frm').submit();		
	});
	
	$('#estado').on('change', function () { 
		var idEstado = $('#estado').val(); 
		
		var select = $('#cidade');			
		select.find('option').remove();
		$('<option>').val("").text("Selecione...").appendTo(select);
		
		$.post("carregaCidade", {'idEstado' : idEstado} ,function(data) {
			if(data != "") {
				$.each(JSON.parse(data), function(index, value) {
		        	$('<option>').val(value.id).text(value.nome).appendTo(select);
		        });
			}
		});
	});
	
	/* FUNÇÕES */
	function validaCampos() {
		if(!validaCampo("data", "divData", "Data")) {
			return false;
		}
		
		if(!validaCampo("evento", "divNatureza", "Natureza da Ocorrência")) {
			return false;
		}
		
		if(!validaCampo("logradouro", "divEndereco", "Endereço")) {
			return false;
		}
		
		if(!validaCampo("complemento", "divComplemento", "Complemento")) {
			return false;
		}
		
		if(!validaCampo("bairro", "divBairro", "Bairro")) {
			return false;
		}
		
		if(!validaCampo("estado", "divEstado", "Estado")) {
			return false;
		}
		
		if(!validaCampo("cidade", "divCidade", "Cidade")) {
			return false;
		}
		
		if(!validaCampo("relato", "divRelato", "Relato dos Fatos")) {
			return false;
		}
		
		return true;
	}
});