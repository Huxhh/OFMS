app.controller('home', ['$scope', function ($scope) {
	$scope.hotList = [];
	$scope.recommondList = [];
	$scope.newList = [];
}]);
app.directive('homeDirective', ['request', function (request) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			request.get('http://home/url', function (res) {
				if (res.status == 0) {
					scope.hotList = res.message;
				} else {
					request.pop_up(res.message);
				}
			})
			request.get('url', function (res) {
				if (res.status == 0) {
					scope.recommondList = res.message;
				} else {
					request.pop_up(res.message);
				}
			})
			request.get('url', function (res) {
				if (res.status == 0) {
					scope.newList = res.message;
				} else {
					request.pop_up(res.message);
				}
			})
		}
	}
}])