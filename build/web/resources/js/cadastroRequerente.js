$(document).ready(function() {
	
	$("#cpf").focusout(function(){
		if($("#cpf").val() != "") {
			if(validaCPF($('#cpf').val())) {
				$.post("validaCpf", {'cpf' : $("#cpf").val(), 'id' : $("#id").val()} ,function(data) {
					if(data != "") {
						$("#divCpf").realizaCritica({
						      'mensagem' : 'O CPF digitado já está cadastrado no sistema',
						      'field' : "cpf"
						      	 
						 });
					} else {
						$("#divCpf").limparCritica();
					}
				});
			} else {
				$("#divCpf").realizaCritica({
				      'mensagem' : 'Por favor, digite um CPF válido',
				      'field' : "cpf" 
				 });
			}
		}
    });	
	
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
		if(!validaCampo("nome", "divNome", "Nome")) {
			return false;
		}
		
		if(!validaCampo("sobrenome", "divSobrenome", "Sobrenome")) {
			return false;
		}
		
		if(!validaCampo("cpf", "divCpf", "CPF")) {
			return false;
		}
		
		if(!validaCampo("dataNascimento", "divNascimento", "Data de Nascimento")) {
			return false;
		}
		
		if(!validaData("dataNascimento", "divNascimento", "Data de Nascimento")) {
			return false;
		}
		
		if(!validaCampo("email", "divEmail", "Email")) {
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
		
		if(!validaCampo("residencial", "divResidencial", "Telefone Residencial")) {
			return false;
		}
		
		if(!validaCampo("celular", "divCelular", "Celular")) {
			return false;
		}
		
		return true;
	}
});


