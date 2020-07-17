<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="uvo" value="${uvo }" scope="page" /> 



    <div class="main-wrapper">

<h1>test</h1>
<!-- ====================================
———	INTERESTED
===================================== -->
<section class="pt-md-10 sec-pb-70 pt-8 pb-6 bg-light">
	<div class="container">
		
    <div id="all">

        <div id="content">
            <div class="container">

                <div class="col-md-12">

                    <ul class="breadcrumb">
                        <li><a href="#">Home</a>
                        </li>
                        <li>My account</li>
                    </ul>

                </div>

                <div class="col-md-3">
                    <!-- *** CUSTOMER MENU ***
 _________________________________________________________ -->
                    <div class="panel panel-default sidebar-menu">

                        <div class="panel-heading">
                            <h3 class="panel-title">Customer section</h3>
                        </div>

                        <div class="panel-body">

                            <ul class="nav nav-pills nav-stacked">
                                <li class="active">
                                    <a href="customer-orders.html"><i class="fa fa-list"></i> My orders</a>
                                </li>
                                <li>
                                    <a href="customer-wishlist.html"><i class="fa fa-heart"></i> My wishlist</a>
                                </li>
                                <li>
                                    <a href="customer-account.html"><i class="fa fa-user"></i> My account</a>
                                </li>
                                <li>
                                    <a href="index.html"><i class="fa fa-sign-out"></i> Logout</a>
                                </li>
                            </ul>
                        </div>

                    </div>
                    <!-- /.col-md-3 -->

                    <!-- *** CUSTOMER MENU END *** -->
                </div>

                <div class="col-md-9">
                    <div class="box">
                        <h1>My account</h1>
                        <p class="lead">Change your personal details or your password here.</p>
                        <p class="text-muted">Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.</p>

                        <h3>Change password</h3>

                        <form>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="password_old">ID</label>
                                        <input type="text" class="form-control" id="password_old" value="${uvo.id}" readonly="readonly">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="password_1">New password</label>
                                        <input type="password" class="form-control" id="password_1">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="password_2">Retype new password</label>
                                        <input type="password" class="form-control" id="password_2">
                                    </div>
                                </div>
                            </div>
                            <!-- /.row -->

                            <div class="col-sm-12 text-center">
                                <button type="submit" class="btn btn-primary"><i class="fa fa-save"></i> Save new password</button>
                            </div>
                        </form>

                        <hr>

                        <h3>Personal details</h3>
                        <form>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="firstname">Firstname</label>
                                        <input type="text" class="form-control" id="firstname">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="lastname">Lastname</label>
                                        <input type="text" class="form-control" id="lastname">
                                    </div>
                                </div>
                            </div>
                            <!-- /.row -->

                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="company">Company</label>
                                        <input type="text" class="form-control" id="company">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="street">Street</label>
                                        <input type="text" class="form-control" id="street">
                                    </div>
                                </div>
                            </div>
                            <!-- /.row -->

                            <div class="row">
                                <div class="col-sm-6 col-md-3">
                                    <div class="form-group">
                                        <label for="city">Company</label>
                                        <input type="text" class="form-control" id="city">
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-3">
                                    <div class="form-group">
                                        <label for="zip">ZIP</label>
                                        <input type="text" class="form-control" id="zip">
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-3">
                                    <div class="form-group">
                                        <label for="state">State</label>
                                        <select class="form-control" id="state"></select>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-3">
                                    <div class="form-group">
                                        <label for="country">Country</label>
                                        <select class="form-control" id="country"></select>
                                    </div>
                                </div>

                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="phone">Telephone</label>
                                        <input type="text" class="form-control" id="phone">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="email">Email</label>
                                        <input type="text" class="form-control" id="email">
                                    </div>
                                </div>
                                <div class="col-sm-12 text-center">
                                    <button type="submit" class="btn btn-primary"><i class="fa fa-save"></i> Save changes</button>

                                </div>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
            <!-- /.container -->
        </div>
        <!-- /#content -->


		
	</div>
</section>

