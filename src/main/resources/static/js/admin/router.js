app.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.when("", '/film');
    $urlRouterProvider.when("/", '/film');
	$stateProvider
		// 总体indedx的路由关系
		.state('film', {
			url: '/film',
			views: {
				'index': {templateUrl: 'page/film.html'}
			}
		})
})