<%-- 
    Document   : preAlterar_cliente
    Created on : 30/07/2019, 15:38:49
    Author     : bocao
--%>

<%@page import="java.util.List"%>
<%@page import="br.com.hibernate.model.bean.Cliente"%>
<%@page import="br.com.hibernate.core.aplicacao.Resultado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Alterar cliente</h1>
        <% String id = request.getParameter("id");
           Resultado resultado = (Resultado) request.getAttribute("resultado");
            if(resultado == null){
                response.sendRedirect("/HibernateWeb/Clientes/AlterarCliente?acao=consultar&id=" + id);
                return;
            }
            List<Cliente> clientes = (List) resultado.getEntidades();

            if(clientes.size() == 0) {
               out.print("Erro");                   
            }else { 

                String mensagem = (String) resultado.getMensagem();

                if (mensagem != null) {
                    out.println(mensagem);
                }

               for (Cliente cliente : clientes) {
        %>
        <form action="AlterarCliente" method="POST">
            <br><br>      
            <input type="hidden" name="id" id="id" value="<%= cliente.getId() %>">
            
            <label for="nome" data-error="Informe o nome!">Nome</label>
            <input type="text" name="nome" id="nome" value="<%= cliente.getNome()%>" class="validate" required>

            <input type="submit" name="acao" value="alterar" class="btn btn-success">                                  
        </form>
                <%}
            }%>
        <a href="../index.html">Voltar</a> 
    </body>
</html>
