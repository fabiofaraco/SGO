<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div id="wrapper">

    <div class="container-fluid">
        <div class="page-header">
            <h1>Lista de Requerentes</h1>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="table-responsive">          
                    <table class="table nowrap data-table" width="100%" cellspacing="0" >
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>CPF</th>
                                <th>Email</th>
                                <th></th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requerentes}" var="requerente">
                                <tr class="something">
                                    <td>${requerente.nome} ${requerente.sobrenome}</td>
                                    <td>${requerente.cpf}</td>
                                    <td>${requerente.email}</td>
                                    <td>
                                        <a class="btn btn-block btn-visualizar" data-id-requerente="${requerente.id}">
                                            <span class="glyphicon glyphicon-search"></span>
                                        </a>
                                    </td>
                                    <td>
                                        <a class="btn btn-block btn-alterar" data-id-requerente="${requerente.id}">
                                            <span class="glyphicon glyphicon-edit"></span>
                                        </a>
                                    </td>
                                    <td>
                                        <a class="btn btn-block btn-excluir" data-id-requerente="${requerente.id}" data-toggle="modal">
                                            <span class="glyphicon glyphicon-trash"></span>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <button id="btnIncluirRequerente" type="button" class="btn btn-lista col-xs-12 col-sm-4 col-sm-offset-4">Incluir</button>
            </div>

            <div class="modal fade" id="modal-excluir-requerente" role="dialog">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h3 class="modal-title">Confirmação</h3>
                        </div>
                        <div class="modal-body">
                            <h4>Deseja excluir o requerente selecionado?</h4>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-lista btn-realiza-exclusao-requerente">Excluir</button>
                            <button type="button" class="btn btn-lista" data-dismiss="modal">Fechar</button>
                        </div>
                    </div>    
                </div>
            </div>		
        </div>
    </div>
</div>

