app.controller('search', ['$scope', function ($scope) {
	$scope.filmList = [];
}]);
app.directive('searchDirective', ['request', function (request) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			request.get('url', function (res) {
				if (res.status == 0) {
					scope.filmList = res.message;
				} else {
					request.pop_up(res.message);
				}
			})
		}
	}
}])