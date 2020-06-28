<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="kor">

<head>

  <!-- SITE TITTLE -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Home Travel - Listty</title>

  <!-- PLUGINS CSS STYLE -->
  <link href="resources/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="resources/plugins/listtyicons/style.css" rel="stylesheet">
  <link href="resources/plugins/menuzord/css/menuzord.css" rel="stylesheet">
  <link href="resources/plugins/map/css/map.css" rel="stylesheet">
  <link href="resources/plugins/selectric/selectric.css" rel="stylesheet">
  <link href="resources/plugins/dzsparallaxer/dzsparallaxer.css" rel="stylesheet">
  <link href="resources/plugins/owl-carousel/assets/owl.carousel.min.css" rel="stylesheet">
  <link href="resources/plugins/owl-carousel/assets/owl.theme.default.min.css" rel="stylesheet">
  <link href="resources/plugins/map/css/map.css" rel="stylesheet">
  <link href="resources/plugins/fancybox/jquery.fancybox.min.css" rel="stylesheet">

  <!-- GOOGLE FONT -->
  <link href="https://fonts.googleapis.com/css?family=Muli:200,300,400,600,700,800,900" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">

  <!-- CUSTOM CSS -->
  <link href="resources/css/listty.css" rel="stylesheet" id="option_style">

  <!-- <link rel="stylesheet" href="resources/css/default.css" id="option_color"> -->

  <!-- FAVICON -->
  <link href="resources/img/favicon.png" rel="shortcut icon">

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

</head>