<!-- ====================================
———	POPULAR LISTING
===================================== -->
<section class="pt-md-10 sec-pb-70 pt-8 pb-6">
	<div class="container">
    <div class="section-title">
      <h2>Popular things near you</h2>
      <p>This are some of most popular listing</p>
    </div>

    <div class="owl-carousel owl-theme popular-listing pt-10">

        <div class="card rounded-0 card-hover-overlay">
          <div class="position-relative">
            <img class="card-img rounded-0" src="/comic/resources/img/listing/listing-4.jpg" alt="Card image cap">
            <div class="card-img-overlay">
              <ul class="list-inline list-inline-rating">
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star-o" aria-hidden="true"></i>
                </li>
              </ul>
              <h3>
                <a href="listing-reservation.html">
                  Think Coffee <i class="fa fa-check-circle" aria-hidden="true"></i>
                </a>
              </h3>
              <p class="text-white">215 Terry Lane, New York</p>
            </div>
          </div>

          <div class="card-footer bg-transparent">
            <ul class="list-unstyled d-flex mb-0 py-2">
              <li>
                <button class="btn-like px-2" data-toggle="tooltip" data-placement="top" title="Favourite this listing">
                  <i class="fa fa-heart-o text-primary" aria-hidden="true"></i>
                  <span>8 k</span>
                </button>
              </li>
              <li class="ml-auto">
                <a class="px-2" href="listings-half-screen-map-list.html">Eat & Drink</a>
              </li>
            </ul>
          </div>
        </div>

        <div class="card rounded-0 card-hover-overlay">
          <div class="position-relative">
            <img class="card-img rounded-0" src="/comic/resources/img/listing/listing-5.jpg" alt="Card image cap">
            <div class="card-img-overlay">
              <ul class="list-inline list-inline-rating">
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
              </ul>
              <h3>
                <a href="listing-reservation.html">
                  Burger House<i class="fa fa-check-circle" aria-hidden="true"></i>
                </a>
              </h3>
              <p class="text-white">2726 Shinn Street, New York</p>
            </div>
          </div>

          <div class="card-footer bg-transparent">
            <ul class="list-unstyled d-flex mb-0 py-2">
              <li>
                <button class="btn-like px-2" data-toggle="tooltip" data-placement="top" title="Favourite this listing">
                  <i class="fa fa-heart-o text-primary" aria-hidden="true"></i>
                  <span>9.2 k</span>
                </button>
              </li>
              <li class="ml-auto">
                <a class="px-2" href="listings-half-screen-map-list.html">Eat & Drink</a>
              </li>
            </ul>
          </div>
        </div>

        <div class="card rounded-0 card-hover-overlay">
          <div class="position-relative">
            <img class="card-img rounded-0" src="/comic/resources/img/listing/listing-10.jpg" alt="Card image cap">
            <div class="card-img-overlay">
              <ul class="list-inline list-inline-rating">
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star-o" aria-hidden="true"></i>
                </li>
              </ul>
              <h3>
                <a href="listing-reservation.html">
                  Tom's Restaurant<i class="fa fa-check-circle" aria-hidden="true"></i>
                </a>
              </h3>
              <p class="text-white">964 School Street, New York</p>
            </div>
          </div>

          <div class="card-footer bg-transparent">
            <ul class="list-unstyled d-flex mb-0 py-2">
              <li>
                <button class="btn-like px-2" data-toggle="tooltip" data-placement="top" title="Favourite this listing">
                  <i class="fa fa-heart-o text-primary" aria-hidden="true"></i>
                  <span>9.5 k</span>
                </button>
              </li>
              <li class="ml-auto">
                <a class="px-2" href="listings-half-screen-map-list.html">Eat & Drink</a>
              </li>
            </ul>
          </div>
        </div>

        <div class="card rounded-0 card-hover-overlay">
          <div class="position-relative">
            <img class="card-img rounded-0" src="/comic/resources/img/listing/listing-1.jpg" alt="Card image cap">
            <div class="card-img-overlay">
              <ul class="list-inline list-inline-rating">
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
              </ul>
              <h3>
                <a href="listing-event.html">
                  The City Theater
                </a>
              </h3>
              <p class="text-white">155 1st Avenue, New York</p>
            </div>
          </div>

          <div class="card-footer bg-transparent">
            <ul class="list-unstyled d-flex mb-0 py-2">
              <li>
                <button class="btn-like px-2" data-toggle="tooltip" data-placement="top" title="Favourite this listing">
                  <i class="fa fa-heart-o text-primary" aria-hidden="true"></i>
                  <span>9.5 k</span>
                </button>
              </li>
              <li class="ml-auto">
                <a class="px-2" href="listings-half-screen-map-list.html">Movie Theaters</a>
              </li>
            </ul>
          </div>
        </div>

        <div class="card rounded-0 card-hover-overlay">
          <div class="position-relative">
            <img class="card-img rounded-0" src="/comic/resources/img/listing/listing-7.jpg" alt="Card image cap">
            <div class="card-img-overlay">
              <ul class="list-inline list-inline-rating">
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star-o" aria-hidden="true"></i>
                </li>
              </ul>
              <h3>
                <a href="listing-event.html">
                  Sticky Band<i class="fa fa-check-circle" aria-hidden="true"></i>
                </a>
              </h3>
              <p class="text-white">Bishop Avenue, New York</p>
            </div>
          </div>

          <div class="card-footer bg-transparent">
            <ul class="list-unstyled d-flex mb-0 py-2">
              <li>
                <button class="btn-like px-2" data-toggle="tooltip" data-placement="top" title="Favourite this listing">
                  <i class="fa fa-heart-o text-primary" aria-hidden="true"></i>
                  <span>5.9 k</span>
                </button>
              </li>
              <li class="ml-auto">
                <a class="px-2" href="listings-half-screen-map-list.html">Nightclubs</a>
              </li>
            </ul>
          </div>
        </div>

        <div class="card rounded-0 card-hover-overlay">
          <div class="position-relative">
            <img class="card-img rounded-0" src="/comic/resources/img/listing/listing-8.jpg" alt="Card image cap">
            <div class="card-img-overlay">
              <ul class="list-inline list-inline-rating">
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
                <li class="list-inline-item">
                  <i class="fa fa-star" aria-hidden="true"></i>
                </li>
              </ul>
              <h3>
                <a href="listing-rental.html">
                  Hotel Govendor
                </a>
              </h3>
              <p class="text-white">78 Country Street, New York</p>
            </div>
          </div>

          <div class="card-footer bg-transparent">
            <ul class="list-unstyled d-flex mb-0 py-2">
              <li>
                <button class="btn-like px-2" data-toggle="tooltip" data-placement="top" title="Favourite this listing">
                  <i class="fa fa-heart-o text-primary" aria-hidden="true"></i>
                  <span>5 k</span>
                </button>
              </li>
              <li class="ml-auto">
                <a class="px-2" href="listings-half-screen-map-list.html">Hotels</a>
              </li>
            </ul>
          </div>
        </div>

    </div>
	</div>
