<div class="col-lg-12 col-md-6 mt-3 row hidden" id="grvDivId">
	<div class="card" style="height: 414px;">
		<div class="card-body">
			<div class="d-flex justify-content-between align-items-baseline" >
				<h5 class="card-title">Grievance Redressal</h5>
            	<div class="fiYear form-group">
                	<div class="row align-items-center">
                    	<div class="col-auto">
                        	<label class="mb-0 form-label" >Month :</label>
                        </div>
                        <div class="col-auto">
                        	<select id="stsMonthId" class="form-control" onchange="fetchStatusWiseGrievance();fetchCategoryWiseGrievance()">
                                <option value="1" ${monthId eq 1 ? 'selected' : ''}>January</option>
                            	<option value="2" ${monthId eq 2 ? 'selected' : ''}>February</option>
                            	<option value="3" ${monthId eq 3 ? 'selected' : ''}>March</option>
                            	<option value="4" ${monthId eq 4 ? 'selected' : ''}>April</option>
                            	<option value="5" ${monthId eq 5 ? 'selected' : ''}>May</option>
                            	<option value="6" ${monthId eq 6 ? 'selected' : ''}>June</option>
                           		<option value="7" ${monthId eq 7 ? 'selected' : ''}>July</option>
                            	<option value="8" ${monthId eq 8 ? 'selected' : ''}>August</option>
                            	<option value="9" ${monthId eq 9 ? 'selected' : ''}>September</option>
                           		<option value="10" ${monthId eq 10 ? 'selected' : ''}>October</option>
                            	<option value="11" ${monthId eq 11 ? 'selected' : ''}>November</option>
                           		<option value="12" ${monthId eq 12 ? 'selected' : ''}>December</option>
							</select>
						</div>
					</div>
				</div>
			</div>
			<div class="row mt-5">
            	<div class="col-md-6">
                	<div id="grievance" class="d-flex justify-content-center"></div>
                </div>
                <div class="col-md-6">
                	<div id="grivanceChart" class="d-flex justify-content-center"></div>
                </div>
			</div>
		</div>
	</div>
</div>