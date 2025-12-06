<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<body>
	<div class="wrapper sidebar_minimize">
		<div class="main-panel main-panel-bg">
			<div class="content">
				<div class="page-inner">
					<div class="row" style="margin-top: 20px;">
						<div class="col-md-12">
							<div class="card card-outer">
								<div class="card-header">
									<h4 class="card-title">
										<b><spring:message code="site.bms.beneficiary.application"></spring:message></b>
									</h4>
								</div>
								<div class="card-body">
									<div class="col-md-12">
										<form class="form-horizontal"
											action="${contextPath}/bms/scheme/filter" method="POST">
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" />
											<div class="col-md-4">
												<div class="form-group">
													<label class="col-md-12 required1" for="applicantName"><spring:message
															code="site.bms.choose.scheme"></spring:message></label>
													<div class="col-md-12">
														<select name="schemeId" id="schemeId" class="form-control"
															required="required">
															<option value="">Select</option>
															<c:forEach items="${schemeList}" var="scheme">
																<option value="${scheme.schemeId}"
																	${scheme.schemeId eq componentData.beneScheme.schemeId?'selected':''}>${scheme.schemeName}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="col-md-2">
												<div class="form-group">
													<div class="col-md-12 mt-4 pt-2 text-center">
														<button type="submit" class="btn btn-sm btn-primary">
															<spring:message code="site.common.proceed"></spring:message>
														</button>
													</div>
												</div>
											</div>
										</form>
										<form class="form-horizontal" method="get">
											<div class="col-md-4">
												<div class="form-group">
													<label class="col-md-12 required1" for="applicantName"><spring:message
															code="site.bms.treck.status"></spring:message></label>
													<div class="col-md-12">
														<select name="beneScheme.schemeId" id="state"
															class="form-control"
															title="This field should not be left blank.">
															<option value="">Select</option>
															<c:forEach items="${schemeList}" var="scheme">
																<option value="${scheme.schemeId}"
																	${scheme.schemeId eq componentData.beneScheme.schemeId?'selected':''}>${scheme.schemeName}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="col-md-2">
												<div class="form-group">
													<div class="col-md-12 mt-4 pt-2 text-center">
														<button type="submit" class="btn btn-sm btn-primary">
															<spring:message code="site.common.track"></spring:message>
														</button>
													</div>
												</div>
											</div>
										</form>
									</div>
									<div class="col-md-12 mt-4">
										<div class="table-responsive">
											<table class="display table table-bordered table-hover">
												<thead>
													<tr>
														<td colspan="2"><h5
																style="color: #137aa5; margin-left: -12px;">
																<spring:message code="site.bms.scheme.guidelines"></spring:message>
																:-
															</h5></td>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${schemeList}" var="scheme">
														<tr>
															<td>${scheme.schemeName}</td>
															<td style="width: 200px;"><a
																class="btn btn-primary btn-sm"
																href="${contextPath}/bms/scheme/viewGuidelinesDocument?schemeId=${scheme.schemeId}"
																target="_blank"><i class="fas fa-file-download"></i></a>
															</td>
														</tr>
													</c:forEach>

												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12 mt-4" style="padding: 0px;">
						<div class="card card-outer">
							<div class="card-header">
								<h4 class="card-title">
									<b>Grievence Application</b>
								</h4>
							</div>
							<div class="card-body">
								<form class="form-horizontal" method="get">
									<div class="row">
										<div class="col-md-12">
											<div class="col-md-5">
												<a href="#" class="btn btn-sm btn-primary">Apply
													Grievence</a>
											</div>
											<div class="col-md-5">
												<a href="#" class="btn btn-sm btn-secondary">Track
													Grievence Status</a>
											</div>
											<div class="col-md-2">
												<a href="#" class="btn btn-sm btn-info">View Guidelines</a>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>