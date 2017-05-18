<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="wrapper">
    <div class="container-fluid">
        <div class="page-header">
            <h1 class="titulo">Lista de Requerentes</h1>
        </div>
        <c:import url="mensagem.jsp" />

        <div class="row filtro">
            <div class="col-sm-4">
                <div class="form-group">
                    <input type="text" id="nomeFiltro" name="nomeFiltro" class="form-control" placeholder="Buscar Nome" value="${nomeFiltro}" />
                </div>
            </div>

            <div class="col-sm-4">
                <div class="form-group">
                    <input type="text" id="cpfFiltro" name="cpfFiltro" class="form-control mascara-cpf" placeholder="Buscar CPF" value="${cpfFiltro}" />
                </div>
            </div>
                
            <div class="col-sm-2">
                <div class="form-group">
                    <button type="button" id="btnFiltroRequerente" name="btnFiltroRequerente" class="btn botao-filtro btn-block btn-sm" >Filtrar </button>
                </div>
            </div>
        </div>

        <c:choose>
            <c:when test="${!empty requerentes}">
                <div class="list-group">
                    <c:forEach items="${requerentes}" var="requerente">
                        <a href="javascript:void(0);" class="list-group-item list-group-style">
                            <div class="row">
                                <div class="col-sm-12 col-md-10">
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <h4 class="list-group-item-heading titulo">${requerente.nome}</h4>
                                        </div>

                                        <div class="col-sm-6">
                                            <label class="titulo">CPF:</label>
                                            <label class="descricao">${requerente.cpf}</label>
                                        </div>

                                        <div class="col-sm-6">
                                            <label class="titulo">Nascimento:</label>
                                            <label class="descricao lbl-formata-data">${requerente.dataNascimento}</label>
                                        </div>

                                        <div class="col-xs-12">
                                            <label class="titulo">E-mail:</label>
                                            <label class="descricao">${requerente.email}</label>
                                        </div>

                                        <div class="col-sm-12 col-md-6">
                                            <label class="titulo">Endereço:</label>
                                            <label class="descricao">
                                                ${requerente.endereco.logradouro},
                                                ${requerente.endereco.complemento}
                                            </label>
                                        </div>

                                        <div class="col-sm-12 col-md-4">
                                            <label class="titulo">Bairro:</label>
                                            <label class="descricao">
                                                ${requerente.endereco.bairro}
                                            </label>
                                        </div>

                                        <div class="col-sm-6">
                                            <label class="titulo">Cidade:</label>
                                            <label class="descricao">
                                                ${requerente.endereco.cidade.nome}
                                            </label>
                                        </div>

                                        <div class="col-sm-6">
                                            <label class="titulo">Estado:</label>
                                            <label class="descricao">
                                                ${requerente.endereco.cidade.estado.sigla} 
                                            </label>
                                        </div>

                                        <div class="col-sm-6">
                                            <label class="titulo">Tel Res:</label>
                                            <label class="descricao">
                                                ${requerente.telefone.residencial} 
                                            </label>
                                        </div>

                                        <div class="col-xs-12 col-md-6 col-sm-6">
                                            <label class="titulo">Tel Cel:</label>
                                            <label class="descricao">
                                                ${requerente.telefone.celular} 
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-12 col-md-2">
                                    <div class="form-group">
                                        <button type="button" class="btn btn-link btn-alterar-requerente" 
                                                data-id-requerente="${requerente.id}">
                                            <span class="glyphicon glyphicon-pencil"></span> Alterar
                                        </button>

                                        <button type="button" class="btn btn-link btn-excluir-requerente" 
                                                data-id-requerente="${requerente.id}" 
                                                data-nome-requerente="${requerente.nome}"
                                                data-toggle="modal">
                                            <span class="glyphicon glyphicon-trash"></span> Remover
                                        </button>
                                    </div>  
                                </div>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <div class="list-group">
                    <a href="javascript:void(0);" class="list-group-item">
                        <h4 class="descricao">${msgConsulta}</h4>
                    </a>
                </div>
            </c:otherwise>
        </c:choose>
        <button id="btnIncluirRequerente" type="button" class="btn botao-lista col-xs-12 col-sm-4 col-sm-offset-4">Incluir</button>

        <div class="modal fade" id="modal-excluir-requerente" role="dialog">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h3 class="modal-title titulo">Confirmação</h3>
                    </div>
                    <div class="modal-body">
                        <h4 id="msgConfirmacao" class="descricao"></h4>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn botao btn-realiza-exclusao-requerente">Excluir</button>
                        <button type="button" class="btn botao" data-dismiss="modal">Fechar</button>
                    </div>
                </div>    
            </div>
        </div>
    </div>
</div>