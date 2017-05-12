app.controller('user', ['$scope', function ($scope) {
	$scope.userAbout = [];
}]);
app.directive('userDirective', ['request', function (request) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			scope.$emit('classify_change', false);
			request.post('/user/showallpaidfilm', {
				uid: '1'
			}, function (res) {
				if (res.code == 0) {
					scope.userAbout = res.body;
					console.log(scope.userAbout)
				} else {
					request.pop_up(res.msg);
				}
			})
		}
	}
}])