$(document).ready(function() {
	$("#cpf").mask("999.999.999-99");
		
	$("#cpf").focusout(function(){
		if($("#cpf").val() != "") {
			if(validaCPF($('#cpf').val())) {
				$.post("validaCpf", {'cpf' : $("#cpf").val(), 'id' : $("#id").val()} ,function(data) {
					if(data != "") {
						$("#divCpf").realizaCritica({'mensagem' : 'O CPF digitado já está cadastrado no sistema', 'field' : "cpf"});
					} else {
						$("#divCpf").limparCritica();
					}
				});
			} else {
				$("#divCpf").realizaCritica({'mensagem' : 'Por favor digite um CPF válido', 'field' : "cpf"})
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
	
	/* FUNÇÕES */
	function validaCampos() {
		$("#divNome").limparCritica();
		$("#divSobrenome").limparCritica();
		$("#divCpf").limparCritica();
		$("#divEmail").limparCritica();
		$("#divPerfil").limparCritica();
		$("#divSenha").limparCritica();
		$("#divConfirmaSenha").limparCritica();
		
		if(!validaCampo("nome", "divNome", "Nome")) {
			return false;
		}
		
		if(!validaCampo("sobrenome", "divSobrenome", "Sobrenome")) {
			return false;
		}
		
		if(!validaCampo("cpf", "divCpf", "CPF")) {
			return false;
		}
		
		if(!validaCampo("email", "divEmail", "Email")) {
			return false;
		}
		
		if(!validaCampo("perfil", "divPerfil", "Perfil")) {
			return false;
		}
		
		if(!validaCampo("senha", "divSenha", "Senha")) {
			return false;
		}
		
		if(!validaCampo("confirmaSenha", "divConfirmaSenha", "Confirmar Senha")) {
			return false;
		}
		
		if($("#senha").val().trim() == "") {
			$('.msg-warning').html('Por favor insira o Senha');
			$('#warning').css("display", "block");
			$("#senha").focus();
			$("#divSenha").addClass("has-error has-feedback")
			
			return false;
		}
		
		if($("#senha").val().trim().length < 6 || $("#senha").val().trim().length > 10) {
			$('.msg-warning').html('A senha deve possuir no mínimo 6 e no máximo 10 digitos');
			$('#warning').css("display", "block");
			$("#senha").focus();
			$("#divSenha").addClass("has-error has-feedback")
			
			return false;
		}
		
		if($("#confirmaSenha").val().trim() == "") {
			$('.msg-warning').html('Por favor Confirme a Senha');
			$('#warning').css("display", "block");
			$("#confirmaSenha").focus();
			$("#divConfirmaSenha").addClass("has-error has-feedback")
			
			return false;
		}
		
		if($("#senha").val() != $("#confirmaSenha").val()) {
			$('.msg-warning').html('Senhas não conferem! Digite novamente.');
			$('#warning').css("display", "block");
			$("#senha").focus();
			$("#senha").val("");
			$("#confirmaSenha").val("");
			$("#divSenha").addClass("has-error has-feedback")
			
			return false;
		}
		
		return true;
	}
});	