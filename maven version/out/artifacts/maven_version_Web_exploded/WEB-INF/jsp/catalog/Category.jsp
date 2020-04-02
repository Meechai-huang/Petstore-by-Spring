<%@ include file="../common/IncludeTop.jsp"%>
<div id="BackLink"><a href="Main">Return to Main Menu</a></div>
<div id="Catalog">
<h2>${sessionScope.category.name}</h2>
<table>
	<tr>
		<th>Product ID</th>
		<th>Name</th>
	</tr>
	<c:forEach var="product" items="${sessionScope.productList}">
		<tr>
			<td>
				<a href="Product?productId=${product.productId}">${product.productId}</a>
			</td>
			<td>${product.name}</td>
		</tr>
	</c:forEach>
</table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>


