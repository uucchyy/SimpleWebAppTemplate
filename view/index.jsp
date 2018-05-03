<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="./inc/head.jsp"></jsp:include>
<title></title>
</head>
<body>

	<jsp:include page="./inc/header.jsp"></jsp:include>

	<!-- .container is main centered wrapper -->
	<div class="container">
		<div class="row">
			<div class="eight columns">
				
				<section class="sec">
					<div class="sec_title">
						<h1>
							<span>About</span>
						</h1>
					</div>
					<div class="sec_body">
					</div>
				</section>

			</div>
			<div class="four columns">
				<jsp:include page="./inc/right-column.jsp"></jsp:include>
			</div>
		</div>

	</div>

	<jsp:include page="./inc/footer.jsp"></jsp:include>
	
</body>
</html>