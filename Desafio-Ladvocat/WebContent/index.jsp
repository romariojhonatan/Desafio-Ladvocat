<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-br">
	<head>
	    <!-- Meta tags Obrigatórias -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	    <!-- Bootstrap CSS -->
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	
	    <title>Tela de Cadastro Processo</title>	
	    
	    <!-- JavaScript (Opcional) -->
	    <!-- jQuery primeiro, depois Popper.js, depois Bootstrap JS -->
	    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	    <script src="http://code.jquery.com/jquery-latest.js"></script>
	    <script>
		    $(document).ready(function() {
				$('#salvar').click(function(event) {
					$.ajax({
						type: "POST",
						url: "TelaCadastro",
						contentType: "application/x-www-form-urlencoded",
						data: $('#processoForm').serialize(),
						dataType: "application/json",
						complete: function (response) {
							if(response.status == 200) {
						    	$("#sucesso").show();
								setTimeout(function () {
									$("#sucesso").hide();
								}, 3000);
							} else {
								$("#erro").show();
								setTimeout(function () {
									$("#erro").hide();
								}, 3000);
							}
						}
					});
					event.preventDefault();
				});
				
				$('#buscar').click(function(event) {
					$.ajax({
					    type: "GET",
					    url: "TelaCadastro?proc="+ $('#numeroPublicacao').val(),
					    dataType: "application/json",
					    complete: function (response) {
						    if(response.status == 200) {
						    	var processo = JSON.parse(response.responseText);
						    	$('#numeroPublicacao').val(processo.processo.numeroPublicacao);
						    	$('#numeroPedidoInternacional').val(processo.processo.numeroPedidoInternacional);
						    	$('#dataPublicacao').val(processo.processo.dataPublicacao);
						    	$('#requerentes').val(processo.processo.requerentes);
						    	$('#titulo').val(processo.processo.titulo);
							} else if (response.status == 204){
								$("#aviso").show();
								setTimeout(function () {
								  $("#aviso").hide();
								}, 3000);
							} else {
								$("#erro").show();
								setTimeout(function () {
								  $("#erro").hide();
								}, 3000);
							}
					    }
					});
				});
				
			})
		</script>
	</head>
	<body>
		<div id="aviso" class="alert alert-warning" style="display: none">
                        <button type="button" class="close" onclick="$('#aviso').hide()">&times;</button>
                        Nº de publicação não foi encontrado!!! 
 		</div>
 		<div id="erro" class="alert alert-danger" style="display: none">
                        <button type="button" class="close" onclick="$('#erro').hide()">&times;</button>
                        Erro inesperado!!! 
 		</div>
 		<div id="sucesso" class="alert alert-success" style="display: none">
                        <button type="button" class="close" onclick="$('#sucesso').hide()">&times;</button>
                        Sucesso!!! 
 		</div>
		<h1 style="text-align: center">Tela de Cadastro Processo</h1>
		<form id="processoForm" action="TelaCadastro" method="post" style="padding: 30px;">
			<div class="form-row">
				<div class="form-group row col-md-8">
			    	<label class="col-sm-2 col-form-label">Nº de publicação</label>
				    <div class="col-sm-9">
				    	<input type="text" class="form-control" id="numeroPublicacao" name="numeroPublicacao" placeholder="Nº de publicação">
				    </div>
				    <div class="form-group col-md-1">
				    	<button type="button" id="buscar" class="btn btn-primary mb-2">Buscar</button>
			    	</div>
			    </div>
			</div>
			<div class="form-row">
				<div class="form-group row col-md-8">
			    	<label class="col-sm-2 col-form-label">Nº do pedido internacional</label>
				    <div class="col-sm-10">
				    	<input type="text" class="form-control" id="numeroPedidoInternacional" name="numeroPedidoInternacional" placeholder="Nº do pedido internacional">
				    </div>
			    </div>
			</div>
			<div class="form-row">
				<div class="form-group row col-md-8">
			    	<label class="col-sm-2 col-form-label">Data de publicação</label>
				    <div class="col-sm-10">
				    	<input type="text" class="form-control" id="dataPublicacao" name="dataPublicacao" placeholder="Data de publicação">
				    </div>
			    </div>
			</div>
			<div class="form-row">
				<div class="form-group row col-md-8">
			    	<label class="col-sm-2 col-form-label">Requerentes</label>
				    <div class="col-sm-10">
				    	<input type="text" class="form-control" id="requerentes" name="requerentes" placeholder="Requerentes">
				    </div>
			    </div>
			</div>
			<div class="form-row">
				<div class="form-group row col-md-8">
			    	<label class="col-sm-2 col-form-label">Titulo</label>
				    <div class="col-sm-10">
				    	<input type="text" class="form-control" id="titulo" name="titulo" placeholder="Titulo">
				    </div>
			    </div>
			</div>
			<button type="submit" id="salvar" class="btn btn-primary">Salvar</button>
		</form>
	</body>
</html>