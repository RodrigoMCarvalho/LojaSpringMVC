<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>	
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Livros de Programação</title>
<style type="text/css">
input, textarea{
	margin-bottom:5px;
}
</style>
</head>
<body>
	<form:form action="${s:mvcUrl('PC#gravar').build()}" method="POST" commandName="produto">
		<div>
			<label>Título</label>
			<input type="text" name="titulo"/> 
			<form:errors path="titulo" cssStyle="color:red; font-weight:bold"/>
		</div>
		<div>
			<label>Descrição</label>
			<textarea rows="10" cols="20" name="descricao"></textarea>
			<form:errors path="descricao" cssStyle="color:red; font-weight:bold"/>
		</div>
		<div>
			<label>Páginas</label>
			<input type="text" name="paginas"/>
			<form:errors path="paginas" cssStyle="color:red; font-weight:bold"/>
		</div>
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
		<div>
			<label>${tipoPreco}</label>
			<input type="text" name="precos[${status.index}].valor"/> <!-- ex: precos[0].valor -->
			<input type="hidden" name="precos[${status.index}].tipo" value="${tipoPreco}"/>
		</div>	
		</c:forEach>
		<button title="submit">Cadastrar</button>
	</form:form>
	
	<div style="color:red; font-size:18px; font-weight:bold ">
		
		
		
	</div>

</body>