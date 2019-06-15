<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page
	import="com.dragon.common.Management,com.dragon.common.StringConstant"%>
<%@page import="java.util.List,java.util.ArrayList"%>
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
<title></title>
<style type="text/css">
.searchL {
	position: relative;
}

.onSearchButton {
	position: absolute;
	right: 20px;
	bottom: 10px;
}

.onSearchButton>a {
	margin-right: 50px;
}

.container-fluid {
	background-color: rgba(0, 0, 255, 0.3);
}

.container-fluid>.searchL {
	width: 80%;
	border: solid 3px #eee;
	margin: 0 auto;
}

.container-fluid>.contant>.tab-content>.tab-pane>a>img {
	padding: 10px;
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
.page-header > .loginregister{ position: absolute;right: 200px;top: 0;}
</style>
</head>

<body>
	<div class="container-fluid">
		<div class="top page-header">
			<h1>
				UPCOMING<small> 图书管理系统</small>
			</h1>
			<div class="loginregister">
				<a class="btn btn-success" href="LoginRegist.html">登录</a>
			</div>
			<div class="del">
				<span><%=session.getAttribute(StringConstant.SESSION_STUDENTID) %></span>
				<a class="btn btn-danger" href="del?del=del">退出登录</a>
			</div>
		</div>
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="index.html">UPCOMING</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav nav-tabs" role="tablist">
						<li class="active"><a href="#home" aria-controls="home"
							role="tab" data-toggle="tab">文学类</a></li>
						<li><a href="#profile" aria-controls="profile" role="tab"
							data-toggle="tab">计算机</a></li>
						<li><a href="#messages" aria-controls="messages" role="tab"
							data-toggle="tab">移动通信</a></li>
						<li><a href="#settings" aria-controls="settings" role="tab"
							data-toggle="tab">经济管理</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<form class="navbar-form navbar-left" action="management">
							<label for="selectName"> <select class="form-control"
								name="optionName">
									<option>name</option>
									<option>author</option>
									<option>booknumber</option>
							</select>
							</label>
							<div class="form-group">
								<input type="text" class="form-control" name="selectName"
									id="selectName" placeholder="Search">
							</div>
							<button type="submit" class="btn btn-default">Submit</button>
						</form>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
		<%
			List<Management> listManagement = (List) request.getAttribute(StringConstant.REQ_MANAGEMENT);
			if (listManagement != null) {
				for (int i = 0; i < listManagement.size(); i++) {
					Management management = (Management) listManagement.get(i);
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
						<th class="col-md-6">简介</th>
						<th class="col-md-1">库存量</th>
					</tr>
					<tr class="row">
						<td class="col-md-1"><%=management.getName()%></td>
						<td class="col-md-1"><%=management.getAuthor()%></td>
						<td class="col-md-1"><%=management.getNumber()%></td>
						<td class="col-md-7"><%=management.getSummary()%></td>
						<td class="col-md-1"><%=management.getQuantity()%></td>
					</tr>
				</table>
			</div>
			<div class="onSearchButton">
				<a
					href="management?optionName=name&selectName=<%=management.getName()%>&skip=skip"
					class="btn btn-primary" role="button">评 价</a> <a
					href="borrowrecord?booknumber=<%=management.getNumber()%>"
					class="btn btn-success" role="button">预借书</a>
			</div>


		</div>
		<%
			}
			}
		%>
		<div class="contant ">
			<!-- Tab panes -->
			<div class="searchBook"></div>
			<%
				List<Management> ListManagementAllName = (List) request.getAttribute(StringConstant.REQ_ALLMANAGEMENTNAME);
				List<Management> wenxueList = new ArrayList<Management>();
				List<Management> computerList = new ArrayList<Management>();
				List<Management> yidongList = new ArrayList<Management>();
				List<Management> jingjiList = new ArrayList<Management>();

				for (int i = 0; i < ListManagementAllName.size(); i++) {
					Management ListManagement = (Management) ListManagementAllName.get(i);
					if (ListManagement.getType().equals("文学类")) {
						wenxueList.add(ListManagement);
					} else if (ListManagement.getType().equals("计算机")) {
						computerList.add(ListManagement);
					} else if (ListManagement.getType().equals("移动通信")) {
						yidongList.add(ListManagement);
					} else if (ListManagement.getType().equals("经济管理")) {
						jingjiList.add(ListManagement);
					}
				}
			%>
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active row" id="home">
					<h3>文学类</h3>

					<%
						/* List ListManagementAllName = (List) request
						    .getAttribute(StringConstant.REQ_ALLMANAGEMENTNAME); */
						for (int i = 0; i < wenxueList.size(); i++) {
							Management wenxuetManagement = (Management) wenxueList.get(i);
					%>

					<a
						href="management?optionName=name&selectName=<%=wenxuetManagement.getName()%>">
						<img class="col-md-offset-1 col-md-1"
						src="/MyDragonBookimages/<%=wenxuetManagement.getImages()%>">
					</a> <a
						href="management?optionName=name&selectName=<%=wenxuetManagement.getName()%>">
						<img class="col-md-offset-1 col-md-1"
						src="/MyDragonBookimages/<%=wenxuetManagement.getImages()%>">
					</a>
					<%
						}
					%>

				</div>
				<div role="tabpanel" class="tab-pane" id="profile">
					<h3>计算机</h3>

					<%
						/* List ListManagementAllName = (List) request
						    .getAttribute(StringConstant.REQ_ALLMANAGEMENTNAME); */

						for (int i = 0; i < computerList.size(); i++) {
							Management computerManagement = (Management) computerList.get(i);
					%>

					<a
						href="management?optionName=name&selectName=<%=computerManagement.getName()%>">
						<img class="col-md-offset-1 col-md-1"
						src="/MyDragonBookimages/<%=computerManagement.getImages()%>">
					</a> <a
						href="management?optionName=name&selectName=<%=computerManagement.getName()%>">
						<img class="col-md-offset-1 col-md-1"
						src="/MyDragonBookimages/<%=computerManagement.getImages()%>">
					</a>
					<%
						}
					%>
				</div>
				<div role="tabpanel" class="tab-pane" id="messages">
					<h3>移动通信</h3>

					<%
						/* List ListManagementAllName = (List) request
						    .getAttribute(StringConstant.REQ_ALLMANAGEMENTNAME); */

						for (int i = 0; i < yidongList.size(); i++) {
							Management yidongManagement = (Management) yidongList.get(i);
					%>

					<a
						href="management?optionName=name&selectName=<%=yidongManagement.getName()%>">
						<img class="col-md-offset-1 col-md-1"
						src="/MyDragonBookimages/<%=yidongManagement.getImages()%>">
					</a> <a
						href="management?optionName=name&selectName=<%=yidongManagement.getName()%>">
						<img class="col-md-offset-1 col-md-1"
						src="/MyDragonBookimages/<%=yidongManagement.getImages()%>">
					</a>
					<%
						}
					%>
				</div>
				<div role="tabpanel" class="tab-pane" id="settings">
					<h3>经济管理</h3>

					<%
						/* List ListManagementAllName = (List) request
						    .getAttribute(StringConstant.REQ_ALLMANAGEMENTNAME); */

						for (int i = 0; i < jingjiList.size(); i++) {
							Management jingjiManagement = (Management) jingjiList.get(i);
					%>

					<a
						href="management?optionName=name&selectName=<%=jingjiManagement.getName()%>">
						<img class="col-md-offset-1 col-md-1"
						src="/MyDragonBookimages/<%=jingjiManagement.getImages()%>">
					</a> <a
						href="management?optionName=name&selectName=<%=jingjiManagement.getName()%>">
						<img class="col-md-offset-1 col-md-1"
						src="/MyDragonBookimages/<%=jingjiManagement.getImages()%>">
					</a>
					<%
						}
					%>
				</div>
			</div>

		</div>

	</div>

</body>

</html>