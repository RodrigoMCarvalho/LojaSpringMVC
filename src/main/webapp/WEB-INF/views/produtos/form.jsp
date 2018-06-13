<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>	
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>	
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Livros de Programação</title>
<style type="text/css">
input, textarea{
	margin-bottom:5px;
}
body{
	
}
</style>

<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath }/bootstrap.min.css" >
<link rel="stylesheet" href="${cssPath }/bootstrap-theme.min.css">

</head>
<body>

	<nav class="navbar navbar-inverse">
	<div class="container">
	<div class="navbar-header">
	<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-">
		<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
		<span class="icon-bar"></span> <span class="icon-bar"></span>
	</button>
	<a class="navbar-brand" href="${s:mvcUrl('HC#index').build()}">Casa do Código</a>
	</div>
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li><a href="${s:mvcUrl('PC#listar').build()}">Lista de Produtos</a></li>
			<li><a href="${s:mvcUrl('PC#form').build()}">Cadastro de Produtos</a></li>
		</ul>
		
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#">
				<security:authentication property="principal" var="usuario"/>
				Olá, ${usuario}
			</a></li>
		</ul>
					
	</div>
	<!-- /.navbar-collapse -->
	</div>
	</nav>

	<div class="container">
	<h1>Cadastro de produtos</h1>
	<form:form action="${s:mvcUrl('PC#gravar').build()}" method="POST" 
	commandName="produto" enctype="multipart/form-data">
	
		<div class="form-group">
			<label>Título</label>
			<form:input path="titulo" cssClass="form-control"/>
			<form:errors path="titulo" cssStyle="color:red; font-weight:bold"/>
		</div>
		<div class="form-group">
			<label>Descrição</label>
			<form:textarea path="descricao" cssClass="form-control"/>
			<form:errors path="descricao" cssStyle="color:red; font-weight:bold"/>
		</div>
		<div class="form-group">
			<label>Páginas</label>
			<form:input path="paginas" cssClass="form-control"/>
			<form:errors path="paginas" cssStyle="color:red; font-weight:bold"/>
		</div>
		<div class="form-group">
			<label>Data de lançamento</label>
			<form:input path="dataLancamento" cssClass="form-control"/>
			<form:errors path="dataLancamento" cssStyle="color:red; font-weight:bold"/>
		</div>
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
		<div>
			<label>${tipoPreco}</label>
			<form:input path="precos[${status.index}].valor" cssClass="form-control"/> <!-- ex: precos[0].valor -->
			<form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}"/>
		</div>	
		</c:forEach>
		
		<div class="form-group">
			<label>Sumário</label>
			<input type="file" name="sumario" class="form-control"/>
		</div>
		<button title="submit" class="btn btn-primary">Cadastrar</button>
	</form:form>
	
	</div>

</body>