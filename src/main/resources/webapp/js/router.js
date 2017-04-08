app.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.when("", '/home');
	$stateProvider
		.state('home', {
			url: '/home',
			views: {
				'index': {templateUrl: 'page/home.html'}
			}
		})
		.state('overview', {
			url: '/overview',
			views: {
				'index': {templateUrl: 'page/overview.html'}
			}
		})
		.state('search', {
			url: '/search',
			views: {
				'index': {templateUrl: 'page/search.html'}
			}
		})
		.state('user', {
			url: '/user',
			views: {
				'index': {templateUrl: 'page/user.html'}
			}
		})
})