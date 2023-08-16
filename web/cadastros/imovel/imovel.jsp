<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

    <h2>Im�vel</h2>
    <table id="datatable" class="display">
        <thead>
            <tr>
                <th align="left">ID</th>
                <th align="left">Descri��o</th>
                <th align="left">Endere�o</th>
                <th align="left">Aluguel</th>
                <th align="right"></th>
                <th align="right"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="imovel" items="${imoveis}">
                <tr>
                    <td align="left">${imovel.idImovel}</td>
                    <td align="left">${imovel.descricao}</td>
                    <td align="left">${imovel.endereco}</td>
                    <td align="left">${imovel.valorAluguel}</td>
                    <td align="center">
                        <a href="${pageContext.request.contextPath}/ImovelExcluir?idImovel=${imovel.idImovel}">Excluir</a>
                    </td>
                    <td align="center">
                        <a href="${pageContext.request.contextPath}/ImovelCarregar?idImovel=${imovel.idImovel}">Alterar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <div align="center">
        <a href="${pageContext.request.contextPath}/ImovelNovo">Novo</a>
        <a href="index.jsp">Voltar � P�gina Prinipal</a>
    </div>
    
    <script>
           $(document).ready(function() {
               console.log('entrei ready');
               $('#datatable').DataTable({
                   "oLanguage": {
                       "sProcessing": "Processando...",
                       "sLengthMenu": "Mostrar _MENU_ registros",
                       "sZeroRecords": "Nenhum registro encontrado",
                       "sInfo": "Mostrando de _START_ at� _END_ de _TOTAL_ registros",
                       "sInfoEmpty": "Mostrando 0 at� 0 de 0 registros",
                       "sInfoFiltered": "",
                       "sInfoPostFix": "",
                       "sSearch": "Buscar",
                       "sUrl": "",
                       "oPaginate": {
                           "sFirst": "Primeiro",
                           "sPrevious": "Anterior",
                           "sNext": "Seguinte",
                           "sLast": "�ltimo"
                       }
                   }
               });
           });
    </script>
<%@include file="/footer.jsp" %>    