</section>


<!-- ====================================
———	COUNTER UP
===================================== -->


<div class="dzsparallaxer auto-init use-loading counter-paralax">
  <div class="divimage dzsparallaxer--target"  data-src="/comic/resources/img/background/bg-countdown.jpg"></div>

  <div class="container paralax-container">
    <div class="section-title">
      <h2 class="text-white"> Why Listty? </h2>
    </div>
    <div class="row" id="counter">
      <div class="col-sm-3 col-xs-12">
        <div class="counter-circle text-center text-white mb-7">
          <div class="counter-value" data-count="140">0</div>
          <span class="counter-label">Cities</span>
        </div>
      </div>

      <div class="col-sm-3 col-xs-12">
        <div class="counter-circle text-center text-white mb-7">
          <div class="counter-value" data-count="120">0</div>
          <span class="counter-label">Restaurants</span>
        </div>
      </div>

      <div class="col-sm-3 col-xs-12">
        <div class="counter-circle text-center text-white mb-7">
          <div class="counter-value" data-count="180">0</div>
          <span class="counter-label">Hotels</span>
        </div>
      </div>

      <div class="col-sm-3 col-xs-12">
        <div class="counter-circle text-center text-white mb-7">
          <div class="counter-value" data-count="220">0</div>
          <span class="counter-label">Retail Services</span>
        </div>
      </div>
    </div>

    <div class="text-center mt-md-7">
      <a href="#get-it-now" class="btn btn-outline-white"> Get it now </a>
    </div>
  </div>


</div>


