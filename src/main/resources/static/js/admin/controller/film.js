app.controller('film', ['$scope', function ($scope) {
	$scope.filmList = [];
}]);
app.directive('filmDirective', ['request', function (request) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			
		}
	}
}])