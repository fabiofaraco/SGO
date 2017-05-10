<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Menu Principal</title>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>

        <link href=<c:url value="/resources/bootstrap/css/bootstrap.min.css"/> rel="stylesheet"/>
        <link href=<c:url value="/resources/css/mensagem.css"/> rel="stylesheet"/>
        <link href=<c:url value="/resources/css/cadastro.css"/> rel="stylesheet"/>
        <link href=<c:url value="/resources/css/menu-principal.css"/> rel="stylesheet"/>
        <link href=<c:url value="/resources/css/sb-admin.css"/> rel="stylesheet"/>
        <link href=<c:url value="/resources/css/morris.css"/> rel="stylesheet"/>
        <link href=<c:url value="/resources/font-awesome/css/font-awesome.min.css"/> rel="stylesheet"/>
    </head>
    <body>
        <form id="frm" name="frm" method="post">
            <input type="hidden" id="contextPath" name="contextPath" value="${pageContext.request.contextPath}" />

            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.html">Gerenciador de Ocorrências</a>
                </div>
                <!-- Top Menu Items -->
                <ul class="nav navbar-right top-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Fábio Faraco <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a id="aLogout" href="#"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    <ul class="nav navbar-nav side-nav">
                        <li id="liMenu">
                            <a href="#"><i class="fa fa-fw fa-home"></i> Home</a>
                        </li>
                        <li id="liUsuario">
                            <a href="#" id="aUsuario"><i class="fa fa-fw fa-user"></i> Usuários</a>
                        </li>
                        <li id="liRequerente">
                            <a href="#" id="aRequerente"><i class="fa fa-fw fa-users"></i> Requerentes</a>
                        </li>
                        <li id="liOcorrencia">
                                <a href="#" id="aOcorrencia"><i class="fa fa-fw fa-ambulance"></i> Ocorrências</a>
                        </li>
                        <li id="liProtocolo">
                            <a href="${pageContext.request.contextPath}/protocolo/lista"><i class="fa fa-fw fa-folder"></i> Protocolos</a>
                        </li>
                        <li>
                            <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-pie-chart"></i> Relatórios <i class="fa fa-fw fa-caret-down"></i></a>
                            <ul id="demo" class="collapse">
                                <li>
                                    <a href="#">Certidão</a>
                                </li>
                                <li>
                                    <a href="#">Dropdown Item</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="blank-page.html"><i class="fa fa-fw fa-file"></i> Blank Page</a>
                        </li>
                    </ul>
                </div>
            </nav>
            <div id="conteudo"></div>
        </form>
    </body>

    <script src=<c:url value="/resources/js/jquery/jquery-2.2.2.min.js"/>></script>
    <script src=<c:url value="/resources/js/jquery/jquery.mask.min.js"/>></script>
    <script src=<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>></script>
    <script src=<c:url value="/resources/js/mensagem.js"/>></script>
    <script src=<c:url value="/resources/js/util.js"/>></script>
    <script src=<c:url value="/resources/js/menu.js"/>></script>
    <script src=<c:url value="/resources/js/cadastroUsuario.js"/>></script>
    <script src=<c:url value="/resources/js/tabela.js"/>></script>
    <script src=<c:url value="/resources/js/listaUsuario.js"/>></script>
    <script src=<c:url value="/resources/js/listaRequerente.js"/>></script>
    <script src=<c:url value="/resources/js/listaOcorrencia.js"/>></script>
    <script src=<c:url value="/resources/datatable/js/jquery.dataTables.min.js"/>></script>
    <script src=<c:url value="/resources/datatable/js/dataTables.bootstrap.min.js"/>></script>
    <script src=<c:url value="/resources/datatable/js/dataTables.responsive.min.js"/>></script>
    <script src=<c:url value="/resources/datatable/js/responsive.bootstrap.min.js"/>></script>
</html>