<!-- ====================================
———	HOW IT WORK
===================================== -->
<section class="pt-8 pb-6 pt-md-10 sec-pb-70">
	<div class="container">
		<div class="section-title">
			<h2>How it Works?</h2>
			<p>This are some of most popular listing</p>
		</div>

		<div class="row">
			<div class="col-sm-4 col-xs-12">
				<div class="card">
					<div class="mt-8 mb-3">
						<img class="mx-auto d-block" src="/comic/resources/img/works/works-1.png" alt="Image works">
					</div>
					<div class="card-body text-center pb-6">
						<h3 class="h4">
							<a class="mb-3 d-inline-block text-dark text-decoration-none" href="how-it-works.html">
								Find what you want
							</a>
						</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed eiusmod tempor incididunt  labore et dolore magna aliqua.</p>
					</div>
				</div>
			</div>

			<div class="col-sm-4 col-xs-12">
				<div class="card">
					<div class="mt-8 mb-3">
						<img class="mx-auto d-block" src="/comic/resources/img/works/works-2.png" alt="Image works">
					</div>
					<div class="card-body text-center pb-6">
						<h3 class="h4">
							<a class="mb-3 d-inline-block text-dark text-decoration-none" href="how-it-works.html">
								Review &amp; Plan your day
							</a>
						</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed eiusmod tempor incididunt  labore et dolore magna aliqua.</p>
					</div>
				</div>
			</div>

			<div class="col-sm-4 col-xs-12">
				<div class="card">
					<div class="mt-8 mb-3">
						<img class="mx-auto d-block" src="/comic/resources/img/works/works-3.png" alt="Image works">
					</div>
					<div class="card-body text-center pb-6">
						<h3 class="h4">
							<a class="mb-3 d-inline-block text-dark text-decoration-none" href="how-it-works.html">집에서 편하게 대여하자!</a>
						</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed eiusmod tempor incididunt  labore et dolore magna aliqua.</p>
					</div>
				</div>
			</div>
		</div>

		<div class="text-center my-5">
			<a data-fancybox href="https://vimeo.com/78415143" class="btn btn-primary">
        Watch it now <i class="fa fa-play-circle" aria-hidden="true"></i>
			</a>
    </div>

	</div>
</section>

<!-- ====================================
———	BLOG SECTION
===================================== -->
<section class="pt-8 pb-4 pt-md-10 sec-pb-70 bg-light">
	<div class="container">

    <!-- Section Title -->
		<div class="section-title">
			<h2>Articles</h2>
			<p>Browse latest tips from our blog</p>
		</div>

		<div class="row">
			<div class="col-sm-4 col-xs-12">
				<div class="card border-0 bg-transparent">
					<a href="blog-details.html">
						<img class="card-img-top rounded" src="/comic/resources/img/blog/blog-article-1.jpg" alt="Card image cap">
					</a>
          <div class="card-body p-0 pt-5">
						<div class="meta-post">
							<date class="meta-date">Jan 22, 2018</date>
							<span class="meta-author"> By <a href="#">Jno Deo</a></span>
						</div>
						<h3 class="card-title">
							<a href="blog-details.html"> Lorem ipsum dolor sit amet	</a>
						</h3>
						<p >Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed eiusmod tempor incididunt  labore et dolore magna aliqua.</p>
					</div>
				</div>
			</div>

			<div class="col-sm-4 col-xs-12">
        <div class="card border-0 bg-transparent">
					<a href="blog-details.html">
						<img class="card-img-top rounded" src="/comic/resources/img/blog/blog-article-2.jpg" alt="Card image cap">
					</a>
					<div class="card-body p-0 pt-5">
            <div class="meta-post">
              <date class="meta-date">Jan 20, 2018</date>
              <span class="meta-author"> By <a href="#">Adam Smith</a></span>
            </div>
						<h3 class="card-title">
							<a href="blog-details.html"> Ut Enim Ad Minim Veniam.</a>
						</h3>
            <p >Quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit</p>
					</div>
				</div>
			</div>

			<div class="col-sm-4 col-xs-12">
        <div class="card border-0 bg-transparent">
					<a href="blog-details.html">
						<img class="card-img-top rounded" src="/comic/resources/img/blog/blog-article-3.jpg" alt="Card image cap">
					</a>
          <div class="card-body p-0 pt-5">
            <div class="meta-post">
              <date class="meta-date">Jan 17, 2018</date>
              <span class="meta-author"> By <a href="#">Kathy Brown</a></span>
            </div>
						<h3 class="card-title">
							<a href="blog-details.html"> Velit Esse Cillum Dolore Eu Fugiat	</a>
						</h3>
            <p >Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>


<!-- ====================================
———	GET STARTED
===================================== -->
<section class="bg-primary py-7">
	<div class="container">
		<div class="row align-items-center">
			<div class="col-sm-9 col-xl-10 col-xs-12">
        <h2 class="font-weight-normal mb-5 mb-md-0" style="color: rgba(255, 255, 255, .7)" >
          <span class="text-white">Listty</span> is the <span class="text-white">best way</span> <br> to find great local business
        </h2>
			</div>
			<div class="col-sm-3 col-xl-2 col-xs-12">
				<div >
					<a href="sign-up.html" class="btn btn-outline-white btn-block">Get Started</a>
				</div>
			</div>
		</div>
	</div>
</section>


  </div> <!-- element wrapper ends -->

  