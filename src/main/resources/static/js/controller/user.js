app.controller('user', ['$scope', function ($scope) {
	$scope.userAbout = [];
	$scope.rechargeNum = null;
	$scope.count = null;
}]);
app.directive('userDirective', [function () {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			scope.$emit('classify_change', false);
		}
	}
}]);
app.directive('userFilmDirective', ['request', function (request) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			request.get('/usr/film', function (res) {
				if (res.code == 0) {
					scope.userAbout = res.body;
				} else {
					request.pop_up(res.msg);
				}
			})
		}
	}
}]);
app.directive('userCountDirective', ['request', function ($scope) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			// request.get()
			scope.recharge = function () {

			}
		}
	}
}]);