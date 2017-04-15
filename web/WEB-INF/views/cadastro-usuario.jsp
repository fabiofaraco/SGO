<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Cadastro de Usuários</title>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>

        <link href=<c:url value="/resources/bootstrap/css/bootstrap.min.css"/> rel="stylesheet"/>
        <link href=<c:url value="/resources/css/mensagem.css"/> rel="stylesheet"/>
        <link href=<c:url value="/resources/css/cadastro.css"/> rel="stylesheet"/>
        <link href=<c:url value="/resources/css/sb-admin.css"/> rel="stylesheet"/>
        <link href=<c:url value="/resources/css/morris.css"/> rel="stylesheet"/>
        <link href=<c:url value="/resources/font-awesome/css/font-awesome.min.css"/> rel="stylesheet"/>
    </head>
    <body>
        <form id="frm" name="frm" method="post">
            <div id="wrapper">
                <c:import url="menu-principal.jsp" />   

                <div class="container-fluid">
                    <div class="page-header">
                        <h1>Cadastro de Usuários</h1>
                    </div>
                    <c:import url="mensagem.jsp" />
                    <div class="well">
                        <div class="form-group">
                            <label class="titulo" for="residencial">Dados Pessoais</label>
                        </div>

                        <div class="row">
                            <div class="col-md-6 col-sm-6">
                                <div id="divNome" class="form-group">
                                    <label class="descricao" for="nome">Nome</label>
                                    <input type="text" class="form-control" id="nome" name="nome" value="${usuario.nome}" ${disabled} />
                                </div>
                            </div>
                            <div class="col-md-6 col-sm-6">
                                <div id="divSobrenome" class="form-group">
                                    <label class="descricao" for="sobrenome">Sobrenome</label>
                                    <input type="text" class="form-control" id="sobrenome" name="sobrenome" value="${usuario.sobrenome}" ${disabled}/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-4 col-sm-4">
                                <div id="divCpf" class="form-group">
                                    <label class="descricao" for="cpf">CPF</label>
                                    <input type="text" class="form-control" id="cpf" name="cpf" value="${usuario.cpf}" ${disabled}/>
                                </div>
                            </div>
                            <div class="col-md-5 col-sm-5">
                                <div id="divEmail" class="form-group">
                                    <label class="descricao" for="email">Email</label>
                                    <input type="email" class="form-control" id="email" name="email" value="${usuario.email}" ${disabled}/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-4 col-sm-5">
                                <div id="divPerfil" class="form-group">
                                    <label class="descricao" for="perfil">Perfil</label>
                                    <select class="form-control" id="perfil" name="perfil" ${disabled}>
                                        <option value=""><c:out value="Selecione..."/></option>
                                        <c:forEach items="${perfis}" var="p">
                                            <option value="${p.id}" ${usuario.perfil.id == p.id ? 'selected' : ''}><c:out value="${p.descricao}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-4 col-sm-4">
                                <div id="divSenha" class="form-group">
                                    <label class="descricao" for="senha">Senha</label>
                                    <input type="password" class="form-control" id="senha" name="senha" value="" ${disabled}/>
                                </div>
                            </div>

                            <div class="col-md-4 col-sm-4">
                                <div id="divConfirmaSenha" class="form-group">
                                    <label class="descricao" for="confirmaSenha">Confirma Senha</label>
                                    <input type="password" class="form-control" id="confirmaSenha" name="confirmaSenha" value="" ${disabled} />
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-2 col-sm-4 col-xs-6">
                                <div class="form-group">
                                    <button id="btnSalvar" type="button" class="btn btn-cadastro col-xs-12" ${disabled}>Salvar</button>				  	
                                </div>
                            </div>

                            <div class="col-md-2 col-sm-4 col-xs-6">
                                <div class="form-group">
                                    <button id="btnVoltar" type="button" class="btn btn-voltar col-xs-12">Retornar</button>				  	
                                </div>
                            </div>
                        </div>
                    </div>

                    <input type="hidden" class="form-control" id="id" name="id" value="${usuario.id}"/>		
                </div>
            </div>
        </form>
    </body>

    <script src=<c:url value="/resources/js/jquery/jquery-2.2.2.min.js"/>></script>
    <script src=<c:url value="/resources/js/jquery/jquery.mask.min.js"/>></script>
    <script src=<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>></script>
    <script src=<c:url value="/resources/js/mensagem.js"/>></script>
    <script src=<c:url value="/resources/js/cadastroUsuario.js"/>></script>
    <script src=<c:url value="/resources/js/util.js"/>></script>
</html>