/* Busca CEP através da API do ViaCEP */
$(document).ready(function() {

            function limpa_formulário_cep() {
                // Limpa valores do formulário de cep.
                $("#logradouro").val("");
                $("#bairro").val("");
                $("#cidade").val("");
                $("#uf").val("");
            }
            
            //Quando o campo cep perde o foco.
            $("#cep").blur(function() {

                //Nova variável "cep" somente com dígitos.
                var cep = $(this).val().replace(/\D/g, '');

                //Verifica se campo cep possui valor informado.
                if (cep != "") {

                    //Expressão regular para validar o CEP.
                    var validacep = /^[0-9]{8}$/;

                    //Valida o formato do CEP.
                    if(validacep.test(cep)) {

                        //Preenche os campos com "..." enquanto consulta webservice.
                        $("#logradouro").val("...");
                        $("#bairro").val("...");
                        $("#cidade").val("...");
                        $("#uf").val("...");

                        //Consulta o webservice viacep.com.br/
                        $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?",
                        		function(dados) {

                            if (!("erro" in dados)) {
                                //Atualiza os campos com os valores da consulta.
                                $("#logradouro").val(dados.logradouro);
                                $("#bairro").val(dados.bairro);
                                $("#cidade").val(dados.localidade);
                                $("#uf").val(dados.uf);
                            } //end if.
                            else {
                                //CEP pesquisado não foi encontrado.
                                limpa_formulário_cep();
                                alert("CEP não encontrado.");
                            }
                        });
                    } //end if.
                    else {
                        //cep é inválido.
                        limpa_formulário_cep();
                        alert("Formato de CEP inválido.");
                    }
                } //end if.
                else {
                    //cep sem valor, limpa formulário.
                    limpa_formulário_cep();
                }
            });
        });

/* Tooltip */
$(function tooltip() {
  $('[data-toggle="tooltip"]').tooltip();
});

$(function () {
    $('[data-toggle="popover"]').popover();
});

/* Pegar elementos do Serviço Voluntário */
$(document).ready(function () {
	var emailServicoVoluntario = $("#emailOngDetalhesServicoVoluntario").text();
    $("#emailParaDetalhesServicoVoluntario").val(emailServicoVoluntario);
    var assuntoDetalhesServicoVoluntario = $("#nomeDetalhesServicoVoluntario").text();
    $("#assuntoDetalhesServicoVoluntario")
    	.val("Serviço Voluntário: "+assuntoDetalhesServicoVoluntario);
    
    var emailPedidoDoacao = $("#emailOngDetalhesPedidoDoador").text();
    $("#emailParaDetalhesPedidoDoacao").val(emailPedidoDoacao);
    var assuntoDetalhesPedidoDoador = $("#nomeDetalhesPedidoDoador").text();
    $("#assuntoDetalhesPedidoDoador")
    	.val("Pedido de Doação: "+assuntoDetalhesPedidoDoador);
});

/* Máscaras para os campos */
$(document).ready(function () {
    $("#cep").mask('00000-000');
    $("#cnpj").mask('00.000.000/0000-00', {reverse: true});
    $('#telefone').inputmask({
    	  mask: ['(99) 9999-9999', '(99) 99999-9999'],
    	  keepStatic: true
    });
    $("#whatsapp").inputmask({
  	  mask: ['+99 (99) 9999-9999'],
	  keepStatic: true
    });
});

/* Sidebar Vertival */
$(document).ready(function() {
	$("#sidebar").mCustomScrollbar({
		theme : "minimal"
	});
	
	$('#sidebarCollapse').on('click', function() {
		$('#sidebar, #content').toggleClass('active');
		$('.collapse.in').toggleClass('in');
		$('a[aria-expanded=true]').attr('aria-expanded', 'false');
	});
});

