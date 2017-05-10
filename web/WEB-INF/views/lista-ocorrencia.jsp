<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="wrapper">

    <div class="container-fluid">
        <div class="page-header">
            <h1>Lista de Ocorrências</h1>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="table-responsive">          
                    <table class="table table-striped table-hover data-table">
                        <thead>
                            <tr>
                                <th>Nª Ocorrência</th>
                                <th>Data</th>
                                <th>Natureza</th>
                                <th>Endereço</th>
                                <th></th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${ocorrencias}" var="ocorrencia">
                                <tr>
                                    <td>${ocorrencia.id}</td>
                                    <td>${ocorrencia.data}</td>
                                    <td>${ocorrencia.evento.descricao}</td>
                                    <td>
                                        ${ocorrencia.endereco.logradouro} - 
                                        ${ocorrencia.endereco.complemento} -
                                        ${ocorrencia.endereco.bairro}
                                    </td>
                                    <td>
                                        <a class="btn btn-block btn-visualizar-ocorrencia" data-id-ocorrencia="${ocorrencia.id}">
                                            <span class="glyphicon glyphicon-search"></span>
                                        </a>
                                    </td>
                                    <td>
                                        <a class="btn btn-block btn-alterar-ocorrencia" data-id-ocorrencia="${ocorrencia.id}">
                                            <span class="glyphicon glyphicon-edit"></span>
                                        </a>
                                    </td>
                                    <td>
                                        <a class="btn btn-block btn-excluir-ocorrencia" data-id-ocorrencia="${ocorrencia.id}">
                                            <span class="glyphicon glyphicon-trash"></span>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <button id="btnIncluirOcorrencia" type="button" class="btn btn-lista col-xs-12 col-sm-4 col-sm-offset-4">Incluir</button>
            </div>

            <div class="modal fade" id="modal-excluir-ocorrencia" role="dialog">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h3 class="modal-title">Confirmação</h3>
                        </div>
                        <div class="modal-body">
                            <h4>Deseja excluir a ocorrência selecionada?</h4>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-lista btn-realiza-exclusao-ocorrencia">Excluir</button>
                            <button type="button" class="btn btn-lista" data-dismiss="modal">Fechar</button>
                        </div>
                    </div>    
                </div>
            </div>			
        </div>
    </div>
</div>