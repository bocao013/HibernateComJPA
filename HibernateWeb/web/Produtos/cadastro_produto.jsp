<%-- 
    Document   : cadastro_produto
    Created on : 24/07/2019, 16:53:41
    Author     : bocao
--%>

<%@page import="br.com.hibernate.core.aplicacao.Resultado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Produtos</title>
    </head>
    <body>
        <h1>Cadastro de Produtos</h1>
        <%
                Resultado resultado = (Resultado) request.getAttribute("resultado");
                if(resultado != null) {
                        if(!resultado.getMensagem().isEmpty()) {
                                out.print("<div class='container'><h5 class='forma_redonda_vermelho  negrito center'>Atenção com os campos</h5><div class='card-panel grey lighten-2'>");
                                out.print(resultado.getMensagem());
                                out.print("</div></div><br>");
                        }
                }
            %>
        <form action="SalvarProdutos" name="acao" value="salvar">
            <label for="Nome">Nome</label>
            <input type="text" name="nome" id="nome">
            
            <label for="Descricao">Descrição</label>
            <input type="text" name="descricao" id="descricao">
            
            <label for="Quantidade">Quantidade</label>
            <input type="text" name="quantidade" id="quantidade">
            
            <button type="submit" name="acao" value="salvar">Salvar</button>
        </form>
        <a href="../index.html">Voltar</a>
    </body>
</html>
