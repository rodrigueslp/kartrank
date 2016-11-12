<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rank</title>
</head>
<body>
	<h1>Rank Corrida Kart</h1>
	<table>
		<tr>
			<td>Posição</td>
			<td>Código</td>
			<td>Piloto</td>
			<td>Voltas Completadas</td>
			<td>Tempo Total de Prova</td>
		</tr>
		<c:forEach var="r" items="${rank}">
			<tr>
				<td>${r.getPosicao()}</td>
				<td>${r.getPiloto().getId()}</td>
				<td>${r.getPiloto().getNome()}</td>
				<td>${r.getTotalVoltas()}</td>
				<td>${r.getTempoTotal()}</td>
			</tr>
		</c:forEach>
	</table>
	
	<h3>Melhor Média de Cada Piloto</h3>
	<table>
		<tr>
			<td>Código</td>
			<td>Piloto</td>
			<td>Melhor Volta</td>
		</tr>
		<c:forEach var="r" items="${rank}">
			<tr>
				<td>${r.getPiloto().getId()}</td>
				<td>${r.getPiloto().getNome()}</td>
				<td>${r.getMelhorVoltaCadaPiloto()}</td>
			</tr>
		</c:forEach>
	</table>
	
	<h3>Melhor Volta da Corrida</h3>
	<table>
		<tr>
			<td>Piloto</td>
			<td>Tempo</td>
			<td>Volta</td>
		</tr>
		<tr>
			<td>${melhorVolta.getPiloto().getNome()}</td>
			<td>${melhorVolta.getTempo()}</td>
			<td>${melhorVolta.getNumeroVolta()}</td>
		</tr>
	</table>
	
	<h3>Velocidade Média de Cada Piloto</h3>
	<table>
		<tr>
			<td>Código</td>
			<td>Piloto</td>
			<td>Velocidade Média</td>
		</tr>
		<c:forEach var="r" items="${rank}">
			<tr>
				<td>${r.getPiloto().getId()}</td>
				<td>${r.getPiloto().getNome()}</td>
				<td>${r.getVelocidadeMediaNaCorrida()}</td>
			</tr>
		</c:forEach>
	</table>
	
	<h3>Quanto Tempo Cada Piloto Chegou Após o Vencedor</h3>
	<table>
		<tr>
			<td>Código</td>
			<td>Piloto</td>
			<td>Velocidade Média</td>
		</tr>
		<c:forEach var="r" items="${rankAposVencedor}">
			<tr>
				<td>${r.getPiloto().getId()}</td>
				<td>${r.getPiloto().getNome()}</td>
				<td>${r.getTempoAposVencedor()}</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>