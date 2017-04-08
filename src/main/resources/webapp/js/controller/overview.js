app.controller('overview', ['$scope', function ($scope) {
	$scope.filmOverview = [];
}]);
app.directive('overviewDirective', ['request', function (request) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			
		}
	}
}])