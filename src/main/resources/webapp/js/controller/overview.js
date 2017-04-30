app.controller('overview', ['$scope', function ($scope) {
	$scope.filmOverview = [];
}]);
app.directive('overviewDirective', ['request', function (request) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			request.get('url', function (res) {
				if (res.status == 0) {
					scope.filmOverview = res.message;
				} else {
					request.pop_up(res.message);
				}
			})
		}
	}
}])