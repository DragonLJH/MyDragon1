<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page
	import="com.dragon.common.StringConstant,com.dragon.common.Management,com.dragon.common.Discuss,java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="img/LOGO16mm.png">
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css" />
<script src="jquery/jquery-1.11.0.js" type="text/javascript"
	charset="utf-8"></script>
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="js/discuss.js" type="text/javascript" charset="utf-8"></script>
<title></title>
<style type="text/css">
a, a:hover, a:focus {
	color: #000;
	text-decoration: none
}

.doDiscuss {
	border: solid 1px #000;
	margin-top: 50px;
	margin-bottom: 50px;
	padding-top: 20px;
	padding-bottom: 20px;
}

.doDiscuss>form input, .doDiscuss>form textarea {
	margin-bottom: 20px;
}

.button {
	float: right;
}

.page-header {
	position: relative;
}

.page-header>.del {
	position: absolute;
	right: 0;
	top: 0;
}
.page-header>.del >span{
	margin-right:10px;
	color:#00f;
	cursor:pointer;
}
.page-header>.loginregister {
	position: absolute;
	right: 200px;
	top: 0;
}
</style>
</head>

<body>
	<div class="container-fluid">
		<div class="top page-header">
			<h1>
				<a href="index.html">UPCOMING</a><small> 图书管理系统</small>
			</h1>
			<div class="loginregister">

				<a class="btn btn-success" href="LoginRegist.html">登录</a>
			</div>
			<div class="del">
			
				<span><%=session.getAttribute(StringConstant.SESSION_STUDENTID)%></span>
				<a class="btn btn-danger" href="del?del=del">退出登录</a>
			</div>
		</div>
		<div class="contant">

			<div class="bookContant">
				<%
					int booknumber = 0;
					String selectName = "";
					List<Management> listManagement = (List) request.getAttribute(StringConstant.REQ_MANAGEMENT);
					if (listManagement != null) {
						for (int i = 0; i < listManagement.size(); i++) {
							Management management = (Management) listManagement.get(i);

							booknumber = management.getNumber();
							selectName = management.getName();
				%>
				<div class="searchL row">
					<img class="col-md-2"
						src="/MyDragonBookimages/<%=management.getImages()%>" />
					<div class="col-md-10">
						<table class="table">
							<tr class="row">
								<th class="col-md-1">书名</th>
								<th class="col-md-1">作者</th>
								<th class="col-md-1">编号</th>
								<th class="col-md-7">简介</th>
							</tr>
							<tr class="row">
								<td class="col-md-1"><%=management.getName()%></td>
								<td class="col-md-1"><%=management.getAuthor()%></td>
								<td class="col-md-1"><%=management.getNumber()%></td>
								<td class="col-md-7"><%=management.getSummary()%></td>
							</tr>
						</table>
					</div>

				</div>
				<%
					}
					}
				%>
				<div class="doDiscuss container">
					<form action="discuss" method="post">
						<div class="form-group">
							<label for="discussTextarea">discussTextarea</label> <input
								id="selectName" name="selectName" style="display: none"
								value="<%=selectName%>"> <input id="booknumber"
								name="booknumber" style="display: none" value="<%=booknumber%>">
							<textarea id="discussTextarea" name="summary"
								class="form-control" rows="3"></textarea>
						</div>
						<button type="submit" class="button btn btn-default">Submit</button>
					</form>
				</div>
				<div class="discuss row">
					<div class="col-md-offset-2 col-md-8">
						<ul class="list-group">
							<%
								List discussList = (List) request.getAttribute(StringConstant.REQ_DISCUSS);
								for (int i = 0; i < discussList.size(); i++) {
									Discuss discuss = (Discuss) discussList.get(i);
							%>
							<li class="list-group-item">
								<table class="table table-striped">
									<thead>
										<tr>
											<th><%=discuss.getStudentId()%></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><%=discuss.getDiscuss()%></td>
										</tr>
									</tbody>
								</table>
							</li>
							<%
								}
							%>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>