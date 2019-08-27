<%-- 
    Document   : listarTodos_cliente
    Created on : 30/07/2019, 15:40:56
    Author     : bocao
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="br.com.hibernate.model.bean.Cliente"%>
<%@page import="br.com.hibernate.model.bean.EntidadeDominio"%>
<%@page import="br.com.hibernate.core.aplicacao.Resultado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listar Todos os Clientes!</h1>
        <% Resultado resultado = (Resultado) request.getAttribute("resultado");
            if(resultado != null){%>
            <table border="1">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Nome</th>
                        <th>Alterar</th>
                        <th>Deletar</th>
                    </tr>
                </thead>
                <%for(EntidadeDominio ent: resultado.getEntidades()){
                    Cliente cl = (Cliente) ent;%>
                    <tr>
                        <td><input type="radio" name="id" value="<%= cl.getId()%>" id="<%= cl.getId()%>" required></td>
                        <td><input type="text" value="<%= cl.getNome()%>"></td>
                        <td><a href="preAlterar_cliente.jsp?id=<%= cl.getId()%>">Alterar</a></td>
                        <td><a href="DeletarCliente?id=<%= cl.getId()%>&acao=deletar">Deletar</a></td>
                    </tr>
                <%}%>
            </table>
            <%}else{
                out.println("<div class='container'><h5 class='forma_redonda_vermelho  negrito center'>Erro ao listar clientes, possivelmente clientes não cadastrados</h5><div class='card-panel grey lighten-2'>");
            }%>
            <br><br>
           <a href="../index.html">Voltar</a> 
    </body>
</html>
