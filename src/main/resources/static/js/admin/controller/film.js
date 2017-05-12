app.controller('film', ['$scope', function ($scope) {
	$scope.filmList = [];
	// 页码总计
    $scope.filmPage = 0;
    // 页码列表
    $scope.page_num = [];
    // 当前页数
    $scope.isActive = 0;
}]);
app.directive('filmDirective', ['request', function (request) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			console.log('adfasf')
			var max_page = 10, final_page;
			scope.admin_film = function(go_page, reset) {
				scope.isActive = go_page;
                page = go_page || 0;
				request.get('/admin/', function (res) {
					if (res.status == 1) {
						scope.filmList = res.movie.content;
						scope.filmPage = res.movie.totalPages;
						final_page = Math.min(scope.filmPage, max_page);
                        if (reset) {
                        	scope.page_num = [];
                            for (var i = 0; i < final_page; ++i) {
                                scope.page_num.push(i);
                            }
                        }
					} else {
						request.pop_up(res.message);
					}
				})
			}
			scope.prev = function () {
                if ((0 != scope.page_num[0]) && (scope.isActive == scope.page_num[0])) {
                    for (var i = 0; i < scope.page_num.length; ++i) {
                        scope.page_num[i]--;
                    }
                }
                if (scope.isActive != 0) {
                    scope.isActive--;
                }
                scope.admin_film(scope.isActive);
            }
            scope.next = function () {
                if ((scope.page != (scope.page_num[final_page - 1]) + 1) &&
                 	(scope.isActive == scope.page_num[final_page - 1])) {
                    for (var i = 0; i < scope.page_num.length; ++i) {
                        scope.page_num[i]++;
                    }
                }
                if (scope.isActive != (scope.page - 1)) {
                    scope.isActive++;
                }
                scope.admin_film(scope.isActive);
            }
            scope.admin_film(0, true);
		}
	}
}])