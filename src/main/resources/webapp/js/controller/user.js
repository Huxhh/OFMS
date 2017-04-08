app.controller('user', ['$scope', function ($scope) {
	$scope.userAbout = [];
}]);
app.directive('userDirective', ['request', function (request) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			
		}
	}
}])