<body id="body" class="up-scroll">

  <!-- Preloader -->
  <div id="preloader" class="smooth-loader-wrapper">
    <div class="smooth-loader">
      <div class="loader1">
        <div class="loader-target">
          <div class="loader-target-main"></div>
          <div class="loader-target-inner"></div>
        </div>
      </div>
    </div>
  </div>


    <!-- HEADER -->
    <header class="header">
      <nav class="nav-menuzord nav-menuzord-transparent navbar-sticky">
        <div class="container clearfix">
          <div id="menuzord" class="menuzord menuzord-responsive">

            <a href="index.html" class="menuzord-brand">
              <svg class="logo-svg" version="1.1" xmlns="http://www.w3.org/2000/svg" width="140" height="44">
                <path class="fill-primay" d="M0 44V0h139.813v44H0zM137.346 2.467H2.467v39.065h134.879V2.467z" />
                <path class="fill-primay"
                  d="M120.927 22.389v11.095h-4.566V22.389a371.288 371.288 0 0 0-2.086-2.888 347.047 347.047 0 0 1-2.2-3.053 386.86 386.86 0 0 0-2.201-3.053c-.7-.959-1.395-1.922-2.086-2.888h5.617l5.255 7.287 5.222-7.287h5.649c.002 0-8.604 11.882-8.604 11.882zM98.034 33.484h-4.565V15.069h-6.372v-4.562h17.244v4.562h-6.306v18.415zm-21.908 0H71.56V15.069h-6.372v-4.562h17.244v4.562h-6.306v18.415zm-17.425-1.789c-.69.623-1.511 1.116-2.463 1.477-.953.361-1.987.542-3.104.542-1.007 0-1.982-.143-2.923-.427a10.814 10.814 0 0 1-2.661-1.214h.033a9.928 9.928 0 0 1-1.577-1.215 18.73 18.73 0 0 1-.953-.952l3.416-3.151c.153.197.399.432.739.706.339.274.728.537 1.166.788.44.253.902.467 1.38.64.481.175.941.262 1.379.262.372 0 .744-.044 1.117-.131.359-.082.703-.22 1.018-.41.305-.185.564-.437.755-.739.197-.306.296-.689.296-1.149 0-.175-.06-.366-.181-.574-.12-.208-.329-.432-.624-.673-.296-.241-.706-.498-1.232-.771a20.567 20.567 0 0 0-1.971-.87 25.42 25.42 0 0 1-2.562-1.132 8.896 8.896 0 0 1-2.053-1.428 5.903 5.903 0 0 1-1.347-1.871c-.317-.7-.476-1.51-.476-2.429 0-.94.175-1.822.526-2.642a6.21 6.21 0 0 1 1.494-2.133c.646-.602 1.423-1.072 2.332-1.412.908-.339 1.911-.509 3.006-.509.591 0 1.22.077 1.889.23.668.153 1.319.35 1.954.591a12.95 12.95 0 0 1 1.79.837c.558.317 1.023.64 1.396.968l-2.825 3.545a15.71 15.71 0 0 0-1.281-.788 10.316 10.316 0 0 0-1.281-.558 4.311 4.311 0 0 0-1.478-.263c-.919 0-1.637.181-2.151.542-.515.361-.772.881-.772 1.559 0 .307.093.586.279.837.186.252.438.482.756.689.348.225.717.417 1.1.574.416.176.854.34 1.314.492 1.314.504 2.42 1.013 3.318 1.526.898.514 1.62 1.062 2.168 1.642s.936 1.204 1.166 1.871c.23.668.345 1.395.345 2.183 0 .963-.197 1.871-.591 2.724a6.803 6.803 0 0 1-1.626 2.216zM34.839 10.507h4.532v22.977h-4.532V10.507zm-20.036 0h4.566v18.415h9.263v4.563H14.803V10.507z" />
              </svg>
            </a>
            <div class="float-right btn-wrapper">
              <a class="btn btn-outline-primary" href="add-listings.html">+ <span>Add listing</span></a>
            </div>
            <ul class="menuzord-menu menuzord-right">
              <li class="active">
                <a href="javascript:0">Home</a>
                <ul class="dropdown">
                  <li><a href="index.html">Home Map</a></li>
                  <li><a href="index-2.html">Home Travel</a></li>
                  <li><a href="index-3.html">Home City</a></li>
                  <li><a href="index-4.html">Home Automobile</a></li>
                </ul>
              </li>
              <li class="">
                <a href="javascript:0">Listing</a>
                <div class="megamenu">
                  <div class="megamenu-row">
                    <div class="col4">
                      <ul class="list-unstyled">
                        <li>
                          <h5 class="heading">
                            <i class="fa fa-map mr-2 text-primary" aria-hidden="true"></i>
                            Half Screen Map
                          </h5>
                        </li>
                        <li>
                          <a href="listings-half-screen-map-list.html"> List Layout </a>
                        </li>
                        <li>
                          <a href="listings-half-screen-map-grid.html"> Grid Layout </a>
                        </li>
                      </ul>
                      <ul class="list-unstyled">
                        <li>
                          <h5 class="heading">
                            <i class="fa fa-address-book mr-2 text-primary" aria-hidden="true"></i>
                            Listing Without Map
                          </h5>
                        </li>
                        <li>
                          <a href="listing-grid-right-sidebar-without-map.html">Grid Right Sidebar</a>
                        </li>
                        <li>
                          <a href="listing-grid-fullwidth-without-map.html">Grid Fullwidth</a>
                        </li>
                        <li>
                          <a href="listing-list-right-sidebar-without-map.html">List Right Sidebar</a>
                        </li>
                        <li>
                          <a href="listing-list-fullwidth-without-map.html">List Fullwidth</a>
                        </li>
                      </ul>
                    </div>
                    <div class="col4">
                      <ul class="list-unstyled">
                        <li>
                          <h5 class="heading">
                            <i class="fa fa-th-large text-primary mr-2" aria-hidden="true"></i>
                            Listing Grid
                          </h5>
                        </li>
                        <li>
                          <a href="listing-grid-left-sidebar.html">Left Sidebar</a>
                        </li>
                        <li>
                          <a href="listing-grid-right-sidebar.html">Right Sidebar</a>
                        </li>
                        <li>
                          <a href="listing-grid-fullwidth.html">Fullwidth</a>
                        </li>
                      </ul>
                      <ul class="list-unstyled">
                        <li>
                          <h5 class="heading">
                            <i class="fa fa-th-list text-primary mr-2" aria-hidden="true"></i>
                            Listing List
                          </h5>
                        </li>
                        <li>
                          <a href="listing-list-left-sidebar.html"> Left Sidebar</a>
                        </li>
                        <li>
                          <a href="listing-list-right-sidebar.html"> Right Sidebar</a>
                        </li>
                        <li>
                          <a href="listing-list-fullwidth.html"> Fullwidth</a>
                        </li>
                      </ul>
                    </div>
                    <div class="col4">
                      <ul class="list-unstyled">
                        <li>
                          <h5 class="heading">
                            <i class="fa fa-file-text text-primary mr-2" aria-hidden="true"></i>
                            Single Listing
                          </h5>
                        </li>
                        <li>
                          <a href="listing-store.html">Store Listing</a>
                        </li>
                        <li>
                          <a href="listing-vendor.html">Vendor Listing</a>
                        </li>
                        <li>
                          <a href="listing-event.html">Event Listing </a>
                        </li>
                        <li>
                          <a href="listing-rental.html">Rental Listing </a>
                        </li>
                        <li>
                          <a href="listing-reservation.html">Reservation Listing</a>
                        </li>
                        <li>
                          <a href="listing-service.html">Service Listing </a>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="">
                <a href="javascript:0">Pages</a>
                <ul class="dropdown">
                  <li><a href="contact-us.html">Contact Us</a></li>
                  <li><a href="terms-of-services.html">Terms and Conditions</a></li>
                  <li><a href="pricing-table.html">Pricing Table</a></li>
                  <li><a href="how-it-works.html">How It Works</a></li>
                  <li><a href="user-profile.html">User Profile</a></li>
                  <li><a href="comming-soon.html">Coming Soon</a></li>
                  <li><a href="404.html">404 Page</a></li>
                </ul>
              </li>
              <li class="">
                <a href="blog.html">Blog</a>
              </li>
              <li class="">
                <a href="javascript:0">Admin</a>
                <ul class="dropdown">
                  <li>
                    <a href="javascript:0">User Admin</a>
                    <ul class="dropdown">
                      <li><a href="dashboard-user.html">Dashboard</a></li>
                      <li><a href="my-bookings.html">My Booking</a></li>
                      <li><a href="my-favourites.html">My Favourites</a></li>
                      <li><a href="my-reviews.html">My reviews</a></li>
                      <li><a href="profile-user.html">My Profile</a></li>
                      <li><a href="message-users.html">My Messages</a></li>
                    </ul>
                  </li>
                  <li>
                    <a href="javascript:0">List Admin</a>
                    <ul class="dropdown">
                      <li><a href="dashboard-list-admin.html">Dashboard</a></li>
                      <li><a href="my-listings.html">My Listings</a></li>
                      <li><a href="add-listings.html">Add Listings</a></li>
                      <li><a href="edit-listings.html">Edit Listings</a></li>
                      <li><a href="admin-booking-requests.html">Admin Booking</a></li>
                      <li><a href="admin-reviews.html">Admin Reviews</a></li>
                      <li><a href="payment-process.html">Payment</a></li>
                      <li><a href="profile-list-admin.html">My Profile</a></li>
                      <li><a href="messages-list-admin.html">My Messages</a></li>
                    </ul>
                  </li>
                  <li>
                    <a href="javascript:0">Site Admin</a>
                    <ul class="dropdown">
                      <li><a href="dashboard-stie-admin.html">Dashboard</a></li>
                      <li><a href="admin-listings.html">Admin Listings</a></li>
                      <li><a href="user-list-owners.html">List Owners</a></li>
                      <li><a href="user-generals.html">General Users</a></li>
                      <li><a href="profile-site-admin.html">My Profile</a></li>
                    </ul>
                  </li>
                  <li><a href="login.html">Login</a></li>
                  <li><a href="sign-up.html">Create Account</a></li>
                </ul>
              </li>

            </ul>

          </div>
        </div>
      </nav>
    </header>
    <div class="main-wrapper">


