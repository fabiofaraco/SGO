
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Cadastro de Requerentes</title>
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
                        <h1>Cadastro de Requerentes</h1>
                    </div>
                    <c:import url="mensagem.jsp" />
                    <div class="well">
                        <div class="row">
                            <div id="divNome" class="col-md-6 col-sm-6">
                                <div class="form-group">
                                    <label class="descricao" for="nome">Nome</label>
                                    <input type="text" class="form-control" id="nome" name="nome" value="${requerente.nome}" ${disabled} />
                                </div>
                            </div>

                            <div id="divSobrenome" class="col-md-6 col-sm-6">
                                <div class="form-group">
                                    <label class="descricao" for="sobrenome">Sobrenome</label>
                                    <input type="text" class="form-control" id="sobrenome" name="sobrenome" value="${requerente.sobrenome}" ${disabled} />
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div id="divCpf" class="col-md-3 col-sm-3">
                                <div class="form-group">
                                    <label class="descricao" for="cpf">CPF</label>
                                    <input type="text" class="form-control mascara-cpf" id="cpf" name="cpf" value="${requerente.cpf}" ${disabled} />
                                </div>
                            </div>

                            <div id="divNascimento" class="col-md-4 col-sm-3">
                                <div class="form-group">
                                    <label class="descricao" for="dataNascimento">Nascimento</label>
                                    <input type="text" class="form-control mascara-data" id="dataNascimento" name="dataNascimento" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${requerente.dataNascimento}"/>" ${disabled} />
                                </div>
                            </div>

                            <div id="divEmail" class="col-md-5 col-sm-6">
                                <div class="form-group">
                                    <label class="descricao" for="email">Email</label>
                                    <input type="email" class="form-control" id="email" name="email" value="${requerente.email}" ${disabled} />
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="titulo" for="residencial">Endereço</label>
                        </div>

                        <div class="row">
                            <div id="divEndereco" class="col-md-5 col-sm-3">
                                <div class="form-group">
                                    <label class="descricao" for="logradouro">Endereço</label>
                                    <input type="text" class="form-control" id="logradouro" name="endereco.logradouro" value="${requerente.endereco.logradouro}" ${disabled} />
                                </div>
                            </div>

                            <div id="divComplemento" class="col-md-4 col-sm-6">
                                <div class="form-group">
                                    <label class="descricao" for="complemento">Complemento</label>
                                    <input type="text" class="form-control" id="complemento" name="endereco.complemento" value="${requerente.endereco.complemento}" ${disabled} />
                                </div>
                            </div>

                            <div id="divBairro" class="col-md-3 col-sm-6">
                                <div class="form-group">
                                    <label class="descricao" for="bairro">Bairro</label>
                                    <input type="text" class="form-control" id="bairro" name="endereco.bairro" value="${requerente.endereco.bairro}" ${disabled} />
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-5 col-sm-3">
                                <div id="divEstado" class="form-group">
                                    <label class="descricao" for="estado">Estado</label>

                                    <select class="form-control" id="estado" name="endereco.cidade.estado" ${disabled}>
                                        <option value=""><c:out value="Selecione..."/></option>
                                        <c:forEach items="${estados}" var="es">
                                            <option value="${es.id}" ${requerente.endereco.cidade.estado.id == es.id? 'selected' : ''}><c:out value="${es.sigla} - ${es.nome}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-5 col-sm-6">
                                <div id="divCidade" class="form-group">
                                    <label class="descricao" for="cidade">Cidade</label>

                                    <select class="form-control" id="cidade" name="endereco.cidade" ${disabled}>
                                        <option value=""><c:out value="Selecione..."/></option>
                                        <c:forEach items="${cidades}" var="c">
                                            <option value="${c.id}" ${requerente.endereco.cidade.id == c.id? 'selected' : ''}><c:out value="${c.nome}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="titulo" for="telefone">Telefone</label>
                        </div>

                        <div class="row">
                            <div class="col-md-3 col-sm-6">
                                <div id="divResidencial" class="form-group">
                                    <label class="descricao" for="residencial">Residencial</label>
                                    <input type="text" class="form-control mascara-telefone" id="residencial" name="telefone.residencial" value="${requerente.telefone.residencial}" ${disabled} />
                                </div>
                            </div>

                            <div class="col-md-3 col-sm-6">
                                <div id="divCelular" class="form-group">
                                    <label class="descricao" for="celular">Celular</label>
                                    <input type="text" class="form-control mascara-celular" id="celular" name="telefone.celular" value="${requerente.telefone.celular}" ${disabled} />
                                </div>
                            </div>
                        </div>
                        <hr/>
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

                    <input type="hidden" class="form-control" id="id" name="id" value="${requerente.id}"/>
                </div>
            </div>
        </form>
    </body>

    <script src=<c:url value="/resources/js/jquery/jquery-2.2.2.min.js"/>></script>
    <script src=<c:url value="/resources/js/jquery/jquery.mask.min.js"/>></script>
    <script src=<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>></script>
    <script src=<c:url value="/resources/js/mensagem.js"/>></script>
    <script src=<c:url value="/resources/js/cadastroRequerente.js"/>></script>
    <script src=<c:url value="/resources/js/util.js"/>></script>
</html>