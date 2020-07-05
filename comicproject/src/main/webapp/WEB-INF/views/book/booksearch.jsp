<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<section>
	<div class="container">
		<div class="row text-center align-items-center justify-content-center"
			style="height: 624px;">
			<!-- Search Box -->
			<div class="search-box-2">
				<form class="form-row justify-content-center" method="GET"
					action="#">

					<div class="form-group col-md-5 col-lg-4">
						<div class="input-group mb-2">
							<div class="input-group-prepend">
								<!--<div class="input-group-text">Find</div>-->
								<select class="input-group-text" name="findBook">
									<option selected="selected" value="title">책 제목</option>
									<option value="author">저자</option>
									<option value="library">출판사</option>
								</select>


							</div>
							<input type="text" class="form-control" placeholder="찾는 책이 있나요?">
						</div>
					</div>


					<div class="form-group col-md-3 col-lg-2">
						<button type="submit" class="btn btn-block btn-primary">
							검색 <i class="fa fa-search" aria-hidden="true"></i>
						</button>
					</div>

				</form>
			</div>
		</div>
	</div>
</section>