<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Livros de Programação</title>
<style type="text/css">
input, textarea {
	margin-bottom: 5px;
}

body {
	
}
</style>

<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath }/bootstrap.min.css">
<link rel="stylesheet" href="${cssPath }/bootstrap-theme.min.css">

</head>
<body>
	<div class="modal-dialog">
		<div class="loginmodal-container">
				<h1>Login da Casa do Código</h1>
				<form:form servletRelativeAction="/login" method="POST"
					commandName="produto" enctype="multipart/form-data">

					<div class="form-group">
						<label>E-mail</label> <input type="text" name="username"
							class="form-control" />
					</div>
					<div class="form-group">
						<label>Senha</label> <input type="password" name="password"
							class="form-control" />

					</div>
					<button title="submit" class="btn btn-primary">Logar</button>
				</form:form>
			</div>
	</div>

</body>