<!-- ====================================
———	BANNER SECTION (TRAVEL)
===================================== -->
<section class="banner" style="background-image: url('resources/img/background/banner-img1.jpg');">
  <div class="container">
    <div class="row text-center align-items-center justify-content-center" style="height:624px;">
      <div class="col-12">
        <!-- Banner Info -->
        <div class="banner-info">
          <h1 class="text-uppercase text-white mb-4">
            explore. discover. share
          </h1>
          <p class="lead text-white">
            Listty helps to find out great things arround you
          </p>
        </div>

        <!-- Search Box -->
        <div class="search-box-2">
          <form class="form-row justify-content-center" method="GET" action="listings-half-screen-map-list.html">

            <div class="form-group col-md-5 col-lg-4">
              <div class="input-group mb-2">
                <div class="input-group-prepend">
                  <div class="input-group-text">Find</div>
                </div>
                <input type="text" class="form-control" placeholder="What are you looking for?">
              </div>
            </div>

            <div class="form-group prepend-append col-md-5 col-lg-4">
              <div class="input-group mb-2">
                <div class="input-group-prepend">
                  <div class="input-group-text">Near</div>
                </div>
                <input type="text" class="form-control" placeholder="Location">
                <div class="input-group-append">
                  <span class="input-group-text" data-toggle="tooltip" data-placement="left" title="Find my location">
                    <i class="icon-listy icon-target" aria-hidden="true"></i>
                  </span>
                </div>
              </div>
            </div>

            <div class="form-group col-md-3 col-lg-2">
              <button type="submit" class="btn btn-block btn-primary">Search
                <i class="fa fa-search" aria-hidden="true"></i>
              </button>
            </div>

          </form>
        </div>
      </div>
    </div>
  </div>
