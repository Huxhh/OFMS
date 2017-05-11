app.controller('user', ['$scope', function ($scope) {
	$scope.userAbout = [];
}]);
app.directive('userDirective', ['request', function (request) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			request.get('url', function (res) {
				if (res.status == 0) {
					scope.userAbout = res.message;
				} else {
					request.pop_up(res.message);
				}
			})
		}
	}
}])