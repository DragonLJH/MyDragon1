<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page
	import="java.util.List,com.dragon.common.StringConstant,com.dragon.common.Management,com.dragon.common.LoginRegister"%>
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
<link rel="stylesheet" type="text/css"
	href="font-awesome-4.7.0/css/font-awesome.css" />
<link rel="stylesheet" type="text/css" href="css/user.css" />
<script src="js/user.js" type="text/javascript" charset="utf-8"></script>
<title></title>
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
		<div class="contant row">
			<div class="left col-md-3">
				<ul class="nav nav-tabs row" role="tablist">
					<li role="presentation" class="active col-lg-12" id="nav-home">
						<a href="#home" aria-controls="home" role="tab" data-toggle="tab">
							<h4>
								<i class="fa fa-user-circle"></i>USER
							</h4>
					</a>
					</li>
					<li role="presentation" class="col-md-12" id="nav-profile"><a
						href="#profile" aria-controls="profile" role="tab"
						data-toggle="tab">
							<h4>
								<i class="fa fa-bookmark"></i>已借书籍
							</h4>
					</a></li>
					<li role="presentation" class="col-md-12"><a href="#messages"
						aria-controls="messages" role="tab" data-toggle="tab">
							<h4>
								<i class="fa fa-book"></i>已还书籍
							</h4>
					</a></li>
				</ul>
			</div>
			<div class="right col-md-9">
				<!-- Tab panes -->
				<div class="tab-content ">
					<div role="tabpanel" class="tab-pane active row " id="home">
						<div class="col-md-offset-2 col-md-10">
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="home-contant">
									<div class="col-md-6">
										<table class="table table-hover ">
											<%
												LoginRegister loginRegister = (LoginRegister) request.getAttribute(StringConstant.REQ_LOGINREGISTER);
											%>
											<thead>
												<tr>
													<h3>
														<i class="fa fa-user-circle"></i>USER
													</h3>
													</a>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>
														<h3>头像</h3>
													</td>
													<td>
														<div class="row">
															<div
																class="col-xs-6 col-xs-offset-3 col-md-offset-3 col-md-6">
																<a href="#" class="thumbnail"><img
																	class=" img-thumbnail" src="img/book/1.jpg" /></a>
															</div>
														</div>
													</td>
												</tr>
											</tbody>
											<tbody>
												<tr>
													<td>
														<h3>学号</h3>
													</td>
													<td>
														<h3><%=loginRegister.getStudentId()%></h3>
													</td>
												</tr>
											</tbody>
											<tbody>
												<tr>
													<td>
														<h3>昵称</h3>
													</td>
													<td>
														<h3><%=loginRegister.getStudentName()%></h3>
													</td>
												</tr>
											</tbody>
											<tbody>
												<tr>
													<td>
														<h3>签名</h3>
													</td>
													<td>
														<h3><%=loginRegister.getSign()%></h3>
													</td>
												</tr>
											</tbody>
											<tbody>
												<tr>
													<td>
														<h3>性别</h3>
													</td>
													<td>
														<h3><%=loginRegister.getSex()%></h3>
													</td>
												</tr>
											</tbody>
											<tbody>
												<tr>
													<td>
														<h3>家乡</h3>
													</td>
													<td>
														<h3><%=loginRegister.getHone()%></h3>
													</td>
												</tr>
											</tbody>
											<tbody>
												<tr>
													<td>
														<h3>邮箱</h3>
													</td>
													<td>
														<h3><%=loginRegister.getEmail()%></h3>
													</td>
												</tr>
											</tbody>
										</table>
										<a href="#edit"
											class="btn btn-primary col-md-offset-7 col-md-5"
											aria-controls="edit" role="tab" data-toggle="tab">编辑</a>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane row" id="edit">
									<div class="col-md-6">
										<h3>
											<i class="fa fa-user-circle"></i>USER
										</h3>
										</a>
									</div>
									<div class="col-md-12">
										<form class="form-horizontal" action="LoginRegister"
											method="post">
											<input type="text" name="studentId" style="display: none"
												value="<%=loginRegister.getStudentId()%>">
											<div class="form-group">
												<label for="name" class="col-sm-1 control-label"> 昵称
												</label>
												<div class="col-sm-5">
													<input type="text" class="form-control" id="name"
														name="name" placeholder="昵称"
														value="<%=loginRegister.getStudentName()%>">
												</div>
											</div>
											<div class="form-group">
												<label for="autograph" class="col-sm-1 control-label">签名</label>
												<div class="col-sm-5">
													<textarea class="form-control" rows="3" id="autograph"
														name="autograph" placeholder="签名"
														value="<%=loginRegister.getSign()%>"></textarea>
												</div>
											</div>
											<div class="form-group">
												<label for="sex" class="col-sm-1 control-label">性别</label>
												<div id="sex">
													<label> <input type="radio" name="optionsRadios"
														id="optionsRadios1" value="男" checked>男
													</label> <label> <input type="radio" name="optionsRadios"
														id="optionsRadios2" value="女"> 女
													</label>
												</div>
											</div>
											<div class="form-group">
												<label for="hometown" class="col-sm-1 control-label">
													家乡 </label>
												<div class="col-sm-5">
													<input type="text" class="form-control" id="hometown"
														name="hometown" placeholder="家乡"
														value="<%=loginRegister.getHone()%>">
												</div>
											</div>
											<div class="form-group">
												<label for="email" class="col-sm-1 control-label">
													email </label>
												<div class="col-sm-5">
													<input type="email" class="form-control" id="email"
														name="email" placeholder="email"
														value="<%=loginRegister.getEmail()%>">
												</div>
											</div>
											<button class="btn btn-primary col-md-offset-3 col-md-3">完成</button>
										</form>
									</div>

									<!-- <a href="#home-contant"
										class="btn btn-primary col-md-offset-3 col-md-3"
										aria-controls="home-contant" role="tab" data-toggle="tab">home</a> -->
								</div>
							</div>

						</div>

					</div>
					<div role="tabpanel" class="tab-pane row" id="profile">
						<div class="col-md-1">
							<h3 class="col-md-4 tab-pane-contant">
								<i class="fa fa-bookmark"></i>已借书籍
							</h3>
						</div>
						<div class="col-md-11">
							<div class="row">
								<%
									int result = (int) request.getAttribute(StringConstant.REQ_UPDATEBORROW);

									if (result == 1) {
								%>
								<div class="page-header">
									<h1 id="success">预借书成功</h1>
								</div>
								<%
									}
									List<Management> managementList = (List) request.getAttribute(StringConstant.REQ_RECORD);
									for (int i = 0; i < managementList.size(); i++) {
										Management management = managementList.get(i);
								%>
								<div class="col-md-2">
									<div class="row">
										<img class="col-md-offset-1 col-md-10"
											src="/MyDragonBookimages/<%=management.getImages()%>">
										<div class="col-md-offset-1 col-md-10"><%=management.getName()%></div>
									</div>
								</div>

								<%
									}
								%>


							</div>
						</div>
					</div>
					<div role="tabpanel" class="tab-pane row" id="messages">
						<div class="col-md-1">
							<h3 class="col-md-4 tab-pane-contant">
								<i class="fa fa-book"></i>已还书籍
							</h3>
						</div>

					</div>
				</div>
			</div>

		</div>

	</div>

</body>

</html>