app.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.when("", '/home');
    $urlRouterProvider.when("/", '/home');
    $urlRouterProvider.when("/user", '/user/film');
	$stateProvider
		// 总体indedx的路由关系
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
		// 用户中心的路由关系
		.state('user.myfilm', {
			url: '/film',
			views: {
				'user': {templateUrl: 'user/film.html'}
			}
		})
		.state('user.mycount', {
			url: '/count',
			views: {
				'user': {templateUrl: 'user/count.html'}
			}
		})
		.state('user.myinfo', {
			url: '/info',
			views: {
				'user': {templateUrl: 'user/info.html'}
			}
		})
})