</section>

<!-- ====================================
———	INTERESTED
===================================== -->
<section class="pt-md-10 sec-pb-70 pt-8 pb-6 bg-light">
	<div class="container">
		<div class="section-title">
			<h2>What are You Interested in?</h2>
      <p>Explore and connect with great local businesses</p>
		</div>
		<div class="row">
			<div class="col-md-4 col-lg-3 col-xs-12">
        <a class="card py-5 hover-bg-primary bg-transparent" href="listings-half-screen-map-list.html">
          <div class="card-body text-center">
            <div class="icon-md">
              <i class="icon-listy icon-tea-cup-1"></i>
            </div>
						<span class="h5">Restaurant</span>
          </div>
        </a>
			</div>

      <div class="col-md-4 col-lg-3 col-xs-12">
        <a class="card py-5 hover-bg-primary bg-transparent" href="listings-half-screen-map-list.html">
          <div class="card-body text-center">
            <div class="icon-md">
              <i class="icon-listy icon-building"></i>
            </div>
            <span class="h5">Hotels</span>
          </div>
        </a>
			</div>

      <div class="col-md-4 col-lg-3 col-xs-12">
        <a class="card py-5 hover-bg-primary bg-transparent" href="listings-half-screen-map-list.html">
          <div class="card-body text-center">
            <div class="icon-md">
              <i class="icon-listy icon-martini"></i>
            </div>
            <span class="h5">Nightclubs</span>
          </div>
        </a>
			</div>

      <div class="col-md-4 col-lg-3 col-xs-12">
        <a class="card py-5 hover-bg-primary bg-transparent" href="listings-half-screen-map-list.html">
          <div class="card-body text-center">
            <div class="icon-md">
              <i class="icon-listy icon-car-1"></i>
            </div>
            <span class="h5">Auto Motive</span>
          </div>
        </a>
			</div>

      <div class="col-md-4 col-lg-3 col-xs-12">
        <a class="card py-5 hover-bg-primary bg-transparent" href="listings-half-screen-map-list.html">
          <div class="card-body text-center">
            <div class="icon-md">
              <i class="icon-listy icon-castle"></i>
            </div>
            <span class="h5">Museums</span>
          </div>
        </a>
			</div>

      <div class="col-md-4 col-lg-3 col-xs-12">
        <a class="card py-5 hover-bg-primary bg-transparent" href="listings-half-screen-map-list.html">
          <div class="card-body text-center">
            <div class="icon-md">
              <i class="icon-listy icon-television"></i>
            </div>
            <span class="h5">Movie Theaters</span>
          </div>
        </a>
			</div>

      <div class="col-md-4 col-lg-3 col-xs-12">
        <a class="card py-5 hover-bg-primary bg-transparent" href="listings-half-screen-map-list.html">
          <div class="card-body text-center">
            <div class="icon-md">
              <i class="icon-listy icon-mall-1"></i>
            </div>
            <span class="h5">Shopping</span>
          </div>
        </a>
			</div>

      <div class="col-md-4 col-lg-3 col-xs-12">
        <a class="card py-5 hover-bg-primary bg-transparent" href="listings-half-screen-map-list.html">
          <div class="card-body text-center">
            <div class="icon-md">
              <i class="icon-listy icon-more"></i>
            </div>
            <span class="h5">View All</span>
          </div>
        </a>
			</div>
		</div>
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
            <img class="card-img rounded-0" src="resources/img/listing/listing-4.jpg" alt="Card image cap">
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
            <img class="card-img rounded-0" src="resources/img/listing/listing-5.jpg" alt="Card image cap">
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
            <img class="card-img rounded-0" src="resources/img/listing/listing-10.jpg" alt="Card image cap">
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
            <img class="card-img rounded-0" src="resources/img/listing/listing-1.jpg" alt="Card image cap">
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
            <img class="card-img rounded-0" src="resources/img/listing/listing-7.jpg" alt="Card image cap">
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
            <img class="card-img rounded-0" src="resources/img/listing/listing-8.jpg" alt="Card image cap">
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
  <div class="divimage dzsparallaxer--target"  data-src="resources/img/background/bg-countdown.jpg"></div>

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
						<img class="mx-auto d-block" src="resources/img/works/works-1.png" alt="Image works">
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
						<img class="mx-auto d-block" src="resources/img/works/works-2.png" alt="Image works">
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
						<img class="mx-auto d-block" src="resources/img/works/works-3.png" alt="Image works">
					</div>
					<div class="card-body text-center pb-6">
						<h3 class="h4">
							<a class="mb-3 d-inline-block text-dark text-decoration-none" href="how-it-works.html">Explore Location</a>
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
						<img class="card-img-top rounded" src="resources/img/blog/blog-article-1.jpg" alt="Card image cap">
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
						<img class="card-img-top rounded" src="resources/img/blog/blog-article-2.jpg" alt="Card image cap">
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
						<img class="card-img-top rounded" src="resources/img/blog/blog-article-3.jpg" alt="Card image cap">
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

  <footer class="footer-dark" style="background-image: url(resources/img/background/bg-footer.jpg)">

    <div class="container">
      <div class="row">
        <div class="col-sm-7 col-xs-12">
          <a class="d-inline-block mb-6" href="index.html">
            <svg class="logo-svg" version="1.1" xmlns="http://www.w3.org/2000/svg" width="140" height="44">
              <path class="fill-primay" d="M0 44V0h139.813v44H0zM137.346 2.467H2.467v39.065h134.879V2.467z" />
              <path class="fill-primay"
                d="M120.927 22.389v11.095h-4.566V22.389a371.288 371.288 0 0 0-2.086-2.888 347.047 347.047 0 0 1-2.2-3.053 386.86 386.86 0 0 0-2.201-3.053c-.7-.959-1.395-1.922-2.086-2.888h5.617l5.255 7.287 5.222-7.287h5.649c.002 0-8.604 11.882-8.604 11.882zM98.034 33.484h-4.565V15.069h-6.372v-4.562h17.244v4.562h-6.306v18.415zm-21.908 0H71.56V15.069h-6.372v-4.562h17.244v4.562h-6.306v18.415zm-17.425-1.789c-.69.623-1.511 1.116-2.463 1.477-.953.361-1.987.542-3.104.542-1.007 0-1.982-.143-2.923-.427a10.814 10.814 0 0 1-2.661-1.214h.033a9.928 9.928 0 0 1-1.577-1.215 18.73 18.73 0 0 1-.953-.952l3.416-3.151c.153.197.399.432.739.706.339.274.728.537 1.166.788.44.253.902.467 1.38.64.481.175.941.262 1.379.262.372 0 .744-.044 1.117-.131.359-.082.703-.22 1.018-.41.305-.185.564-.437.755-.739.197-.306.296-.689.296-1.149 0-.175-.06-.366-.181-.574-.12-.208-.329-.432-.624-.673-.296-.241-.706-.498-1.232-.771a20.567 20.567 0 0 0-1.971-.87 25.42 25.42 0 0 1-2.562-1.132 8.896 8.896 0 0 1-2.053-1.428 5.903 5.903 0 0 1-1.347-1.871c-.317-.7-.476-1.51-.476-2.429 0-.94.175-1.822.526-2.642a6.21 6.21 0 0 1 1.494-2.133c.646-.602 1.423-1.072 2.332-1.412.908-.339 1.911-.509 3.006-.509.591 0 1.22.077 1.889.23.668.153 1.319.35 1.954.591a12.95 12.95 0 0 1 1.79.837c.558.317 1.023.64 1.396.968l-2.825 3.545a15.71 15.71 0 0 0-1.281-.788 10.316 10.316 0 0 0-1.281-.558 4.311 4.311 0 0 0-1.478-.263c-.919 0-1.637.181-2.151.542-.515.361-.772.881-.772 1.559 0 .307.093.586.279.837.186.252.438.482.756.689.348.225.717.417 1.1.574.416.176.854.34 1.314.492 1.314.504 2.42 1.013 3.318 1.526.898.514 1.62 1.062 2.168 1.642s.936 1.204 1.166 1.871c.23.668.345 1.395.345 2.183 0 .963-.197 1.871-.591 2.724a6.803 6.803 0 0 1-1.626 2.216zM34.839 10.507h4.532v22.977h-4.532V10.507zm-20.036 0h4.566v18.415h9.263v4.563H14.803V10.507z" />
            </svg>
          </a>
          <p class="text-white pt-1 pb-3">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor
            incididunt ut
            labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
            aliquip
            ex
            ea commodo consequat. Duis aute irure dolor</p>
          <ul class="list-unstyled text-white">
            <li class="mb-3">
              <i class="fa fa-phone mr-3" aria-hidden="true"></i>
              <a class="text-white" href="tel:[88] 657 524 332">[88] 657 524 332</a>
            </li>
            <li class="mb-3">
              <i class="fa fa-envelope mr-3" aria-hidden="true"></i>
              <a class="text-white" href="mailto:info@listy.com">info@listy.com</a>
            </li>
          </ul>
        </div>

        <div class="col-sm-3 col-xs-12">
          <div class="mb-4 mt-4 mt-md-0">
            <h2 class="h4 text-white">Links</h2>
          </div>
          <ul class="list-unstyled list-gray">
            <li class="mb-3">
              <a href="index.html">Home</a>
            </li>
            <li class="mb-3">
              <a href="sign-up.html">Create Account</a>
            </li>
            <li class="mb-3">
              <a href="login.html">Login</a>
            </li>
            <li class="mb-3">
              <a href="add-listings.html">Add Listing</a>
            </li>
            <li class="mb-3">
              <a href="edit-listings.html">Edit Listing</a>
            </li>
          </ul>
        </div>

        <div class="col-sm-2 col-xs-12">
          <div class="mb-4 mt-4 mt-md-0">
            <h2 class="h4 text-white">Company</h2>
          </div>
          <ul class="list-unstyled list-gray">
            <li class="mb-3">
              <a href="contact-us.html">Contact Us</a>
            </li>
            <li class="mb-3">
              <a href="terms-of-services.html">Terms and Conditions</a>
            </li>
            <li class="mb-3">
              <a href="how-it-works.html">How It Works</a>
            </li>
            <li class="mb-3">
              <a href="payment-process.html">Payment</a>
            </li>
            <li class="mb-3">
              <a href="pricing-table.html">Pricing</a>
            </li>
          </ul>
        </div>

      </div>

      <hr>

      <div class="row">

        <div class="col-sm-7 col-xs-12 align-self-center order-3 order-md-0">
          <p class="copy-right mb-0 pb-4 pb-md-0">
            Copyright &copy; 2019. All Rights Reserved by
            <a href="http://www.iamabdus.com/" target="_blank"> Abdus</a>
          </p>
        </div>

        <div class="col-sm-5 col-xs-12 d-flex align-items-center">
          <div class="ml-md-auto mx-auto mx-md-0 mt-3 mb-5 my-md-0">
            <a class="icon-sm icon-default icon-border hover-bg-primary rounded-circle ml-2" href="#facebook">
              <i class="fa fa-facebook" aria-hidden="true"></i>
            </a>
            <a class="icon-sm icon-default icon-border hover-bg-primary rounded-circle ml-2" href="#twitter">
              <i class="fa fa-twitter" aria-hidden="true"></i>
            </a>
            <a class="icon-sm icon-default icon-border hover-bg-primary rounded-circle ml-2" href="#pinterest">
              <i class="fa fa-pinterest-p" aria-hidden="true"></i>
            </a>
            <a class="icon-sm icon-default icon-border hover-bg-primary rounded-circle ml-2" href="#linkedin">
              <i class="fa fa-linkedin" aria-hidden="true"></i>
            </a>
          </div>
        </div>
      </div>

    </div>

  </footer>


    <!-- JAVASCRIPTS -->

    <script src="resources/plugins/jquery/jquery-3.4.1.min.js"></script>
    <script src="resources/plugins/bootstrap/js/bootstrap.bundle.js"></script>
    <script src="resources/plugins/menuzord/js/menuzord.js"></script>
    <script src="resources/plugins/selectric/jquery.selectric.min.js"></script>
    <script src="resources/plugins/dzsparallaxer/dzsparallaxer.js"></script>
    <script src="resources/plugins/dzsparallaxer/dzsparallaxer.js"></script>
    <script src="resources/plugins/isotope/isotope.pkgd.min.js"></script>
    <script src="resources/plugins/daterangepicker/moment.min.js"></script>
    <script src="resources/plugins/daterangepicker/daterangepicker.js"></script>
    <script src="resources/plugins/revolution/js/jquery.themepunch.tools.min.js"></script>
    <script src="resources/plugins/revolution/js/jquery.themepunch.revolution.min.js"></script>
    <script src="resources/plugins/owl-carousel/owl.carousel.min.js"></script>
    <script src="resources/plugins/rateyo/jquery.rateyo.min.js"></script>
    <script src="resources/plugins/fancybox/jquery.fancybox.min.js"></script>
    <script src="resources/plugins/imagesloaded/imagesloaded.pkgd.min.js"></script>


    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDU79W1lu5f6PIiuMqNfT1C6M0e_lq1ECY"></script>
    <script src="resources/plugins/map/js/markerclusterer.js"></script>
    <script src="resources/plugins/map/js/rich-marker.js"></script>
    <script src="resources/plugins/map/js/infobox_packed.js"></script>
    <script src="resources/js/map.js"></script>

    <script src="resources/js/listty.js"></script>
  </body>
</html>

