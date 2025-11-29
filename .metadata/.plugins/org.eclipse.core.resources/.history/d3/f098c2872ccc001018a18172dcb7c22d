<div class="col-lg-6 col-md-6 mt-3 row hidden" id="hoardingDivId">
	<div class="card">
		<div class="card-body">
			<div class="d-flex justify-content-between align-items-baseline">
				<h5 class="card-title">Hoarding Information</h5>
				<div class="fiYear form-group">
					<div class="row align-items-center">
						<div class="col-auto">
                        	<label for="" class="mb-0 form-label">Area :</label>
                        </div>
                        <div class="col-auto">
                        	<select id="areaId" class="form-control" onchange="fetchHoardingInfoDetails()">
								<c:forEach items="${areaList}" var="area">
									<option value="${area.areaId}">${area.areaName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="fiYear form-group">
                	<div class="row align-items-center">
                    	<div class="col-auto">
                        	<label for="" class="mb-0 form-label">Hoarding Type :</label>
                        </div>
                        <div class="col-auto">
                        	<select id="hoardingId" class="form-control" onchange="fetchHoardingInfoDetails()">
                                <c:forEach items="${hoardingTypeList}" var="hrd">
									<option value="${hrd.hoardingTypeId}">${hrd.hoardingTypeName}</option>
								</c:forEach>
                            </select>
                       </div>
					</div>
				</div>
 			</div>
			<div id="hoardingmanagement"></div>
		</div>
	</div>
</div>