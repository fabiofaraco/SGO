<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Lista de Requerentes</title>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>

        <link href=<c:url value="/resources/bootstrap/css/bootstrap.min.css"/> rel="stylesheet"/>
        <link href=<c:url value="/resources/css/mensagem.css"/> rel="stylesheet"/>
        <link href=<c:url value="/resources/css/lista.css"/> rel="stylesheet"/>
        <link href=<c:url value="/resources/datatable/css/dataTables.bootstrap.min.css"/> rel="stylesheet"/>		

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
                        <h1>Lista de Requerentes</h1>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="table-responsive">          
                                <table class="table table-striped table-hover data-table">
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
                                                <td class="col-md-4">${requerente.nome} ${requerente.sobrenome}</td>
                                                <td class="col-md-3">${requerente.cpf}</td>
                                                <td class="col-md-2">${requerente.email}</td>
                                                <td class="col-md-1">
                                                    <a class="btn btn-block btn-visualizar-requerente" data-id-requerente="${requerente.id}">
                                                        <span class="glyphicon glyphicon-search"></span>
                                                    </a>
                                                </td>
                                                <td class="col-md-1">
                                                    <a class="btn btn-block btn-alterar-requerente" data-id-requerente="${requerente.id}">
                                                        <span class="glyphicon glyphicon-edit"></span>
                                                    </a>
                                                </td>
                                                <td class="col-md-1">
                                                    <a class="btn btn-block btn-excluir-requerente" data-id-requerente="${requerente.id}">
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
        </form>
    </body>

    <script src=<c:url value="/resources/js/jquery/jquery-2.2.2.min.js"/>></script>
    <script src=<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>></script>
    <script src=<c:url value="/resources/js/tabela.js"/>></script>
    <script src=<c:url value="/resources/js/listaRequerente.js"/>></script>
    <script src=<c:url value="/resources/js/util.js"/>></script>
    <script src=<c:url value="/resources/datatable/js/jquery.dataTables.min.js"/>></script>
    <script src=<c:url value="/resources/datatable/js/dataTables.bootstrap.min.js"/>></script>
</html
