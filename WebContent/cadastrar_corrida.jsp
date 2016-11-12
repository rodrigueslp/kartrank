<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrar Corrida</title>
</head>
<body>
	
	<h3>Cadastro do Log da Corrida de Kart</h3>

	<form action="criar_rank" method="post" enctype="multipart/form-data">
	    <input required="true" type="file" name="arquivo_log" />
	    <input type="submit" value="Enviar!" />
	</form>
</body>
</html>