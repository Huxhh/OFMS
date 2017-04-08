app.controller('home', ['$scope', function ($scope) {
	$scope.hotList = [];
	$scope.recommondList = [];
	$scope.newList = [];
}]);
app.directive('homeDirective', ['request', function (request) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			
		}
	}
}])