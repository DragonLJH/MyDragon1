<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.dragon.common.StringConstant"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css" />
	<link rel="icon" href="img/LOGO16mm.png">
<script src="jquery/jquery-1.11.0.js" type="text/javascript"
	charset="utf-8"></script>
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"
	charset="utf-8"></script>
<link rel="stylesheet" href="css/upload.css" />
<title>JSP Page</title>
</head>

<body>
	<div class="container-fluid row">
		<form class="form-horizontal col-md-offset-3 col-md-6" method="post"
			action="upload" enctype="multipart/form-data">
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">name</label>
				<div class="col-sm-10">
					<input type="text" name="name" class="form-control" id="name"
						placeholder="name" required>
				</div>
			</div>
			<div class="form-group">
				<label for="author" class="col-sm-2 control-label">author</label>
				<div class="col-sm-10">
					<input type="text" name="author" class="form-control" id="author"
						placeholder="author" required>
				</div>
			</div>
			<div class="form-group">
				<label for="summary" class="col-sm-2 control-label">summary</label>
				<div class="col-sm-10">
					<textarea id="summary" name="summary" class="form-control" rows="3"
						placeholder="summary" required></textarea>
				</div>
			</div>
			<div class="form-group">
				<label for="quantity" class="col-sm-2 control-label">quantity</label>
				<div class="col-sm-10">
					<input type="text" name="quantity" class="form-control" id="quantity"
						placeholder="quantity" required>
				</div>
			</div>
			<div class="form-group">
				<label for="type" class="col-sm-2 control-label">type</label>
				<div class="col-sm-10">
					<input type="text" name="type" class="form-control" id="type"
						placeholder="type" required>
				</div>
			</div>
			<div class="form-group">
				<label for="images" class="col-sm-2 control-label">images</label>
				<div class="col-sm-10">
					<input type="file" name="images" id="images" placeholder="images" required>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Upload</button>
				</div>
			</div>
			<%
			  String message = (String) request.getAttribute(StringConstant.REQ_UPLOAD);
			  if (message != null && !message.isEmpty()) {
			%>
			<h1><p><%=message%></p></h1>
			<%
			  }
			%>
		</form>
			
	</div>

</body>

</html>