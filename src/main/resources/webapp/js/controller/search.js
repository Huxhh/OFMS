app.controller('search', ['$scope', function ($scope) {
	$scope.filmList = [];
}]);
app.directive('searchDirective', ['request', function (request) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			
		}
	}
}])