app.controller('indexController', ['$scope', function ($scope) {
	$scope.addFilmOverflowFlag = false;
	$scope.$on('addFilmOverflow', function (event, data) {
		$scope.addFilmOverflowFlag = data;
	})
	$scope.ifExpand = false;
	$scope.hideTxt = false;
	$scope.expand = function (ifExpand) {
		if (ifExpand) {
			$scope.ifExpand = false;
			$scope.hideTxt = false;
		} else {
			$scope.ifExpand = true;
			$scope.hideTxt = true;
		}
	}
}]);